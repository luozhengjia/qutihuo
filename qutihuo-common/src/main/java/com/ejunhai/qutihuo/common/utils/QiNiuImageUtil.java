package com.ejunhai.qutihuo.common.utils;

import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * 七牛云存储工具类
 * 
 * @author she.yj
 * @date 2015-3-18 下午3:49:02
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class QiNiuImageUtil {

	public static String getUptoken() {
		String uptoken = "";
		try {
			Mac mac = new Mac(CommonConstant.QINIU_ACCESS_KEY, CommonConstant.QINIU_SECRET_KEY);
			// 请确保该bucket已经存在
			PutPolicy putPolicy = new PutPolicy(CommonConstant.QINIU_BUCKET_NAME);
			uptoken = putPolicy.token(mac);
			PutExtra extra = new PutExtra();
	        String key = "mytest1";
	        String localFile = "d://123.jpg";
	        PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
	        System.out.println("PutRet=== "+ret.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uptoken;
	}

	
	public static void main(String[] args) {
		System.out.print("---------------"+QiNiuImageUtil.getUptoken());
	}
}
