package com.ejunhai.qutihuo.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送类
 * 
 * @author she.yj
 * @date 2015-3-20 上午10:01:10
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class SendSms {

	/**
	 * 
	 * @param moblies    目标手机号码，多个以“,”分隔，一次性调用最多100个号码，示例：139********,138********
	 * @param paraMap    短信模板 cid  传入模板参数   p1 等
	 */
	public static void send(String moblies, Map<String, String> paraMap) {

		Map<String, String> para = new HashMap<String, String>();

		/**
		 * 目标手机号码，多个以“,”分隔，一次性调用最多100个号码，示例：139********,138********
		 */
		para.put("mob", moblies);

		/**
		 * 微米账号的接口UID
		 */
		para.put("uid", "8OhaTj2nuZrG");

		/**
		 * 微米账号的接口密码
		 */
		para.put("pas", "5gmkmhyk");

		/**
		 * 接口返回类型：json、xml、txt。默认值为txt
		 */
		para.put("type", "json");

		/**
		 * 短信模板cid，通过微米后台创建，由在线客服审核。必须设置好短信签名，签名规范： <br>
		 * 1、模板内容一定要带签名，签名放在模板内容的最前面；<br>
		 * 2、签名格式：【***】，签名内容为三个汉字以上（包括三个）；<br>
		 * 3、短信内容不允许双签名，即短信内容里只有一个“【】”<br>
		 */
		if (null == paraMap || paraMap.size() == 0) {

			para.put("cid", "5Z0CUu2MlWRo");

			/**
			 * 传入模板参数。<br>
			 * <br>
			 * 短信模板示例：<br>
			 * 【微米网】您的验证码是：%P%，%P%分钟内有效。如非您本人操作，可忽略本消息。<br>
			 * <br>
			 * 传入两个参数：<br>
			 * p1：610912<br>
			 * p2：3<br>
			 * 最终发送内容：<br>
			 * 【微米网】您的验证码是：610912，3分钟内有效。如非您本人操作，可忽略本消息。
			 */
			para.put("p1", "大闸蟹188套餐");
			para.put("p2", "顺丰快递");
			para.put("p2", "SF20150320");

		} else {
			para.putAll(paraMap);
		}

		try {
			System.out.println(HttpClientHelper.convertStreamToString(
					HttpClientHelper.get("http://api.weimi.cc/2/sms/send.html", para), "UTF-8"));

			//			System.out.println(HttpClientHelper.convertStreamToString(
			//					HttpClientHelper.post(
			//							"http://api.weimi.cc/2/sms/send.html", para),
			//					"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//SendSms.send();
	}
}
