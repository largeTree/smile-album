package com.qiuxs.thirdparty.upyun.action;

import java.util.Calendar;
import java.util.Locale;
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
		Long userId = UserContext.getUserLite().getUserId();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, -8);
		String date = DateFormatUtils.format(cal.getTime(), "EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		System.out.println(date);
		String saveKey = userId + "/" + albumId + "/" + "{filename}-{year}-{mon}-{day}-{hour}:{min}:{sec}{.suffix}";
		// 延迟到30分钟之后
		// 过期时间
		String expiration = String.valueOf(cal.getTimeInMillis() + (30 * 60 * 1000));
		// 参数清单
		String policy = UpYunHelper.getPolicy(UpYunHelper.getBucket(), saveKey, expiration, date, null);
		// 目标服务
		String uri = UpYunHelper.getBucket();
		String authorization = UpYunHelper.getAuthorization(UpYunHelper.METHOD_POST, "/" + uri, date, policy, null);

		Map<String, Object> data = MapUtils.genMap("authorization", authorization, "bucket", UpYunHelper.getBucket(), "policy", policy);

		return new ActionResult(data);
	}
}
