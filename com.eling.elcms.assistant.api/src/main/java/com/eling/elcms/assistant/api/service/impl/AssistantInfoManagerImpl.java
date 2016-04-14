package com.eling.elcms.assistant.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.dao.IAssistantInfoDao;
import com.eling.elcms.assistant.api.model.AssistantInfo;
import com.eling.elcms.assistant.api.service.IAssistantInfoManager;
import com.eling.elcms.core.service.impl.GenericManagerImpl;

@Service
public class AssistantInfoManagerImpl extends GenericManagerImpl<AssistantInfo, Long>implements IAssistantInfoManager {

	IAssistantInfoDao assistantInfoDao;
	

	@Autowired
	public void setDao(IAssistantInfoDao dao) {
		this.dao = dao;
		this.assistantInfoDao = dao;
	}
}
