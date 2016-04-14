package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantInfo;
import com.eling.elcms.assistant.api.service.AssistantSender;
import com.eling.elcms.assistant.api.service.IAssistantInfoManager;
import com.eling.elcms.assistant.api.service.IAssistantLocationManager;
import com.eling.elcms.assistant.api.service.IAssistantManager;
import com.eling.elcms.assistant.api.service.IAssistantMessageManager;
import com.eling.elcms.assistant.api.service.IAssistantStepManager;
import com.eling.elcms.assistant.api.service.ProtocolManager;

@Service("default")
public class ProtocolManagerImpl implements ProtocolManager {

	@Autowired
	IAssistantManager assistantManager;
	@Autowired
	private IAssistantInfoManager assistantInfoManager;

	@Autowired
	public IAssistantMessageManager assistantmessageManager;

	@Autowired
	public IAssistantStepManager assistantstepManager;

	@Autowired
	public IAssistantLocationManager assistantLocationManager;

	@Autowired
	public AssistantSender assistantSender;

	@Override
	public String process(String para, String imei) {
		String result = null;
		saveHistory(para, imei, result);
		return result;
	}

	public void saveHistory(String para, String imei, String result) {
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setProtocol(para.substring(0, 6));
		info.setInfo(para);
		info.setReturnInfo(result);
		info.setImei(imei);
		// 保存为history
		assistantInfoManager.save(info);
	}

}
