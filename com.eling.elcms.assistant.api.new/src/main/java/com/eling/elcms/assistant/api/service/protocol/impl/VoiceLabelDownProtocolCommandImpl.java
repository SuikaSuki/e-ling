package com.eling.elcms.assistant.api.service.protocol.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.assistant.api.service.IAssistantMessageManager;
import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.util.Contants;

/**
 * 语音标签下行
 * 
 * @author swq
 *
 */
@Service
public class VoiceLabelDownProtocolCommandImpl  implements IProtocolCommand{
	
	@Override
	public String getProtocol() {
		return "IWAP38";
	}
	@Autowired
	private IAssistantMessageManager assistantmessageManager;
	
	public String process(String para, String imei) {
		// IWAP38,D3590D54,XXXX,6,1,1
		String[] paras = para.split(",");

		// 发送者名称
		String senderName = paras[1];
		// 附加信息
		String appendMessage = paras[2];
		// 总分包数
		int totalNum = Integer.valueOf(paras[3]);
		// 当前第几包
		int nowNum = Integer.valueOf(paras[4]);
		// 传输状态 1：成功 0：失败
		int transportFlag = Integer.valueOf(paras[5]);

		AssistantMessage massage = assistantmessageManager.get(Long.valueOf(appendMessage));
		String data = massage.getVoice();
		String result = null;
		int packegSize = 1024;
		// 语音包内容不记录在history中
		// 不是最后一包或者传输失败
		if (nowNum != totalNum || transportFlag == 0) {
			// 传输失败 再次传输上一个包
			if (transportFlag == 0) {
				// 最后一个分包发送失败的情况
				if (totalNum == nowNum) {
					packegSize = data.length() - (nowNum - 1) * 1024;
				}
				int start = (nowNum - 1) * 1024;
				int end = start + packegSize;
				result = Contants.VOICE_LABEL_DOWN + "," + senderName + "," + appendMessage + "," + totalNum + ","
						+ nowNum + "," + packegSize + "," + data.substring(start, end);
			} else {
				// 即将发送最后一个分包的情况
				if (nowNum == (totalNum - 1)) {
					packegSize = data.length() - nowNum * 1024;
				}
				int start = nowNum * 1024;
				int end = start + packegSize;
				result = Contants.VOICE_LABEL_DOWN + "," + senderName + "," + appendMessage + "," + totalNum + ","
						+ (nowNum + 1) + "," + packegSize + "," + data.substring(start, end);
			}
		} else {
			// 最后一个分包传输完 删除appendMessage
			assistantmessageManager.remove(Long.valueOf(appendMessage));
		}
		return result;
	}

}
