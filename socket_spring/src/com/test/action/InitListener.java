package com.test.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		new ClassPathXmlApplicationContext("applicationContext-minaServer.xml");
		// ��tomcat������������,�ῴ������̨��ӡ�����.
		System.out.println("********mina server �������*********");
	}
}
