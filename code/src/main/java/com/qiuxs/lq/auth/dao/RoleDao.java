/*
 * 文件名称: RoleDao.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-17
 * 修改内容: 
 */
package com.qiuxs.lq.auth.dao;
import com.qiuxs.frm.dao.IParentDaoWithBizKeys;
import com.qiuxs.frm.dao.MyBatisRepository;
import com.qiuxs.lq.auth.entity.Role;

/**
 * 用户表Dao接口类
 * 
 * @author qiuxs created on 2017-11-17
 * @since 
 */
 @MyBatisRepository
public interface RoleDao extends IParentDaoWithBizKeys<Integer, Role> {

}
