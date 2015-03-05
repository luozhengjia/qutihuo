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
import com.ejunhai.qutihuo.system.dto.SystemRoleDto;
import com.ejunhai.qutihuo.system.enums.RoleType;
import com.ejunhai.qutihuo.system.enums.UserState;
import com.ejunhai.qutihuo.system.enums.UserType;
import com.ejunhai.qutihuo.system.model.SystemRole;
import com.ejunhai.qutihuo.system.model.SystemUser;
import com.ejunhai.qutihuo.system.service.SystemRoleService;
import com.ejunhai.qutihuo.system.service.SystemUserService;
import com.ejunhai.qutihuo.system.utils.SystemRoleUtil;
import com.ejunhai.qutihuo.utils.SessionManager;

@Controller
@RequestMapping("merchant")
public class MerchantController extends BaseController {

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private SystemRoleService systemRoleService;

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

		// 获取可以分配给用户的角色列表
		SystemRoleDto systemRoleDto = new SystemRoleDto();
		systemRoleDto.setRoleType(RoleType.sa.getValue());
		systemRoleDto.setOffset(0);
		systemRoleDto.setPageSize(Integer.MAX_VALUE);
		List<SystemRole> systemRoleList = systemRoleService.querySystemRoleList(systemRoleDto);
		modelMap.put("systemRoleList", systemRoleList);
		modelMap.put("systemRoleMap", SystemRoleUtil.getSystemRoleMap(systemRoleList));

		// 新增或编辑商户信息
		modelMap.put("merchant", merchant);
		return merchant.getId() == null ? "merchant/merchantAdd" : "merchant/merchantEdit";
	}

	@RequestMapping("/addMerchant")
	@ResponseBody
	public String addMerchant(HttpServletRequest request, MerchantDto merchantDto) {
		JunhaiAssert.notBlank(merchantDto.getMerchantName(), "商户名称不能为空");
		JunhaiAssert.notBlank(merchantDto.getLoginName(), "登陆账号不能为空");
		JunhaiAssert.notBlank(merchantDto.getPasswd(), "登陆密码不能为空");
		JunhaiAssert.notBlank(merchantDto.getNickname(), "用户昵称不能为空不能为空");
		JunhaiAssert.notBlank(merchantDto.getTelephone(), "手机号码不能为空");

		// 保存商户信息
		Merchant merchant = new Merchant();
		merchant.setMerchantName(merchantDto.getMerchantName());
		merchant.setBusinessLine(merchantDto.getBusinessLine());
		merchant.setRecordNumber(merchantDto.getRecordNumber());
		merchant.setContacts(merchantDto.getContacts());
		merchant.setTelephone(merchantDto.getTelephone());
		merchant.setAddress(merchantDto.getAddress());
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
		systemUser.setPictureUrl("/assets/img/avatars/Osvaldus-Valutis.jpg");
		systemUser.setRoleIds(merchantDto.getRoleIds());
		systemUser.setMerchantId(merchant.getId());
		systemUser.setState(UserState.normal.getValue());
		systemUser.setTelephone(merchantDto.getMobile());
		systemUserService.insert(systemUser);

		return jsonSuccess();
	}

	@RequestMapping("/editMerchant")
	@ResponseBody
	public String editMerchant(HttpServletRequest request, MerchantDto merchantDto) {
		JunhaiAssert.notNull(merchantDto.getId(), "商户ID不能为空");
		JunhaiAssert.notBlank(merchantDto.getMerchantName(), "商户名称不能为空");
		Merchant merchant = merchantService.read(merchantDto.getId());
		JunhaiAssert.notNull(merchant, "商户ID不合法");

		// 更新商户信息
		merchant.setMerchantName(merchantDto.getMerchantName());
		merchant.setBusinessLine(merchantDto.getBusinessLine());
		merchant.setRecordNumber(merchantDto.getRecordNumber());
		merchant.setContacts(merchantDto.getContacts());
		merchant.setTelephone(merchantDto.getTelephone());
		merchant.setAddress(merchantDto.getAddress());
		merchantService.update(merchant);
		return jsonSuccess();
	}

	@RequestMapping("/profile")
	public String profile(HttpServletRequest request, ModelMap modelMap) {
		Integer merchantId = SessionManager.get(request).getMerchantId();
		JunhaiAssert.notNull(merchantId, "商户ID不能为空");

		Merchant merchant = merchantService.read(merchantId);
		modelMap.put("merchant", merchant);
		return "merchant/profile";
	}
}
