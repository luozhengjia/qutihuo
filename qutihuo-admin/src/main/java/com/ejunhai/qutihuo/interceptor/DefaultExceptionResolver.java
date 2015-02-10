package com.ejunhai.qutihuo.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.errors.BusinessException;
import com.ejunhai.qutihuo.errors.ErrorType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 系统默认异常处理类
 * 
 * @author 罗正加
 * @history 2015-1-2 罗正加 新建
 */
public class DefaultExceptionResolver implements HandlerExceptionResolver {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	protected final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// 打印错误日志
		logger.error("接口异常", ex);

		// 处理异步请求-重定向到统一异常处理
		if (this.isAjax(request)) {

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
				writer.write(this.renderJson(errorCode, errorMsg, null));
				writer.flush();
			} catch (IOException e) {
				logger.error("处理异常请求出错", e);
			}
		}

		// 根据不同错误转向不同页面
		if (ex instanceof BusinessException) {
			return new ModelAndView("error-500");
		}

		return new ModelAndView("error-404");
	}

	private boolean isAjax(HttpServletRequest request) {
		String xRequestedWith = request.getHeader("X-Requested-With");
		boolean isAjax = request.getHeader("accept").indexOf("application/json") > -1;
		return isAjax || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") > -1);
	}

	private <T> String renderJson(int code, String message, T data) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("code", code);
		stateMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
		jsonMap.put("state", stateMap);
		jsonMap.put("data", data);
		return gson.toJson(jsonMap);
	}
}
