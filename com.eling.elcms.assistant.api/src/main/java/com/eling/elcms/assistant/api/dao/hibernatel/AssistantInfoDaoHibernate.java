package com.eling.elcms.assistant.api.dao.hibernatel;

import org.springframework.stereotype.Repository;

import com.eling.elcms.assistant.api.dao.IAssistantInfoDao;
import com.eling.elcms.assistant.api.model.AssistantInfo;
import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;

@Repository
public class AssistantInfoDaoHibernate extends GenericDaoHibernate<AssistantInfo, Long> implements IAssistantInfoDao {

	public AssistantInfoDaoHibernate() {
		super(AssistantInfo.class);
	}
}
