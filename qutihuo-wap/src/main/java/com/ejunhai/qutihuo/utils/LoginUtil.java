package com.ejunhai.qutihuo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ejunhai.qutihuo.coupon.model.Coupon;

public class LoginUtil {
	private static final Logger logger = Logger.getLogger(LoginUtil.class);
	public static final String LOGIN_USER = "login_user";

	/**
	 * @param code
	 * @param message
	 * @return
	 */
	public static String validateResponseJson(boolean valid, String message) {
		return "{\"valid\":" + valid + ",\"msg\":\"" + message + "\"}";
	}

	public static boolean isLogin(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute(LOGIN_USER);
		if (obj == null)
			return false;
		return true;
	}

	public static Coupon getLoginUser(HttpServletRequest req) {
		Object obj = req.getSession().getAttribute(LOGIN_USER);
		if (obj == null)
			return null;
		return (Coupon) obj;
	}

	public static void logout(HttpServletRequest req) {
		req.getSession().removeAttribute(LOGIN_USER);
		req.getSession().invalidate();
	}

	public static int savePic(CommonsMultipartFile pic1File, String picServerPath) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(pic1File.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(picServerPath));
			// 开始把文件写到你指定的上传文件夹
			Streams.copy(in, out, true);
			return 1;
		} catch (Exception ex) {
			logger.error("保存图片出错", ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return 0;
	}
}
