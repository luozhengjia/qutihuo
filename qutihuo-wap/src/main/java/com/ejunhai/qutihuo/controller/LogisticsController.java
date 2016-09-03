package com.ejunhai.qutihuo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.utils.HttpClientHelper;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.order.dto.OrderLogDto;
import com.ejunhai.qutihuo.order.enums.LogisticsCompany;
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

	private static String LogisticsServiceUrl = "http://api.ickd.cn/?type=json&encode=utf8&ord=desc&id=F88275FED9B2AFD04ECF53BD4EEFB3F9&callback=?";

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
		OrderRepl orderRepl = orderReplService.getOrderReplByOrderMainNo(orderMain.getOrderMainNo());
		if (orderRepl != null) {
			orderLogDto = new OrderLogDto();
			orderLogDto.setOrderNo(orderRepl.getOrderReplNo());
			orderLogDto.setOffset(0);
			orderLogDto.setPageSize(Integer.MAX_VALUE);
			modelMap.addAttribute("orderRepl", orderRepl);
			modelMap.put("replOrderLogList", orderLogService.queryOrderLogList(orderLogDto));
		}
		return "logistics";
	}

	@RequestMapping(value = "/queryLogistics", method = RequestMethod.GET)
	@ResponseBody
	public String queryLogistics(Integer companyCode, String expressNo) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("com", LogisticsCompany.get(companyCode).getQcode());
		parameters.put("nu", expressNo);
		String postUrl = HttpClientHelper.requestBodyString(LogisticsServiceUrl, parameters);
		String result = postUrl.substring(5, postUrl.length() - 2);
		logger.debug(result);
		
		return jsonSuccess(result);
	}
}
