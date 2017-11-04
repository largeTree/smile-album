/*
 * 文件名称: UserService.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-10-30
 * 修改内容: 
 */
package com.qiuxs.lq.auth.service;

import com.qiuxs.lq.auth.dao.UserDao;
import com.qiuxs.lq.auth.entity.User;
import com.qiuxs.lq.auth.service.UserService;
import com.qiuxs.bizfdn.frm.bean.ViewProperty;
import com.qiuxs.bizfdn.frm.bean.BaseField;
import com.qiuxs.bizfdn.frm.bean.ViewIndex;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.qiuxs.frm.service.filter.IServiceFilter;
import com.qiuxs.frm.service.impl.IdServiceFilter;
import org.springframework.stereotype.Service;
import com.qiuxs.bizfdn.frm.service.AbstractService;
import com.qiuxs.frm.dao.paging.PageInfo;
import com.qiuxs.frm.action.ActionConstants;

/**
 * 用户表服务实现类
 * @author qiuxs created on 2017-10-30
 * @since 
 */
@Service("UserService")
public class UserService extends AbstractService<Long, User, UserDao>
	 {
	
	@Resource
	private UserDao userDao;
	
	private final static String TABLE_NAME = "lq_user";
    public final static String VIEW_USER = "user";
	
	/**
     * 构造函数
     */
	public UserService() {
        super();
        this.pojoClass = User.class;
        this.tableName = TABLE_NAME;
        this.description = "用户表";
        ViewIndex.putService(VIEW_USER, this);//, ViewIndex.TOPIC_BASE
    }  
    
    @Override
    public UserDao getDao() {
        return userDao;
    }
    
    @Override
    protected void initCreate(User user) {
    	super.initCreate(user);
    }
   
	/**
	 * 查询有效的用户表对象列表
	 * @param parameters 参数，条件同默认的findByWhere()
	 * @param pageInfo 分页信息
	 * @return
	 */
    public List<User> findValidsByWhere(Map<String, Object> parameters, PageInfo pageInfo) {
    	parameters.put("status" + ActionConstants.THAN_EQUAL_SUFFIX, com.qiuxs.fdn.entity.IStatus.VALID);
    	return super.findByWhere(parameters, pageInfo);
    }
    
    /**
	 * 查询无效或删除的用户表对象列表
	 * @param parameters 参数，条件同默认的findByWhere()
	 * @param pageInfo 分页信息
	 * @return
	 */
    public List<User> findInValidsByWhere(Map<String, Object> parameters, PageInfo pageInfo) {
    	parameters.put("status" + ActionConstants.LESS_EQUAL_SUFFIX, com.qiuxs.fdn.entity.IStatus.INVALID);
    	return super.findByWhere(parameters, pageInfo);
    }

	
    @Override
    protected void initServiceFilters(List<IServiceFilter> filters) {    
        filters.add(new IdServiceFilter<Long, User>(tableName));//为了主键生成
    }

	// -------------------------------- 以下为增删改查表单元数据配置 -------------------------------- //
    @Override
    protected String[] collectQueryProps() {
        return new String[] {"id"};
    }
    
    @Override
    protected String[] collectInputProps() {
    	return new String[] {"id", "loginId", "password", "name", "phone", "email", "sign", "UNIQUE"};
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
        
		prop = new ViewProperty<Object>(new BaseField("createdDate", "createdDate", "Date"), null);
    	props.add(prop);
        
		prop = new ViewProperty<Object>(new BaseField("updatedDate", "updatedDate", "Date"), null);
    	props.add(prop);
        
		prop = new ViewProperty<Object>(new BaseField("UNIQUE", "UNIQUE", "String"), null);
    	props.add(prop);
    }
}
