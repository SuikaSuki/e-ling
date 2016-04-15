package com.eling.elcms.assistant.api.service.impl;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eling.elcms.assistant.api.model.AssistantLog;
import com.eling.elcms.assistant.api.service.IAssistantInnerService;
import com.eling.elcms.assistant.api.service.IAssistantLogManager;
import com.eling.elcms.assistant.api.service.IProtocolCommand;
import com.eling.elcms.assistant.api.service.protocol.impl.ProtocolCommandImpl;

@Service
public class AssistantInnerServiceImpl implements IAssistantInnerService, BeanFactoryAware {
	private ListableBeanFactory beanFactory;

	@Autowired
	private IAssistantLogManager assistantLogManager;

	@Override
	public String callApi(String imei, String para) {
		// 获取协议号
		String protocol = para.substring(0, 6);
		// 得到上下文环境
		IProtocolCommand protocolManager = findPc(protocol.substring(0, 6));
		// 上行协议
		String result = protocolManager.process(para, imei);

		// 保存为history
		String saveResult = (result != null && result.length() > 200) ? result.substring(0, 200) : result;
		AssistantLog info = new AssistantLog();
		info.setCreateDate(new Date());
		info.setProtocol(para.substring(0, 6));
		info.setInfo(para);
		info.setReturnInfo(saveResult);
		info.setImei(imei);
		assistantLogManager.save(info);
		// 返回的结果对象
		return result;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ListableBeanFactory) beanFactory;
	}

	private IProtocolCommand findPc(String protocol) {
		return beanFactory.getBeansOfType(IProtocolCommand.class).values().stream().filter((a) -> {
			return protocol.equals(a.getProtocol());
		}).findAny().orElse(beanFactory.getBean(ProtocolCommandImpl.class));
	}
}
