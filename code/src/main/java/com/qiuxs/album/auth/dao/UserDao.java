/*
 * 文件名称: UserDao.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-29
 * 修改内容: 
 */
package com.qiuxs.album.auth.dao;
import com.qiuxs.album.auth.entity.User;
import com.qiuxs.frm.dao.IParentDaoWithBizKeys;
import com.qiuxs.frm.dao.MyBatisRepository;

/**
 * 用户表Dao接口类
 * 
 * @author qiuxs created on 2017-11-29
 * @since 
 */
 @MyBatisRepository
public interface UserDao extends IParentDaoWithBizKeys<Long, User> {

}
