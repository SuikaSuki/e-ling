package com.eling.elcms.assistant.api.dao.hibernatel;

import org.springframework.stereotype.Repository;

import com.eling.elcms.assistant.api.dao.IAssistantMessageDao;
import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;

@Repository
public class AssistantMessageDaoHibernate extends GenericDaoHibernate<AssistantMessage, Long>
		implements IAssistantMessageDao {

	public AssistantMessageDaoHibernate() {
		super(AssistantMessage.class);
	}
}
