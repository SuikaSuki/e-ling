package com.eling.elcms.assistant.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantLog;
import com.eling.elcms.assistant.api.service.AssistantSender;
import com.eling.elcms.assistant.api.service.IAssistantLogManager;
import com.eling.elcms.assistant.api.service.IAssistantManagerTest;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.core.util.PropertyUtils;

@Service
public class AssistantManagerTestImpl implements IAssistantManagerTest	{
	
	@Autowired
	private AssistantSender assistantSender;
	@Autowired
	private IAssistantLogManager assistantLogManager;

	@Override
	public List<AssistantLog> getLocations(String imei) {
		AssistantLog cond = new AssistantLog();
		cond.setProtocol(Contants.LOCATION);
		cond.setImei(imei);

		return assistantLogManager.query(cond);
	}


	@Override
	public List<AssistantLog> getSteps(String imei) {
		AssistantLog cond = new AssistantLog();
		PropertyUtils.setProperty(cond, "orderString", "createDate:desc");
		cond.setProtocol(Contants.HEART_BEAT);
		cond.setImei(imei);

		return assistantLogManager.query(cond);
	}

	@Override
	public boolean reset(String imei) {
		// para:IWBP17,353456789012345,080835#
		String para = Contants.RESET_FACTORY + "," + imei + ",080835";
		String result = assistantSender.callApi(para, imei);
		// 保存历史
		AssistantLog info = new AssistantLog();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para);
		info.setProtocol(Contants.RESET_FACTORY);
		assistantLogManager.save(info);
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}

}
