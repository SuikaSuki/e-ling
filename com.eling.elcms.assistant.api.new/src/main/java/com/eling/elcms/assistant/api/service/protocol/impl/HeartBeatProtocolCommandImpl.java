package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantStep;
import com.eling.elcms.assistant.api.service.IAssistantStepManager;
import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.util.Contants;

@Service
public class HeartBeatProtocolCommandImpl implements IProtocolCommand{
	
	@Override
	public String getProtocol() {
		return "IWAP03";
	}

	@Autowired
	private IAssistantStepManager assistantstepManager;
	
	@Override
	public String process(String para, String imei) {

		// 06000908000102:060为GSM信号,009为参与定位的卫星数,080为电池电量,0,为保留位,
		// 01为设防状态,02为工作模式 ,(设防,工作模式如果为00,则代表无或未设置)
		String[] paras = para.split(",");

		// String gsm = paras[1];
		// 计步数据
		String stepCount = paras[2];

		// 计步数据不为0 保存计步数据
		if (Integer.valueOf(stepCount) != 0) {
			AssistantStep step = new AssistantStep();
			step.setCreateDate(new Date());
			step.setImei(imei);
			step.setStep(Integer.valueOf(stepCount));
			assistantstepManager.save(step);
		}
		String result = Contants.HEART_BEA_RETURN;
		return result;
	}

}
