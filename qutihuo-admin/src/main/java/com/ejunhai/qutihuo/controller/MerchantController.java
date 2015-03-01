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

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.base.Pagination;
import com.ejunhai.qutihuo.errors.JunhaiAssert;
import com.ejunhai.qutihuo.merchant.dto.MerchantDto;
import com.ejunhai.qutihuo.merchant.model.Merchant;
import com.ejunhai.qutihuo.merchant.service.MerchantService;
import com.ejunhai.qutihuo.system.enums.UserState;
import com.ejunhai.qutihuo.system.enums.UserType;
import com.ejunhai.qutihuo.system.model.SystemUser;
import com.ejunhai.qutihuo.system.service.SystemUserService;

@Controller
@RequestMapping("merchant")
public class MerchantController extends BaseController {

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private MerchantService merchantService;

	@RequestMapping("/merchantList")
	public String merchantList(HttpServletRequest request, MerchantDto merchantDto, ModelMap modelMap) {
		Integer iCount = merchantService.queryMerchantCount(merchantDto);
		Pagination pagination = new Pagination(merchantDto.getPageNo(), iCount);

		List<Merchant> merchantList = new ArrayList<Merchant>();
		if (iCount > 0) {
			merchantDto.setOffset(pagination.getOffset());
			merchantDto.setPageSize(pagination.getPageSize());
			merchantList = merchantService.queryMerchantList(merchantDto);
		}

		modelMap.put("merchantDto", merchantDto);
		modelMap.put("merchantList", merchantList);
		modelMap.put("pagination", pagination);
		return "merchant/merchantList";
	}

	@RequestMapping("/merchantDetail")
	public String merchantDetail(HttpServletRequest request, Merchant merchant, ModelMap modelMap) {
		if (merchant.getId() != null) {
			merchant = merchantService.read(merchant.getId());
		}

		modelMap.put("merchant", merchant);
		return "merchant/merchantDetail";
	}

	@RequestMapping("/saveMerchant")
	@ResponseBody
	public String addMerchant(HttpServletRequest request, MerchantDto merchantDto) {
		JunhaiAssert.notBlank(merchantDto.getMerchantName(), "用户昵称不能为空");

		Merchant merchant = new Merchant();
		if (merchantDto.getId() == null) {
			merchant = merchantService.read(merchantDto.getId());
		}

		// 设置参数值
		merchant.setMerchantName(merchantDto.getMerchantName());
		merchant.setBusinessLine(merchantDto.getBusinessLine());
		merchant.setRecordNumber(merchantDto.getRecordNumber());
		merchant.setContacts(merchantDto.getContacts());
		merchant.setTelephone(merchantDto.getTelephone());
		merchant.setAddress(merchantDto.getAddress());

		if (merchantDto.getId() == null) {
			merchant.setAvailableSmsNum(50);
			merchant.setMerchantLevel(1);
			merchant.setExpireTime(new Timestamp(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000));
			merchant.setOpenTime(new Timestamp(System.currentTimeMillis()));
			merchantService.insert(merchant);

			// 保存用户账号信息
			SystemUser systemUser = new SystemUser();
			systemUser.setUserType(UserType.sma.getValue());
			systemUser.setLoginName(merchantDto.getLoginName());
			systemUser.setNickname(merchantDto.getNickname());
			systemUser.setPasswd(merchantDto.getPasswd());
			systemUser.setPictureUrl(merchantDto.getPictureUrl());
			systemUser.setRoleIds(merchantDto.getRoleIds());
			systemUser.setMerchantId(merchant.getId());
			systemUser.setState(UserState.normal.getValue());
			systemUser.setTelephone(merchantDto.getTelephone());
			systemUserService.insert(systemUser);
		} else {
			// 保存商户信息
			merchantService.update(merchant);
		}

		return jsonSuccess();
	}
}
