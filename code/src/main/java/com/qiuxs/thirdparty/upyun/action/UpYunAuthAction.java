package com.qiuxs.thirdparty.upyun.action;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.fdn.utils.MapUtils;
import com.qiuxs.fdn.utils.date.DateFormatUtils;
import com.qiuxs.frm.context.UserContext;
import com.qiuxs.thirdparty.upyun.UpYunHelper;

@Service("UpYunAuthAction")
public class UpYunAuthAction {

	/**
	 * 生成Authorization 
	 * @param params
	 * @return
	 */
	public ActionResult getAuthorization(Map<String, String> params) {
		Long albumId = MapUtils.getLongValueMust(params, "albumId");
		String fileName = MapUtils.getString(params, "fileName");
		Long userId = UserContext.getUserLite().getUserId();

		String uri = userId + "/" + albumId + "/" + fileName;
		String date = DateFormatUtils.format(new Date(), DateFormatUtils.RFC1123_PATTERN);

		String authorization = UpYunHelper.getAuthorization(UpYunHelper.METHOD_PUT, uri, date, null);

		Map<String, Object> data = MapUtils.genMap("authorization", authorization, "uri", uri);

		return new ActionResult(data);
	}

}
