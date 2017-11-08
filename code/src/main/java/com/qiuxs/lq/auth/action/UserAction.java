/*
 * 文件名称: UserAction.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-8
 * 修改内容: 
 */
package com.qiuxs.lq.auth.action;

import com.qiuxs.lq.auth.entity.User;
import com.qiuxs.lq.auth.service.UserService;
import com.qiuxs.lq.auth.dao.UserDao;
import com.qiuxs.bizfdn.frm.action.BaseAction;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.frm.action.ActionConstants;

/**
 * 用户表入口
 * @author qiuxs created on 2017-11-8
 * @since 
 */
@Service("UserAction")
public class UserAction extends BaseAction<Long, User, UserDao, UserService> {

	@Resource
	private UserService userService;

	/**
	 * 检查帐号是否重复
	 * @param params
	 * @return
	 */
	public ActionResult checkLoginIdExists(Map<String, String> params) {
		super.checkMustParams(params, "loginId");
		return new ActionResult(this.getService().isExistByBizKeys(params.get("loginId")));
	}

	@Override
	protected UserService getService() {
		return userService;
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

	@Override
	public ActionResult list(Map<String, String> reqParam, String jsonData, String listMethod, boolean paging) {
		reqParam.put("status" + ActionConstants.THAN_EQUAL_SUFFIX, String.valueOf(com.qiuxs.fdn.entity.IStatus.VALID));
		return super.list(reqParam, jsonData, listMethod, paging);
	}
}
