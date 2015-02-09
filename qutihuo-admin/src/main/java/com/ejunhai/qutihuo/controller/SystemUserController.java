package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ejunhai.qutihuo.system.dto.SystemPrivilageDto;
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

		// 获取当前用户角色
		String roleIds = "1,2,3";

		// 获取当前用户所拥有的action列表
		List<SystemPrivilage> authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		List<Integer> authorizedActionIdList = SystemPrivilageUtil.getActionIdList(authorizedPrivilageList);
		List<SystemAction> authorizedActionList = systemActionService.getSystemActionListByIds(authorizedActionIdList);

		// 获取根(一级菜单)action列表
		List<SystemAction> rootSystemActionList = SystemActionUtil.getRootActionList(authorizedActionList);

		// 按父节点分组
		Map<String, List<SystemAction>> parentSystemActionMap = new HashMap<String, List<SystemAction>>();
		for (SystemAction parentSystemAction : rootSystemActionList) {
			List<SystemAction> systemActionGroupList = new ArrayList<SystemAction>();
			for (SystemAction systemAction : authorizedActionList) {
				if (systemAction.getParentId().equals(parentSystemAction.getId())) {
					systemActionGroupList.add(systemAction);
				}
			}
			parentSystemActionMap.put(String.valueOf(parentSystemAction.getId()), systemActionGroupList);
		}

		// 获取当前角色已分配的权限列表
		List<SystemPrivilage> legalPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleId);

		modelMap.put("roleId", roleId);
		modelMap.put("authorizedActionList", authorizedActionList);
		modelMap.put("parentSystemActionMap", parentSystemActionMap);
		modelMap.put("legalPrivilageList", legalPrivilageList);
		return "user/authorize";
	}

	@RequestMapping("/saveAuthorize")
	@ResponseBody
	public String saveAuthorize(SystemPrivilageDto systemPrivilageDto, ModelMap modelMap) {
		JunhaiAssert.notNull(systemPrivilageDto.getRoleId(), "角色ID不能为空");

		// 获取当前用户角色
		String roleIds = "1,2,3";

		// 获取当前用户所拥有的action列表
		List<SystemPrivilage> authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		Map<Integer, SystemPrivilage> authorizedPrivilageMap = null;
		authorizedPrivilageMap = SystemPrivilageUtil.getSystemPrivilageMap(authorizedPrivilageList);

		// 获取全部action
		List<SystemAction> allSystemActionList = systemActionService.getAllSystemActionList();
		Map<String, SystemAction> systemActionMap = SystemActionUtil.getSystemActionMap(allSystemActionList);

		// 获取出需要并且可以授权的actionId
		Set<Integer> actionIdSet = new HashSet<Integer>();
		if (StringUtils.isNotBlank(systemPrivilageDto.getActionIds())) {
			String[] arrActionId = systemPrivilageDto.getActionIds().split(",");
			for (String strActionId : arrActionId) {
				Integer actionId = Integer.parseInt(strActionId);
				if (authorizedPrivilageMap.get(actionId) == null) {
					continue;
				}

				// 添加操作路径树-会自动对父节点授权
				List<SystemAction> systemActionList = SystemActionUtil.getCurAction2RootList(actionId, systemActionMap);
				for (SystemAction systemAction : systemActionList) {
					actionIdSet.add(systemAction.getId());
				}
			}
		}

		// 保存权限数据
		systemPrivilageService.saveSystemPrivilage(systemPrivilageDto.getRoleId(), actionIdSet);
		return jsonSuccess();
	}
}
