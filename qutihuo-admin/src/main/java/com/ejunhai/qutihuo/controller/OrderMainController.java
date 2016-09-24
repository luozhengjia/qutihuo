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
import com.ejunhai.qutihuo.order.dto.OrderMainDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.system.service.SystemAreaService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderMain")
public class OrderMainController extends BaseController {

	@Resource
	private OrderMainService orderMainService;

	@Resource
	private CouponSchemaService couponSchemaService;

	@Resource
	private CouponService couponService;

	@Resource
	private SystemAreaService systemAreaService;

	@RequestMapping("/orderMainList")
	public String orderMainList(HttpServletRequest request, OrderMainDto orderMainDto, ModelMap modelMap) {
		orderMainDto.setMerchantId(SessionManager.get(request).getMerchantId());
		Integer iCount = orderMainService.queryOrderMainCount(orderMainDto);
		Pagination pagination = new Pagination(orderMainDto.getPageNo(), iCount);

		// 获取分页数据
		List<OrderMain> orderMainList = new ArrayList<OrderMain>();
		if (iCount > 0) {
			orderMainDto.setOffset(pagination.getOffset());
			orderMainDto.setPageSize(pagination.getPageSize());
			orderMainList = orderMainService.queryOrderMainList(orderMainDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("orderMainDto", orderMainDto);
		modelMap.put("orderMainList", orderMainList);
		return "order/orderMainList";
	}

	@RequestMapping("/toOrderMain")
	public String toOrderMain(HttpServletRequest request, String orderMainNo, ModelMap modelMap) {

		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderMainNo);
		JunhaiAssert.notNull(orderMain, "订单号码无效");

		// 获取券详情信息
		Coupon coupon = couponService.getCouponByNo(orderMain.getCouponNumber());
		CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());

		modelMap.put("orderMain", orderMain);
		modelMap.put("couponSchema", couponSchema);
		return "order/editOrderMain";
	}

	@RequestMapping("/changeConsigneeInfo")
	@ResponseBody
	public String changeConsigneeInfo(OrderMain orderMain, ModelMap modelMap) throws Exception {
		JunhaiAssert.notNull(orderMain.getDetailAddress(), "收货人详细地址不能为空");
		JunhaiAssert.notNull(orderMain.getAreaCode(), "收货人省市区不能为空");
		orderMainService.changeConsigneeInfo(orderMain);
		return jsonSuccess();
	}

	@RequestMapping("/deliver")
	@ResponseBody
	public String deliverOrderMain(OrderMain orderMain, ModelMap modelMap) throws Exception {
		JunhaiAssert.notNull(orderMain.getLogisticsCompany(), "物流公司不能为空");
		JunhaiAssert.notNull(orderMain.getExpressOrderNo(), "快递单号不能为空");

		orderMainService.deliverOrderMain(orderMain);
		return jsonSuccess();
	}
}
