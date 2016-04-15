package com.eling.elcms.assistant.api.service;

import java.util.List;

import com.eling.elcms.assistant.api.model.AssistantLog;

public interface IAssistantManagerTest {
	
	
	/**
	 * 取得所有定位信息IWAP01(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	List<AssistantLog> getLocations(String imei);
	
	/**
	 * 取得所有心跳包信息IWAP03(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	List<AssistantLog> getSteps(String imei);
	
	/**
	 * 恢复出厂设置(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	boolean reset(String imei);
}
