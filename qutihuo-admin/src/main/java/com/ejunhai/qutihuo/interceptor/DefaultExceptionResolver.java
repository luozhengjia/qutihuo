package com.ejunhai.qutihuo.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.errors.BusinessException;
import com.ejunhai.qutihuo.errors.ErrorType;
import com.ejunhai.qutihuo.utils.FrontUtil;

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

		// 处理异步请求-重定向到统一异常处理
		if (FrontUtil.isAjax(request)) {

			// 分析异常类型
			Integer errorCode = ErrorType.SYSTEM_BUSY.getValue();
			String errorMsg = ErrorType.SYSTEM_BUSY.getTitle();
			if (ex instanceof BusinessException) {
				BusinessException businessException = (BusinessException) ex;
				errorCode = businessException.getErrorCode();
				errorMsg = businessException.getMessage();
			}

			try {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
				writer.flush();
			} catch (IOException e) {
				logger.error("处理异常请求出错", e);
			}

			return new ModelAndView();
		}

		// 根据不同错误转向不同页面
		if (ex instanceof BusinessException) {
			return new ModelAndView("error/error-500");
		}

		return new ModelAndView("error/error-404");
	}
}
