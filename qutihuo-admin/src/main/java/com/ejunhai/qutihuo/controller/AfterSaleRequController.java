package com.ejunhai.qutihuo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.aftersale.dto.AfterSaleRequDto;
import com.ejunhai.qutihuo.aftersale.enums.RequState;
import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;
import com.ejunhai.qutihuo.aftersale.service.AfterSaleRequService;
import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("orderMain")
public class AfterSaleRequController extends BaseController {

    @Resource
    private AfterSaleRequService afterSaleRequService;

    @Resource
    private CouponSchemaService couponSchemaService;

    @Resource
    private CouponService couponService;

    @Resource
    private OrderMainService orderMainService;

    @RequestMapping("/afterSaleRequList")
    public String afterSaleRequList(HttpServletRequest request, AfterSaleRequDto afterSaleRequDto, ModelMap modelMap) {
        afterSaleRequDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = afterSaleRequService.queryAfterSaleRequCount(afterSaleRequDto);
        Pagination pagination = new Pagination(afterSaleRequDto.getPageNo(), iCount);

        // 获取分页数据
        List<AfterSaleRequ> afterSaleRequList = new ArrayList<AfterSaleRequ>();
        if (iCount > 0) {
            afterSaleRequDto.setOffset(pagination.getOffset());
            afterSaleRequDto.setPageSize(pagination.getPageSize());
            afterSaleRequList = afterSaleRequService.queryAfterSaleRequList(afterSaleRequDto);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("afterSaleRequDto", afterSaleRequDto);
        modelMap.put("afterSaleRequList", afterSaleRequList);
        return "order/afterSaleRequList";
    }

    @RequestMapping("/toAfterSaleRequ")
    public String toAfterSaleRequ(Integer afterSaleRequId, ModelMap modelMap) throws Exception {
        AfterSaleRequ afterSaleRequ = afterSaleRequService.read(afterSaleRequId);
        OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(afterSaleRequ.getOrderMainNo());
        Coupon coupon = couponService.getCouponByOrderNo(orderMain.getOrderMainNo());
        CouponSchema couponSchema = couponSchemaService.read(coupon.getCouponSchemaId());

        modelMap.addAttribute("afterSaleRequ", afterSaleRequ);
        modelMap.addAttribute("orderMain", orderMain);
        modelMap.addAttribute("couponSchema", couponSchema);
        return "order/editAfterSaleRequ";
    }

    @RequestMapping("/refuseAfterSaleRequ")
    @ResponseBody
    public String refuseAfterSaleRequ(AfterSaleRequDto afterSaleRequDto, ModelMap modelMap) throws Exception {
        AfterSaleRequ afterSaleRequ = afterSaleRequService.read(afterSaleRequDto.getId());
        afterSaleRequ.setState(RequState.refuse.getValue());
        afterSaleRequ.setDealInfo(afterSaleRequDto.getDealInfo());
        afterSaleRequ.setDealTime(new Timestamp(System.currentTimeMillis()));
        afterSaleRequService.update(afterSaleRequ);
        return jsonSuccess();
    }
}
