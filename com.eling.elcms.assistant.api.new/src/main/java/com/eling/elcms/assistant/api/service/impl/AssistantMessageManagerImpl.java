package com.eling.elcms.assistant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.dao.IAssistantMessageDao;
import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.assistant.api.service.IAssistantMessageManager;
import com.eling.elcms.core.service.impl.GenericManagerImpl;

@Service
public class AssistantMessageManagerImpl extends GenericManagerImpl<AssistantMessage, Long>implements IAssistantMessageManager {

	IAssistantMessageDao assistantMessageDao;
	
	@Autowired
	public void setDao(IAssistantMessageDao dao) {
		this.dao = dao;
		this.assistantMessageDao = dao;
	}
}
