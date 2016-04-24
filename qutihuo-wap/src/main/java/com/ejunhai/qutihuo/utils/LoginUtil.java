package com.ejunhai.qutihuo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
