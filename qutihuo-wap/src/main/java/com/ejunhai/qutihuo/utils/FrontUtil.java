package com.ejunhai.qutihuo.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FrontUtil {

	protected static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

	public static final String COOKIE_COUPON_NUMBER = "coupon_number";

	public static final String LOGIN_VALIDATE_IMAGE = "LOGIN_VALIDATE_IMAGE";

	private static String[] excludeUrls = { "/index.jhtml", "/login.jhtml", "/logout.jhtml" };

	public static boolean isExcludeUrl(String curUrl) {
		for (String url : excludeUrls) {
			if (curUrl.indexOf(url) > -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		if (request.getCookies() != null) {

			// 从cookie中获取couponNumber
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static boolean isAjax(HttpServletRequest request) {
		String xRequestedWith = request.getHeader("X-Requested-With");
		boolean isAjax = request.getHeader("accept").indexOf("application/json") > -1;
		return isAjax || (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") > -1);
	}

	public static <T> String renderJson(int code, String message, T data) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> stateMap = new HashMap<String, Object>();
		stateMap.put("code", code);
		stateMap.put("msg", StringUtils.isBlank(message) ? "Ok" : message);
		jsonMap.put("state", stateMap);
		jsonMap.put("data", data);
		return gson.toJson(jsonMap);
	}
}
