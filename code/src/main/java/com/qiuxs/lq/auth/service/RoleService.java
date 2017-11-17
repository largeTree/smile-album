/*
 * 文件名称: RoleService.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-17
 * 修改内容: 
 */
package com.qiuxs.lq.auth.service;

import com.qiuxs.lq.auth.dao.RoleDao;
import com.qiuxs.lq.auth.entity.Role;
import com.qiuxs.lq.auth.service.RoleService;
import com.qiuxs.bizfdn.frm.bean.ViewProperty;
import com.qiuxs.bizfdn.frm.bean.BaseField;
import com.qiuxs.bizfdn.frm.bean.ViewIndex;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.qiuxs.frm.service.filter.IServiceFilter;
import com.qiuxs.frm.service.impl.IdServiceFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qiuxs.bizfdn.frm.service.AbstractService;

/**
 * 用户表服务实现类
 * @author qiuxs created on 2017-11-17
 * @since 
 */
@Service("RoleService")
public class RoleService extends AbstractService<Integer, Role, RoleDao>
	 {
	
	@Resource
	private RoleDao roleDao;
	
	private final static String TABLE_NAME = "lq_role";
    public final static String VIEW_ROLE = "role";
	
	/**
     * 构造函数
     */
	public RoleService() {
        super();
        this.pojoClass = Role.class;
        this.tableName = TABLE_NAME;
        this.description = "用户表";
        ViewIndex.putService(VIEW_ROLE, this);//, ViewIndex.TOPIC_BASE
    }  
    
    @Override
    public RoleDao getDao() {
        return roleDao;
    }
    
    @Override
    protected void initCreate(Role role) {
    	super.initCreate(role);
    }
    
    @Override
	@Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
    public void create(Role role) {
        if (!this.isExistByBizKeys(role.getCode())) {
            super.create(role);
        }        
        else
            throw new RuntimeException("指定的信息已经存在!");
    }
    
    /**
     * 根据业务主键查询数据，返回单条记录
     * @return
     */
    public Role getByBizKeys(String code) {
        return super.getByBizKeysInner("code", code);
    }
    
    /**
     * 根据业务主键修改数据
     */
	@Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
    public void updateByBizKeys(Role object) {
        super.updateByBizKeys(object);
    }
    
    /**
     * 根据业务主键删除数据
     */
    @Transactional(rollbackFor = { java.lang.RuntimeException.class, java.lang.Exception.class })
    public void deleteByBizKeys(String code) {
        Role bean = getByBizKeys(code);
        if (bean != null)
        	delete(bean);
    }
    
    /**
     * 是否存在指定的业务主键记录
     * @return
     */
    public boolean isExistByBizKeys(String code) {
        return super.isExistByBizKeysInner("code", code);
    }

	
    @Override
    protected void initServiceFilters(List<IServiceFilter> filters) {    
        filters.add(new IdServiceFilter<Integer, Role>(tableName));//为了主键生成
    }

	// -------------------------------- 以下为增删改查表单元数据配置 -------------------------------- //
    @Override
    protected String[] collectQueryProps() {
        return new String[] {"id"};
    }
    
    @Override
    protected String[] collectInputProps() {
    	return new String[] {"id", "code", "name"};
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
        
		prop = new ViewProperty<Object>(new BaseField("id", "编号", "Integer"), null);    
    	props.add(prop);
        
		prop = new ViewProperty<Object>(new BaseField("code", "code", "String"), null);
    	props.add(prop);
        
		prop = new ViewProperty<Object>(new BaseField("name", "name", "String"), null);
    	props.add(prop);
    }
}
