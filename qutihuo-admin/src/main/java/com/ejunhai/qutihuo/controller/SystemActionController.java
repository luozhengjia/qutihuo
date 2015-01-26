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
import com.ejunhai.qutihuo.system.dto.SystemActionDto;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.service.SystemActionService;
import com.ejunhai.qutihuo.system.utils.SystemActionUtil;

@Controller
@RequestMapping("system")
public class SystemActionController extends BaseController {

	@Resource
	private SystemActionService systemActionService;

	@RequestMapping("/actionList")
	public String actionList(HttpServletRequest request, SystemActionDto systemActionDto, ModelMap modelMap) {
		Integer iCount = systemActionService.querySystemActionCount(systemActionDto);
		Pagination pagination = new Pagination(systemActionDto.getPageNo(), iCount);

		// 获取分页数据
		List<SystemAction> systemActionList = new ArrayList<SystemAction>();
		if (iCount > 0) {
			systemActionDto.setOffset(pagination.getOffset());
			systemActionDto.setPageSize(pagination.getPageSize());
			systemActionList = systemActionService.querySystemActionList(systemActionDto);
		}

		modelMap.put("pagination", pagination);
		modelMap.put("systemActionDto", systemActionDto);
		modelMap.put("systemActionList", systemActionList);
		return "system/actionList";
	}

	@RequestMapping("/actionDetail")
	public String actionDetail(HttpServletRequest request, SystemAction systemAction, ModelMap modelMap) {
		if (systemAction.getId() != null) {
			systemAction = systemActionService.read(systemAction.getId());
		}

		modelMap.put("systemAction", systemAction);
		return "system/actionEdit";
	}

	@RequestMapping("/getParentActionList")
	@ResponseBody
	public String getParentActionList(Integer actionType, ModelMap modelMap) throws Exception {
		// 获取父节点列表
		List<SystemAction> parentActionList = systemActionService.getAllSystemActionList();
		parentActionList = SystemActionUtil.getSystemActionListByNodeType(parentActionList, actionType);
		modelMap.put("parentActionList", parentActionList);
		return jsonSuccess(parseJsonTemplate("system/parentActionList.json", modelMap));
	}

	@RequestMapping("/saveAction")
	@ResponseBody
	public String saveAction(HttpServletRequest request, SystemAction systemAction) {
		JunhaiAssert.notBlank(systemAction.getActionName(), "操作名不能为空");
		if (systemAction.getId() != null && systemAction.getId() > 0) {
			systemActionService.update(systemAction);
		} else {
			systemActionService.insert(systemAction);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteAction")
	@ResponseBody
	public String deleteAction(HttpServletRequest request, SystemAction systemAction) {
		JunhaiAssert.notNull(systemAction.getId(), "id不能为空");
		systemActionService.delete(systemAction.getId());
		return jsonSuccess();
	}

}
