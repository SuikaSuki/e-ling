package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.minigps.Lbs;
import com.eling.elcms.assistant.api.minigps.LocRadiusPoiResponse;
import com.eling.elcms.assistant.api.minigps.Wifi;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.MiniGpsUtil;

@Service("assistantAlarmAdress")
public class AlarmAdressProtocolManagerImpl extends ProtocolManagerImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmAdressProtocolManagerImpl.class);
	
	@SuppressWarnings("unused")
	public String process(String para, String imei) {
		String[] paras = para.split(",");
		
		//日期 080524: 2008 年05 月24
		String date = paras[0].substring(6, 12);
		//A:"A"表示数据有效,"V"无效,如为V则取LBS数据
		String valid = paras[0].substring(12, 13);
		
		//纬度
		String latitude= paras[0].substring(13,22);
		//经度
		String longitude = paras[0].substring(23,33);
		
		//速度
		String speed  = paras[0].substring(34,39);
		//格林尼治时间
		String greenwichTime  = paras[0].substring(39,45);
		
		//角度
		String angle  = paras[0].substring(45,51);

		//06000908000102:060为GSM信号,009为参与定位的卫星数,080为电池电量,0,为保留位,01为设防状态,02为工作模式 ,(设防,工作模式如果为00,则代表无或未设置)
		String gsm  = paras[0].substring(51,65);
		//MCC国家码,460为中国
		String mmc = paras[1];
		//MNC,0为移动
		String mnc = paras[2];
		//LAC,十进制
		String lac = paras[3];
		//LAC,十进制
		String cid = paras[4];
		//00为报警状态,00为无任何报警(01：SOS,02：低电,03：脱落报警)
		String alarmType = paras[5];
		//设备语言
		String language = paras[6];
		
		//00:第一个0:是否需要回复地址信息,0:不回复,1回复.第二个0:地址信息中是否包含手机超链接,0不包含,1包含
		String otherFlag = paras[7];
		
		String result = Contants.ALARM_ADRESS_RETURN;
		String resultSave = Contants.ALARM_ADRESS_RETURN;
		
		AssistantLocation location = new AssistantLocation();
		
		
		if (otherFlag.startsWith("1")) {
			/*
			 * 平台回应地址内容为HEX的UNICODE编码,非明文,上方示例内容为:
			 * 深圳市南山区南海大道1079号http://www.gps.com/map.aspx?lat=23.123&lng=113.123
			 * 内容语言根据AP10数据包中语言自动判断,是否回复超链接也根据AP10状态判断
			 */
			result += "6df157335e0253575c71533a53576d7759279053003100300037003953f7002000"
					+ "200068007400740070003a002f002f007700770077002e006700700073002e00"
					+ "63006f006d002f006d00610070002e0061007300700078003f006c0061007400"
					+ "3d00320033002e0031003200330026006c006e0067003d003100310033002e003100320033";
		}
		
		// 经纬度有效 直接保存记录
		if ("A".equals(valid)) {
			location.setCreateDate(new Date());
			location.setImei(imei);
			String latitudeStr = latitude.replace(".", "");
			String longitudeStr = longitude.replace(".", "");
			location.setLatitude(latitudeStr.substring(0, 2) + "." + latitudeStr.substring(2, latitudeStr.length()));
			location.setLongitude(
					longitudeStr.substring(0, 3) + "." + longitudeStr.substring(3, longitudeStr.length()));
			location.setProtocol(Contants.ALARM_ADRESS);
			assistantLocationManager.save(location);
		}
		// 根据LBS和wifi计算经纬度
		else {
			// 460,0,9520,3671 :
			// LBS基站数据,MCC国家码,460为中国,0:MNC,0为移动,9520:LAC,十进制,3671,CID,十进制
			Lbs lbsData = new Lbs();
			lbsData.setMcc(Integer.valueOf(mmc));
			lbsData.setMnc(Integer.valueOf(mnc));
			lbsData.setLac(Integer.valueOf(lac));
			lbsData.setCellid(Integer.valueOf(cid));
			List<Lbs> lbsList = new ArrayList<Lbs>();
			lbsList.add(lbsData);

			List<Wifi> wifiList = new ArrayList<Wifi>();
			// 存在wifi定位信息
			if (para.length() > 93 && paras.length>7) {
				// 未修复版本 包含AP名 待厂家去掉AP名
				// lishe88|88-25-93-7d-f1-45|40&EPTR_N|20-aa-4b-01-d9-78|61&LISHE01|88-25-93-86-8b-f3|38&macfree|64-09-80-15-e9-14|53
				String wifiStr = paras[8];
				String[] wifiInfos = wifiStr.split("&");
				for (int i = 0; i < wifiInfos.length; i++) {
					// 含AP名
					if (wifiInfos[i].length() > 22) {
						String[] wifiPara = wifiInfos[i].split("\\|");
						Wifi wifi = new Wifi();
						wifi.setS("");
						wifi.setMac(wifiPara[1]);
						wifi.setR(Integer.valueOf(wifiPara[2]));
						wifiList.add(wifi);
					}
				}
			}
			// 定位结果
			LocRadiusPoiResponse locationResponse = MiniGpsUtil.getLocation(lbsList, wifiList);
			// 定位成功 保存记录
			if ("OK".equals(locationResponse.getCause())) {
				location.setCreateDate(new Date());
				location.setImei(imei);
				location.setLatitude(locationResponse.getLat().toString());
				location.setLongitude(locationResponse.getLon().toString());
				location.setRadius(String.valueOf(locationResponse.getRadius()));
				location.setProtocol(Contants.ALARM_ADRESS);
				assistantLocationManager.save(location);
			} else {
				logger.debug("minigps调用失败,para:{},result:{}",para,locationResponse);
			}

		}
		
		saveHistory(para,imei,resultSave);
		//推送消息至APP
		assistantManager.getAssistantElcmsSender().alarmSos(location);
		return result ;
	}

}
