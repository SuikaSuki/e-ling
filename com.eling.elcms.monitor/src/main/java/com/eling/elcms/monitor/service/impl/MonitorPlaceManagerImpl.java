package com.eling.elcms.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.core.service.impl.GenericManagerImpl;
import com.eling.elcms.monitor.dao.IMonitorDao;
import com.eling.elcms.monitor.dao.IMonitorPlaceDao;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;
import com.eling.elcms.monitor.service.IMonitorManager;
import com.eling.elcms.monitor.service.IMonitorPlaceManager;

@Service
public class MonitorPlaceManagerImpl extends GenericManagerImpl<MonitorPlace, Long>implements IMonitorPlaceManager {

	IMonitorPlaceDao monitorPlaceDao;
	

	@Autowired
	public void setDao(IMonitorPlaceDao dao) {
		this.dao = dao;
		this.monitorPlaceDao = dao;
	}

	@Override
	public List<MonitorPlace> queryMonitorPlace(MonitorPlace cond) {
		List<MonitorPlace> list = monitorPlaceDao.query(cond);
		return list;
	}

}
