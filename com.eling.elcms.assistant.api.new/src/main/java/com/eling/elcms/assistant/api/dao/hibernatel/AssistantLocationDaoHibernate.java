package com.eling.elcms.assistant.api.dao.hibernatel;

import org.springframework.stereotype.Repository;

import com.eling.elcms.assistant.api.dao.IAssistantLocationDao;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;

@Repository
public class AssistantLocationDaoHibernate extends GenericDaoHibernate<AssistantLocation, Long>
		implements IAssistantLocationDao {

	public AssistantLocationDaoHibernate() {
		super(AssistantLocation.class);
	}
}
