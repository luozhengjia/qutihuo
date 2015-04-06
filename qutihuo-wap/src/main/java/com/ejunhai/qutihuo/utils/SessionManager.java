package com.ejunhai.qutihuo.utils;

import javax.servlet.http.HttpServletRequest;

import com.ejunhai.qutihuo.coupon.model.Coupon;

/**
 * session管理器
 * 
 * @author parcel
 * 
 */
public class SessionManager {

	public static Coupon get(HttpServletRequest request) {
		return (Coupon) request.getSession().getAttribute("SYSTEM_USER");
	}

	public static void put(HttpServletRequest request, Coupon coupon) {
		request.getSession().setAttribute("SYSTEM_USER", coupon);
	}

	public static void clear(HttpServletRequest request) {
		request.getSession().removeAttribute("SYSTEM_USER");
	}
}
