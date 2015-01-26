package com.ejunhai.qutihuo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("couponNumber", 123456);
        return "index";
    }
}
