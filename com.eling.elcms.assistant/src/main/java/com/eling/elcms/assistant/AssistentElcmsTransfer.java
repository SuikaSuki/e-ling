package com.eling.elcms.assistant;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.http.ElcmsSender;
import com.eling.elcms.assistant.http.IHandler;
import com.eling.elcms.assistant.http.MyHttpServer;
import com.eling.elcms.assistant.socket.IAssistantHandler;
import com.eling.elcms.assistant.socket.IAssistantSender;
import com.sun.net.httpserver.HttpExchange;

@Service
@Lazy(false)
public class AssistentElcmsTransfer implements IAssistantHandler, InitializingBean {
	@Autowired
	private ElcmsSender elcmsSender;

	private IAssistantSender assistantsender;

	@Value("${httpPort}")
	private int httpPort;

	public void start() throws IOException {
		MyHttpServer httpServer = new MyHttpServer();
		httpServer.createContext("/assistant/sendCommond.do", new IHandler() {
			@Override
			public void handle(HttpExchange httpExchange, Map<String, String> parameters) throws IOException {
				String imei = parameters.get("imei");
				String para = parameters.get("para");
				String result = String.valueOf(assistantsender.sendToAssistant(imei, para));
				httpExchange.sendResponseHeaders(200, result.length()); // 设置响应头属性及响应信息的长度
				IOUtils.write(result, httpExchange.getResponseBody());
				httpExchange.close();
			}
		});

		httpServer.createContext("/assistant/getAllImei.do", new IHandler() {
			@Override
			public void handle(HttpExchange httpExchange, Map<String, String> parameters) throws IOException {
				String imeis = StringUtils.join(assistantsender.allImei(), ",");
				httpExchange.sendResponseHeaders(200, imeis.length()); // 设置响应头属性及响应信息的长度
				IOUtils.write(StringUtils.join(assistantsender.allImei(), ","), httpExchange.getResponseBody());
				httpExchange.close();
			}
		});
		httpServer.start(httpPort);
	}

	@Override
	public void setSender(IAssistantSender sender) {
		this.assistantsender = sender;
	}

	@Override
	public String messageReceived(String imei, String msg) {
		return elcmsSender.send(msg, imei);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

}
