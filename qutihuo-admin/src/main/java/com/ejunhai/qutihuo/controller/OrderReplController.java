package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.order.service.OrderReplService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderRepl")
public class OrderReplController extends BaseController {

	@Resource
	private OrderReplService orderReplService;

	@Resource
	private OrderMainService orderMainService;

	@Resource
	private CouponSchemaService couponSchemaService;

	@Resource
	private CouponService couponService;

	@RequestMapping("/orderReplList")
	public String orderReplList(HttpServletRequest request, OrderReplDto orderReplDto, ModelMap modelMap) {
		orderReplDto.setMerchantId(SessionManager.get(request).getMerchantId());
		Integer iCount = orderReplService.queryOrderReplCount(orderReplDto);
		Pagination pagination = new Pagination(orderReplDto.getPageNo(), iCount);

		// 获取分页数据
		List<OrderRepl> orderReplList = new ArrayList<OrderRepl>();
		if (iCount > 0) {
			orderReplDto.setOffset(pagination.getOffset());
			orderReplDto.setPageSize(pagination.getPageSize());
			orderReplList = orderReplService.queryOrderReplList(orderReplDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("orderReplDto", orderReplDto);
		modelMap.put("orderReplList", orderReplList);
		return "order/orderReplList";
	}

	@RequestMapping("/toAddOrderRepl")
	public String toAddOrderRepl(HttpServletRequest request, String orderMainNo, ModelMap modelMap) {
		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderMainNo);
		JunhaiAssert.notNull(orderMain, "订单号码无效");
		modelMap.addAttribute("orderMain", orderMain);
		Coupon coupon = couponService.getCouponByOrderNo(orderMain.getOrderMainNo());
		modelMap.addAttribute("coupon", coupon);
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
		modelMap.addAttribute("couponSchema", couponSchema);
		return "order/editOrderRepl";
	}

	@RequestMapping("/toEditOrderRepl")
	public String toEditOrderRepl(HttpServletRequest request, String orderReplNo, ModelMap modelMap) {
		OrderRepl orderRepl = orderReplService.getOrderReplByOrderReplNo(orderReplNo);
		JunhaiAssert.notNull(orderRepl, "补货单号无效");

		// 获取券详情信息
		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderRepl.getOrderMainNo());
		Coupon coupon = couponService.getCouponByNo(orderMain.getCouponNumber());
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());

		modelMap.put("orderRepl", orderRepl);
		modelMap.put("orderMain", orderMain);
		modelMap.put("couponSchema", couponSchema);
		return "order/editOrderRepl";
	}

	@RequestMapping("/addOrderRepl")
	@ResponseBody
	public String addOrderRepl(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderRepl.getOrderMainNo());
		this.orderReplService.createOrderRepl(orderMain, orderRepl);
		return jsonSuccess();
	}

	@RequestMapping("/changeConsigneeInfo")
	@ResponseBody
	public String changeConsigneeInfo(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
		JunhaiAssert.notNull(orderRepl.getDetailAddress(), "收货人详细地址不能为空");
		JunhaiAssert.notNull(orderRepl.getAreaCode(), "收货人省市区不能为空");
		orderReplService.changeConsigneeInfo(orderRepl);
		return jsonSuccess();
	}

	@RequestMapping("/deliver")
	@ResponseBody
	public String deliverOrderMain(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
		JunhaiAssert.notNull(orderRepl.getLogisticsCompany(), "物流公司不能为空");
		JunhaiAssert.notNull(orderRepl.getExpressOrderNo(), "快递单号不能为空");

		orderReplService.deliverOrderRepl(orderRepl);
		return jsonSuccess();
	}
}
