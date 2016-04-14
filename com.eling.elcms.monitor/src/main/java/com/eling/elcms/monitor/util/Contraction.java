package com.eling.elcms.monitor.util;

public class Contraction {
	public static String tempString = "acdesfghsadajfkqsfab";


	/**
	  * 利用字节码的空间去进行压缩
	  * @param args
	  */
	public static void main(String[] args) {
		 /*List<Monitor> ms = new ArrayList<Monitor>();
			Monitor m = new Monitor();
			m.setIp("192.168.1.64");
			m.setPort("554");
			m.setAccount("admin");
			m.setPassword("admin123");
			Monitor m1 = new Monitor();
			m1.setIp("192.168.1.64");
			m1.setPort("554");
			m1.setAccount("admin");
			m1.setPassword("admin123");
			ms.add(m);
			ms.add(m1);
			String a = JSON.toJSONString(ms); 
			tempString = a;
	  System.out.println("压缩前字符串内容："+tempString);
	  System.out.println("压缩前字符串大小:"+tempString.length());
	  
	  String resultString = compactString(tempString);
	  System.out.println("压缩后字符串内容："+resultString);
	  System.out.println("压缩后字符串大小："+resultString.length());
	  
	  String convertString = decompressionString(resultString);
	  System.out.println("解压后字符串内容："+convertString);
	  System.out.println("解压后字符串大小："+convertString.length());*/
	}

	/**
	  * 通过接口compactString()的压缩方式进行解压
	  * @param tempString
	  * @return
	  */
	public static String decompressionString(String tempString){
	  char[] tempBytes = tempString.toCharArray();
	  StringBuffer sb = new StringBuffer();
	  for (int i = 0; i < tempBytes.length; i++) {
	   char c = tempBytes[i];
	   char firstCharacter = (char) (c >>> 8);
	   char secondCharacter = (char) ((byte)c);
	   sb.append(firstCharacter);
	   if(secondCharacter != 0)
	    sb.append(secondCharacter);
	  }
	  return sb.toString();
	}


	/**
	  * 对需要进行压缩的字符串进行压缩，返回一个相对较小的字符串
	  * @param tempString
	  * @return
	  */
	public static String compactString(String tempString) {
	  StringBuffer sb = new StringBuffer();
	  byte[] tempBytes = tempString.getBytes();
	  for (int i = 0; i < tempBytes.length; i+=2) {
	   char firstCharacter = (char)tempBytes[i];
	   char secondCharacter = 0;
	   if(i+1<tempBytes.length)
	    secondCharacter = (char)tempBytes[i+1];
	   firstCharacter <<= 8;
	   sb.append((char)(firstCharacter+secondCharacter));
	  }
	  return sb.toString();
	}
}
