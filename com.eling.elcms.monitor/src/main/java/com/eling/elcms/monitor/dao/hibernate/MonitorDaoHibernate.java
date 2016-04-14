package com.eling.elcms.monitor.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;
import com.eling.elcms.monitor.dao.IMonitorDao;
import com.eling.elcms.monitor.model.Monitor;

@Repository
public class MonitorDaoHibernate extends GenericDaoHibernate<Monitor,Long> implements IMonitorDao{

	public MonitorDaoHibernate() {
		super(Monitor.class);
	}
}
