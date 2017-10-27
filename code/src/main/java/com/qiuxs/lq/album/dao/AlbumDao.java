/*
 * 文件名称: AlbumDao.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-10-27
 * 修改内容: 
 */
package com.qiuxs.lq.album.dao;
import com.qiuxs.frm.dao.IParentDAO;
import com.qiuxs.frm.dao.MyBatisRepository;
import com.qiuxs.lq.album.entity.Album;

/**
 * 相册表Dao接口类
 * 
 * @author qiuxs created on 2017-10-27
 * @since 
 */
 @MyBatisRepository
public interface AlbumDao extends IParentDAO<Long, Album> {

}
