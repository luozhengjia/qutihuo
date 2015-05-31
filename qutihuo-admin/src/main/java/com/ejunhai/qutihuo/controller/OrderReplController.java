package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderReplService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderRepl")
public class OrderReplController extends BaseController {

	@Resource
	private OrderReplService orderReplService;

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
}
