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
 * 助手Socket信息历史(有计步数据的心跳包)
 * 
 * @author swq
 *
 */
@Entity
@Indexed
@Table(name = "assistant_step",
indexes = {@Index(name = "imei", columnList = "imei")})
public class AssistantStep extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7956276208589314801L;
	@Id
	@GeneratedValue
	private Long pkAssistantStep;
	
	/** 步数  */
	@Column
	private Integer step;
	
	/** IMEI号  */
	@Column

	private String imei;
	
	/** 连接日期日期 */
	@Column(nullable = false)
	@Between
	private Date createDate;
	
	
	public Long getPkAssistantStep() {
		return pkAssistantStep;
	}
	public void setPkAssistantStep(Long pkAssistantStep) {
		this.pkAssistantStep = pkAssistantStep;
	}
	
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
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
