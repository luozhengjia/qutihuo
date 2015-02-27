package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.ejunhai.qutihuo.order.dto.LogisticsCompanyDto;
import com.ejunhai.qutihuo.order.dto.OrderMainDto;
import com.ejunhai.qutihuo.order.model.LogisticsCompany;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.LogisticsCompanyService;
import com.ejunhai.qutihuo.order.service.OrderMainService;

/**
 * 
 * 订单管理
 * 
 * @date 2015-1-26 下午3:49:36
 * @version 0.1.0 
 */
@Controller
@RequestMapping("orderMain")
public class OrderMainController extends BaseController {

	@Resource
	private OrderMainService orderMainService;

	@Resource
	private LogisticsCompanyService logisticsCompanyService;

	@RequestMapping("/orderMainList")
	public String orderMainList(HttpServletRequest request, OrderMainDto orderMainDto, ModelMap modelMap) {
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

	@RequestMapping("/orderMainAdd")
	public String orderMainAdd(HttpServletRequest request, OrderMain orderMain, ModelMap modelMap) {
		return "order/orderMainAdd";
	}
	
	@RequestMapping("/orderMainDetail")
	public String orderMainDetail(HttpServletRequest request, OrderMain orderMain, ModelMap modelMap) {
		if (orderMain.getId() != null) {
			orderMain = orderMainService.read(orderMain.getId());
		}

		modelMap.put("orderMain", orderMain);
		return "order/orderMainEdit";
	}

	/**
	 * 获取订单详情
	 * @param request
	 * @param orderMain
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/getOrderMain")
	@ResponseBody
	public String getOrderMain(HttpServletRequest request, OrderMain orderMain, ModelMap modelMap) {
		if (orderMain.getId() != null) {
			orderMain = orderMainService.read(orderMain.getId());
		}

		modelMap.put("orderMain", orderMain);
		return jsonSuccess(modelMap);
	}

	/**
	 * 打印快递单
	 * @param request
	 * @param orderMain
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/printOrder")
	@ResponseBody
	public String printOrder(HttpServletRequest request, OrderMain orderMain, String lcCode, ModelMap modelMap) {
		orderMain = orderMainService.read(orderMain.getId());
		modelMap.put("orderMain", orderMain);
		LogisticsCompanyDto logisticsCompanyDto = new LogisticsCompanyDto();
		logisticsCompanyDto.setLcCode(lcCode);
		LogisticsCompany logisticsCompany = logisticsCompanyService.findLogisticsCompany(logisticsCompanyDto);
		//替换需要打印快递单信息
		
		modelMap.put("lc", logisticsCompany);
		return jsonSuccess(modelMap);
	}

	@RequestMapping("/orderMainPrint")
	public String orderMainPrint(HttpServletRequest request, OrderMain orderMain, ModelMap modelMap) {
		if (orderMain.getId() != null) {
			orderMain = orderMainService.read(orderMain.getId());
		}
		LogisticsCompanyDto logisticsCompanyDto = new LogisticsCompanyDto();
		logisticsCompanyDto.setOffset(0);
		logisticsCompanyDto.setPageSize(100);
		List<LogisticsCompany> logisticsCompanyList = logisticsCompanyService
				.queryLogisticsCompanyList(logisticsCompanyDto);
		modelMap.put("lcList", logisticsCompanyList);
		modelMap.put("orderMain", orderMain);
		return "order/orderMainPrint";
	}

	@RequestMapping("/saveOrderMain")
	@ResponseBody
	public String saveOrderMain(HttpServletRequest request, OrderMain orderMain) {
		//JunhaiAssert.notBlank(orderMain.getActionName(), "操作名不能为空");
		if (orderMain.getId() != null && orderMain.getId() > 0) {
			orderMainService.update(orderMain);
		} else {
			//订单号
			orderMain.setOrderMainNo("11");
			//优惠券带出商家信息
			
			
			orderMainService.insert(orderMain);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteOrderMain")
	@ResponseBody
	public String deleteOrderMain(HttpServletRequest request, OrderMain orderMain) {
		JunhaiAssert.notNull(orderMain.getId(), "id不能为空");
		orderMainService.delete(orderMain.getId());
		return jsonSuccess();
	}

}
