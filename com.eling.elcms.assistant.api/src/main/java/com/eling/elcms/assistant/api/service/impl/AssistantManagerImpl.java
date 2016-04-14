package com.eling.elcms.assistant.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantInfo;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.model.AssistantMessage;
import com.eling.elcms.assistant.api.model.AssistantStep;
import com.eling.elcms.assistant.api.model.view.AssistantVoice;
import com.eling.elcms.assistant.api.model.view.Coordinate;
import com.eling.elcms.assistant.api.model.view.StepCount;
import com.eling.elcms.assistant.api.service.AssistantSender;
import com.eling.elcms.assistant.api.service.IAssistantElcmsSender;
import com.eling.elcms.assistant.api.service.IAssistantInfoManager;
import com.eling.elcms.assistant.api.service.IAssistantLocationManager;
import com.eling.elcms.assistant.api.service.IAssistantManager;
import com.eling.elcms.assistant.api.service.IAssistantMessageManager;
import com.eling.elcms.assistant.api.service.IAssistantStepManager;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.DateUtils;
import com.eling.elcms.core.util.PropertyUtils;

@Service
public class AssistantManagerImpl implements IAssistantManager	{
	
	@Autowired
	private AssistantSender assistantSender;
	@Autowired
	private IAssistantInfoManager assistantInfoManager;
	@Autowired
	private IAssistantMessageManager assistantMessageManager;
	@Autowired
	private IAssistantLocationManager assistantLocationManager;
	@Autowired
	private IAssistantStepManager assistantStepManager;

	//@Autowired
	private IAssistantElcmsSender sender;

	@Override
	public boolean setServicePhone(String imei, String phone) {
		// para:IWBP11,353456789012345,080835,135XXXXXXXX
		String para = Contants.SET_MIAN_CONTROL_TEL + "," + imei + ",080835," + phone;
		String result = assistantSender.callApi(para, imei);
		// 保存历史
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para);
		info.setProtocol(Contants.SET_MIAN_CONTROL_TEL);
		assistantInfoManager.save(info);
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}

	@Override
	public List<String> getSosPhone(String imei) {
		AssistantInfo cond = new AssistantInfo();
		cond.setProtocol(Contants.SET_SOS_TEL_RETURN);
		cond.setImei(imei);
	    PropertyUtils.setProperty(cond, "maxResults", 1);
	    PropertyUtils.setProperty(cond, "orderString", "createDate:desc");
		List<AssistantInfo> infos = assistantInfoManager.query(cond);
		List<String> phones = new ArrayList<String>();
		if (infos.size() > 0) {
			AssistantInfo info = infos.get(0);
			// 取得设置SOS的记录
			String para = info.getInfo();
			String[] paras = para.split(",");
			if (paras.length > 3) {
				// 取得设置的电话
				for (int i = 3; i < paras.length; i++) {
					if (StringUtils.isNotBlank(paras[i])) {
						phones.add(paras[i]);
					}
				}
			}
		}
		return phones;
	}

	@Override
	public boolean setSosPhone(String imei, String phone) {
		// 取得已经设置的SOS号码
		List<String> phones = getSosPhone(imei);
		// 不可设置3个以上的号码
		if (phones.size() == 3) {
			return false;
		}
		// IWBP12,353456789012345,080835,135XXXXXXXX,135XXXXXXXX,135XXXXXXXX
		String phonePara = "";
		for (int i = 0; i < phones.size(); i++) {
			phonePara += phones.get(i) + ",";
		}
		phonePara += phone;
		int phoneNum = phones.size() + 1;
		// 需要设置3个号码 没有则设为空
		if (phoneNum < 3) {
			for (int i = 0; i < 3 - phoneNum; i++) {
				phonePara += ",";
			}
		}
		String para = Contants.SET_SOS_TEL + "," + imei + ",080835," + phonePara;
		String result = assistantSender.callApi(para, imei);
		if (Boolean.valueOf(result)) {
			// 成功则保存历史
			AssistantInfo info = new AssistantInfo();
			info.setCreateDate(new Date());
			info.setImei(imei);
			info.setInfo(para);
			info.setProtocol(Contants.SET_SOS_TEL);
			assistantInfoManager.save(info);
		}
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}

	@Override
	public boolean deleteSosPhone(String imei, String phone) {
		List<String> phones = getSosPhone(imei);
		// IWBP12,353456789012345,080835,135XXXXXXXX,135XXXXXXXX,135XXXXXXXX#
		String phonePara = "";
		boolean containsPhone = false;
		for (int i = 0; i < phones.size(); i++) {
			if (!phones.get(i).equals(phone)) {
				phonePara += phones.get(i) + ",";
			} else {
				containsPhone = true;
			}
		}
		//如果没有设置过次号码为Sos号码
		if (!containsPhone) {
			return true;
		}
		int phoneNum = phones.size() - 1;
		// 需要设置3个号码 没有则设为空
		for (int i = 0; i < 2 - phoneNum; i++) {
			phonePara += ",";
		}
		String para = Contants.SET_SOS_TEL + "," + imei + ",080835," + phonePara;
		String result = assistantSender.callApi(para, imei);
		if (Boolean.valueOf(result)) {
			// 成功则保存历史
			AssistantInfo info = new AssistantInfo();
			info.setCreateDate(new Date());
			info.setImei(imei);
			info.setInfo(para);
			info.setProtocol(Contants.SET_SOS_TEL);
			assistantInfoManager.save(info);
		}
		return Boolean.valueOf(result);
	}

	@Override
	public Coordinate getLocation(String imei) {
		// 取得最后一次定位及定位时间 如果不是在最近1分钟内 则发送立即定位请求
		Coordinate coordinate = new Coordinate();
		AssistantLocation cond = new AssistantLocation();
		cond.setImei(imei);
		PropertyUtils.setProperty(cond, "maxResults", 1);
		PropertyUtils.setProperty(cond, "orderString", "createDate:desc");
		List<AssistantLocation> locations = assistantLocationManager.query(cond);
		if (!locations.isEmpty()) {
			AssistantLocation location = locations.get(0);
			// 如果最后一次发送位置时间大于1分钟 则发送立即定位请求
			if (location.getCreateDate().compareTo(DateUtils.addMinute(new Date(), -1)) > 0) {
				assistantSender.callApi("IWBP16," + imei + ",080835", imei);
			}
			coordinate.setLatitude(location.getLatitude());
			coordinate.setLongitude(location.getLongitude());
			coordinate.setRadius(Integer.valueOf(location.getRadius()));
			coordinate.setTime(location.getCreateDate());
		}
		return coordinate;
	}

	@Override
	public List<Coordinate> getTrail(String imei, Date start, Date end) {
		AssistantLocation cond = new AssistantLocation();
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		cond.setImei(imei);
		cond.setCreateDate(start);
		PropertyUtils.setProperty(cond, "createDateEnd", end);
		List<AssistantLocation> locations = assistantLocationManager.query(cond);
		if (!locations.isEmpty()) {
			for (int i = 0; i < locations.size(); i++) {
				Coordinate coordinate = new Coordinate();
				coordinate.setLatitude(locations.get(i).getLatitude());
				coordinate.setLongitude(locations.get(i).getLongitude());
				coordinate.setRadius(Integer.valueOf(locations.get(i).getRadius()));
				coordinate.setTime(locations.get(i).getCreateDate());
				coordinateList.add(coordinate);
			}
		}
		return coordinateList;
	}

	@Override
	public boolean setVoiceRemaind(AssistantVoice voice) {
		// 保存语音数据 用于其他分包下发
		AssistantMessage remaind = new AssistantMessage();
		remaind.setProtocol(Contants.VOICE_REMIND);
		remaind.setImei(voice.getImei());
		remaind.setVoice(voice.getVoice());
		remaind.setCreateDate(new Date());
		remaind = assistantMessageManager.save(remaind);
		// 总分包数
		int allPackage = voice.getVoice().length() / 1024 + (voice.getVoice().length() % 1024 != 0 ? 1 : 0);
		// 从第一个开始下发
		int size = allPackage > 1 ? 1024 : voice.getVoice().length();
		String date = DateUtils.parseDateToString(voice.getDate(), DateUtils.datePatternStringWithHM);
		date = date.substring(2, date.length());
		String para = Contants.VOICE_REMIND + "," + remaind.getPkAssistantMessage() + "," + date + ","
				+ voice.getRepetDay() + "," + allPackage + ",1," + size + ",";
		String savePara = para + "...";
		String result = assistantSender.callApi(para + voice.getVoice().substring(0, size), voice.getImei());
		// 保存发送记录
		saveHistory(voice.getImei(), Contants.VOICE_REMIND, savePara, null);
		return Boolean.valueOf(result);
	}

	/**
	 * 保存调用历史
	 * 
	 * @param imei
	 * @param protocol
	 * @param para
	 * @param returnInfo
	 */
	private void saveHistory(String imei, String protocol, String para, String returnInfo) {
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para);
		info.setProtocol(protocol);
		info.setReturnInfo(returnInfo);
		assistantInfoManager.save(info);
	}

	@Override
	public boolean deleteVoice(String imei, Date time) {

		// IWBP39,080835,1602121056
		String date = DateUtils.parseDateToString(time, DateUtils.datePatternStringWithHM);
		String para = Contants.DELETE_VOICE_REMIND + ",080835," + date.substring(2, date.length());
		String result = assistantSender.callApi(para, imei);
		if (Boolean.valueOf(result)) {
			// 成功则保存历史
			AssistantInfo info = new AssistantInfo();
			info.setCreateDate(new Date());
			info.setImei(imei);
			info.setInfo(para);
			info.setProtocol(Contants.DELETE_VOICE_REMIND);
			assistantInfoManager.save(info);
		}
		return Boolean.valueOf(result);
	}

	@Override
	public boolean setVoiceMessage(String imei, String message) {
		int space = getVoiceMessageSpace(imei);
		if (message.length() > space * 1024) {
			return false;
		}
		// 存入assistant_message, 等待下发
		AssistantMessage remaind = new AssistantMessage();
		remaind.setProtocol(Contants.VIOCE_DOWNLOAD);
		remaind.setImei(imei);
		remaind.setVoice(message);
		remaind.setCreateDate(new Date());
		remaind = assistantMessageManager.save(remaind);

		String para = Contants.NEW_VIOCE + "," + imei + ",080835,1";
		// 下发语音通知至助手
		String result = assistantSender.callApi(para, imei);
		saveHistory(imei, Contants.NEW_VIOCE, para, null);
		return Boolean.valueOf(result);
	}

	@Override
	public List<StepCount> getStepCount(String imei, Date start, Date end, int timeInterval) {
		// 设置查询条件
		AssistantStep cond = new AssistantStep();
		cond.setCreateDate(start);
		PropertyUtils.setProperty(cond, "createDateEnd", end);
		cond.setImei(imei);
		List<AssistantStep> stepsList = assistantStepManager.query(cond);
		List<StepCount> steps = new ArrayList<>();
		if (!stepsList.isEmpty()) {
			// 不设置间隔
			if (timeInterval == 0) {
				for (int i = 0; i < stepsList.size(); i++) {
					StepCount step = new StepCount();
					step.setStep(stepsList.get(i).getStep());
					step.setTime(stepsList.get(i).getCreateDate());
					steps.add(step);
				}
			} else {// 根据间隔和开始结束日期生成list
				Stack<AssistantStep> stepStack = new Stack<AssistantStep>();
				for (int i = stepsList.size() - 1; i >= 0; i--) {
					stepStack.push(stepsList.get(i));
				}
				Date time = start;
				Date timeLater = DateUtils.addMinute(time, timeInterval);
				while (timeLater.compareTo(end) <= 0) {
					StepCount step = new StepCount();
					step.setTime(time);
					AssistantStep top = stepStack.isEmpty() ? null : stepStack.peek();
					// 间隔之间
					if (top != null && top.getCreateDate().compareTo(time) >= 0
							&& top.getCreateDate().compareTo(timeLater) <= 0) {
						step.setStep(top.getStep());
						stepStack.pop();
					}
					time = timeLater;
					timeLater = DateUtils.addMinute(timeLater, timeInterval);
					steps.add(step);
				}

			}
		}
		return steps;
	}

	@Override
	public List<AssistantLocation> getAlarmInfo(String imei, Date Start, Date end) {

		// 设置查询条件
		AssistantLocation cond = new AssistantLocation();
		cond.setImei(imei);
		cond.setCreateDate(Start);
		cond.setProtocol(Contants.ALARM_ADRESS);
		PropertyUtils.setProperty(cond, "createDateEnd", end);
		List<AssistantLocation> locations = assistantLocationManager.query(cond);
		return locations;
	}

	@Override
	public AssistantStatus getAssistantStatus(String imei) {
		// 10分钟内没有收到心跳包 则认为离线
		AssistantInfo cond = new AssistantInfo();
		cond.setImei(imei);
		cond.setProtocol(Contants.HEART_BEAT);
		Date now = new Date();
		cond.setCreateDate(DateUtils.addMinute(new Date(), -30));
		PropertyUtils.setProperty(cond, "createDateEnd", now);
		List<AssistantInfo> infoList = assistantInfoManager.query(cond);
		if (infoList.isEmpty()) {
			return AssistantStatus.Offline;
		} else {
			return AssistantStatus.Online;
		}
	}

	@Override
	public int getVoiceMessageSpace(String imei) {
		// 默认留言存储空间30K
		int space = 30;
		// 设置查询条件
		AssistantInfo cond = new AssistantInfo();
		cond.setImei(imei);
		cond.setProtocol(Contants.VOICE_RECEIVE);
		PropertyUtils.setProperty(cond, "maxResults", 1);
		PropertyUtils.setProperty(cond, "orderString", "createDate:desc");
		List<AssistantInfo> infoList = assistantInfoManager.query(cond);
		if (!infoList.isEmpty()) {
			AssistantInfo info = infoList.get(0);
			String para = info.getInfo();
			String paras[] = para.split(",");
			// 已将返回内存加入协议
			if (paras.length == 3) {
				space = Integer.valueOf(paras[2]);
			}
		}
		return space;
	}

	@Override
	public boolean setWhiteList(String imei, List<String> phones) {
		// IWBP14,353456789012345,080835,D3590D54|135xxxxxxxxxx,
		// D3590D54|135xxxxxxxxxx, D3590D54|135xxxxxxxxxx,
		// D3590D54|135xxxxxxxxxx, D3590D54|135xxxxxxxxxx,
		// D3590D54|135xxxxxxxxxx, D3590D54|135xxxxxxxxxx,
		// D3590D54|135xxxxxxxxxx, D3590D54|135xxxxxxxxxx,
		// D3590D54|135xxxxxxxxxx#

		String para = Contants.SET_WHITE_LIST + "," + imei + ",080835,";
		// 白名单最多10个
		for (int i = 0; i < 10; i++) {
			if (phones.size() > i) {
				para += "D3590D54|" + phones.get(i);
			}
			if (i != 9) {
				para += ",";
			}
		}
		String result = assistantSender.callApi(para, imei);
		// 保存历史
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para);
		info.setProtocol(Contants.SET_WHITE_LIST);
		assistantInfoManager.save(info);
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}

	@Override
	public List<AssistantInfo> getLocations(String imei) {
		AssistantInfo cond = new AssistantInfo();
		cond.setProtocol(Contants.LOCATION);
		cond.setImei(imei);

		return assistantInfoManager.query(cond);
	}

	@Override
	public IAssistantElcmsSender getAssistantElcmsSender() {
		return sender;
	}

	@Override
	public List<AssistantInfo> getSteps(String imei) {
		AssistantInfo cond = new AssistantInfo();
		//PropertyUtils.setProperty(cond, "maxResults", 100);
		PropertyUtils.setProperty(cond, "orderString", "createDate:desc");
		cond.setProtocol(Contants.HEART_BEAT);
		cond.setImei(imei);

		return assistantInfoManager.query(cond);
	}

	@Override
	public boolean reset(String imei) {
		// para:IWBP17,353456789012345,080835#
		String para = Contants.RESET_FACTORY + "," + imei + ",080835";
		String result = assistantSender.callApi(para, imei);
		// 保存历史
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para);
		info.setProtocol(Contants.RESET_FACTORY);
		assistantInfoManager.save(info);
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}
	@Override
	public boolean setMessageForward(String imei, List<String> sourcePhone, List<String> targetPhone) {
		// IWBP40,080835,10086,10087,10088, 13800000000, 13800000001, 13800000002#
		// 最多可以设置的号码数量
		int maxNum = 3;
		StringBuffer para = new StringBuffer(Contants.MESSAGE_FORWARD + "," + imei + ",080835");
		for (int i = 0; i < maxNum; i++) {
			if(sourcePhone!=null && sourcePhone.size()>=(i+1)){
				para.append(",");
				para.append(sourcePhone.get(i));
			}else{
				para.append(",");
			}
		}
		for (int i = 0; i < maxNum; i++) {
			if(targetPhone!=null && targetPhone.size()>=(i+1)){
				para.append(",");
				para.append(targetPhone.get(i));
			}else{
				para.append(",");
			}
		}
		String result = assistantSender.callApi(para.toString(), imei);
		// 保存历史
		AssistantInfo info = new AssistantInfo();
		info.setCreateDate(new Date());
		info.setImei(imei);
		info.setInfo(para.toString());
		info.setProtocol(Contants.MESSAGE_FORWARD);
		assistantInfoManager.save(info);
		// 返回是否设置成功
		return Boolean.valueOf(result);
	}

}
