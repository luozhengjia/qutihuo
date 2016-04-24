package com.ejunhai.qutihuo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.order.dto.OrderLogDto;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderLogService;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.order.service.OrderReplService;
import com.ejunhai.qutihuo.utils.SessionManager;

/**
 * after sale Controller
 * 
 * @author parcel
 * @history 2014-05-04 parcel 新建
 */
@Controller
@RequestMapping("")
public class LogisticsController extends BaseController {

	@Resource
	private OrderMainService orderMainService;

	@Resource
	private OrderReplService orderReplService;

	@Resource
	private OrderLogService orderLogService;

	@RequestMapping("/toLogistics")
	public String toLogistics(ModelMap modelMap, HttpServletRequest request) {
		Coupon coupon = SessionManager.get(request);
		if (coupon == null) {
			return "index";
		}

		OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(coupon.getOrderNumber());
		modelMap.addAttribute("orderMain", orderMain);

		// 查询订单处理日志
		OrderLogDto orderLogDto = new OrderLogDto();
		orderLogDto.setOrderNo(orderMain.getOrderMainNo());
		orderLogDto.setOffset(0);
		orderLogDto.setPageSize(Integer.MAX_VALUE);
		modelMap.put("orderLogList", orderLogService.queryOrderLogList(orderLogDto));

		// 查询补货单列表
		OrderReplDto orderReplDto = new OrderReplDto();
		orderReplDto.setOrderMainNo(orderMain.getOrderMainNo());
		orderReplDto.setOffset(0);
		orderReplDto.setPageSize(Integer.MAX_VALUE);
		List<OrderRepl> orderReplList = orderReplService.queryOrderReplList(orderReplDto);
		if (CollectionUtils.isNotEmpty(orderReplList)) {
			OrderRepl orderRepl = orderReplList.get(0);
			orderLogDto = new OrderLogDto();
			orderLogDto.setOrderNo(orderRepl.getOrderReplNo());
			orderLogDto.setOffset(0);
			orderLogDto.setPageSize(Integer.MAX_VALUE);
			modelMap.addAttribute("orderRepl", orderRepl);
			modelMap.put("replOrderLogList", orderLogService.queryOrderLogList(orderLogDto));
		}

		return "logistics";
	}
}
