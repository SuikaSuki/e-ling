package com.eling.elcms.assistant.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantLog;

/**
 * @author Administrator
 *
 */
@Service
public class AssistantSender {
	
	@Value("${assistant.api.socket.url}")
	public String socketUrl;
	
	@Autowired
	private IAssistantLogManager assistantLogManager;
	
	
	
	private String allImeiUrl = "http://117.121.26.96:50032/assistant/getAllImei.do";
	/**
	 * 把转码后的参数传给socket平台
	 * 
	 * @param para
	 * @param imei
	 */
	public String callApi(String para, String imei) {
		try {
			// 创建名/值组列表
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("para", para));
			parameters.add(new BasicNameValuePair("imei", imei));
			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, "UTF-8");

			// 实例化HTTP方法
			HttpPost request = new HttpPost(socketUrl);
			request.setEntity(formEntiry);
			// 执行请求
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String response = IOUtils.toString(httpclient.execute(request).getEntity().getContent());
			
			// 保存为history
			AssistantLog info = new AssistantLog();
			info.setCreateDate(new Date());
			info.setProtocol(para.substring(0, 6));
			String savePara = (para!=null && para.length()>200)?para.substring(0,200):para;
			info.setInfo(savePara);
			info.setReturnInfo(response);
			info.setImei(imei);
			assistantLogManager.save(info);
			
			return response;
		} catch (Exception e) {
			throw new RuntimeException("http调用异常，imei:" + imei + ",para:" + para, e);
		}
	}

	/**
	 * 取得所有在线的imei号(测试用)
	 */
	public String getAllImei() {
		try {
			// 创建名/值组列表
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, "UTF-8");

			// 实例化HTTP方法
			HttpPost request = new HttpPost(allImeiUrl);
			request.setEntity(formEntiry);
			// 执行请求
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String response = IOUtils.toString(httpclient.execute(request).getEntity().getContent());
			return response;
		} catch (Exception e) {
			throw new RuntimeException("取得所有在线的imei号(测试用)调用异常", e);
		}
	}

}
