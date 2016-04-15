package com.eling.elcms.assistant.api.webapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eling.elcms.assistant.api.service.IAssistantInnerService;


/**
 * http调用服务
 * @author swq
 *
 */
@Controller
public class AssistantApiController {
	@Autowired
	private  IAssistantInnerService innerService;
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
		return innerService.callApi(imei, para);
    }


}
