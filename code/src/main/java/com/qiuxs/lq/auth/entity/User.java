/*
 * 文件名称: User.java
 * 版权信息: Copyright 2001-2017 qiuxs Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: qiuxs
 * 修改日期: 2017-11-8
 * 修改内容: 
 */
package com.qiuxs.lq.auth.entity;

import com.qiuxs.fdn.entity.BaseEntity;
import com.qiuxs.fdn.entity.IStatus;

/**
 * 用户表对象类
 * @author qiuxs created on 2017-11-8
 * @since
 */
public class User extends BaseEntity<Long> implements IStatus {
    private static final long serialVersionUID = 1L;
	
	/***/		
	private String loginId;
	
	/***/		
	private String password;
	
	/***/		
	private String name;
	
	/***/		
	private Long phone;
	
	/***/		
	private String email;
	
	/***/		
	private String sign;
	
	/***/		
	private Integer status;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public Integer getFlag() {
		return status;
	}
	
	@Override
	public void setFlag(Integer flag) {
		this.status = flag;
	}
}
