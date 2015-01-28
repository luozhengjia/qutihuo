package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dto.LogisticsCompanyDto;
import com.ejunhai.qutihuo.order.model.LogisticsCompany;
import com.ejunhai.qutihuo.order.service.LogisticsCompanyService;

/**
 * 
 * 快递公司管理
 * 
 * @date 2015-1-26 下午3:49:36
 * @version 0.1.0 
 */
@Controller
@RequestMapping("logisticsCompany")
public class LogisticsCompanyController extends BaseController {

	@Resource
	private LogisticsCompanyService logisticsCompanyService;

	@RequestMapping("/logisticsCompanyList")
	public String logisticsCompanyList(HttpServletRequest request, LogisticsCompanyDto logisticsCompanyDto,
			ModelMap modelMap) {
		Integer iCount = logisticsCompanyService.queryLogisticsCompanyCount(logisticsCompanyDto);
		Pagination pagination = new Pagination(logisticsCompanyDto.getPageNo(), iCount);

		// 获取分页数据
		List<LogisticsCompany> logisticsCompanyList = new ArrayList<LogisticsCompany>();
		if (iCount > 0) {
			logisticsCompanyDto.setOffset(pagination.getOffset());
			logisticsCompanyDto.setPageSize(pagination.getPageSize());
			logisticsCompanyList = logisticsCompanyService.queryLogisticsCompanyList(logisticsCompanyDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("logisticsCompanyDto", logisticsCompanyDto);
		modelMap.put("logisticsCompanyList", logisticsCompanyList);
		return "order/logisticsCompanyList";
	}

	@RequestMapping("/logisticsCompanyDetail")
	public String logisticsCompanyDetail(HttpServletRequest request, LogisticsCompany logisticsCompany,
			ModelMap modelMap) {
		if (logisticsCompany.getId() != null) {
			logisticsCompany = logisticsCompanyService.read(logisticsCompany.getId());
		}

		modelMap.put("logisticsCompany", logisticsCompany);
		return "order/logisticsCompanyEdit";
	}

	@RequestMapping("/saveLogisticsCompany")
	@ResponseBody
	public String saveLogisticsCompany(HttpServletRequest request, LogisticsCompany logisticsCompany) {
		//JunhaiAssert.notBlank(logisticsCompany.getActionName(), "操作名不能为空");
		
		if (logisticsCompany.getId() != null && logisticsCompany.getId() > 0) {
			logisticsCompanyService.update(logisticsCompany);
		} else {
			logisticsCompanyService.insert(logisticsCompany);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteLogisticsCompany")
	@ResponseBody
	public String deleteLogisticsCompany(HttpServletRequest request, LogisticsCompany logisticsCompany) {
		JunhaiAssert.notNull(logisticsCompany.getId(), "id不能为空");
		logisticsCompanyService.delete(logisticsCompany.getId());
		return jsonSuccess();
	}

}
