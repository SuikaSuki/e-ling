package com.eling.elcms.assistant.api.service.protocol.impl;

import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.assistant.api.util.Contants;

/**
 * 智能提醒下行
 * 
 * @author swq
 *
 */
@Service("assistantVocieRemindReturn")
public class VoiceRemindReturnManagerImpl extends ProtocolManagerImpl {

	public String process(String para, String imei) {
		// IWAP36,XXXX,1602121056,1367,6,1,1#
		String[] paras = para.split(",");

		// 附加信息（assistantMessagePK）
		String appendMessage = paras[1];

		// 语音提醒时间
		String date = paras[2];

		// 语音提醒时间重复时间 1367：表示周一、三、六、日重复，0表示不重复
		String times = paras[3];

		// 总分包数
		int totalNum = Integer.valueOf(paras[4]);
		// 当前第几包
		int nowNum = Integer.valueOf(paras[5]);
		// 传输状态 1：成功 0：失败
		int transportFlag = Integer.valueOf(paras[6]);
		AssistantMessage massage = assistantmessageManager.get(Long.valueOf(appendMessage));

		// String data = AssistantApiController.voiceMap.get(appendMessage);
		String data = massage.getVoice();
		String result = null;
		int packegSize = 1024;
		// 语音包内容不记录在history中
		String saveResult = null;
		// 不是最后一包或者传输失败
		if (nowNum != totalNum || transportFlag == 0) {
			if (transportFlag == 0) {
				// 最后一个分包发送失败的情况
				if (totalNum == nowNum) {
					packegSize = data.length() - (nowNum - 1) * 1024;
				}
				int start = (nowNum - 1) * 1024;
				int end = start + packegSize;
				saveResult = Contants.VOICE_REMIND + "," + appendMessage + "," + date + "," + times + "," + totalNum
						+ "," + nowNum + "," + packegSize + ",.....";
				result = Contants.VOICE_REMIND + "," + appendMessage + "," + date + "," + times + "," + totalNum + ","
						+ nowNum + "," + packegSize + "," + data.substring(start, end);

			} else {
				// 即将发送最后一个分包的情况
				if (nowNum == (totalNum - 1)) {
					packegSize = data.length() - nowNum * 1024;
				}
				int start = nowNum * 1024;
				int end = start + packegSize;
				saveResult = Contants.VOICE_REMIND + "," + appendMessage + "," + date + "," + times + "," + totalNum
						+ "," + (nowNum + 1) + "," + packegSize + ",.....";
				result = Contants.VOICE_REMIND + "," + appendMessage + "," + date + "," + times + "," + totalNum + ","
						+ (nowNum + 1) + "," + packegSize + "," + data.substring(start, end);
				System.out.println(data.substring(start, end));
			}
		} else {
			// 最后一个分包传输完 删除appendMessage
			assistantmessageManager.remove(Long.valueOf(appendMessage));
		}
		saveHistory(para, imei, saveResult);
		return result;
	}

}
