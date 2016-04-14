package com.eling.elcms.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.core.dao.hibernate.search.SearchCondition;
import com.eling.elcms.core.service.impl.GenericManagerImpl;
import com.eling.elcms.monitor.dao.IMonitorDao;
import com.eling.elcms.monitor.dao.IMonitorPlaceDao;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;
import com.eling.elcms.monitor.service.IMonitorManager;

@Service
public class MonitorManagerImpl extends GenericManagerImpl<Monitor, Long>implements IMonitorManager {

	IMonitorDao monitorDao;
	

	@Autowired
	public void setDao(IMonitorDao dao) {
		this.dao = dao;
		this.monitorDao = dao;
	}

	@Override
	public List<Monitor> queryMonitor(Monitor cond) {
		List<Monitor> list = monitorDao.query(cond);
		return list;
	}
	@Override
	public List<Monitor> sarchMonitor(Monitor cond) {
		SearchCondition sc = new SearchCondition ();
		sc.setS("select m from Monitor ");
		sc.setSearchProperties(new String[]{"coordinate"});
		List<Monitor> list = monitorDao.search(sc);
		return list;
	}

}
