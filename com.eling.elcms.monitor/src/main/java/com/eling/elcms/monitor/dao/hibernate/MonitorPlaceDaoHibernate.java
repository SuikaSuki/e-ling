package com.eling.elcms.monitor.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.eling.elcms.core.dao.hibernate.GenericDaoHibernate;
import com.eling.elcms.monitor.dao.IMonitorDao;
import com.eling.elcms.monitor.dao.IMonitorPlaceDao;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;

@Repository
public class MonitorPlaceDaoHibernate extends GenericDaoHibernate<MonitorPlace,Long> implements IMonitorPlaceDao{

	public MonitorPlaceDaoHibernate() {
		super(MonitorPlace.class);
	}
}
