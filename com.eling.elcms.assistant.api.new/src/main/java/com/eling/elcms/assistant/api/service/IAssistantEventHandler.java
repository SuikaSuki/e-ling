package com.eling.elcms.assistant.api.service;

import com.eling.elcms.assistant.api.model.view.Coordinate;

public interface IAssistantEventHandler {

	/**
	 *  SOS报警推送
	 * @param location,包含imei和定位数据
	 * 
	 */
	void alarmSos(Coordinate location,String imei);
}
