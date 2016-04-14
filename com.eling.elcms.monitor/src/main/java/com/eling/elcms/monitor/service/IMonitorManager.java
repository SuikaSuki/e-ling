package com.eling.elcms.monitor.service;

import java.util.List;

import com.eling.elcms.core.service.IGenericManager;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;

public interface IMonitorManager extends IGenericManager <Monitor,Long>{
	List<Monitor> queryMonitor(Monitor cond);

	List<Monitor> sarchMonitor(Monitor cond);
}
