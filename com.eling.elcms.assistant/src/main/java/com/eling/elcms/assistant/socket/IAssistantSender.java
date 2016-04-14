package com.eling.elcms.assistant.socket;

import java.util.List;

public interface IAssistantSender {
	boolean sendToAssistant(String imei, String msg);
	List<String> allImei();
}
