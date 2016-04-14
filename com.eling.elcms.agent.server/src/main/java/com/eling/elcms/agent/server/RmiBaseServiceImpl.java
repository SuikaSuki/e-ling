package com.eling.elcms.agent.server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.eling.elcms.agent.impl.IRmiBaseService;
import com.eling.elcms.agent.model.OperationConfig;

@Service("RmiBaseService")
public class RmiBaseServiceImpl implements IRmiBaseService, BeanFactoryAware, InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(RmiBaseServiceImpl.class);
	private ListableBeanFactory factory;
	@Resource(name="jdbcMap")
	private Map<String, JdbcTemplate> jdbcMap;

	private Map<String, OperationConfig> wsMap = new HashMap<>();

	public List<Object> queryDb(String sql, String ds) {
		log.debug("执行数据库查询，ds = {}， sql = {}", ds, sql);
		return jdbcMap.get(ds).query(sql, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				int columnCount = rs.getMetaData().getColumnCount();
				if (columnCount == 1) {
					return rs.getObject(1);
				} else {
					Object[] row = new Object[columnCount];
					for (int i = 0; i < columnCount; i++) {
						row[i] = rs.getObject(i + 1);
					}
					return row;
				}
			}
		});
	}

	public byte[] getUrl(String url) throws RemoteException {
		log.debug("请求内网网络资源，url = {}", url);
		return doRequest(new HttpGet(url));
	}

	private final static int TIME_OUT = ObjectUtils.CONST(60 * 1000); // 一分钟
	private static byte[] doRequest(HttpRequestBase req) {
		// 设置超时
		req.setConfig(RequestConfig.custom().setSocketTimeout(TIME_OUT).setConnectTimeout(TIME_OUT).build());

		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			HttpResponse response = httpclient.execute(req);
			return IOUtils.toByteArray(response.getEntity().getContent());
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public Object invokeWebServiceSync(String operationName, Object... params) throws Exception {
		OperationConfig config = wsMap.get(operationName);
		if(config==null || config.getOperationName()==null || config.getOperationName().isEmpty()) {
			return null;
		}
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		log.debug("wsdl:"+config.getWsdlAddr()+" | OperationName:"+config.getOperationName()
				+" | NameSpace:"+config.getTargetNamespace()+" | ServiceName:"+config.getServiceName());
		log.debug("before ——> createClient ");
        Client client = factory.createClient(config.getWsdlAddr(), new QName(config.getTargetNamespace(),config.getServiceName()));
        
        log.debug("before ——> client.invoke");
        Object[] o = client.invoke(new QName(config.getTargetNamespace(),config.getOperationName()), params);
        log.debug("after ——> client.invoke");
        return o[0];
 
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = (ListableBeanFactory) beanFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (String beanName : factory.getBeanDefinitionNames()) {
			if (beanName.startsWith("wsMap_")) {
				@SuppressWarnings("unchecked")
				Map<? extends String, ? extends OperationConfig> subMap = (Map<? extends String, ? extends OperationConfig>) factory.getBean(beanName);
				wsMap.putAll(subMap);
			}
		}
	}
}
