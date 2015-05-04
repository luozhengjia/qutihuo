package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejunhai.qutihuo.aftersale.dto.AfterSaleRequDto;
import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;
import com.ejunhai.qutihuo.aftersale.service.AfterSaleRequService;
import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.utils.CouponUtil;
import com.ejunhai.qutihuo.order.utils.OrderUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderMain")
public class AfterSaleRequController extends BaseController {

	@Resource
	private AfterSaleRequService afterSaleRequService;

	@Resource
	private CouponSchemaService couponSchemaService;

	@RequestMapping("/afterSaleRequList")
	public String afterSaleRequList(HttpServletRequest request, AfterSaleRequDto afterSaleRequDto, ModelMap modelMap) {
		afterSaleRequDto.setMerchantId(SessionManager.get(request).getMerchantId());
		Integer iCount = afterSaleRequService.queryAfterSaleRequCount(afterSaleRequDto);
		Pagination pagination = new Pagination(afterSaleRequDto.getPageNo(), iCount);

		// 获取分页数据
		List<AfterSaleRequ> afterSaleRequList = new ArrayList<AfterSaleRequ>();
		if (iCount > 0) {
			afterSaleRequDto.setOffset(pagination.getOffset());
			afterSaleRequDto.setPageSize(pagination.getPageSize());
			afterSaleRequList = afterSaleRequService.queryAfterSaleRequList(afterSaleRequDto);
		}

		// 获取优惠券模板映射
//		List<String> orderNoList = OrderUtil.getOrderNoList(afterSaleRequList);
//		List<CouponSchema> couponSchemaList = couponSchemaService.getCouponSchemaListByOrderNos(orderNoList);
//		Map<String, CouponSchema> couponSchemaMap = CouponUtil.getCouponSchemaMap(couponSchemaList);
//		modelMap.put("couponSchemaMap", couponSchemaMap);

		modelMap.put("pagination", pagination);
		modelMap.put("afterSaleRequDto", afterSaleRequDto);
		modelMap.put("afterSaleRequList", afterSaleRequList);
		return "order/afterSaleRequList";
	}
}
