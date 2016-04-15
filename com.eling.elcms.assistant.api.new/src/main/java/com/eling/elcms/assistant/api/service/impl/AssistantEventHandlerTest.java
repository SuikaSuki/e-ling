package com.eling.elcms.assistant.api.service.impl;

import com.eling.elcms.assistant.api.model.view.Coordinate;
import com.eling.elcms.assistant.api.service.IAssistantEventHandler;

public class AssistantEventHandlerTest implements IAssistantEventHandler {

	@Override
	public void alarmSos(Coordinate location, String imei) {
		System.out.println("调用");

	}

}
