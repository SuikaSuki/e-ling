package com.eling.elcms.assistant.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.eling.elcms.core.dao.annotation.Between;
import com.eling.elcms.core.model.BaseModel;

/**
 * 助手Socket信息历史
 * 
 * @author swq
 *
 */
@Entity
@Table(name = "assistant_log",
indexes = {@Index(name = "imei", columnList = "imei"),@Index(name = "protocol", columnList = "protocol")})
public class AssistantLog extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5171841651566636051L;

	@Id
	@GeneratedValue
	private Long pkAssistantInfo;

	/** 协议号  */
	@Column
	private String protocol;

	/** 通信内容（不包含音频数据）  */
	@Column(length = 400) 
	private String info;
	
	/** 服务端的回复内容（不包含音频数据）  */
	@Column(length = 400) 
	private String returnInfo;


	/** IMEI号  */
	@Column
	private String imei;

	/** 连接日期日期 */
	@Column(nullable = false)
	@Between
	private Date createDate;
	
	public Long getPkAssistantInfo() {
		return pkAssistantInfo;
	}

	public void setPkAssistantInfo(Long pkAssistantInfo) {
		this.pkAssistantInfo = pkAssistantInfo;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}


}
