package com.ejunhai.qutihuo.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.coupon.enums.Confusion;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.ejunhai.qutihuo.utils.LoginUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

/**
 * Login Controller
 * 
 * @author parcel
 * @history 2014-04-29 parcel 新建
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponSchemaService couponSchemaService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, ModelMap modelMap) throws Exception {

		// 从cookie中获取couponNumber
		modelMap.put("couponNumber", FrontUtil.getCookieValue(request, FrontUtil.COOKIE_COUPON_NUMBER));

		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String couponNumber, String couponPassword, String validateCode, HttpServletRequest request,
			HttpServletResponse response) {
		JunhaiAssert.notBlank(couponNumber, "优惠券号码不能为空");
		JunhaiAssert.notBlank(couponPassword, "优惠券密码不能为空");
		JunhaiAssert.notBlank(validateCode, "验证码不能为空");

		// 验证验证码
		String sValidateCode = (String) request.getSession().getAttribute(FrontUtil.LOGIN_VALIDATE_IMAGE);
		JunhaiAssert.isTrue(validateCode.equals(sValidateCode), "验证码无效");

		// 无效礼品券处理
		Coupon coupon = couponService.getCouponByNo(couponNumber);
		boolean isValid = coupon == null || CouponState.discard.getValue().equals(coupon.getState());
		JunhaiAssert.isFalse(isValid, "优惠券号码无效");

		// 由于客户端过来时使用md5加密，要根据服务端是否扰乱来判断礼品卡的有效性
		String realCouponPassword = coupon.getCouponPassword();
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		if (Confusion.no.getValue().equals(couponSchema.getHasConfusion())) {
			realCouponPassword = DigestUtils.md5Hex(realCouponPassword);
		}

		// 验证礼品券和密码
		JunhaiAssert.isTrue(couponPassword.equalsIgnoreCase(realCouponPassword), "优惠券密码无效");

		// 礼品券验证通过，保存至session中
		SessionManager.put(request, coupon);
		request.getSession().setAttribute(LoginUtil.LOGIN_USER, coupon);
		Cookie cookie = new Cookie(FrontUtil.COOKIE_COUPON_NUMBER, couponNumber);
		cookie.setMaxAge(60 * 60 * 24 * 120);
		cookie.setPath("/");
		response.addCookie(cookie);

		return jsonSuccess();
	}

	@RequestMapping("/dispatchCenter")
	public String dispatchCenter(ModelMap modelMap, HttpServletRequest request) {

		// 如果礼品券已使用则跳转到用户主页面
		Coupon coupon = SessionManager.get(request);
		modelMap.put("coupon", coupon);
		if (CouponState.used.getValue().equals(coupon.getState())) {
			return "profile";
		}

		// 礼品券不可用状态跳转至notice页面
		if (!CouponState.unused.getValue().equals(coupon.getState()) || coupon.getUseEnddate().before(new Date())) {
			modelMap.put("curTime", new Date());
			return "notice";
		}

		// 礼品券未使用则跳转到下单预约页面
		return "redirect:toSubscribe.jhtml";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest req) {
		SessionManager.clear(req);
		return "redirect:index.jhtml";
	}
}
