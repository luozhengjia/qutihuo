package com.ejunhai.qutihuo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.order.dto.OrderReplDto;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.model.OrderRepl;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.order.service.OrderReplService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderRepl")
public class OrderReplController extends BaseController {

    @Resource
    private OrderReplService orderReplService;

    @Resource
    private OrderMainService orderMainService;

    @Resource
    private CouponSchemaService couponSchemaService;

    @Resource
    private CouponService couponService;

    @RequestMapping("/orderReplList")
    public String orderReplList(HttpServletRequest request, OrderReplDto orderReplDto, ModelMap modelMap) {
        orderReplDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = orderReplService.queryOrderReplCount(orderReplDto);
        Pagination pagination = new Pagination(orderReplDto.getPageNo(), iCount);

        // 获取分页数据
        List<OrderRepl> orderReplList = new ArrayList<OrderRepl>();
        if (iCount > 0) {
            orderReplDto.setOffset(pagination.getOffset());
            orderReplDto.setPageSize(pagination.getPageSize());
            orderReplList = orderReplService.queryOrderReplList(orderReplDto);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("orderReplDto", orderReplDto);
        modelMap.put("orderReplList", orderReplList);
        return "order/orderReplList";
    }

    @RequestMapping("/toAddOrderRepl")
    public String toAddOrderRepl(HttpServletRequest request, String orderMainNo, ModelMap modelMap) {
        OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderMainNo);
        JunhaiAssert.notNull(orderMain, "订单号码无效");
        modelMap.addAttribute("orderMain", orderMain);
        Coupon coupon = couponService.getCouponByOrderNo(orderMain.getOrderMainNo());
        modelMap.addAttribute("coupon", coupon);
        CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());
        modelMap.addAttribute("couponSchema", couponSchema);
        return "order/addOrderRepl";
    }

    @RequestMapping("/addOrderRepl")
    public ModelAndView addOrderRepl(OrderRepl orderRepl, ModelMap modelMap) throws Exception {
        OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(orderRepl.getOrderMainNo());
        this.orderReplService.createOrderRepl(orderMain, orderRepl);
        return new ModelAndView(new RedirectView("/system/order/orderReplList.sc?state=0"));
    }
}
