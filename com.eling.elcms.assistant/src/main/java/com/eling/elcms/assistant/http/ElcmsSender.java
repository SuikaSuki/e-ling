package com.eling.elcms.assistant.http;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class ElcmsSender {
	private static final Logger logger = LoggerFactory.getLogger(ElcmsSender.class);
	
	@Autowired
	private JdbcTemplate jdbcTemp;

	private Map<String, String> imeiUrlMap = new HashMap<String, String>();
	
	/**
	 * 把转码后的socket参数传给平台
	 * 
	 * @param para
	 * @param imei 
	 * @throws Exception 
	 */
	public String send(String para, String imei) {
		try {
			// 创建名/值组列表
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("para", para));
			parameters.add(new BasicNameValuePair("imei", imei));
			// 创建UrlEncodedFormEntity对象
			UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters, "UTF-8");

			// 实例化HTTP方法
			HttpPost request = new HttpPost(getImeiUrl(imei));
			request.setEntity(formEntiry);

			// 执行请求
			CloseableHttpClient httpclient = HttpClients.createDefault();
			String response = IOUtils.toString(httpclient.execute(request).getEntity().getContent());
			return response.substring(1, response.length()-1);
		} catch (Exception e) {
			throw new RuntimeException("http调用异常，imei:" + imei + ",para:"+para, e);
		}
	}

	private String getImeiUrl(String imei){
		//测试需要 不使用缓存
		String url = queryUrl(imei);
		/*String url = imeiUrlMap.get(imei);
		if (StringUtils.isBlank(url)) {
			url = queryUrl(imei);
			imeiUrlMap.put(imei, url);
		}*/
		return url;
	}

	private String queryUrl(String imei) {
		String sql = "select uc.url from url_config uc left join assistant_url_config auc on auc.pkUrlConfig=uc.pkUrlConfig  where auc.imei='"+imei+"'";
		logger.debug("执行数据库查询，sql = {}", sql);
		return jdbcTemp.queryForObject(sql, String.class);
	}

}
