package com.eling.elcms.assistant.api.service;

public interface ProtocolManager {

	/**
	 * 根据参数处理相应请求
	 * @param para
	 * @param imei
	 * @return
	 */
	public String process(String para, String imei);
}
