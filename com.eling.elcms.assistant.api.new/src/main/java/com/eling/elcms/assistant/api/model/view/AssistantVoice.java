package com.eling.elcms.assistant.api.model.view;

import java.util.Date;

public class AssistantVoice {

	//此段留言的唯一标示ID
	private String voiceId;
	
	//语音提醒时间
	private Date date;
	
	//0表示不重复 135表示星期一星期三星期五重复
	private int repetDay;
	
	private String imei;
	
	//返回base64后的String字符串
	private String voice;

	public String getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(String voiceId) {
		this.voiceId = voiceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRepetDay() {
		return repetDay;
	}

	public void setRepetDay(int repetDay) {
		this.repetDay = repetDay;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}
	
 }
