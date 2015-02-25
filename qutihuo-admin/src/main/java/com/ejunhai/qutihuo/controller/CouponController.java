package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.dto.CouponSchemaDto;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController {

	@Resource
	private CouponSchemaService couponSchemaService;

	@Resource
	private CouponService couponService;

	@RequestMapping("/couponSchemaList")
	public String couponSchemaList(HttpServletRequest request, CouponSchemaDto couponSchemaDto, ModelMap modelMap) {
		couponSchemaDto.setMerchantId(SessionManager.get(request).getMerchantId());
		Integer iCount = couponSchemaService.queryCouponSchemaCount(couponSchemaDto);
		Pagination pagination = new Pagination(couponSchemaDto.getPageNo(), iCount);

		// 获取分页数据
		List<CouponSchema> couponSchemaList = new ArrayList<CouponSchema>();
		if (iCount > 0) {
			couponSchemaDto.setOffset(pagination.getOffset());
			couponSchemaDto.setPageSize(pagination.getPageSize());
			couponSchemaList = couponSchemaService.queryCouponSchemaList(couponSchemaDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("couponSchemaDto", couponSchemaDto);
		modelMap.put("couponSchemaList", couponSchemaList);
		return "coupon/couponSchemaList";
	}

	@RequestMapping("/toCouponSchema")
	public String toCouponSchema(HttpServletRequest request, CouponSchemaDto couponSchemaDto, ModelMap modelMap) {
		if (couponSchemaDto.getId() != null) {
			CouponSchema couponSchema = couponSchemaService.read(couponSchemaDto.getId());
			Integer merchantId = SessionManager.get(request).getMerchantId();
			JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");
			modelMap.put("couponSchema", couponSchema);
		}

		return "coupon/couponSchemaEdit";
	}

	@RequestMapping("/saveCouponSchema")
	@ResponseBody
	public String saveCouponSchema(HttpServletRequest request, CouponSchemaDto couponSchemaDto) {
		JunhaiAssert.notBlank(couponSchemaDto.getCouponName(), "礼品卡名称不能为空");
		JunhaiAssert.notBlank(couponSchemaDto.getIconUrl(), "礼品卡图标不能为空");
		JunhaiAssert.notNull(couponSchemaDto.getUseStartdate(), "礼品卡开始时间不能为空");
		JunhaiAssert.notNull(couponSchemaDto.getUseStartdate(), "礼品卡结束时间不能为空");
		JunhaiAssert.notNull(couponSchemaDto.getIssueAmount(), "礼品卡总发行量不能为空");

		if (couponSchemaDto.getId() != null) {
			CouponSchema couponSchema = couponSchemaService.read(couponSchemaDto.getId());
			Integer merchantId = SessionManager.get(request).getMerchantId();
			JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");
			couponSchemaService.update(couponSchemaDto);
		} else {
			couponSchemaService.insert(couponSchemaDto);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteCouponSchema")
	@ResponseBody
	public String deleteCouponSchema(HttpServletRequest request, Integer couponSchemaId) {
		JunhaiAssert.notNull(couponSchemaId, "id不能为空");
		CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
		Integer merchantId = SessionManager.get(request).getMerchantId();
		JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");
		couponSchemaService.delete(couponSchemaId);
		return jsonSuccess();
	}

	@RequestMapping("/generateCoupons")
	@ResponseBody
	public String generateCoupons(HttpServletRequest request, CouponDto couponDto, ModelMap modelMap) {

		return "coupon/couponList";
	}

	@RequestMapping("/exportCoupons")
	public String exportCoupons(HttpServletRequest request, CouponDto couponDto, ModelMap modelMap) {

		return "coupon/couponList";
	}

	@RequestMapping("/disturbCoupons")
	@ResponseBody
	public ModelAndView disturbCoupon(Integer couponSchemaId, ModelMap modelMap) throws Exception {
		return null;
	}

	@RequestMapping("/couponList")
	public String couponList(HttpServletRequest request, CouponDto couponDto, ModelMap modelMap) {
		couponDto.setMerchantId(SessionManager.get(request).getMerchantId());
		Integer iCount = couponService.queryCouponCount(couponDto);
		Pagination pagination = new Pagination(couponDto.getPageNo(), iCount);

		// 获取分页数据
		List<Coupon> couponList = new ArrayList<Coupon>();
		if (iCount > 0) {
			couponDto.setOffset(pagination.getOffset());
			couponDto.setPageSize(pagination.getPageSize());
			couponList = couponService.queryCouponList(couponDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("couponDto", couponDto);
		modelMap.put("couponList", couponList);
		return "coupon/couponList";
	}

}
