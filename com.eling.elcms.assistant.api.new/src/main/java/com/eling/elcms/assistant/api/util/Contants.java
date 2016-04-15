package com.eling.elcms.assistant.api.util;

public class Contants {
	//终端主动发起
	//1. 登录包
	public static String LOGIN = "IWAP00";
	public static String LOGIN_RETURN = "IWBP00";
	//2. 定位数据包，GPS+LBS+状态+基站+WIFI合并包
	public static String LOCATION = "IWAP01";
	public static String LOCATION_RETURN = "IWBP01";
	//3. 多基站定位数据包
	public static String COMPLEX_STATION_LOCATION = "IWAP02";
	public static String COMPLEX_STATION_LOCATION_RETURN = "IWBP02";
	//4. 报警与地址回复包
	public static String ALARM_ADRESS = "IWAP10";
	public static String ALARM_ADRESS_RETURN = "IWBP10";
	//5. 心跳包
	public static String HEART_BEAT = "IWAP03";
	public static String HEART_BEA_RETURN = "IWBP03";
	//6. 低电量报警上报数据包
	public static String LOW_POWER = "IWAP04";
	public static String LOW_POWER_RETURN = "IWBP04";
	//7. 语音查询接收协议
	public static String VOICE_RECEIVE = "IWAP05";
	public static String VOICE_RECEIVE_RETURN = "IWBP05";
	//8. AGPS辅助定位包
	public static String AGPS_LOCATION = "IWAP06";
	public static String AGPS_LOCATION_RETURN = "IWBP06";
	//9. 语音上行
	public static String UPLOAD_VOICE = "IWAP07";
	//10. 语音标签查收协议
	public static String VOICE_LABEL = "IWAP09";
	public static String VOICE_LABEL_RETURN = "IWBP09";
	//服务器发起
	//1. 设置主控号码
	public static String SET_MIAN_CONTROL_TEL = "IWBP11";
	//2. 设置SOS号码
	public static String SET_SOS_TEL = "IWBP12";
	public static String SET_SOS_TEL_RETURN = "IWAP12";
	//3. 设置联系人白名单
	public static String SET_WHITE_LIST = "IWBP14";
	//4. GPRS定位数据上传时间间隔
	public static String GPRS_TIME_INTERVAL = "IWBP15";
	//5. 立即定位指令
	public static String LOCATION_IMMEDIATELY = "IWBP16";
	//6. 恢复出厂设置
	public static String RESET_FACTORY = "IWBP17";
	//7. 重启终端
	public static String RESTART = "IWBP18";
	//8. 设置服务器信息
	public static String SET_SERVER_INFO = "IWBP19";
	//9. 设置终端语言地区
	public static String SET_LANGUAGE_ZONE = "IWBP20";
	//10. 设置计步器开关
	public static String PEDOMETER_SWITCH = "IWBP21";
	//11. 设置闹钟
	public static String SET_CLOCK = "IWBP25";
	//12. 设置上课隐身时间短（免打扰时间）
	public static String NONE_DISTURB_TIME = "IWBP26";
	//13. 新语音记录提醒
	public static String NEW_VIOCE = "IWBP27";
	//14. 留言下行
	public static String VIOCE_DOWNLOAD = "IWBP28";
	public static String VIOCE_DOWNLOAD_RETURN = "IWAP28";
	//15. 脱落报警
	public static String DROP_ALRM = "IWBP30";
	//16. 拨打电话
	public static String CALL_TEL = "IWBP32";
	//17. 进入设备验证码显示界面
	public static String VERIFY_CODE_IN = "IWBP35";
	//18. 退出设备验证码显示界面
	public static String VERIFY_CODE_OUT = "IWBP36";
	//19. 设定语音提醒 
	public static String VOICE_REMIND = "IWBP36";
	public static String VOICE_REMIND_RETURN = "IWAP36";
	//19. 查询语音设置提醒
	public static String SEARCH_VOICE_REMIND  = "IWBP37";
	//19. 语音标签下发
	public static String VOICE_LABEL_DOWN = "IWBP38";
	public static String VOICE_LABEL_DOWN_RETURN = "IWAP38";
	//19. 删除语音提醒
	public static String DELETE_VOICE_REMIND = "IWBP39";
	public static String DELETE_VOICE_REMIND_RETURN = "IWAP39";
	//20. 短信转发设置
	public static String MESSAGE_FORWARD = "IWBP40";
	
	/*public static Map<String, String> protocolMap = new HashMap<String,String>(){
	private static final long serialVersionUID = -6005897234490289527L;

	{
		put(LOGIN,"assistantLogin");
		put(LOCATION,"assistantLocation");
		put(COMPLEX_STATION_LOCATION,"assistantComplexLocation");
		put(ALARM_ADRESS,"assistantAlarmAdress");
		put(HEART_BEAT,"assistantHeartBeat");
		put(LOW_POWER,"assistantLowPower");
		put(VOICE_RECEIVE,"assistantVoiceReceive");
		put(AGPS_LOCATION,"assistantApgsLocation");
		put(VOICE_LABEL,"assistantVoiceLabel");
		put(VIOCE_DOWNLOAD_RETURN,"assistantVoiceDownloadReturn");
		put(VOICE_REMIND_RETURN,"assistantVocieRemindReturn");
		put(VOICE_LABEL_DOWN_RETURN,"assistantVocieLabelDown");
	}};*/
}
