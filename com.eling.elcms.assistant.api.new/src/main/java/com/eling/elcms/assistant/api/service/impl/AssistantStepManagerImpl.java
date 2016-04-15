package com.eling.elcms.assistant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.dao.IAssistantStepDao;
import com.eling.elcms.assistant.api.model.AssistantStep;
import com.eling.elcms.assistant.api.service.IAssistantStepManager;
import com.eling.elcms.core.service.impl.GenericManagerImpl;

@Service
public class AssistantStepManagerImpl extends GenericManagerImpl<AssistantStep, Long>implements IAssistantStepManager {

	IAssistantStepDao assistantStepDao;
	@Autowired
	public void setDao(IAssistantStepDao dao) {
		this.dao = dao;
		this.assistantStepDao = dao;
	}
}
