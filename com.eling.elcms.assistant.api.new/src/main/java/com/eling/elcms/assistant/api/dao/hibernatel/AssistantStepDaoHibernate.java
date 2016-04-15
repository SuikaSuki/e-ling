package com.eling.elcms.assistant.api.dao.hibernatel;

import org.springframework.stereotype.Repository;

import com.eling.elcms.assistant.api.dao.IAssistantStepDao;
import com.eling.elcms.assistant.api.model.AssistantStep;
import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;

@Repository
public class AssistantStepDaoHibernate extends GenericDaoHibernate<AssistantStep, Long> implements IAssistantStepDao {

	public AssistantStepDaoHibernate() {
		super(AssistantStep.class);
	}
}
