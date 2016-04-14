package com.eling.elcms.assistant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private static final Logger log = LoggerFactory.getLogger(App.class);
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String configLocation = null;
		if (args.length == 1) {
				configLocation = args[0];
				log.debug("用配置文件 {} 启动应用", configLocation);
		} else {
			configLocation = "classpath*:/applicationContext.xml";
		}
		new ClassPathXmlApplicationContext(configLocation);
	}

}
