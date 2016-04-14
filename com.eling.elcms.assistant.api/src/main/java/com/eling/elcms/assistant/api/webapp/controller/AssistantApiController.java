package com.eling.elcms.assistant.api.webapp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.eling.elcms.assistant.api.model.view.Coordinate;
import com.eling.elcms.assistant.api.service.IAssistantManager;
import com.eling.elcms.assistant.api.service.ProtocolManager;
import com.eling.elcms.assistant.api.util.Contants;
import com.eling.elcms.assistant.api.util.DateUtils;


/**
 * http调用服务
 * @author swq
 *
 */
@Controller
public class AssistantApiController {
	/**
	 * 上行协议 被SOCKET服务调用
	 * @param para
	 * @param imei
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/api/callApi")
    @ResponseBody
    public String callApi(@RequestParam(value="para",required=false)  String para,
    		@RequestParam(value="imei",required=false)  String imei) throws IOException {  
		//获取协议号
		String protocol = para.substring(0, 6);
		// 得到上下文环境
		WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		String beanName = Contants.protocolMap.containsKey(protocol)?Contants.protocolMap.get(protocol):Contants.DEFAULT;
		ProtocolManager protocolManager =  (ProtocolManager) webContext.getBean(beanName);
		//上行协议
		String result = protocolManager.process(para, imei);
		//返回的结果对象
		return result;
    }
}
