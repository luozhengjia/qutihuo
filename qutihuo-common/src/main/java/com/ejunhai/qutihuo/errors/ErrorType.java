package com.ejunhai.qutihuo.errors;

public enum ErrorType {

	// 系统级别异常
	PARAM_EMPTY(5000001, "参数%s错误"), 
	PARAMS_ERROR(5000002, "%s不能为空哦"), 
	OBJ_IS_EMPTY(5000003, "对象%s不存在"), 
	TOO_LONG(5000004, "%s最多只能输入%s字哦"), 
	SYSTEM_BUSY(5000005, "系统繁忙，请稍后再试！"),
	SYSTEM_FORBIDDEN(5000006, "没有操作权限！"),
	
	// 用户模块
	SYSTEM_USER_LOGIN_NAME_INVALID(5001001, "用户账号无效"),
	SYSTEM_USER_LOGIN_PWD_INVALID(5001002, "用户密码无效"),
	SYSTEM_USER_VALIDATE_CODE_INVALID(5001003, "验证码无效"),
	SYSTEM_USER_STATE_LOCK(5001004, "用户已锁定");

	private ErrorType(Integer flag, String title) {
		this.flag = flag;
		this.title = title;
	}

	private Integer flag;

	private String title;

	public Integer getValue() {
		return flag;
	}

	public String getTitle() {
		return title;
	}

	public static ErrorType get(Integer flag) {
		for (ErrorType temp : ErrorType.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
