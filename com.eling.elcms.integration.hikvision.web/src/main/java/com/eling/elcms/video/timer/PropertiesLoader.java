package com.eling.elcms.video.timer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader {
	public static String propertiesPath = null;
	public static String vlcPath = null;
	public static int portStart;
	public static int portEnd;
	
	/**
	 * 修改指定key的value
	 * @param propertiesName
	 * @param propertiesValue
	 */
	public static void changeProperties(String propertiesName,String propertiesValue){
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutStream = null;
		try {
			fileInputStream = new FileInputStream(propertiesPath);
			
			properties.load(fileInputStream);
			properties.setProperty(propertiesName, propertiesValue);
			fileOutStream = new FileOutputStream(propertiesPath);
			properties.store(fileOutStream, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				fileOutStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 取得指定key的value
	 * @param propertiesName
	 * @return
	 */
	public static String getProperties(String propertiesName){
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		String propertiesValue = null;
		try {
			fileInputStream = new FileInputStream(propertiesPath);
			properties.load(fileInputStream);
			propertiesValue = properties.getProperty(propertiesName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propertiesValue;
	}
	/**
	 * 项目启动时初始化所有参数
	 * @param path
	 */
	public static void initProperties(String path) {
		/*int classesIndex = path.indexOf("classes");
		path = path.substring(0, classesIndex) + "config/application.properties";*/
		path = path + "spring/config/application.properties";
		System.out.println("proterties Path = "+path);
		propertiesPath = path;
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(path);
			properties.load(fileInputStream);
			vlcPath = properties.getProperty("vlcPath");
			portStart = Integer.valueOf(properties.getProperty("portStart"));
			portEnd = Integer.valueOf(properties.getProperty("portEnd"));
			System.out.println("init"+properties.getProperty("vlcPath"));
			System.out.println("init"+properties.getProperty("portStart"));
			System.out.println("init"+properties.getProperty("portEnd"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
