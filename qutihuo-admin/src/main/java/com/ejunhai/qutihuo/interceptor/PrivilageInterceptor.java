package com.ejunhai.qutihuo.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.errors.ErrorType;
import com.ejunhai.qutihuo.system.model.SystemAction;
import com.ejunhai.qutihuo.system.model.SystemPrivilage;
import com.ejunhai.qutihuo.system.service.SystemActionService;
import com.ejunhai.qutihuo.system.service.SystemPrivilageService;
import com.ejunhai.qutihuo.system.utils.SystemActionUtil;
import com.ejunhai.qutihuo.system.utils.SystemPrivilageUtil;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

public class PrivilageInterceptor implements HandlerInterceptor {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	@Resource
	private SystemActionService systemActionService;

	@Resource
	private SystemPrivilageService systemPrivilageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		if (SessionManager.get(request) == null) {
			return true;
		}

		// 获取当前用户所拥有的action列表
		String roleIds = SessionManager.get(request).getRoleIds();
		List<SystemPrivilage> authorizedPrivilageList = systemPrivilageService.getSystemPrivilageListByRoleIds(roleIds);
		List<Integer> authorizedActionIdList = SystemPrivilageUtil.getActionIdList(authorizedPrivilageList);
		List<SystemAction> authorizedActionList = systemActionService.getSystemActionListByIds(authorizedActionIdList);
		Map<String, SystemAction> url2ActionMap = SystemActionUtil.getUrl2ActionMap(authorizedActionList);
		if (url2ActionMap.get(request.getRequestURI()) != null) {
			return true;
		}

		// 处理异步请求-重定向到统一异常处理
		if (FrontUtil.isAjax(request)) {
			try {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter writer = response.getWriter();
				String msg = ErrorType.SYSTEM_FORBIDDEN.getTitle();
				writer.write(FrontUtil.renderJson(ErrorType.SYSTEM_FORBIDDEN.getValue(), msg, null));
				writer.flush();
			} catch (IOException e) {
				logger.error("处理异常请求出错", e);
			}
		} else {
			response.sendRedirect("/error-403.jhtml");
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

}
