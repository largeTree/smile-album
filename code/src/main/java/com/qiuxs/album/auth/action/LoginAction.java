package com.qiuxs.album.auth.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiuxs.album.auth.entity.Role;
import com.qiuxs.album.auth.entity.User;
import com.qiuxs.album.auth.service.RoleService;
import com.qiuxs.album.auth.service.UserService;
import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.fdn.bean.UserSession;
import com.qiuxs.fdn.exception.utils.ExceptionUtil;
import com.qiuxs.fdn.utils.ListUtils;
import com.qiuxs.fdn.utils.MapUtils;
import com.qiuxs.fdn.utils.generator.GUIDGenerator;
import com.qiuxs.fdn.utils.security.MD5Util;
import com.qiuxs.frm.context.UserContext;
import com.qiuxs.gateway.controller.DefaultApiGatewayController;

/**
 * 登录
 * @author qiuxs
 *
 */
@Service("LoginAction")
public class LoginAction {

	@Resource
	private UserService userSvc;

	@Resource
	private RoleService roleSvc;

	public ActionResult login(Map<String, String> params) {
		String loginId = MapUtils.getStringValueMust(params, "loginId");
		String pass = MapUtils.getStringValueMust(params, "pass");
		User user = userSvc.getByBizKeys(loginId);
		if (user == null) {
			ExceptionUtil.throwLoginException("user_is_not_exists", loginId);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		if (user.getPassword().equals(MD5Util.MD5Encode(pass, Constant.UTF8))) {
			setUserSession(user, data);
		} else {
			ExceptionUtil.throwLoginException("pass_error");
		}

		return new ActionResult(data);
	}

	/**
	 * 设置用户会话
	 * @param user
	 */
	private void setUserSession(User user, Map<String, Object> data) {
		UserSession session = new UserSession();
		session.setUserId(user.getId());
		session.setSessionId(new GUIDGenerator().toString());
		session.setLoginName(user.getLoginId());
		session.setUserName(user.getName());
		session.setDsId(user.getDsId());
		List<Integer> roleIds = ListUtils.stringToIntegerList(user.getRoleIds());
		List<Role> roles = roleSvc.getByIds(roleIds);
		if (roles == null || roles.size() == 0) {
			ExceptionUtil.throwLoginException("user_role_error");
		}
		session.setRoleIds(ListUtils.toArray(roleIds));
		String[] roleNames = new String[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			Role role = roles.get(i);
			roleNames[i] = role.getName();
		}
		session.setRoleNames(roleNames);
		UserContext.setUserSession(session);
		data.put("loginId", user.getLoginId());
		data.put("name", user.getName());
		data.put("sign", user.getSign());
		data.put(DefaultApiGatewayController.SESSIONID_KEY, session.getSessionId());
	}

}
