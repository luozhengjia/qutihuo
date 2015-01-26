package com.ejunhai.qutihuo.errors;

/**
 * 业务错误异常。
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 5334801213034608213L;

	/**
	 * 业务CODE码，详情见ResponseCode。
	 */
	private int code;

	public BusinessException() {
		super();
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(int errorCode, String message) {
		super(message);
		this.code = errorCode;
	}

	public BusinessException(int errorCode, String message, Object... args) {
		super(String.format(message, args));
		this.code = errorCode;
	}

	public int getErrorCode() {
		return code;
	}

	public void setErrorCode(int errorCode) {
		this.code = errorCode;
	}

}
