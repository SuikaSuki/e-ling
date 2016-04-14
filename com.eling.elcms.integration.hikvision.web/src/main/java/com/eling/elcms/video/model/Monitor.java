package com.eling.elcms.video.model;


public class Monitor {

	private Long pkMonitor;

	/** 摄像头名 */
	private String name;

	/** IP */
	private String ip;

	/** 端口 */
	private String port;

	/** 用户名 */
	private String account;

	/** 连接密码 */
	private String password;
	
	/** 所属位置 */
	private String place;


	public Long getPkMonitor() {
		return pkMonitor;
	}

	public void setPkMonitor(Long pkMonitor) {
		this.pkMonitor = pkMonitor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
