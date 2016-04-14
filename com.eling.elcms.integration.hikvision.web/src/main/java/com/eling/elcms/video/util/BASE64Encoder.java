package com.eling.elcms.video.util;

import java.io.IOException;
import java.util.Base64;

public class BASE64Encoder {
	public static void main(String[] args) throws Exception {
		/*
		 * String a = new String (Base64.getDecoder(). decode(
		 * "MTkyLjE2OC4xLjY0LDE5Mi4xNjguMS42NC81NTQsNTU0L2FkbWluLGFkbWluL2FkbWluMTIzLGFkbWluMTIzL3N1YixtYWluL+aRhOWDj+WktOWVijEs5pGE5YOP5aS05ZWKMg=="
		 * )); System.out.println(a); String b = new String
		 * (Base64.getEncoder().encode(
		 * "192.168.1.64,192.168.1.64/554,554/admin,admin/admin123,admin123/sub,main/摄像头啊1,摄像头啊2"
		 * .getBytes())); System.out.println(b);
		 */
		// 编码
		String asB64 = Base64.getEncoder().encodeToString("192.168.11.19,192.168.11.20/554,554/admin,admin/admin123,admin123/sub,main/摄像头君A,摄像头君B".getBytes("utf-8"));
		//System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=

		// 解码
		byte[] asBytes = Base64.getUrlDecoder().decode("MTkyLjE2OC4xMS4xMCwxOTIuMTY4LjExLjExLDE5Mi4xNjguMTEuMTIsMTkyLjE2OC4xMS4xMy81NTQsNTU0LDU1NCw1NTQvYWRtaW4sYWRtaW4sYWRtaW4sYWRtaW4vYWRtaW4xMjMsYWRtaW4xMjMsYWRtaW4xMjMsYWRtaW4xMjMvc3ViLHN1YixzdWIsc3ViL2MxLGMyLGMzLGM0");
		//System.out.println(new String(asBytes, "utf-8")); // 输出为: some string

		String basicEncoded = Base64.getEncoder().encodeToString("192.168.3.56,192.168.3.57,192.168.3.58,192.168.3.59/554,554,554,554/admin,admin,admin,admin/admin123,admin123,admin123,admin123/sub,sub,sub,sub/c1,c2,c3,c4".getBytes("utf-8"));
		System.out.println("Using Basic Alphabet: " + basicEncoded);

		String urlEncoded = Base64.getUrlEncoder().encodeToString("192.168.1.64,192.168.1.64/554,554/admin,admin/admin123,admin123/sub,main/c1,c2".getBytes("utf-8"));
		//System.out.println("Using URL Alphabet: " + urlEncoded);
	}
}
