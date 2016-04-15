package com.eling.elcms.assistant.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import com.eling.elcms.core.dao.annotation.Between;
import com.eling.elcms.core.model.BaseModel;

/**
 * 助手留言信息表
 * @author swq
 *
 */
@Entity
@Indexed
@Table(name = "assistant_message",
indexes = {@Index(name = "imei", columnList = "imei"),@Index(name = "protocol", columnList = "protocol")})
public class AssistantMessage  extends BaseModel{

	private static final long serialVersionUID = 3132136905743566167L;
	
	@Id
	@GeneratedValue
	private Long pkAssistantMessage;
	
	
	/** 音频数据base64后的String字符串  */
	@Column(length = 16777215) 
	private String voice;

	/** 协议号 */
	@Column
	private String protocol;

	/** IMEI号  */
	@Column
	private String imei;

	/** 创建时间  */
	@Column
	@Between
	private Date createDate;
	
	public Long getPkAssistantMessage() {
		return pkAssistantMessage;
	}


	public void setPkAssistantMessage(Long pkAssistantMessage) {
		this.pkAssistantMessage = pkAssistantMessage;
	}

	public String getVoice() {
		return voice;
	}


	public void setVoice(String voice) {
		this.voice = voice;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
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
	
	
	
}
