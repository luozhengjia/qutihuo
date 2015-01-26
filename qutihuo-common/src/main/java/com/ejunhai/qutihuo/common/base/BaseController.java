package com.ejunhai.qutihuo.common.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ejunhai.qutihuo.common.utils.ServiceLocator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import freemarker.template.Template;

public class BaseController {

	/** Logger available to subclasses */
	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	protected final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

	protected String jsonSuccess() {
		return this.jsonSuccess(null);
	}

	protected <T> String jsonSuccess(T data) {
		return this.jsonSuccess(data, "ok");
	}

	protected <T> String jsonSuccess(T data, String message) {
		return this.renderJson(200, message, data);
	}

	protected <T> String jsonFailed(int code, String message) {
		return this.renderJson(code, message, null);
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

	/**
	 * 解析模板为json
	 * 
	 * @param templateURL
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> parseJsonTemplate(String templateURL, Object modelMap) throws Exception {
		FreeMarkerConfigurer freeMarkerConfigurer = ServiceLocator.getBean("freemarkerConfig");
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateURL);
		String returnStr = FreeMarkerTemplateUtils.processTemplateIntoString(template, modelMap);
		return gson.fromJson(returnStr.replaceAll("\\s", " "), Map.class);
	}
}
