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
import com.ejunhai.qutihuo.system.dto.SystemRoleDto;
import com.ejunhai.qutihuo.system.dto.SystemUserDto;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.model.SystemRole;
import com.ejunhai.qutihuo.system.model.SystemUser;
import com.ejunhai.qutihuo.system.service.SystemRoleService;
import com.ejunhai.qutihuo.system.service.SystemUserService;

@Controller
@RequestMapping("system")
public class SystemUserController extends BaseController {
	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemRoleService systemRoleService;

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
		if (systemRole.getId() != null && systemRole.getId() > 0) {
			systemRoleService.update(systemRole);
		} else {
			systemRoleService.insert(systemRole);
		}
		return jsonSuccess();
	}

	@RequestMapping("/deleteRole")
	@ResponseBody
	public String deleteAction(HttpServletRequest request, SystemRole systemRole) {
		JunhaiAssert.notNull(systemRole.getId(), "id不能为空");
		systemRoleService.delete(systemRole.getId());
		return jsonSuccess();
	}
}
