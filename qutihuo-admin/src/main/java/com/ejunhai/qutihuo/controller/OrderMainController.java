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
import com.ejunhai.qutihuo.order.dto.OrderMainDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderMain")
public class OrderMainController extends BaseController {

	@Resource
	private OrderMainService orderMainService;

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

}
