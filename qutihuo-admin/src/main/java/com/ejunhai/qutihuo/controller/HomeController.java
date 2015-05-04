package com.ejunhai.qutihuo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.utils.PropertyConfigurer;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("couponNumber", 123456);
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
    
    
}
