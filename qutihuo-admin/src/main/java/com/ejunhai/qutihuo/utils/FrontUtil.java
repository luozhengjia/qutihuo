package com.ejunhai.qutihuo.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FrontUtil {

	protected static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

	public static final String LOGIN_VALIDATE_IMAGE = "LOGIN_VALIDATE_IMAGE";

	private static String[] excludeUrls = { "/login.jhtml", "/authentication.jhtml", "/logout.jhtml",
			"/forbidden.jhtml", "getUptoken.jhtml" };

	public static boolean isExcludeUrl(String curUrl) {
		for (String url : excludeUrls) {
			if (curUrl.indexOf(url) > -1) {
				return true;
			}
		}
		return false;
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
