package com.eling.elcms.assistant.api.model.view;

public class Assistant {

	//助手imei号
	private String imei;
	
	//用户手机号
	private String phone;
	
	//助手的手机号
	private String assistantPhone;
	
	//操作结果
	private String result;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAssistantPhone() {
		return assistantPhone;
	}

	public void setAssistantPhone(String assistantPhone) {
		this.assistantPhone = assistantPhone;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
