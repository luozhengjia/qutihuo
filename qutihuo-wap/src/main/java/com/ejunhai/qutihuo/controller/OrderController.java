package com.ejunhai.qutihuo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.utils.DateUtil;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.enums.OrderSource;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.utils.SessionManager;

/**
 * order Controller
 * 
 * @author parcel
 * @history 2014-05-04 parcel 新建
 */
@Controller
@RequestMapping("")
public class OrderController extends BaseController {

	@Resource
	private CouponService couponService;

	@Resource
	private CouponSchemaService couponSchemaService;

	@Resource
	private OrderMainService orderMainService;

	@RequestMapping("/toSubscribe")
	public String toSubscribe(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = SessionManager.get(request);
		if (CouponState.used.getValue().equals(coupon.getState())) {
			return "redirect:orderInfo.jhtml";
		}

		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponSchema);

		// 提前预订时间
		Date useStartDate = DateUtil.addDate(new Date(), couponSchema.getFrontDayNum());
		useStartDate = couponSchema.getUseStartdate().after(useStartDate) ? couponSchema.getUseStartdate()
				: useStartDate;
		Date useEndDate = DateUtil.addDate(coupon.getUseEnddate(), couponSchema.getFrontDayNum());
		modelMap.put("startDate", DateUtil.format(useStartDate, "yyyy-MM-dd"));
		modelMap.put("endDate", DateUtil.format(useEndDate, "yyyy-MM-dd"));
		return "subscribe";
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(OrderMain orderMain, ModelMap modelMap, HttpServletRequest request) throws Exception {
		Coupon coupon = SessionManager.get(request);

		// 验证礼品卡的可用性
		coupon = couponService.getCouponByNo(coupon.getCouponNumber());
		if (!CouponState.unused.getValue().equals(coupon.getState())) {
			logger.info("优惠券状态不对");
			return "redirect:toSubscribe.jhtml";
		}

		JunhaiAssert.notNull(orderMain.getConsignee(), "consignee is null");
		JunhaiAssert.notNull(orderMain.getProvinceCode(), "provinceCode is null");
		JunhaiAssert.notNull(orderMain.getCityCode(), "cityCode is null");
		JunhaiAssert.notNull(orderMain.getAreaCode(), "areaCode is null");
		JunhaiAssert.notNull(orderMain.getOrderDate(), "orderDate is null");
		JunhaiAssert.notNull(orderMain.getDetailAddress(), "detailAddress is null");
		JunhaiAssert.notNull(orderMain.getTelephone(), "telephone is null");

		// 提前预订时间
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		Date useStartDate = DateUtil.addDate(new Date(), couponSchema.getFrontDayNum());
		useStartDate = couponSchema.getUseStartdate().after(useStartDate) ? couponSchema.getUseStartdate()
				: useStartDate;
		Date useEndDate = DateUtil.addDate(coupon.getUseEnddate(), couponSchema.getFrontDayNum());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date orderDate = format.parse(orderMain.getOrderDate());

		// 预定日期无效
		if (orderDate.getTime() < DateUtil.getZeroTime(useStartDate) || orderDate.getTime() > useEndDate.getTime()) {
			logger.info("卡券" + coupon.getCouponNumber() + "预定日期无效，预定日期：" + orderMain.getOrderDate());
			return "redirect:toSubscribe.jhtml";
		}

		orderMain.setSource(OrderSource.PHONE.getValue());
		orderMain = orderMainService.createOrderMain(coupon, orderMain);

		// 更新coupon状态
		coupon = couponService.getCouponByNo(coupon.getCouponNumber());
		SessionManager.put(request, coupon);
		modelMap.put("orderMain", orderMain);
		modelMap.put("createOrderSuccess", true);
		return "profile";
	}

	@RequestMapping("/orderInfo")
	public String orderInfo(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = SessionManager.get(request);

		coupon = this.couponService.getCouponByNo(coupon.getCouponNumber());
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		modelMap.put("coupon", coupon);
		modelMap.put("couponScheme", couponSchema);

		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(coupon.getOrderNumber());
		modelMap.put("orderMain", orderMain);
		return "orderInfo";
	}
}
