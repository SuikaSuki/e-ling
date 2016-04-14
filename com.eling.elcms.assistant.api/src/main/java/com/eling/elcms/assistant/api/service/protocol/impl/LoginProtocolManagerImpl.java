package com.eling.elcms.assistant.api.service.protocol.impl;

import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.DateUtils;

@Service("assistantLogin")
public class LoginProtocolManagerImpl extends ProtocolManagerImpl {

	public String process(String para, String imei) {
		// 截取imei号
		String now = DateUtils.getUTC0TimeStr();
		String result = Contants.LOGIN_RETURN + "," + now + ",8";
		saveHistory(para, imei, result);
		return result;
	}

}
