package com.ejunhai.qutihuo.utils;

import javax.servlet.http.HttpServletRequest;

import com.ejunhai.qutihuo.system.model.SystemUser;

/**
 * session管理器
 * 
 * @author parcel
 * 
 */
public class SessionManager {

	public static SystemUser get(HttpServletRequest request) {
		return (SystemUser) request.getSession().getAttribute("SYSTEM_USER");
	}

	public static void put(SystemUser systemUser, HttpServletRequest request) {
		request.getSession().setAttribute("SYSTEM_USER", systemUser);
	}

	public static void clear(HttpServletRequest request) {
		request.getSession().removeAttribute("SYSTEM_USER");
	}
}
