package com.ejunhai.qutihuo.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 通用工具类
 * 
 * @author parcel
 * 
 * @date 2015-1-1
 * 
 */
public class CommonUtils {

	/**
	 * 获取分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int[] getPager(Integer pageNo, Integer pageSize) {
		// 默认20条一页
		pageSize = (pageSize == null || pageSize <= 0) ? 20 : pageSize;

		// 默认页码
		pageNo = (pageNo == null || pageNo <= 0) ? 1 : pageNo;

		// 计算开始位置
		Integer start = (pageNo - 1) * pageSize;

		return new int[] { start, pageSize };
	}

	/**
	 * 获取子列表
	 * 
	 * @param list
	 * @param start
	 * @param limit
	 * @return
	 */
	public static <T> List<T> getSubList(List<T> list, int start, int limit) {
		if (CollectionUtils.isEmpty(list)) {
			return new ArrayList<T>(0);
		}

		int listLength = list.size();
		if (start > listLength - 1) {
			return new ArrayList<T>();
		}

		limit = start + limit > listLength ? listLength - start : limit;
		return list.subList(start, start + limit);
	}

	/**
	 * 将字符串按分隔符分割并转成整型列表
	 * 
	 * @param ids
	 * @param separator
	 * @return
	 */
	public static List<Integer> str2IntList(String ids, String separator) {
		List<Integer> idList = new ArrayList<Integer>();
		if (StringUtils.isBlank(ids)) {
			return idList;
		}

		String[] arrId = ids.split("\\" + separator);
		for (String id : arrId) {
			idList.add(Integer.parseInt(id));
		}
		return idList;
	}
}
