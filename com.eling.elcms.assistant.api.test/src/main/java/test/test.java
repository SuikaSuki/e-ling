package test;

public class test {

	public static void main (String args[]){
	/*	String a = "IWAP01080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97& Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97";
		String b = "IWAP01080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671";
		System.out.println(a.substring(6,12));
		System.out.println(a.substring(12,13));
		System.out.println(a.substring(13,22));
		System.out.println(a.substring(23,33));
		System.out.println(a.substring(34,39));
		System.out.println(a.substring(39,45));
		System.out.println(a.substring(45,51));
		System.out.println(a.substring(51,65));
		System.out.println(a.substring(66,81));
		System.out.println(b.length());
		if(a.length()>81){
			String wifiInfos = a.substring(82,a.length());
			System.out.println(wifiInfos);
		}*/
		//System.out.println(a.indexOf("Home"));
		
		
		
/*		String para = "IWAP02,zh_cn,0,7,460,0,9520|3671|13,9520|3672|12,9520|3673|11,9520|3674|10,9520|3675|9,9520|3676|8,9520|3677|7";
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
		
		//9520|3671|10: LAC|CID|dbm 表示一组基站信息，后面数量需和基站组数地应  10：dbm为信号强度  多基站信号强度建议从高到低排列后上传
*/		
		
		
		
		/*String para = "IWAP10080524A2232.9806N11404.9355E000.1061830323.8706000908000502,"
				+ "460,0,9520,3671,00,zh_cn,00,"
				+ "HOME|74-DE-2B-44-88-8C|97&HOME1|74-DE-2B-44-88-8C|97&HOME2|74-DE-2B-44-88-8C|97&HOME3|74-DE-2B-44-88-8C|97";
		String[] paras = para.split(",");
		
		//日期 080524: 2008 年05 月24
		String date = paras[0].substring(6, 12);
		System.out.println(date);
		//A:"A"表示数据有效,"V"无效,如为V则取LBS数据
		String validFlag = paras[0].substring(12, 13);
		System.out.println(validFlag);
		
		//纬度
		String latitude= paras[0].substring(13,22);
		System.out.println(latitude);
		//经度
		String longitude = paras[0].substring(23,33);
		System.out.println(longitude);
		
		//速度
		String speed  = paras[0].substring(34,39);
		System.out.println(speed);
		//格林尼治时间
		String greenwichTime  = paras[0].substring(39,45);
		System.out.println(greenwichTime);
		
		//角度
		String angle  = paras[0].substring(45,51);
		System.out.println(angle);

		//06000908000102:060为GSM信号,009为参与定位的卫星数,080为电池电量,0,为保留位,01为设防状态,02为工作模式 ,(设防,工作模式如果为00,则代表无或未设置)
		String gsm  = paras[0].substring(51,65);
		System.out.println(gsm);
		//MCC国家码,460为中国
		String mmc = paras[1];
		System.out.println(mmc);
		//MNC,0为移动
		String mnc = paras[2];
		System.out.println(mnc);
		//LAC,十进制
		String lac = paras[3];
		System.out.println(lac);
		//LAC,十进制
		String cid = paras[4];
		System.out.println(cid);
		//00为报警状态,00为无任何报警(01：SOS,02：低电,03：脱落报警)
		String alarmType = paras[5];
		System.out.println(alarmType);
		//设备语言
		String language = paras[6];
		System.out.println(language);
		
		//00:第一个0:是否需要回复地址信息,0:不回复,1回复.第二个0:地址信息中是否包含手机超链接,0不包含,1包含
		String otherFlag = paras[7];
		System.out.println(otherFlag);
		//Home|74-DE-2B-44-88-8C|97 : 一组WIFI信息，Home为SSID， 74-DE-2B-44-88-8C为MAC地址，97为信号强度，变量之间用“|”分隔开
		String wifiInfo = paras[8];
		System.out.println(wifiInfo);*/
		
	/*	//0:回复标识，为0时不回复地址，为 1 时，服务器回复地址信息
		String replyFlag  = paras[2];
		//7: 表示共有7组基站
		String locationNumbers = paras[3];
		//460: MCC 国家码
		String mmc = paras[4];
		//0: MNC运营商代码
		String mnc = paras[5];*/
		
		//9520|3671|10: LAC|CID|dbm 表示一组基站信息，后面数量需和基站组数地应  10：dbm为信号强度  多基站信号强度建议从高到低排列后上传
		
		
		String a = "201603251212";
		System.out.println(a.substring(2,a.length()));
		
		
		
		
		
		
	}
}
