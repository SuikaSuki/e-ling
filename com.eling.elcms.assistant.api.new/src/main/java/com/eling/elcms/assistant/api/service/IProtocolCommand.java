package com.eling.elcms.assistant.api.service;

public interface IProtocolCommand {
	
	public String getProtocol();

	/**
	 * 根据参数处理相应请求
	 * @param para
	 * @param imei
	 * @return 助手协议上行结果的服务器响应
	 */
	public String process(String para, String imei);
}
