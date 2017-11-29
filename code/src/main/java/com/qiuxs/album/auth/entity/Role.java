/*
 * 文件名称: Role.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-29
 * 修改内容: 
 */
package com.qiuxs.album.auth.entity;

import com.qiuxs.fdn.entity.BaseEntity;

/**
 * 角色表对象类
 * @author qiuxs created on 2017-11-29
 * @since
 */
public class Role extends BaseEntity<Integer> {
    private static final long serialVersionUID = 1L;
	
	/***/		
	private String code;
	
	/***/		
	private String name;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
