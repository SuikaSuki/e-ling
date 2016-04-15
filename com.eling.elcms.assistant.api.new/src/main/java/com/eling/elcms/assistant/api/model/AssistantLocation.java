package com.eling.elcms.assistant.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.search.annotations.DocumentId;

import com.eling.elcms.core.dao.annotation.Between;
import com.eling.elcms.core.model.BaseModel;

/**
 * 助手Socket信息历史(定位相关信息)
 * 
 * @author swq
 *
 */
@Entity
@Table(name = "assistant_location",
indexes = {@Index(name = "imei", columnList = "imei"),@Index(name = "protocol", columnList = "protocol")})
public class AssistantLocation extends BaseModel {


	private static final long serialVersionUID = 4616222237375585896L;
	@Id
	@GeneratedValue
	@DocumentId
	private Long pkAssistantLocation;
	
	/** IMEI号  */
	@Column
	private String imei;
	
	/** 协议号  */
	@Column
	private String protocol;
	
	/** 经度*/
	@Column
	private String longitude;
	
	/** 纬度*/
	@Column
	private String latitude;
	
	
	/** 坐标半径*/
	@Column
	private String radius;
	
	/** 连接日期日期 */
	@Column(nullable = false)
	@Between
	private Date createDate;


	public Long getPkAssistantLocation() {
		return pkAssistantLocation;
	}

	public void setPkAssistantLocation(Long pkAssistantLocation) {
		this.pkAssistantLocation = pkAssistantLocation;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}


}
