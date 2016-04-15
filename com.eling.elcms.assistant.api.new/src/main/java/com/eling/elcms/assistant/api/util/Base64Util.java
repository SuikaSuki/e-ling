package com.eling.elcms.assistant.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Base64;

/**
 * 文件转换成base64字符
 * @author Administrator
 *
 */
public class Base64Util {
	 /**
	  * 将byte转成base64 字符串
	  * @param path文件路径
	  * @return  *
	  * @throws Exception
	  */

	 public static String encodeBase64Byte(byte[] buffer) throws Exception {
	  return Base64.getEncoder().encodeToString(buffer);

	 }
	 /**
	  * 将文件转成base64 字符串
	  * @param path文件路径
	  * @return  *
	  * @throws Exception
	  */

	 public static String encodeBase64File(String path,int byteStart, int byteEnd) throws Exception {
	  File file = new File(path);;
	  FileInputStream inputFile = new FileInputStream(file);
	  byte[] buffer = new byte[(int) file.length()];
	  
	  inputFile.read(buffer);
	  inputFile.close();
	  byte[] bufferIn = Arrays.copyOfRange(buffer, byteStart, byteEnd);
	  return Base64.getEncoder().encodeToString(bufferIn);

	 }

	 /**
	  * 将base64字符解码保存文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void decoderBase64File(String base64Code, String targetPath)
	   throws Exception {
	  byte[] buffer = Base64.getDecoder().decode(base64Code);
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();

	 }

	 /**
	  * 将base64字符保存文本文件
	  * @param base64Code
	  * @param targetPath
	  * @throws Exception
	  */

	 public static void toFile(String base64Code, String targetPath)
	   throws Exception {

	  byte[] buffer = base64Code.getBytes();
	  FileOutputStream out = new FileOutputStream(targetPath);
	  out.write(buffer);
	  out.close();
	 }

	 public static void main(String[] args) {
	  try {
		String base64Code = encodeBase64File("E:\\FFOutput\\test.amr",0,5000);
		   System.out.println(base64Code);
		   System.out.println(base64Code.length());
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
}
