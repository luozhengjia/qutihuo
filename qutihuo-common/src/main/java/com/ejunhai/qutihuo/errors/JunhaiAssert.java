package com.ejunhai.qutihuo.errors;

import org.apache.commons.lang.StringUtils;

public class JunhaiAssert {

	private static void throwEx(ErrorType error, Object... message) {
		String msg = error.getTitle();
		if (message != null && message.length > 0) {
			msg = String.valueOf(message[0]);
		}
		throw new BusinessException(error.getValue(), msg);
	}

	/**
	 * 验证对象是否存在，如果不存在，抛出异常
	 * 
	 */
	public static void notNull(Object object, ErrorType error, Object... message) {
		if (object == null) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证对象是否存在，如果不存在，抛出异常
	 */
	public static void notNull(Object object, String paramName) {
		notNull(object, ErrorType.PARAM_EMPTY, paramName);
	}

	/**
	 * 验证对象是否存在，如果存在，抛出异常
	 */
	public static void isNull(Object object, String paramName) {
		isNull(object, ErrorType.PARAMS_ERROR, paramName);
	}

	/**
	 * 验证对象是否存在，如果存在，抛出异常
	 * 
	 */
	public static void isNull(Object object, ErrorType error, Object... message) {
		if (object != null) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证字符串是否存在，如果不存在，抛出异常
	 * 
	 */
	public static void notBlank(String str, String paramName) {
		notBlank(str, ErrorType.PARAM_EMPTY, paramName);
	}

	/**
	 * 验证字符串是否存在，如果不存在，抛出异常
	 */
	public static void notBlank(String str, ErrorType error, Object... message) {
		if (StringUtils.isBlank(str)) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证字符串是否存在，如果存在，抛出异常
	 */
	public static void isBlank(String str, String paramName) {
		isBlank(str, ErrorType.PARAMS_ERROR, paramName);
	}

	/**
	 * 验证字符串是否存在，如果存在，抛出异常
	 * 
	 */
	public static void isBlank(String str, ErrorType error, Object... message) {
		if (StringUtils.isNotBlank(str)) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证是否为true，如果不是，抛出异常
	 */
	public static void isTrue(boolean isTrue, String paramName) {
		isTrue(isTrue, ErrorType.PARAMS_ERROR, paramName);
	}

	/**
	 * 验证是否为true，如果不是，抛出异常
	 * 
	 */
	public static void isTrue(boolean isTrue, ErrorType error, Object... message) {
		if (!isTrue) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证是否为false，如果不是，抛出异常
	 */
	public static void isFalse(boolean isFalse, String paramName) {
		isFalse(isFalse, ErrorType.PARAMS_ERROR, paramName);
	}

	/**
	 * 验证是否为false，如果不是，抛出异常
	 * 
	 */
	public static void isFalse(boolean isFalse, ErrorType error, Object... message) {
		if (isFalse) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证是否超过最大值，如果超过，抛出异常
	 */
	public static void max(int value, int max, String paramName) {
		max(value, max, ErrorType.TOO_LONG, paramName, max);
	}

	/**
	 * 验证是否超过最大值，如果超过，抛出异常
	 */
	public static void max(int value, int max, ErrorType error, Object... message) {
		if (value > max) {
			throwEx(error, message);
		}
	}

	/**
	 * 验证字符串是否相同，如果不是，抛出异常
	 */
	public static void isEquals(String source, String target, String paramName) {
		isEquals(source, target, ErrorType.PARAMS_ERROR, paramName);
	}

	/**
	 * 验证字符串是否相同，如果不是，抛出异常
	 * 
	 */
	public static void isEquals(String source, String target, ErrorType error, Object... message) {
		isTrue(StringUtils.equals(source, target), error, message);
	}
}
