package com.qiuxs.lq.thirdparty.upyun;

public class UpYun {
	
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
