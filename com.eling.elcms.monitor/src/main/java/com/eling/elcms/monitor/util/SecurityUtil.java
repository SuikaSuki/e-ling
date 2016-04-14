package com.eling.elcms.monitor.util;

import java.util.ArrayList;
import java.util.List;

import com.eling.elcms.monitor.model.Monitor;

public class SecurityUtil {
	 /** 
    * 加密解密算法 执行一次加密，两次解密 
    */   
   public static String convertMD5(String inStr){  
 
       char[] a = inStr.toCharArray();  
       for (int i = 0; i < a.length; i++){  
           a[i] = (char) (a[i] ^ 't');  
       }  
       String s = new String(a);  
       return s;  
 
   } 
   public static String encodeRstpLink(String ip, String port,String account ,String password){
	   return convertMD5(ip+":"+port+"");
   }

	

	public static void main(String[] args) throws Exception {
		List<Monitor> ms = new ArrayList<Monitor>();
		Monitor m = new Monitor();
		m.setIp("192.168.1.1");
		m.setPort("554");
		m.setPassword("123456");
		m.setAccount("admin");
		Monitor m1 = new Monitor();
		m1.setIp("192.168.1.1");
		m1.setPort("554");
		m1.setPassword("123456");
		m1.setAccount("admin");
		Monitor m2 = new Monitor();
		m2.setIp("192.168.1.1");
		m2.setPort("554");
		m2.setPassword("123456");
		m2.setAccount("admin");
		ms.add(m);
		ms.add(m1);
		ms.add(m2);
		//String a = JSON.toJSONString(ms); 
		String a = ""; 
		System.out.println(a);
		System.out.println(convertMD5(a));
		/*String s = convertMD5("192.168.1.1:8080admin");
		System.out.println(s);
		s=convertMD5(s);
		
		System.out.println(s);*/
	}
}