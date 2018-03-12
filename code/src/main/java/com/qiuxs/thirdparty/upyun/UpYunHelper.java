package com.qiuxs.thirdparty.upyun;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.qiuxs.core.context.EnvironmentHolder;
import com.qiuxs.fdn.Constant;
import com.qiuxs.fdn.utils.security.MD5Util;
import com.qiuxs.fdn.utils.security.SecurityUtil;

/**
 * 又拍云帮助类
 * @author qiuxs
 *
 */
public class UpYunHelper {

	public static final String Authorization_PREFIX = "UPYUN";

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
	 * 生成Authorization 
	 * @param method
	 * @param uri
	 * @param date
	 * @param contentMd5
	 * @return
	 */
	public static String getAuthorization(String method, String uri, String date, String contentMd5) {
		return Authorization_PREFIX + " " + getOperator() + ":" + getSignature(method, uri, date, contentMd5);
	}

	/**
	 * 生成签名
	 * @param method
	 * @param uri
	 * @param date
	 * @param contentMd5
	 * @return
	 */
	private static String getSignature(String method, String uri, String date, String contentMd5) {
		StringBuilder sb = new StringBuilder();
		sb.append(method).append("&").append(uri).append("&").append(date);
		if (StringUtils.isNotBlank(contentMd5)) {
			sb.append("&").append(contentMd5);
		}
		return Base64.encodeBase64String(SecurityUtil.hmacSHA1EncryptBytes(getPasswordMd5(), sb.toString()));
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
