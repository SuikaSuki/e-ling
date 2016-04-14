package com.eling.elcms.video.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.context.ApplicationContext;

public class TestURL {
	public static void main(String[] args) throws IOException {
		new TestURL().readPropertites();
		//test3();
		/*test4();
		test3();
		test2();
		test();*/
	}
	
	public  void readPropertites(){
		
		try {
			/*String path = File.separator+"com.eling.elcms.integration.hikvision.web"+ File.separator + "src"+ File.separator + "main"+ File.separator + "webapp" + File.separator + "WEB-INF"
					+ File.separator + "config" + File.separator + "application.properties";*/
			String path = this.getClass().getResource(File.separator).getPath();
			System.out.println(path);
			String websiteURL = (path.replace("/build/classes", "").replace("%20"," ").replace("classes/", "") + "parameter.properties").replaceFirst("/", "");
			System.out.println(websiteURL);
			int classesIndex = path.indexOf("classes");
			path = path.substring(0,classesIndex)+"webapp/WEB-INF/config/application.properties";
			/*String path = ".."+File.separator+"com.eling.elcms.integration.hikvision.web"+ File.separator + "src"+ File.separator + "main"+ File.separator + "webapp" + File.separator + "WEB-INF"
					+ File.separator + "config" + File.separator + "application.properties";*/
			System.out.println(path);
			InputStream ins = this.getClass().getResourceAsStream(path);
			Properties p = new Properties();
			p.load(ins);
			if (p.containsKey("vlcPath")) {
				System.out.println(p.getProperty("vlcPath"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	/**
	 * 获取URL指定的资源。
	 *
	 * @throws IOException
	 */
	public static void test4() throws IOException {
		URL url = new URL("http://lavasoft.blog.51cto.com/attachment/200811/200811271227767778082.jpg");
		// 获得此 URL 的内容。
		Object obj = url.getContent();
		System.out.println(obj.getClass().getName());
	}

	/**
	 * 获取URL指定的资源
	 * @return 
	 *
	 * @throws IOException
	 */
	public static boolean test3() throws IOException {
		boolean isOpen = true;
		try {
			URL url = new URL("http://127.0.0.1:8080/192.168.1.64main");
			// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
			URLConnection uc = url.openConnection();
			// 打开的连接读取的输入流。
			InputStream in = uc.getInputStream();
			// 如果能读到URL中的stream则说明VLC服务争产
			if (in.read() != -1) {
				System.out.print("读取到视频流");
				isOpen = false;
			} else {
				isOpen = true;
				System.out.print("未读取到视频流");
			}
			in.close();
		} catch (Exception e) {
			isOpen = false;
			System.out.print("未读取到视频流");
		}
		return isOpen;
	}

	/**
	 * 读取URL指定的网页内容
	 *
	 * @throws IOException
	 */
	public static void test2() throws IOException {
		URL url = new URL("http://www.hrtsea.com/down/soft/45.htm");
		// 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
		Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()));
		int c;
		while ((c = reader.read()) != -1) {
			System.out.print((char) c);
		}
		reader.close();
	}

	/**
	 * 获取URL的输入流，并输出
	 *
	 * @throws IOException
	 */
	public static void test() throws IOException {
		URL url = new URL("http://lavasoft.blog.51cto.com/62575/120430");
		// 打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream。
		InputStream in = url.openStream();
		int c;
		while ((c = in.read()) != -1)
			System.out.print(c);
		in.close();
	}

}
