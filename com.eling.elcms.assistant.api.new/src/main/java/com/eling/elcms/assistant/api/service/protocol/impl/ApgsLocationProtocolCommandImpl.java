package com.eling.elcms.assistant.api.service.protocol.impl;

import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.util.Contants;

/**
 * apgs辅助定位（暂无应用）
 * 
 * @author swq
 *
 */
@Service
public class ApgsLocationProtocolCommandImpl implements IProtocolCommand {

	@Override
	public String getProtocol() {
		return "IWAP06";
	}

	public String process(String para, String imei) {

		// 根据AGPS定位
		String coordinate = "23.11333,113.12333";

		String result = Contants.AGPS_LOCATION_RETURN + "," + coordinate;
		return result;
	}

}
