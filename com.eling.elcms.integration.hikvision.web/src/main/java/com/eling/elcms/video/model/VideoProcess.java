package com.eling.elcms.video.model;

import com.eling.elcms.video.timer.StreamGobbler;


public class VideoProcess {

	private String caremaName;
	private String caremaIp;
	private String caremaPort;
	private String serverPort;
	private String caremaSize;
	private String caremaAccount;
	private Process process;
	private String lastConnectTime;
	private StreamGobbler errorGobbler;
	private StreamGobbler outputGobbler;
	
	public String getCaremaName() {
		return caremaName;
	}
	public void setCaremaName(String caremaName) {
		this.caremaName = caremaName;
	}
	public String getCaremaIp() {
		return caremaIp;
	}
	public void setCaremaIp(String caremaIp) {
		this.caremaIp = caremaIp;
	}
	public String getCaremaPort() {
		return caremaPort;
	}
	public void setCaremaPort(String caremaPort) {
		this.caremaPort = caremaPort;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public String getLastConnectTime() {
		return lastConnectTime;
	}
	public void setLastConnectTime(String lastConnectTime) {
		this.lastConnectTime = lastConnectTime;
	}
	public StreamGobbler getErrorGobbler() {
		return errorGobbler;
	}
	public void setErrorGobbler(StreamGobbler errorGobbler) {
		this.errorGobbler = errorGobbler;
	}
	public StreamGobbler getOutputGobbler() {
		return outputGobbler;
	}
	public void setOutputGobbler(StreamGobbler outputGobbler) {
		this.outputGobbler = outputGobbler;
	}
	public String getCaremaSize() {
		return caremaSize;
	}
	public void setCaremaSize(String caremaSize) {
		this.caremaSize = caremaSize;
	}
	public String getCaremaAccount() {
		return caremaAccount;
	}
	public void setCaremaAccount(String caremaAccount) {
		this.caremaAccount = caremaAccount;
	}
	
}
