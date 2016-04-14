package com.eling.elcms.assistant.api.service.protocol.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.minigps.Lbs;
import com.eling.elcms.assistant.api.minigps.LocRadiusPoiResponse;
import com.eling.elcms.assistant.api.model.AssistantLocation;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.MiniGpsUtil;

@Service("assistantComplexLocation")
@SuppressWarnings("unused")
public class ComplexLocationProtocolManagerImpl extends ProtocolManagerImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(ComplexLocationProtocolManagerImpl.class);
	
	public String process(String para, String imei) {
		
		/*IWAP02,zh_cn,0,7,460,0,9520|3671|13,9520|3672|12,9520|3673|11,9520|3674|10,9520|3675|9,9520|3676|8,9520|3677|7#*/
		String[] paras = para.split(",");
		
		String language = paras[1];
		
		//0:回复标识，为0时不回复地址，为 1 时，服务器回复地址信息
		String replyFlag  = paras[2];
		//7: 表示共有7组基站
		String locationNumbers = paras[3];
		//460: MCC 国家码
		String mmc = paras[4];
		//0: MNC运营商代码
		String mnc = paras[5];
		List<Lbs> lbsList = new ArrayList<Lbs>();
		for (int i = 6; i < paras.length; i++) {
			String[] lbsArray = paras[i].split("\\|");
			Lbs lbsData = new Lbs();
			lbsData.setMcc(Integer.valueOf(mnc));
			lbsData.setMnc(Integer.valueOf(mnc));
			lbsData.setLac(Integer.valueOf(lbsArray[0]));
			lbsData.setCellid(Integer.valueOf(lbsArray[1]));
			lbsData.setSdb(Integer.valueOf(lbsArray[2]));
			lbsList.add(lbsData);
		}
		//定位结果
		LocRadiusPoiResponse locationResponse = MiniGpsUtil.getLocation(lbsList, null);
		//定位成功 保存记录
		if("OK".equals(locationResponse.getCause())){
			AssistantLocation location = new AssistantLocation();
			location.setCreateDate(new Date());
			location.setImei(imei);
			location.setLatitude(locationResponse.getLat().toString());
			location.setLongitude(locationResponse.getLon().toString());
			location.setRadius(String.valueOf(locationResponse.getRadius()));
			location.setProtocol(Contants.COMPLEX_STATION_LOCATION);
			assistantLocationManager.save(location);
		}else{
			logger.debug("minigps调用失败,para:{},result:{}",para,locationResponse);
		}
		//9520|3671|10: LAC|CID|dbm 表示一组基站信息，后面数量需和基站组数地应  10：dbm为信号强度  多基站信号强度建议从高到低排列后上传
		String result = Contants.COMPLEX_STATION_LOCATION_RETURN;
		if(replyFlag.equals("1")){
			/*若标志位为1 时。则回复
			IWBP02F16D3357025E5753715C3A535753776D275953903100300037003900F753#
			平台回复的地址信息以UNICODE HEX String形式返回，其中包含地址信息：深圳市南山区南海大道1079号*/
			result+="F16D3357025E5753715C3A535753776D275953903100300037003900F753";
		}
		saveHistory(para,imei,result);
		return result ;
	}

}
