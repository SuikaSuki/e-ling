package com.eling.elcms.assistant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.dao.IAssistantLocationDao;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.service.IAssistantLocationManager;
import com.eling.elcms.core.service.impl.GenericManagerImpl;

@Service
public class AssistantLocationManagerImpl extends GenericManagerImpl<AssistantLocation, Long>implements IAssistantLocationManager {

	IAssistantLocationDao assistantLocationDao;
	

	@Autowired
	public void setDao(IAssistantLocationDao dao) {
		this.dao = dao;
		this.assistantLocationDao = dao;
	}
}
