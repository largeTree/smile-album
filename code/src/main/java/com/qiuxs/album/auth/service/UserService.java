/*
 * 文件名称: UserService.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-29
 * 修改内容: 
 */
package com.qiuxs.album.auth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiuxs.album.auth.dao.UserDao;
import com.qiuxs.album.auth.entity.User;
import com.qiuxs.bizfdn.frm.bean.BaseField;
import com.qiuxs.bizfdn.frm.bean.ViewIndex;
import com.qiuxs.bizfdn.frm.bean.ViewProperty;
import com.qiuxs.bizfdn.frm.service.AbstractService;
import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.utils.security.MD5Util;
import com.qiuxs.frm.service.filter.IServiceFilter;
import com.qiuxs.frm.service.impl.IdServiceFilter;

/**
 * 用户表服务实现类
 * @author qiuxs created on 2017-11-29
 * @since 
 */
@Service("UserService")
public class UserService extends AbstractService<Long, User, UserDao>
{

	@Resource
	private UserDao userDao;

	private final static String TABLE_NAME = "user";
	public final static String VIEW_USER = "user";

	/**
	 * 构造函数
	 */
	public UserService() {
		super();
		this.pojoClass = User.class;
		this.tableName = TABLE_NAME;
		this.description = "用户表";
		ViewIndex.putService(VIEW_USER, this);// , ViewIndex.TOPIC_BASE
	}

	@Override
	public UserDao getDao() {
		return userDao;
	}

	@Override
	protected void initCreate(User user) {
		super.initCreate(user);
		user.setPassword(MD5Util.MD5Encode(user.getPassword(), Constant.UTF8));
	}

	@Override
	@Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
	public void create(User user) {
		if (!this.isExistByBizKeys(user.getLoginId())) {
			super.create(user);
		}
		else
			throw new RuntimeException("指定的信息已经存在!");
	}

	/**
	 * 根据业务主键查询数据，返回单条记录
	 * @return
	 */
	public User getByBizKeys(String loginId) {
		return super.getByBizKeysInner("loginId", loginId);
	}

	/**
	 * 根据业务主键修改数据
	 */
	@Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
	public void updateByBizKeys(User object) {
		super.updateByBizKeys(object);
	}

	/**
	 * 根据业务主键删除数据
	 */
	@Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
	public void deleteByBizKeys(String loginId) {
		User bean = getByBizKeys(loginId);
		if (bean != null)
			delete(bean);
	}

	/**
	 * 是否存在指定的业务主键记录
	 * @return
	 */
	public boolean isExistByBizKeys(String loginId) {
		return super.isExistByBizKeysInner("loginId", loginId);
	}

	@Override
	protected void initServiceFilters(List<IServiceFilter> filters) {
		filters.add(new IdServiceFilter<Long, User>(tableName));// 为了主键生成
	}

	// -------------------------------- 以下为增删改查表单元数据配置
	// -------------------------------- //
	@Override
	protected String[] collectQueryProps() {
		return new String[] { "id" };
	}

	@Override
	protected String[] collectInputProps() {
		return new String[] { "id", "loginId", "password", "name", "phone", "email", "sign", "roleIds", "dsId" };
	}

	@Override
	protected void initQueryProps(Map<String, ViewProperty<?>> queryPropMap, String viewId) {
		super.initQueryProps(queryPropMap, viewId);
	}

	@Override
	protected void initInputProps(Map<String, ViewProperty<?>> inputPropMap, String viewId) {
		super.initInputProps(inputPropMap, viewId);
	}

	@Override
	protected void initPropMapInner(List<ViewProperty<?>> props) {
		ViewProperty<?> prop = null;

		prop = new ViewProperty<Object>(new BaseField("id", "编号", "Long"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("loginId", "loginId", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("password", "password", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("name", "name", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("phone", "phone", "Long"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("email", "email", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("sign", "sign", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("status", "status", "Integer"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("roleIds", "roleIds", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("dsId", "dsId", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("createdDate", "createdDate", "Date"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("updatedDate", "updatedDate", "Date"), null);
		props.add(prop);
	}
}
