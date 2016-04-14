package com.eling.elcms.monitor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.eling.elcms.core.dao.annotation.In;
import com.eling.elcms.core.model.BaseModel;


/**
 * 摄像头信息关联表
 * 
 * @author swq
 *
 */
@Entity
@Indexed
@Table(name = "video_monitor_place")
public class MonitorPlace  extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7383231865951490207L;

	@Id
	@GeneratedValue
	@DocumentId
	@In
	private Long pkMonitorPlace;
	
	/** 摄像头*/
	@ManyToOne
	@JoinColumn(name="pkMonitor")
	@IndexedEmbedded
	private Monitor monitor;
	
	
	/** 所属位置 */
	@Column
	@Field
	private String pkPlace;


	public Long getPkMonitorPlace() {
		return pkMonitorPlace;
	}


	public void setPkMonitorPlace(Long pkMonitorPlace) {
		this.pkMonitorPlace = pkMonitorPlace;
	}


	public Monitor getMonitor() {
		return monitor;
	}


	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}


	public String getPkPlace() {
		return pkPlace;
	}


	public void setPkPlace(String pkPlace) {
		this.pkPlace = pkPlace;
	}
	
	
	
}
