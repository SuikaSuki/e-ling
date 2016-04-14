package com.eling.elcms.monitor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.eling.elcms.core.dao.annotation.In;
import com.eling.elcms.core.model.BaseModel;

/**
 * 摄像头信息
 * 
 * @author swq
 *
 */
@Entity
@Indexed
@Table(name = "video_monitor")
public class Monitor extends BaseModel {
	private static final long serialVersionUID = 3363429135304954303L;

	@Id
	@GeneratedValue
	@DocumentId
	@In
	private Long pkMonitor;

	/** 摄像头名 */
	@Column
	@Field
	private String name;

	/** IP */
	@Column
	@Field
	private String ip;

	/** 端口 */
	@Column
	@Field
	private String port;

	/** 用户名 */
	@Column
	@Field
	private String account;

	/** 连接密码 */
	@Column
	@Field
	private String password;
	
	/**  */
	@Column
	@Field
	private String description;
	
	/** 坐标 */
	@Column
	@Field
	private String coordinate;
	
	/** 摄像头半径 */
	@Column
	@Field
	private Double radius;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}


}
