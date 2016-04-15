package com.eling.elcms.assistant.api.service.protocol.impl;

import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.util.Contants;

@Service
@SuppressWarnings("unused")
public class LowPowerProtocolCommandImpl implements IProtocolCommand{
	
	@Override
	public String getProtocol() {
		return "IWAP04";
	}
	public String process(String para, String imei) {
		// 075：电池电量低于百分之75 , 电量在低于75%，50%，25%，10%的时候出发该电量状态上报报警包，用于平台获取电量信息
		String power = para.substring(6, 9);
		// 计步数据
		String result = Contants.LOW_POWER_RETURN;
		return result;
	}

}
