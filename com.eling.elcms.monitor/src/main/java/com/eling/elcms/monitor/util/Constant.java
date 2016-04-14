package com.eling.elcms.monitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class Constant {
	@Value("${monitor.ip}")
	public static String monitorIp;
	@Value("${monitor.port}")
	public static String monitorPort;

	private static final Logger log = LoggerFactory.getLogger(Constant.class);
    private static final String fileProperties = "/config/monitor.properties";
	public Constant(){
		
		if(monitorIp == null || monitorPort == null){
			try {
				Properties properties = new Properties();
				String tpath = Constant.class.getResource("/").getFile();
				
				tpath = tpath.substring(0, tpath.lastIndexOf("classes"));
				String configPath = tpath + fileProperties;
				
				InputStream file = new FileInputStream(new File(configPath));
				properties.load(file);
				
				monitorIp = (String) properties.get("monitor.ip");
				monitorPort = (String) properties.get("monitor.port");
				
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			
		}
	}
	public static String getMonitorIp() {
		if(monitorIp == null || monitorPort == null){
			try {
				Properties properties = new Properties();
				String tpath = Constant.class.getResource("/").getFile();
				
				tpath = tpath.substring(0, tpath.lastIndexOf("classes"));
				String configPath = tpath + fileProperties;
				
				InputStream file = new FileInputStream(new File(configPath));
				properties.load(file);
				
				monitorIp = (String) properties.get("monitor.ip");
				monitorPort = (String) properties.get("monitor.port");
				
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			
		}
		return monitorIp;
	}
	public static void setMonitorIp(String monitorIp) {
		Constant.monitorIp = monitorIp;
	}
	public static String getMonitorPort() {
		if(monitorIp == null || monitorPort == null){
			try {
				Properties properties = new Properties();
				String tpath = Constant.class.getResource("/").getFile();
				
				tpath = tpath.substring(0, tpath.lastIndexOf("classes"));
				String configPath = tpath + fileProperties;
				
				InputStream file = new FileInputStream(new File(configPath));
				properties.load(file);
				
				monitorIp = (String) properties.get("monitor.ip");
				monitorPort = (String) properties.get("monitor.port");
				
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
			
		}
		return monitorPort;
	}
	public static void setMonitorPort(String monitorPort) {
		Constant.monitorPort = monitorPort;
	}
	
	
}
