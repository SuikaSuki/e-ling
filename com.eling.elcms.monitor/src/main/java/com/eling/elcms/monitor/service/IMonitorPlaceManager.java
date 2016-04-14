package com.eling.elcms.monitor.service;

import java.util.List;

import com.eling.elcms.core.service.IGenericManager;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;

public interface IMonitorPlaceManager extends IGenericManager <MonitorPlace,Long>{
	List<MonitorPlace> queryMonitorPlace(MonitorPlace cond);
}
