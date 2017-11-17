/*
 * 文件名称: RoleAction.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-17
 * 修改内容: 
 */
package com.qiuxs.lq.auth.action;

import com.qiuxs.lq.auth.entity.Role;
import com.qiuxs.lq.auth.service.RoleService;
import com.qiuxs.lq.auth.dao.RoleDao;
import com.qiuxs.bizfdn.frm.action.BaseAction;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户表入口
 * @author qiuxs created on 2017-11-17
 * @since 
 */
@Service("RoleAction")
public class RoleAction extends BaseAction<Integer, Role, RoleDao, RoleService> {
    
	@Resource
    private RoleService roleService;
    
    @Override
    protected RoleService getService() {
        return roleService;
    }    

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

}
