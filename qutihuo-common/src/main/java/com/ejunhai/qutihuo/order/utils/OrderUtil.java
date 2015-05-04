package com.ejunhai.qutihuo.order.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;

import com.ejunhai.qutihuo.aftersale.model.AfterSaleRequ;

public final class OrderUtil {

	/**
	 * 订单编号规则
	 * 
	 * @return
	 */
	public static synchronized String createOrderMainNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		return "D" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(4);
	}

	/**
	 * 补货单规则
	 * 
	 * @return
	 */
	public static synchronized String createOrderReplNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmss");
		return "B" + sdf.format(new Date()) + RandomStringUtils.randomNumeric(4);
	}

	/**
	 * 获取订单号列表
	 * 
	 * @param afterSaleRequList
	 * @return
	 */
	public static List<String> getOrderNoList(List<AfterSaleRequ> afterSaleRequList) {
		List<String> orderNoList = new ArrayList<String>();
		if (CollectionUtils.isEmpty(afterSaleRequList)) {
			return orderNoList;
		}

		for (AfterSaleRequ afterSaleRequ : afterSaleRequList) {
			orderNoList.add(afterSaleRequ.getOrderMainNo());
		}
		return orderNoList;
	}

	/**
	 * 获取物流公司列表
	 * 
	 * @return
	 */
	public static List<Map<String, String>> getLogisticCompanyList() {
		List<Map<String, String>> logisticCompanyList = new ArrayList<Map<String, String>>(2);
		Map<String, String> shunfengMap = new HashMap<String, String>();
		shunfengMap.put("logisticsCompanyCode", "shunfeng");
		shunfengMap.put("logisticsCompany", "顺丰快递");
		logisticCompanyList.add(shunfengMap);
		Map<String, String> zhongtongMap = new HashMap<String, String>();
		zhongtongMap.put("logisticsCompanyCode", "zhongtong");
		zhongtongMap.put("logisticsCompany", "中通快递");
		logisticCompanyList.add(zhongtongMap);
		return logisticCompanyList;
	}

	/**
	 * 通过物流公司编码获取物流公司名称
	 * 
	 * @param logisticsCompanyCode
	 * @return
	 */
	public static String getLogisticCompany(String logisticsCompanyCode) {
		List<Map<String, String>> logisticCompanyList = getLogisticCompanyList();
		for (Map<String, String> item : logisticCompanyList) {
			if (logisticsCompanyCode.equals(item.get("logisticsCompanyCode"))) {
				return item.get("logisticsCompany");
			}
		}
		return null;
	}

}
