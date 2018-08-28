package com.ejunhai.qutihuo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ejunhai.qutihuo.merchant.service.MerchantService;
import com.ejunhai.qutihuo.utils.FrontUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private MerchantService merchantService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        if (FrontUtil.isExcludeUrl(request.getRequestURI())) {
            return true;
        }

        // 若用户未登录，则跳转至登陆页
        if (SessionManager.get(request) == null) {
            response.sendRedirect("/index.jhtml");
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        // 处理异步请求-无需准备菜单数据
        if (FrontUtil.isAjax(request) || SessionManager.get(request) == null) {
            return;
        }

        arg3.addObject("_user", merchantService.read(SessionManager.get(request).getMerchantId()));
    }

}
