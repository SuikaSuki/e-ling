package com.eling.elcms.assistant.socket;

public interface IAssistantHandler {
	void setSender(IAssistantSender sender);

	String messageReceived(String imei, String msg);
}
