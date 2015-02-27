package com.ejunhai.qutihuo.controller;

import java.sql.Timestamp;
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
import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.system.dto.SystemPrivilageDto;
import com.ejunhai.qutihuo.system.dto.SystemRoleDto;
import com.ejunhai.qutihuo.system.dto.SystemUserDto;
import com.ejunhai.qutihuo.system.enums.UserState;
import com.ejunhai.qutihuo.system.enums.UserType;
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
import com.ejunhai.qutihuo.system.utils.SystemRoleUtil;
import com.ejunhai.qutihuo.system.utils.SystemUserUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

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

			// 获取角色ID-角色映射关系
			List<Integer> roleIdList = SystemUserUtil.getRoleIdList(systemUserList);
			List<SystemRole> systemRoleList = systemRoleService.getSystemRoleListByIds(roleIdList);
			Map<String, SystemRole> systemRoleMap = SystemRoleUtil.getSystemRoleMap(systemRoleList);
			modelMap.put("systemRoleMap", systemRoleMap);
		}

		modelMap.put("systemUserDto", systemUserDto);
		modelMap.put("systemUserList", systemUserList);
		modelMap.put("pagination", pagination);
		return "system/userList";
	}

	@RequestMapping("/userDetail")
	public String userDetail(HttpServletRequest request, Integer userId, ModelMap modelMap) {

		// 验证用户是否有操作权限
		SystemUser systemUser = systemUserService.read(userId);
		Integer merchantId = SessionManager.get(request).getMerchantId();
		JunhaiAssert.isTrue(merchantId == null || merchantId.equals(systemUser.getMerchantId()), "id无效");

		// 获取可以分配给用户的角色列表
		SystemRoleDto systemRoleDto = new SystemRoleDto();
		systemRoleDto.setMerchantId(merchantId);
		systemRoleDto.setOffset(0);
		systemRoleDto.setPageSize(Integer.MAX_VALUE);
		List<SystemRole> systemRoleList = systemRoleService.querySystemRoleList(systemRoleDto);
		modelMap.put("systemRoleList", systemRoleList);
		modelMap.put("systemRoleMap", SystemRoleUtil.getSystemRoleMap(systemRoleList));

		modelMap.put("systemUser", systemUser);
		return "system/userEdit";
	}

	@RequestMapping("/saveUser")
	@ResponseBody
	public String saveUser(HttpServletRequest request, SystemUserDto systemUserDto) {
		JunhaiAssert.notBlank(systemUserDto.getNickname(), "用户昵称不能为空");

		// 验证用户是否有操作权限
		SystemUser systemUser = new SystemUser();
		Integer merchantId = SessionManager.get(request).getMerchantId();
		if (systemUserDto.getId() != null) {
			systemUser = systemUserService.read(systemUserDto.getId());
			JunhaiAssert.isTrue(merchantId == null || merchantId.equals(systemUser.getMerchantId()), "id无效");
		}

		systemUser.setNickname(systemUserDto.getNickname());
		systemUser.setTelephone(systemUserDto.getTelephone());
		systemUser.setPictureUrl(systemUserDto.getPictureUrl());
		systemUser.setRoleIds(systemUserDto.getRoleIds());

		// 新增或更新用户信息
		if (systemUserDto.getId() != null) {
			systemUserService.update(systemUser);
		} else {
			systemUser.setLoginName(systemUserDto.getLoginName());
			systemUser.setPasswd(systemUserDto.getPasswd());

			// 系统管理员可以创建系统管理员，商户可以创建商户
			systemUser.setUserType(merchantId == null ? UserType.ma.getValue() : UserType.sa.getValue());
			systemUser.setMerchantId(merchantId);
			systemUser.setState(UserState.normal.getValue());
			systemUserService.insert(systemUser);
		}

		return jsonSuccess();
	}

	@RequestMapping("/editPwd")
	@ResponseBody
	public String editPwd(HttpServletRequest request, SystemUserDto systemUserDto) {
		JunhaiAssert.notNull(systemUserDto.getId(), "用户ID不能为空");
		JunhaiAssert.notBlank(systemUserDto.getPasswd(), "登陆密码不能为空");

		// 验证用户是否有操作权限
		SystemUser systemUser = systemUserService.read(systemUserDto.getId());
		Integer merchantId = SessionManager.get(request).getMerchantId();
		JunhaiAssert.isTrue(merchantId == null || merchantId.equals(systemUser.getMerchantId()), "id无效");

		// 更新密码
		systemUser.setPasswd(systemUserDto.getPasswd());
		systemUser.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		systemUserService.update(systemUser);

		return jsonSuccess();
	}

	@RequestMapping("/lockUser")
	@ResponseBody
	public String lockUser(HttpServletRequest request) {
		return jsonSuccess();
	}

	@RequestMapping("/unlockUser")
	@ResponseBody
	public String unlockUser(HttpServletRequest request) {
		return jsonSuccess();
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
		return "system/roleList";
	}

	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request, SystemRole systemRole) {
		JunhaiAssert.notBlank(systemRole.getRoleName(), "角色名不能为空");

		// 同个商户下角色名称不可以重复

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

		List<SystemAction> authorizedActionList = null;
		if (UserType.ssa.getValue().equals(SessionManager.get(request).getUserType())) {
			authorizedActionList = systemActionService.getAllSystemActionList();
		} else {
			// 获取当前用户角色
			String roleIds = SessionManager.get(request).getRoleIds();

			// 获取当前用户所拥有的action列表
			List<SystemPrivilage> authorizedPrivilageList = null;
			authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
			List<Integer> authorizedActionIdList = SystemPrivilageUtil.getActionIdList(authorizedPrivilageList);
			authorizedActionList = systemActionService.getSystemActionListByIds(authorizedActionIdList);
		}

		// 获取根(一级菜单)action列表，并按父节点分组
		List<SystemAction> rootSystemActionList = new ArrayList<SystemAction>();
		Map<String, List<SystemAction>> parentSystemActionMap = new HashMap<String, List<SystemAction>>();
		for (SystemAction systemAction : authorizedActionList) {
			if (CommonConstant.ROOT_MENU_ID.equals(systemAction.getParentId())) {
				rootSystemActionList.add(systemAction);
			}

			List<SystemAction> groupList = parentSystemActionMap.get(String.valueOf(systemAction.getParentId()));
			if (groupList == null) {
				groupList = new ArrayList<SystemAction>();
				parentSystemActionMap.put(String.valueOf(systemAction.getParentId()), groupList);
			}
			groupList.add(systemAction);
		}

		// 获取当前角色已分配的权限列表
		List<SystemPrivilage> legalPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleId);

		modelMap.put("roleId", roleId);
		modelMap.put("rootSystemActionList", rootSystemActionList);
		modelMap.put("parentSystemActionMap", parentSystemActionMap);
		modelMap.put("legalPrivilageList", legalPrivilageList);
		return "system/authorize";
	}

	@RequestMapping("/saveAuthorize")
	@ResponseBody
	public String saveAuthorize(HttpServletRequest request, SystemPrivilageDto systemPrivilageDto, ModelMap modelMap) {
		JunhaiAssert.notNull(systemPrivilageDto.getRoleId(), "角色ID不能为空");

		// 获取当前用户角色
		String roleIds = SessionManager.get(request).getRoleIds();

		// 获取当前用户所拥有的action列表
		List<SystemPrivilage> authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		Map<Integer, SystemPrivilage> authorizedPrivilageMap = null;
		authorizedPrivilageMap = SystemPrivilageUtil.getSystemPrivilageMap(authorizedPrivilageList);

		// 获取全部action
		List<SystemAction> allSystemActionList = systemActionService.getAllSystemActionList();
		Map<String, SystemAction> systemActionMap = SystemActionUtil.getSystemActionMap(allSystemActionList);

		// 获取出需要并且可以授权的actionId
		Set<Integer> actionIdSet = new HashSet<Integer>();
		Integer userType = SessionManager.get(request).getUserType();
		if (StringUtils.isNotBlank(systemPrivilageDto.getActionIds())) {
			String[] arrActionId = systemPrivilageDto.getActionIds().split(",");
			for (String strActionId : arrActionId) {
				Integer actionId = Integer.parseInt(strActionId);
				if (!UserType.ssa.getValue().equals(userType) && authorizedPrivilageMap.get(actionId) == null) {
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
