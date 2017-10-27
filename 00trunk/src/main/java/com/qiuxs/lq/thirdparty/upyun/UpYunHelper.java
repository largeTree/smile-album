package com.qiuxs.lq.thirdparty.upyun;

import org.apache.commons.lang3.StringUtils;

import com.qiuxs.core.context.EnvironmentHolder;
import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.utils.security.MD5Util;

/**
 * 又拍云帮助类
 * @author qiuxs
 *
 */
public class UpYunHelper {

	public static final String METHOD_HEAD = "HEAD";
	public static final String METHOD_GET = "GET";
	public static final String METHOD_PUT = "PUT";
	public static final String METHOD_DELETE = "DELETE";

	/** 密码Md5值 */
	private static String passwordMd5;

	/**
	 * 获取操作员
	 * @return
	 */
	public static String getOperator() {
		return EnvironmentHolder.getEnvParam("upyun_operator");
	}

	/**
	 * 获取操作员密码
	 * @return
	 */
	private static String getPassword() {
		return EnvironmentHolder.getEnvParam("upyun_password");
	}

	/**
	 * 获取密码的Md5值
	 * @return
	 */
	public static String getPasswordMd5() {
		if (StringUtils.isBlank(passwordMd5)) {
			passwordMd5 = MD5Util.MD5Encode(getPassword(), Constant.UTF8);
		}
		return passwordMd5;
	}

	/**
	 * 获取当前服务
	 * @return
	 */
	public static String getBucket() {
		return EnvironmentHolder.getEnvParam("upyun_bucket");
	}

	/**
	 * 接入点枚举
	 * @author qiuxs
	 *
	 */
	public enum ApiDomain {

		/** 自动节点 */
		AUTO("v0.api.upyun.com"),
		/**  中国电信 */
		CHINA_NET("v1.api.upyun.com"),
		/** 联通（网通） */
		LT("v2.api.upyun.com"),
		/** 移动（铁通） */
		TT("v3.api.upyun.com");

		/** 地址值 */
		private String value;

		ApiDomain(String val) {
			this.value = val;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}

}
