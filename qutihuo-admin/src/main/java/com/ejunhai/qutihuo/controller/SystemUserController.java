package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.system.dto.SystemRoleDto;
import com.ejunhai.qutihuo.system.dto.SystemUserDto;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.model.SystemPrivilage;
import com.ejunhai.qutihuo.system.model.SystemRole;
import com.ejunhai.qutihuo.system.model.SystemUser;
import com.ejunhai.qutihuo.system.service.SystemActionService;
import com.ejunhai.qutihuo.system.service.SystemPrivilageService;
import com.ejunhai.qutihuo.system.service.SystemRoleService;
import com.ejunhai.qutihuo.system.service.SystemUserService;
import com.ejunhai.qutihuo.system.utils.SystemActionUtil;
import com.ejunhai.qutihuo.system.utils.SystemPrivilageUtil;

@Controller
@RequestMapping("system")
public class SystemUserController extends BaseController {
	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemRoleService systemRoleService;

	@Resource
	private SystemActionService systemActionService;

	@Resource
	private SystemPrivilageService systemPrivilageService;

	@RequestMapping("/userList")
	public String userList(HttpServletRequest request, SystemUserDto systemUserDto, ModelMap modelMap) {
		Integer iCount = systemUserService.querySystemUserCount(systemUserDto);
		Pagination pagination = new Pagination(systemUserDto.getPageNo(), iCount);

		List<SystemUser> systemUserList = new ArrayList<SystemUser>();
		if (iCount > 0) {
			systemUserDto.setOffset(pagination.getOffset());
			systemUserDto.setPageSize(pagination.getPageSize());
			systemUserList = systemUserService.querySystemUserList(systemUserDto);

		}
		modelMap.put("systemUserList", systemUserList);
		modelMap.put("pagination", pagination);
		return "user/userList";
	}

	@RequestMapping("/roleList")
	public String roleList(HttpServletRequest request, SystemRoleDto systemRoleDto, ModelMap modelMap) {
		Integer iCount = systemRoleService.querySystemRoleCount(systemRoleDto);
		Pagination pagination = new Pagination(systemRoleDto.getPageNo(), iCount);

		List<SystemRole> systemRoleList = new ArrayList<SystemRole>();
		if (iCount > 0) {
			systemRoleDto.setOffset(pagination.getOffset());
			systemRoleDto.setPageSize(pagination.getPageSize());
			systemRoleList = systemRoleService.querySystemRoleList(systemRoleDto);
		}
		modelMap.put("systemRoleList", systemRoleList);
		modelMap.put("pagination", pagination);
		return "user/roleList";
	}

	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request, SystemRole systemRole) {
		JunhaiAssert.notBlank(systemRole.getRoleName(), "角色名不能为空");

		// 同个商户下角色不可以重复

		if (systemRole.getId() != null && systemRole.getId() > 0) {
			systemRoleService.update(systemRole);
		} else {
			systemRoleService.insert(systemRole);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteRole(HttpServletRequest request, SystemRole systemRole) {
		JunhaiAssert.notNull(systemRole.getId(), "id不能为空");

		// 已分配的角色不可以删除
		systemRoleService.delete(systemRole.getId());

		// 需连带删除
		return jsonSuccess();
	}

	@RequestMapping("/toAuthorize")
	public String toAuthorize(HttpServletRequest request, String roleId, ModelMap modelMap) {

		// 根据角色获取权限列表
		Map<Integer, SystemPrivilage> systemPrivilageMap = null;
		List<SystemPrivilage> legalPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleId);
		systemPrivilageMap = SystemPrivilageUtil.getSystemPrivilageMap(legalPrivilageList);

		List<SystemAction> allSystemActionList = systemActionService.getAllSystemActionList();
		List<SystemAction> menuSystemActionList = SystemActionUtil.getMenuSystemActionList(allSystemActionList);
		List<SystemAction> rootMenuSystemActionList = new ArrayList<SystemAction>();
		for (SystemAction systemAction : menuSystemActionList) {
			if (CommonConstant.ROOT_MENU_ID.equals(systemAction.getParentId())) {
				rootMenuSystemActionList.add(systemAction);
			}
		}

		// 按父节点分组
		Map<String, List<SystemAction>> parentSystemActionMap = new HashMap<String, List<SystemAction>>();
		for (SystemAction parentSystemAction : menuSystemActionList) {
			List<SystemAction> systemActionGroupList = new ArrayList<SystemAction>();
			for (SystemAction systemAction : allSystemActionList) {
				if (systemAction.getParentId().equals(parentSystemAction.getId())) {
					systemActionGroupList.add(systemAction);
				}
			}
			parentSystemActionMap.put(String.valueOf(parentSystemAction.getId()), systemActionGroupList);
		}

		modelMap.put("roleId", roleId);
		modelMap.put("systemPrivilageMap", systemPrivilageMap);
		modelMap.put("parentSystemActionMap", parentSystemActionMap);
		modelMap.put("rootMenuSystemActionList", rootMenuSystemActionList);
		return "user/authorize";
	}

	@RequestMapping("/saveAuthorize")
	@ResponseBody
	public String saveAuthorize(Integer roleId, String actionIds, ModelMap modelMap) {
		JunhaiAssert.notNull(roleId, "角色ID不能为空");

		// 获取当前用户角色
		String roleIds = "1,2,3";

		// 首先必须拥有action的权限，才有对该action进行授权
		Map<Integer, SystemPrivilage> systemPrivilageMap = null;
		List<SystemPrivilage> legalPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		systemPrivilageMap = SystemPrivilageUtil.getSystemPrivilageMap(legalPrivilageList);

		// 获取全部action
		List<SystemAction> allSystemActionList = systemActionService.getAllSystemActionList();
		Map<String, SystemAction> systemActionMap = SystemActionUtil.getSystemActionMap(allSystemActionList);

		// 获取出需要并且可以授权的actionId
		Set<Integer> actionIdSet = new HashSet<Integer>();
		String[] arrActionId = actionIds.split(",");
		for (String strActionId : arrActionId) {
			Integer actionId = Integer.parseInt(strActionId);
			if (systemPrivilageMap.get(actionId) == null) {
				// continue;
			}

			// 添加操作路径树-会自动对父节点授权
			List<SystemAction> systemActionList = SystemActionUtil.getSystemActionTree(actionId, systemActionMap);
			for (SystemAction systemAction : systemActionList) {
				actionIdSet.add(systemAction.getId());
			}
		}

		// 删除历史数据
		systemPrivilageService.deleteByRoleId(roleId);

		legalPrivilageList = new ArrayList<SystemPrivilage>();
		for (Integer actionId : actionIdSet) {
			SystemPrivilage systemPrivilage = new SystemPrivilage();
			systemPrivilage.setActionId(actionId);
			systemPrivilage.setRoleId(roleId);
			legalPrivilageList.add(systemPrivilage);
		}

		// 新增权限数据
		systemPrivilageService.batchAddSystemPrivilage(legalPrivilageList);
		return jsonSuccess();
	}
}
