package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.core.util.PropertyUtils;

/**
 * 语音查询接收协议
 * @author swq
 *
 */
@Service("assistantVoiceReceive")
public class VoiceReceiveProtocolManagerImpl extends ProtocolManagerImpl{
	
	public String process(String para, String imei) {
		AssistantMessage cond = new AssistantMessage();
		cond.setImei(imei);
		cond.setProtocol(Contants.VIOCE_DOWNLOAD);
        PropertyUtils.setProperty(cond, "orderString", "createDate:asc");
        PropertyUtils.setProperty(cond, "maxResults", 1);
		//取得所有未下发的语音
		List<AssistantMessage> messageList = assistantmessageManager.query(cond);
		// 尚未下发的语音数量
		int voiceNum = messageList.size();
		String[] paras = para.split(",");
		
		if(voiceNum>0){
			if(paras.length>2){
				//取得剩余空间
				int space = Integer.valueOf(paras[2])*1024;
				//下发的留言大于可用空间
				if(messageList.get(0).getVoice().length()>space){
					voiceNum = 0;
				}
			}else{
				String message = messageList.get(0).getVoice();
				//下发第一个语音包
				// 总分包数
				int allPackage = message.length() / 1024 + (message.length() % 1024 != 0 ? 1 : 0);
				// 从第一个开始下发
				int size = allPackage > 1 ? 1024 : message.length();
				String passPara = Contants.VIOCE_DOWNLOAD + ",D3590D54," + messageList.get(0).getPkAssistantMessage() + "," + allPackage + ",1," + size
						+ ",";
				String savePara = passPara + "...";
				String result = assistantSender.callApi(passPara + message.substring(0, size), imei);
				saveHistory(savePara, imei, result);
			}

		}
		String result = Contants.VOICE_RECEIVE_RETURN+","+voiceNum;
		saveHistory(para,imei,result);
		return result ;
	}

}
