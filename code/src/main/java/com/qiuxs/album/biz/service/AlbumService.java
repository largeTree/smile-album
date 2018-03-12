/*
 * 文件名称: AlbumService.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-29
 * 修改内容: 
 */
package com.qiuxs.album.biz.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qiuxs.album.biz.dao.AlbumDao;
import com.qiuxs.album.biz.entity.Album;
import com.qiuxs.bizfdn.frm.bean.BaseField;
import com.qiuxs.bizfdn.frm.bean.ViewIndex;
import com.qiuxs.bizfdn.frm.bean.ViewProperty;
import com.qiuxs.bizfdn.frm.bean.WriteField;
import com.qiuxs.bizfdn.frm.service.AbstractService;
import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.exception.utils.ExceptionUtil;
import com.qiuxs.fdn.utils.NumberUtils;
import com.qiuxs.frm.context.UserContext;
import com.qiuxs.frm.service.filter.IServiceFilter;
import com.qiuxs.frm.service.impl.IdServiceFilter;

/**
 * 相册表服务实现类
 * @author qiuxs created on 2017-11-29
 * @since 
 */
@Service("AlbumService")
public class AlbumService extends AbstractService<Long, Album, AlbumDao>
{

	@Resource
	private AlbumDao albumDao;

	private final static String TABLE_NAME = "album";
	public final static String VIEW_ALBUM = "album";

	/**
	 * 构造函数
	 */
	public AlbumService() {
		super();
		this.pojoClass = Album.class;
		this.tableName = TABLE_NAME;
		this.description = "相册表";
		ViewIndex.putService(VIEW_ALBUM, this);// , ViewIndex.TOPIC_BASE
	}

	@Override
	public AlbumDao getDao() {
		return albumDao;
	}

	@Override
	public Album get(Long pkObj) {
		Album album = super.get(pkObj);
		// 禁止访问非本人创建且非公开的相册
		if (album.getOnlySelf() == Constant.TRUE && NumberUtils.compareTo(album.getCreatedBy(), UserContext.getUserLite().getUserId()) != 0) {
			ExceptionUtil.throwLogicalException("album_access_denied");
		}
		return album;
	}

	@Override
	protected void initCreate(Album album) {
		super.initCreate(album);
	}

	@Override
	protected void initServiceFilters(List<IServiceFilter> filters) {
		filters.add(new IdServiceFilter<Long, Album>(tableName));// 为了主键生成
	}

	// -------------------------------- 以下为增删改查表单元数据配置
	// -------------------------------- //
	@Override
	protected String[] collectQueryProps() {
		return new String[] { "id" };
	}

	@Override
	protected String[] collectInputProps() {
		return new String[] { "id", "name", "onlySelf", "desc" };
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

		prop = new ViewProperty<Object>(new WriteField("name", "name", "String", "相册名称", true), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("onlySelf", "onlySelf", "Integer"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("desc", "desc", "String"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("status", "status", "Integer"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("createdBy", "createdBy", "Long"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("createdDate", "createdDate", "Date"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("updatedBy", "updatedBy", "Long"), null);
		props.add(prop);

		prop = new ViewProperty<Object>(new BaseField("updatedDate", "updatedDate", "Date"), null);
		props.add(prop);
	}

}
