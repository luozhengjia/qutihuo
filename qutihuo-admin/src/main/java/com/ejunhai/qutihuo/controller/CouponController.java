package com.ejunhai.qutihuo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.coupon.dto.CouponDto;
import com.ejunhai.qutihuo.coupon.dto.CouponSchemaDto;
import com.ejunhai.qutihuo.coupon.enums.Confusion;
import com.ejunhai.qutihuo.coupon.enums.CouponState;
import com.ejunhai.qutihuo.coupon.enums.ExchangeMode;
import com.ejunhai.qutihuo.coupon.model.Coupon;
import com.ejunhai.qutihuo.coupon.model.CouponSchema;
import com.ejunhai.qutihuo.coupon.service.CouponSchemaService;
import com.ejunhai.qutihuo.coupon.service.CouponService;
import com.ejunhai.qutihuo.coupon.utils.CouponUtil;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController {

    @Resource
    private CouponSchemaService couponSchemaService;

    @Resource
    private CouponService couponService;

    @RequestMapping("/couponSchemaList")
    public String couponSchemaList(HttpServletRequest request, CouponSchemaDto couponSchemaDto, ModelMap modelMap) {
        couponSchemaDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = couponSchemaService.queryCouponSchemaCount(couponSchemaDto);
        Pagination pagination = new Pagination(couponSchemaDto.getPageNo(), iCount);

        // 获取分页数据
        List<CouponSchema> couponSchemaList = new ArrayList<CouponSchema>();
        if (iCount > 0) {
            couponSchemaDto.setOffset(pagination.getOffset());
            couponSchemaDto.setPageSize(pagination.getPageSize());
            couponSchemaList = couponSchemaService.queryCouponSchemaList(couponSchemaDto);
        }

        modelMap.put("pagination", pagination);
        modelMap.put("couponSchemaDto", couponSchemaDto);
        modelMap.put("couponSchemaList", couponSchemaList);
        return "coupon/couponSchemaList";
    }

    @RequestMapping("/toCouponSchema")
    public String toCouponSchema(HttpServletRequest request, Integer couponSchemaId, ModelMap modelMap) {
        if (couponSchemaId != null) {
            CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
            modelMap.put("couponSchema", couponSchema);
        }

        return "coupon/couponSchemaEdit";
    }

    @RequestMapping("/saveCouponSchema")
    @ResponseBody
    public String saveCouponSchema(HttpServletRequest request, CouponSchemaDto couponSchemaDto) {
        JunhaiAssert.notBlank(couponSchemaDto.getCouponName(), "礼品卡名称不能为空");
        JunhaiAssert.notNull(couponSchemaDto.getParValue(), "礼品卡面值不能为空");
        JunhaiAssert.notBlank(couponSchemaDto.getIconUrl(), "礼品卡图标不能为空");
        JunhaiAssert.notNull(couponSchemaDto.getUseStartdate(), "礼品卡开始时间不能为空");
        JunhaiAssert.notNull(couponSchemaDto.getUseStartdate(), "礼品卡结束时间不能为空");
        JunhaiAssert.notNull(couponSchemaDto.getIssueAmount(), "礼品卡总发行量不能为空");

        // 验证用户是否有操作权限
        CouponSchema couponSchema = new CouponSchema();
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.notNull(merchantId, "只有商户才能创建优惠券方案");
        if (couponSchemaDto.getId() != null) {
            couponSchema = couponSchemaService.read(couponSchemaDto.getId());
            JunhaiAssert.isTrue(merchantId == null || merchantId.equals(couponSchema.getMerchantId()), "id无效");
        }

        // 设置参数
        couponSchema.setCouponName(couponSchemaDto.getCouponName());
        couponSchema.setParValue(couponSchemaDto.getParValue());
        couponSchema.setIconUrl(couponSchemaDto.getIconUrl());
        couponSchema.setUseStartdate(couponSchemaDto.getUseStartdate());
        couponSchema.setUseEnddate(couponSchemaDto.getUseEnddate());
        couponSchema.setIssueAmount(couponSchemaDto.getIssueAmount());
        couponSchema.setDayLimitNum(couponSchemaDto.getDayLimitNum());
        couponSchema.setFrontDayNum(couponSchemaDto.getFrontDayNum());
        couponSchema.setInitActivate(couponSchemaDto.getInitActivate());
        couponSchema.setRemark(couponSchemaDto.getRemark());

        // 新增或更新用户信息
        if (couponSchemaDto.getId() != null) {
            couponSchemaService.update(couponSchema);
        } else {
            couponSchema.setMerchantId(merchantId);
            couponSchema.setExchangeMode(ExchangeMode.single.getValue());
            couponSchema.setHasIssueNum(0);
            couponSchema.setHasUseNum(0);
            couponSchema.setHasConfusion(Confusion.no.getValue());
            couponSchemaService.insert(couponSchema);
        }

        return jsonSuccess();
    }

    @RequestMapping("/deleteCouponSchema")
    @ResponseBody
    public String deleteCouponSchema(HttpServletRequest request, Integer couponSchemaId) {
        JunhaiAssert.notNull(couponSchemaId, "id不能为空");
        CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");
        couponSchemaService.delete(couponSchemaId);
        return jsonSuccess();
    }

    @RequestMapping("/generateCoupons")
    @ResponseBody
    public String generateCoupons(HttpServletRequest request, Integer couponSchemaId, ModelMap modelMap) {
        JunhaiAssert.notNull(couponSchemaId, "id不能为空");
        CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");

        // 每次最多生成1000张优惠券
        int oneMaxGenerateNum = 1000;
        int canGenerateAmount = couponSchema.getIssueAmount() - couponSchema.getHasIssueNum();
        int generateNum = canGenerateAmount > oneMaxGenerateNum ? oneMaxGenerateNum : canGenerateAmount;
        couponService.batchGenerateCoupon(couponSchemaId, generateNum);
        return jsonSuccess();
    }

    @RequestMapping("/exportCoupons")
    public String exportCoupons(HttpServletRequest request, Integer couponSchemaId, HttpServletResponse response)
            throws IOException {

        JunhaiAssert.notNull(couponSchemaId, "id不能为空");
        CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");

        // 查询礼品卡列表
        CouponDto querycouponDto = new CouponDto();
        querycouponDto.setCouponSchemaId(couponSchemaId);
        querycouponDto.setOffset(0);
        querycouponDto.setPageSize(Integer.MAX_VALUE);
        List<Coupon> couponList = couponService.queryCouponList(querycouponDto);

        OutputStream outputStream = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("优惠券码");
            cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("优惠券密码");
            cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("开始日期");
            cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("结束日期");
            cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("状态");

            for (int i = 0; i < couponList.size(); i++) {
                Coupon coupon = couponList.get(i);
                row = sheet.createRow(i + 1);
                cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(coupon.getCouponNumber());
                cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(coupon.getCouponPassword());
                cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(simpleDateFormat.format(coupon.getUseStartdate()));
                cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(simpleDateFormat.format(coupon.getUseEnddate()));
                cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(CouponState.get(coupon.getState()).getTitle());
            }

            response.reset();
            response.setContentType("application/x-msdownload ");
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fileName = couponSchema.getCouponName() + "明细单" + simpleDateFormat.format(new Date()) + ".xls";
            String encodeFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + encodeFileName);
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            logger.error("导出优惠券失败", e);
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
        return null;
    }

    @RequestMapping("/disturbCoupons")
    @ResponseBody
    public String disturbCoupon(HttpServletRequest request, Integer couponSchemaId) throws Exception {
        JunhaiAssert.notNull(couponSchemaId, "id不能为空");
        CouponSchema couponSchema = couponSchemaService.read(couponSchemaId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(couponSchema.getMerchantId().equals(merchantId), "id无效");

        couponService.disturbCoupons(couponSchemaId);
        return jsonSuccess();
    }

    @RequestMapping("/couponList")
    public String couponList(HttpServletRequest request, CouponDto couponDto, ModelMap modelMap) {
        couponDto.setMerchantId(SessionManager.get(request).getMerchantId());
        Integer iCount = couponService.queryCouponCount(couponDto);
        Pagination pagination = new Pagination(couponDto.getPageNo(), iCount);

        // 获取礼品卡方案信息
        CouponSchemaDto couponSchemaDto = new CouponSchemaDto();
        couponSchemaDto.setMerchantId(SessionManager.get(request).getMerchantId());
        couponSchemaDto.setOffset(0);
        couponSchemaDto.setPageSize(Integer.MAX_VALUE);
        List<CouponSchema> couponSchemaList = couponSchemaService.queryCouponSchemaList(couponSchemaDto);
        modelMap.put("couponSchemaList", couponSchemaList);

        // 获取分页数据
        List<Coupon> couponList = new ArrayList<Coupon>();
        if (iCount > 0) {
            couponDto.setOffset(pagination.getOffset());
            couponDto.setPageSize(pagination.getPageSize());
            couponList = couponService.queryCouponList(couponDto);
            modelMap.put("couponSchemaMap", CouponUtil.getCouponSchemaMap(couponSchemaList));
        }

        modelMap.put("pagination", pagination);
        modelMap.put("couponDto", couponDto);
        modelMap.put("couponList", couponList);
        return "coupon/couponList";
    }

    @RequestMapping("/activateCoupon")
    @ResponseBody
    public String activateCoupon(HttpServletRequest request, Integer couponId) throws Exception {
        JunhaiAssert.notNull(couponId, "id不能为空");
        Coupon coupon = couponService.read(couponId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(coupon.getMerchantId().equals(merchantId), "id无效");

        couponService.activateCoupon(couponId);
        return jsonSuccess();
    }

    @RequestMapping("/discardCoupon")
    @ResponseBody
    public String discardCoupon(HttpServletRequest request, Integer couponId) throws Exception {
        JunhaiAssert.notNull(couponId, "id不能为空");
        Coupon coupon = couponService.read(couponId);
        Integer merchantId = SessionManager.get(request).getMerchantId();
        JunhaiAssert.isTrue(coupon.getMerchantId().equals(merchantId), "id无效");

        couponService.discardCoupon(couponId);
        return jsonSuccess();
    }
}
