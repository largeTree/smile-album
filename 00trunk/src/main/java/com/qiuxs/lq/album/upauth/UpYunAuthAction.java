package com.qiuxs.lq.album.upauth;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.fdn.exception.utils.ExceptionUtil;

@Service("UpYunAuthAction")
public class UpYunAuthAction {

	/**
	 * 生成参数清单
	 * @param params
	 * @return
	 */
	public ActionResult getPolicy(Map<String, String> params, String jsonParam) {
		if (StringUtils.isBlank(jsonParam)) {
			ExceptionUtil.throwLogicalException("required_param", "jsonParam");
		}
		String policy = Base64.encodeBase64String(jsonParam.getBytes());
		return new ActionResult(policy);
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
