package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.minigps.Lbs;
import com.eling.elcms.assistant.api.minigps.LocRadiusPoiResponse;
import com.eling.elcms.assistant.api.minigps.Wifi;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.service.IAssistantLocationManager;
import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.MiniGpsUtil;

@Service
public class LocationProtocolCommandImpl implements IProtocolCommand{
	
	@Override
	public String getProtocol() {
		return "IWAP01";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(LocationProtocolCommandImpl.class);
	
	@Autowired
	private IAssistantLocationManager assistantLocationManager;
	
	@SuppressWarnings("unused")
	public String process(String para, String imei) {
		/*IWAP01080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97& Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97*/
		
		//080524: 2008 年05 月24
		String date = para.substring(6,12);
		//A:“A”表示数据有效,”V”无效,如为V则取LBS数据
		String valid = para.substring(12,13);
		//2232.9806N11404.9355E000.1: 北纬22度32.9806分，东经114度04.9355分，速度为000.1 km/h，如经纬度无效,可默认全为0,如0000.0000N00000.0000E
		//纬度
		String latitude = para.substring(13,22);
		//经度
		String longitude = para.substring(23,33);
		
		//速度
		String speed  = para.substring(34,39);
		//格林尼治时间
		String greenwichTime  = para.substring(39,45);
		//角度
		String angle  = para.substring(45,51);
		//06000908000102:060为GSM信号,009为参与定位的卫星数,080为电池电量,0,为保留位,01为设防状态,02为工作模式 ,(设防,工作模式如果为00,则代表无或未设置)
		String gsm  = para.substring(51,65);
		//460,0,9520,3671 : LBS基站数据,MCC国家码,460为中国,0:MNC,0为移动,9520:LAC,十进制,3671,CID,十进制
		String lbs = para.substring(66,81);
		
		// 经纬度有效 直接保存记录
		if ("A".equals(valid)) {
			AssistantLocation location = new AssistantLocation();
			location.setCreateDate(new Date());
			location.setImei(imei);
			String latitudeStr = latitude.replace(".", "");
			String longitudeStr = longitude.replace(".", "");
			location.setLatitude(latitudeStr.substring(0, 2) + "." + latitudeStr.substring(2, latitudeStr.length()));
			location.setLongitude(
					longitudeStr.substring(0, 3) + "." + longitudeStr.substring(3, longitudeStr.length()));
			location.setProtocol(Contants.LOCATION);
			assistantLocationManager.save(location);
		} 
		//根据LBS和wifi计算经纬度
		else {
			//460,0,9520,3671 : LBS基站数据,MCC国家码,460为中国,0:MNC,0为移动,9520:LAC,十进制,3671,CID,十进制
			String [] lbsArray = lbs.split(",");
			Lbs lbsData = new Lbs();
			lbsData .setMcc(Integer.valueOf(lbsArray[0]));
			lbsData .setMnc(Integer.valueOf(lbsArray[1]));
			lbsData.setLac(Integer.valueOf(lbsArray[2]));
			lbsData .setCellid(Integer.valueOf(lbsArray[3]));
			List<Lbs> lbsList = new ArrayList<Lbs>();
			lbsList.add(lbsData);
			
			List<Wifi> wifiList = new ArrayList<Wifi>();
			//存在wifi定位信息
			if(para.length()>81){
				//lishe88|88-25-93-7d-f1-45|40&EPTR_N|20-aa-4b-01-d9-78|61&LISHE01|88-25-93-86-8b-f3|38&macfree|64-09-80-15-e9-14|53
				String wifiStr = para.substring(82,para.length());
				String[] wifiInfos = wifiStr.split("&");
				for (int i = 0; i < wifiInfos.length; i++) {
					//含AP名
					if(wifiInfos[i].length()>22){
						String[] wifiPara = wifiInfos[i].split("\\|");
						Wifi wifi = new Wifi();
						wifi.setS("");
						wifi.setMac(wifiPara[1]);
						wifi.setR(Integer.valueOf(wifiPara[2]));
						wifiList.add(wifi);
					}
				}
			}
			//定位结果
			LocRadiusPoiResponse locationResponse = MiniGpsUtil.getLocation(lbsList, wifiList);
			//定位成功  保存记录
			if("OK".equals(locationResponse.getCause())){
				AssistantLocation location = new AssistantLocation();
				location.setCreateDate(new Date());
				location.setImei(imei);
				location.setLatitude(locationResponse.getLat().toString());
				location.setLongitude(locationResponse.getLon().toString());
				location.setRadius(String.valueOf(locationResponse.getRadius()));
				location.setProtocol(Contants.LOCATION);
				assistantLocationManager.save(location);
			}else{
				logger.error("minigps调用失败,para:{},result:{}",para,locationResponse);
			}
			
		}
		String result = Contants.LOCATION_RETURN;
		return result ;
	}

}
