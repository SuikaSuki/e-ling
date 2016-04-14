package com.eling.elcms.monitor.model.view;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import com.eling.elcms.monitor.model.Monitor;

public class MonitorInfo {

	private Monitor monitor ;
	
	private String serverUrl;
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public String getBase64UrlSub() throws UnsupportedEncodingException{
		return Base64.getUrlEncoder().encodeToString((getMonitor().getIp()+"/"+getMonitor().getPort()+"/"+getMonitor().getAccount()+"/"+getMonitor().getPassword()+"/sub/"+getMonitor().getName()+getMonitor().getDescription()).getBytes("utf-8"));
	}

	public String getBase64UrlMain() throws UnsupportedEncodingException{
		return Base64.getUrlEncoder().encodeToString((getMonitor().getIp()+"/"+getMonitor().getPort()+"/"+getMonitor().getAccount()+"/"+getMonitor().getPassword()+"/main/"+getMonitor().getName()+getMonitor().getDescription()).getBytes("utf-8"));
	}

}
