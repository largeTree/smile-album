/*
 * 文件名称: Album.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-29
 * 修改内容: 
 */
package com.qiuxs.album.biz.entity;

import com.qiuxs.fdn.entity.BaseEntity;

/**
 * 相册表对象类
 * @author qiuxs created on 2017-11-29
 * @since
 */
public class Album extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	/***/
	private String name;

	/***/
	private Integer onlySelf;

	/***/
	private String desc;

	/***/
	private Integer status;

	private String cover;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOnlySelf() {
		return onlySelf;
	}

	public void setOnlySelf(Integer onlySelf) {
		this.onlySelf = onlySelf;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

}
