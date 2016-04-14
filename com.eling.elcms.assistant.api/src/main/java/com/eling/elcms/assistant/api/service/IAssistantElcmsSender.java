package com.eling.elcms.assistant.api.service;

import com.eling.elcms.assistant.api.model.AssistantLocation;

public interface IAssistantElcmsSender {

	/**
	 *  SOS报警推送
	 * @param location,包含imei和定位数据
	 * @return
	 */
	boolean alarmSos(AssistantLocation location);
}
