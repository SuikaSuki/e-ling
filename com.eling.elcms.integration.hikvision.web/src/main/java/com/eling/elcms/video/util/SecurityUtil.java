package com.eling.elcms.video.util;

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

	

	public static void main(String[] args) throws Exception {
		String s = convertMD5("192.168.1.64,192.168.1.64admin,admin/admin123,admin123/");
		System.out.println(s);
		s=convertMD5(s);
		
		System.out.println(s);
	}
}
