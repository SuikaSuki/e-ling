package com.eling.elcms.assistant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.dao.IAssistantInfoDao;
import com.eling.elcms.assistant.api.model.AssistantLog;
import com.eling.elcms.assistant.api.service.IAssistantLogManager;
import com.eling.elcms.core.service.impl.GenericManagerImpl;

@Service
public class AssistantLogManagerImpl extends GenericManagerImpl<AssistantLog, Long>implements IAssistantLogManager {

	IAssistantInfoDao assistantInfoDao;
	

	@Autowired
	public void setDao(IAssistantInfoDao dao) {
		this.dao = dao;
		this.assistantInfoDao = dao;
	}
}
