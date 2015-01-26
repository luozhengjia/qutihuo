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
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderReplService;

/**
 * 
 * 补货订单管理
 * 
 * @date 2015-1-26 下午3:49:36
 * @version 0.1.0 
 */
@Controller
@RequestMapping("orderRepl")
public class OrderReplController extends BaseController {

	@Resource
	private OrderReplService orderReplService;

	@RequestMapping("/orderReplList")
	public String orderReplList(HttpServletRequest request, OrderReplDto orderReplDto, ModelMap modelMap) {
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

	@RequestMapping("/orderReplDetail")
	public String orderReplDetail(HttpServletRequest request, OrderRepl orderRepl, ModelMap modelMap) {
		if (orderRepl.getId() != null) {
			orderRepl = orderReplService.read(orderRepl.getId());
		}

		modelMap.put("orderRepl", orderRepl);
		return "order/orderReplEdit";
	}

	@RequestMapping("/saveOrderRepl")
	@ResponseBody
	public String saveOrderRepl(HttpServletRequest request, OrderRepl orderRepl) {
		//JunhaiAssert.notBlank(orderRepl.getActionName(), "操作名不能为空");
		if (orderRepl.getId() != null && orderRepl.getId() > 0) {
			orderReplService.update(orderRepl);
		} else {
			orderReplService.insert(orderRepl);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteOrderRepl")
	@ResponseBody
	public String deleteOrderRepl(HttpServletRequest request, OrderRepl orderRepl) {
		JunhaiAssert.notNull(orderRepl.getId(), "id不能为空");
		orderReplService.delete(orderRepl.getId());
		return jsonSuccess();
	}

}
