package com.qiuxs.lq.upauth.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.fdn.exception.utils.ExceptionUtil;
import com.qiuxs.fdn.utils.converter.JsonUtils;
import com.qiuxs.fdn.utils.date.DateFormatUtils;
import com.qiuxs.fdn.utils.security.SecurityUtil;
import com.qiuxs.lq.thirdparty.upyun.UpYunHelper;

@Service("UpYunAuthAction")
public class UpYunAuthAction {

	/** 上传图片操作 */
	private static final String ACTION_UPLOAD = "upload";

	/**
	 * 生成参数清单和签名
	 * @param params
	 * @return
	 */
	public ActionResult getPolicyAndSignature(Map<String, String> params, String jsonData) {
		if (StringUtils.isBlank(jsonData)) {
			ExceptionUtil.throwLogicalException("required_param", "jsonParam");
		}
		JSONObject jsonParams = JsonUtils.toJSONObject(jsonData);
		String action = params.get("action");
		if (ACTION_UPLOAD.equals(action)) {
			Map<String, Object> data = new HashMap<String, Object>();
			// 设置服务
			jsonParams.put("bucket", UpYunHelper.getBucket());
			// 超时时间 向后30分钟
			jsonParams.put("expiration", System.currentTimeMillis() + (1000 * 60 * 30));
			jsonParams.put("date", DateFormatUtils.format(new Date(), DateFormatUtils.RFC1123_PATTERN));
			String policy = Base64.encodeBase64String(jsonParams.toJSONString().getBytes());
			data.put("policy", policy);

			// 生成签名
			StringBuilder encryptKey = new StringBuilder();
			encryptKey.append(UpYunHelper.METHOD_PUT)
					.append("&").append("");
			String signature = Base64.encodeBase64String(SecurityUtil.hmacSHA1EncryptBytes(UpYunHelper.getPasswordMd5(), encryptKey.toString()));
			data.put("signature", signature);
			return new ActionResult(data);
		}
		throw ExceptionUtil.newLogicalException("action_is_invalid");
	}

	/**
	 * 生成签名
	 * @param params
	 * @return
	 */
	public ActionResult getSignature(Map<String, String> params) {
		String signature = "";
		return new ActionResult(signature);
	}

}
