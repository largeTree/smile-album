package com.qiuxs.lq.auth.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.fdn.bean.UserSession;
import com.qiuxs.fdn.exception.utils.ExceptionUtil;
import com.qiuxs.fdn.utils.MapUtils;
import com.qiuxs.fdn.utils.generator.GUIDGenerator;
import com.qiuxs.fdn.utils.security.MD5Util;
import com.qiuxs.frm.context.UserContext;
import com.qiuxs.lq.auth.entity.User;
import com.qiuxs.lq.auth.service.UserService;

/**
 * 登录
 * @author qiuxs
 *
 */
@Service("LoginAction")
public class LoginAction {

	@Resource
	private UserService userSvc;

	public ActionResult login(Map<String, String> params) {
		String loginId = MapUtils.getStringValueMust(params, "loginId");
		String pass = MapUtils.getStringValueMust(params, "pass");
		User user = userSvc.getByBizKeys(loginId);
		if (user == null) {
			ExceptionUtil.throwLoginException("user_is_not_exists", loginId);
		}

		Map<String, Object> data = new HashMap<String, Object>();

		if (user.getPassword().equals(MD5Util.MD5Encode(pass, Constant.UTF8))) {
			data.put("loginId", user.getLoginId());
			data.put("name", user.getName());
			data.put("sign", user.getSign());
			setUserSession(user);
		} else {
			ExceptionUtil.throwLoginException("pass_error");
		}

		return new ActionResult(data);
	}

	/**
	 * 设置用户会话
	 * @param user
	 */
	private void setUserSession(User user) {
		UserSession session = new UserSession();
		session.setUserId(user.getId());
		session.setSessionId(new GUIDGenerator().toString());
		session.setLoginName(user.getLoginId());
		session.setUserName(user.getName());
		UserContext.setUserSession(session);
	}

}
