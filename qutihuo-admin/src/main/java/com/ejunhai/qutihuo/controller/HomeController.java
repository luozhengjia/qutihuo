package com.ejunhai.qutihuo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.utils.PropertyConfigurer;
import com.ejunhai.qutihuo.errors.ErrorType;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
@RequestMapping("")
public class HomeController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
		return "index";
	}

	@RequestMapping("getUptoken")
	@ResponseBody
	public String getUptoken(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String bucketName = PropertyConfigurer.getContextProperty("qiniu.bucket.name");
		String accessKey = PropertyConfigurer.getContextProperty("qiniu.access.key");
		String secretKey = PropertyConfigurer.getContextProperty("qiniu.secret.key");

		Mac mac = new Mac(accessKey, secretKey);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		return "{ \"uptoken\": \"" + putPolicy.token(mac) + "\" }";
	}

	@RequestMapping("/forbidden")
	public String forbidden(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 处理异步请求-重定向到统一异常处理
		if (FrontUtil.isAjax(request)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			Integer errorCode = ErrorType.SYSTEM_FORBIDDEN.getValue();
			String errorMsg = ErrorType.SYSTEM_FORBIDDEN.getTitle();
			writer.write(FrontUtil.renderJson(errorCode, errorMsg, null));
			writer.flush();
			return null;
		}

		return "errors/error-403";
	}
}
