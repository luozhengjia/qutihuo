package com.ejunhai.qutihuo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.errors.BusinessException;

/**
 * 系统默认异常处理类
 * 
 * @author 罗正加
 * @history 2015-1-2 罗正加 新建
 */
public class DefaultExceptionResolver implements HandlerExceptionResolver {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// 打印错误日志
		logger.error("接口异常", ex);

		// 处理异步请求
		if (StringUtils.isNotBlank(request.getHeader("X-Requested-With"))) {
			// 重定向到统一异常处理
			return new ModelAndView("redirect:/toList");
		}

		// 根据不同错误转向不同页面
		if (ex instanceof BusinessException) {
			return new ModelAndView("error-500");
		}

		return new ModelAndView("error-404");
	}
}
