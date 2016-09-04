package com.ejunhai.qutihuo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.errors.ErrorType;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.system.enums.UserState;
import com.ejunhai.qutihuo.system.model.SystemUser;
import com.ejunhai.qutihuo.system.service.SystemUserService;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("")
public class LoginController extends BaseController {

	@Resource
	private SystemUserService systemUserService;

	@RequestMapping("login")
	public String login(HttpServletRequest request) {

		// 若用户已登录则跳转到欢迎页
		if (SessionManager.get(request) != null) {
			return "redirect:/index.jhtml";
		}

		return "login";
	}

	@RequestMapping("authentication")
	@ResponseBody
	public String authentication(String loginName, String password, String validateCode, HttpServletRequest request) {
		JunhaiAssert.notBlank(loginName, "用户账号不能为空");
		JunhaiAssert.notBlank(password, "用户密码不能为空");
		JunhaiAssert.notBlank(validateCode, "验证码不能为空");

		// 验证验证码
		String sValidateCode = (String) request.getSession().getAttribute(FrontUtil.LOGIN_VALIDATE_IMAGE);
		JunhaiAssert.isTrue(validateCode.equals(sValidateCode), "验证码无效");

		// 验证用户账号
		SystemUser systemUser = systemUserService.getSystemUserByLoginName(loginName);
		JunhaiAssert.notNull(systemUser, ErrorType.SYSTEM_USER_LOGIN_NAME_INVALID);

		// 验证用户密码
		String userPasswd = DigestUtils.md5Hex(systemUser.getPasswd().toUpperCase());
		JunhaiAssert.isTrue(userPasswd.equals(password), ErrorType.SYSTEM_USER_LOGIN_PWD_INVALID);

		// 验证用户状态
		JunhaiAssert.isFalse(UserState.lock.getValue().equals(systemUser.getState()), ErrorType.SYSTEM_USER_STATE_LOCK);

		// 登录用户
		SessionManager.put(systemUser, request);
		return jsonSuccess();
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// 注销用户
		SessionManager.clear(request);
		return "redirect:/login.jhtml";
	}
}
