/*
 * 文件名称: AlbumAction.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-10-27
 * 修改内容: 
 */
package com.qiuxs.album.biz.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiuxs.album.biz.dao.AlbumDao;
import com.qiuxs.album.biz.entity.Album;
import com.qiuxs.album.biz.service.AlbumService;
import com.qiuxs.bizfdn.frm.action.BaseAction;
import com.qiuxs.fdn.bean.ActionResult;
import com.qiuxs.frm.action.ActionConstants;
import com.qiuxs.frm.dao.paging.PageInfo;

/**
 * 相册表入口
 * @author qiuxs created on 2017-10-27
 * @since 
 */
@Service("AlbumAction")
public class AlbumAction extends BaseAction<Long, Album, AlbumDao, AlbumService> {

	@Resource
	private AlbumService albumService;

	@Override
	protected AlbumService getService() {
		return albumService;
	}

	@Override
	protected Class<Album> getEntityClass() {
		return Album.class;
	}

	/**
	 * 公开相册列表
	 * @param params
	 * @return
	 */
	public ActionResult publicList(Map<String, String> params) {
		params.put("onlySelf", "0");
		return super.list(params, null);
	}

	/**
	 * 私有相册列表
	 * @param params
	 * @return
	 */
	public ActionResult privateList(Map<String, String> params) {
		params.put("onlySelf", "1");
		return super.list(params, null);
	}

	@Override
	public ActionResult list(Map<String, String> reqParam, String jsonData, String listMethod, boolean paging) {
		reqParam.put("status" + ActionConstants.THAN_EQUAL_SUFFIX, String.valueOf(com.qiuxs.fdn.entity.IStatus.VALID));
		return super.list(reqParam, jsonData, listMethod, paging);
	}
}
