package com.eling.elcms.assistant.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.eling.elcms.assistant.api.model.AssistantInfo;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.model.view.AssistantVoice;
import com.eling.elcms.assistant.api.model.view.Coordinate;
import com.eling.elcms.assistant.api.model.view.StepCount;

public interface IAssistantManager {
	
	/**
	 * 添加平台调用接口 用于调用平台的各项信息
	 */
	IAssistantElcmsSender getAssistantElcmsSender();
	
	/**
	 * 根据imei设置服务中心号码
	 * 
	 * @param imei
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	boolean setServicePhone(String imei, String phone);
	
	/**
	 * 设定白名单号码 最多可设置10个 会覆盖上一次设置的号码
	 * @param imei
	 * @param phone
	 * @return
	 */
	boolean setWhiteList(String imei, List<String> phones);
	
	/**
	 * 取得设定的紧急呼救号码
	 * @param imei
	 * @return
	 */
	List<String> getSosPhone(String imei);
			
	/**
	 * 设定紧急呼救号码
	 * @param imei
	 * @param phone
	 * @return
	 */
	boolean setSosPhone(String imei, String phone);
	
	/**
	 * 删除紧急呼救号码
	 * @param imei
	 * @param phone
	 * @return
	 */
	boolean deleteSosPhone(String imei, String phone);
	
	/**
	 * 获取当前位置
	 * @param imei
	 * @param phone
	 * @return
	 */
	Coordinate getLocation(String imei);
	
	/**
	 * 获取时间段内的位置信息
	 * @param imei
	 * @param start
	 * @param end
	 * @return
	 */
	List<Coordinate> getTrail(String imei,Date start,Date end);

	/**
	 * 设置语音提醒
	 * @param imei
	 * @return
	 */
	boolean setVoiceRemaind(AssistantVoice voice);
	
	/**
	 * 删除语音提醒 yyyyMMddHHmm 删除精确至分钟的语音提醒
	 * @param imei
	 * @return
	 */
	boolean deleteVoice(String imei,Date time);
	
	
	
	/**
	 * 设置留言
	 * @param imei message为base64后字符串
	 * @return
	 */
	boolean setVoiceMessage(String imei,String message);
	
	/**
	 * 获取设备可用留言空间 单位 kb
	 * @param imei
	 * @return
	 */
	int getVoiceMessageSpace(String imei);
	
	/**
	 * 根据时间获取计步数据
	 * @param imei
	 * @param Start
	 * @param end
	 * @param timeInterval 时间间隔 单位分钟 设置为0只返回有步数的数据
	 * @return
	 */
	List<StepCount> getStepCount(String imei, Date Start, Date end,int timeInterval);
	
	/**
	 * 根据时间获得报警事件（SOS求救）
	 * @param imei
	 * @param Start
	 * @param end
	 * @return
	 */
	List<AssistantLocation> getAlarmInfo(String imei, Date start, Date end);
	
	/**
	 * 取得助手状态 离线 在线
	 * @param imei
	 * @return
	 */
	AssistantStatus getAssistantStatus(String imei);
	
	/**
	 * 短信转发设置（最多可分别设置3条）
	 * @param imei
	 * @param sourcePhone 需要转发的短信源
	 * @param targetPhone 转发到目标号码
	 * @return
	 */
	boolean setMessageForward(String imei,List<String> sourcePhone,List<String> targetPhone);
	
	
	/**
	 * 助手状态 在线 离线
	 *
	 */
	public enum AssistantStatus {
		Online,Offline;
	}
	
	/**
	 * 取得所有定位信息IWAP01(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	List<AssistantInfo> getLocations(String imei);
	
	/**
	 * 取得所有心跳包信息IWAP03(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	List<AssistantInfo> getSteps(String imei);
	
	/**
	 * 恢复出厂设置(测试用)
	 * 
	 * @param imei
	 * @return
	 */
	boolean reset(String imei);
}
