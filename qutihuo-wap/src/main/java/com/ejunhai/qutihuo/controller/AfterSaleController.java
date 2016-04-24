package com.ejunhai.qutihuo.controller;

import java.sql.Timestamp;
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
import com.ejunhai.qutihuo.common.utils.PropertyConfigurer;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.merchant.model.Merchant;
import com.ejunhai.qutihuo.merchant.service.MerchantService;
import com.ejunhai.qutihuo.order.model.OrderMain;
import com.ejunhai.qutihuo.order.service.OrderMainService;
import com.ejunhai.qutihuo.utils.SessionManager;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

/**
 * after sale Controller
 * 
 * @author parcel
 * @history 2014-05-04 parcel 新建
 */
@Controller
@RequestMapping("")
public class AfterSaleController extends BaseController {

    @Resource
    private OrderMainService orderMainService;

    @Resource
    private AfterSaleRequService afterSaleRequService;

    @Resource
    private MerchantService merchantService;

    @RequestMapping("/toAfterSaleRequ")
    public String toAfterSaleRequ(ModelMap modelMap, HttpServletRequest request) {
        Coupon coupon = SessionManager.get(request);
        if (coupon == null) {
            return "index";
        }

        OrderMain orderMain = orderMainService.getOrderMainByOrderMainNo(coupon.getOrderNumber());
        AfterSaleRequDto afterSaleRequDto = new AfterSaleRequDto();
        afterSaleRequDto.setOrderMainNo(orderMain.getOrderMainNo());
        afterSaleRequDto.setOffset(0);
        afterSaleRequDto.setPageSize(Integer.MAX_VALUE);
        List<AfterSaleRequ> afterSaleRequList = afterSaleRequService.queryAfterSaleRequList(afterSaleRequDto);
        if (afterSaleRequList.size() > 0) {
            modelMap.addAttribute("afterSaleRequ", afterSaleRequList.get(0));
        }
        modelMap.addAttribute("orderMain", orderMain);
        return "afterSale";
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

    @RequestMapping("/savaAfterSaleRequ")
    @ResponseBody
    public String savaAfterSaleRequ(AfterSaleRequ afterSaleRequ, ModelMap modelMap, HttpServletRequest request) {
        Coupon coupon = SessionManager.get(request);

        afterSaleRequ.setMerchantId(coupon.getMerchantId());
        afterSaleRequ.setOrderMainNo(coupon.getOrderNumber());
        afterSaleRequ.setState(RequState.wait.getValue());
        afterSaleRequ.setCreateTime(new Timestamp(System.currentTimeMillis()));

        // 将图片保存至服务器
        afterSaleRequService.insert(afterSaleRequ);
        return jsonSuccess();
    }

    @RequestMapping("/introduction")
    public String introduction(ModelMap modelMap, HttpServletRequest request) {
        Coupon coupon = SessionManager.get(request);
        Merchant merchant = merchantService.read(coupon.getMerchantId());

        modelMap.addAttribute("merchant", merchant);
        return "introduction";
    }

}
