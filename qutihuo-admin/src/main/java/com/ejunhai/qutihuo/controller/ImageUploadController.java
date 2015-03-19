package com.ejunhai.qutihuo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ejunhai.qutihuo.common.base.BaseController;
import com.ejunhai.qutihuo.common.constant.CommonConstant;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * 文件上传处理类
 * @author she.yj
 * @date 2015-3-19 上午8:47:21
 * @version 0.1.0 
 * @copyright yougou.com
 */
@Controller
@RequestMapping("")
public class ImageUploadController extends BaseController {

	/**
	 * 上传文件
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping("imageUpload")
	@ResponseBody
	public String imageUpload(HttpServletRequest request, MultipartFile file, ModelMap modelMap) {
		try {
			//文件名
			String originalFilename = file.getOriginalFilename();
			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) file;
			DiskFileItem diskFile = (DiskFileItem) commonsMultipartFile.getFileItem();
			String filePath = diskFile.getStoreLocation().getAbsolutePath();
			Mac mac = new Mac(CommonConstant.QINIU_ACCESS_KEY, CommonConstant.QINIU_SECRET_KEY);
			// 请确保该bucket已经存在
			PutPolicy putPolicy = new PutPolicy(CommonConstant.QINIU_BUCKET_NAME);
			String uptoken = putPolicy.token(mac);

			PutExtra extra = new PutExtra();
			//存储文件名称
			String key = System.currentTimeMillis() +"_"+ originalFilename;
			PutRet ret = IoApi.putFile(uptoken, key, filePath, extra);
			modelMap.put("imageKey", key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonSuccess(modelMap);
	}

}
