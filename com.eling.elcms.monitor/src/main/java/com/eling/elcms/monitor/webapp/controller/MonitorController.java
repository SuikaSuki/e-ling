package com.eling.elcms.monitor.webapp.controller;

import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eling.elcms.core.webapp.controller.BaseFormController;
import com.eling.elcms.monitor.model.Monitor;
import com.eling.elcms.monitor.model.MonitorPlace;
import com.eling.elcms.monitor.model.view.MonitorInfo;
import com.eling.elcms.monitor.service.IMonitorManager;
import com.eling.elcms.monitor.service.impl.MonitorPlaceManagerImpl;

@Controller
public class MonitorController extends BaseFormController{
	
	@Value("${monitor.ip}")
	private  String monitorIp;
	@Value("${monitor.port}")
	private  String monitorPort;
	
	@Autowired
	private IMonitorManager monitorManager;
	
	@Autowired
	private MonitorPlaceManagerImpl monitorPlaceManager;
	
	//@Autowired
	//private Constant constant;
	
	@RequestMapping("/api/monitor/query")
    @ResponseBody
    public List<MonitorInfo> query(@ModelAttribute Monitor monitor) {
		List<MonitorInfo> infos= new ArrayList<MonitorInfo>();
		List<Monitor> monitors = monitorManager.queryMonitor(monitor);
		for (int i = 0; i < monitors.size(); i++) {
			MonitorInfo info = new MonitorInfo();
			info.setMonitor(monitors.get(i));
			info.setServerUrl(monitorIp+":"+monitorPort);
			infos.add(info);
		}
		//String a = constant.monitorIp;
    	return infos;
    }

	@RequestMapping("/api/monitor/queryUrl")
    @ResponseBody
    public String queryUrl(@ModelAttribute MonitorPlace monitorPlace) {
		if (!StringUtils.isNotBlank(monitorPlace.getPkPlace()) && 
				(monitorPlace.getMonitor()== null || monitorPlace.getMonitor().getCoordinate()==null)) {
			return null;
		}
		List<Monitor> monitors = new ArrayList<Monitor>();
		//固定报警
		if(StringUtils.isNotBlank(monitorPlace.getPkPlace())){
			List<MonitorPlace> monitorPlaces= monitorPlaceManager.queryMonitorPlace(monitorPlace);
			
			for (int i = 0; i < monitorPlaces.size(); i++) {
				monitors.add(monitorPlaces.get(i).getMonitor());
			}
		}else{//移动报警
			//取得所有摄像头信息
			monitors = new ArrayList<Monitor>();
			List<Monitor> monitorResults= monitorManager.query(new Monitor());
			for (int i = 0; i < monitorResults.size(); i++) {
				//有坐标且有半径
				if(StringUtils.isNoneBlank(monitorResults.get(i).getCoordinate()) &&
						monitorResults.get(i).getRadius()!=null){
					//取得在坐标半径内的摄像头
					String coorDinate = monitorPlace.getMonitor().getCoordinate();
					//取得参数的XY
					Double targetCoorDinateX = Double.valueOf(coorDinate.split(",")[0]);
					Double targetCoorDinateY = Double.valueOf(coorDinate.split(",")[1]);
					//取得摄像头的XY
					Double monitorCoorDinateX = Double.valueOf(monitorResults.get(i).getCoordinate().split(",")[0]);
					Double monitorCoorDinateY = Double.valueOf(monitorResults.get(i).getCoordinate().split(",")[1]);
					double distance = Point2D.distance(targetCoorDinateX, targetCoorDinateY, monitorCoorDinateX, monitorCoorDinateY);
					if(distance<=monitorResults.get(i).getRadius()){
					monitors.add(monitorResults.get(i));}
				}
				
			}
		}
		if(monitors!=null && monitors.size()>0){
			//取得摄像头各项信息 并base64
			StringBuffer ip = new StringBuffer();
			StringBuffer port = new StringBuffer();
			StringBuffer account = new StringBuffer();
			StringBuffer password = new StringBuffer();
			StringBuffer size = new StringBuffer();
			StringBuffer name = new StringBuffer();
			for (int i = 0; i < monitors.size(); i++) {
				ip.append(monitors.get(i).getIp());
				port.append(monitors.get(i).getPort());
				account.append(monitors.get(i).getAccount());
				password.append(monitors.get(i).getPassword());
				size.append("sub");
				name.append(monitors.get(i).getName()+monitors.get(i).getDescription());
				if(i!=monitors.size()-1){
					ip.append(",");
					port.append(",");
					account.append(",");
					password.append(",");
					size.append(",");
					name.append(",");
				}
			}
			String url = ip+"/"+port+"/"+account+"/"+password+"/"+size+"/"+name;
			try {
				return "http://"+monitorIp+":"+monitorPort+"/video/players/"+Base64.getUrlEncoder().encodeToString(url.getBytes("utf-8"))+".do";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
}
