package com.eling.elcms.video.timer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.eling.elcms.video.model.VideoProcess;
import com.eling.elcms.video.service.impl.CommandService;
import com.eling.elcms.video.util.DateUtils;

public class VedioTask implements InitializingBean{

	
	private static Logger log = LoggerFactory.getLogger(VedioTask.class); 
	
    /* 
     * 启动时关闭所有的VLC进程 同时读取properties文件
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet()  
    {  	
    	String path = this.getClass().getResource("/").getPath();
    	PropertiesLoader.initProperties(path);
    	for (int i = PropertiesLoader.portEnd; i >=PropertiesLoader.portStart ; i--) {
			CommandService.stack.push(String.valueOf(i));
		}
    	try {
			CommandService.shutDownVlc();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	for (int i = PropertiesLoader.portEnd; i >=PropertiesLoader.portStart ; i--) {
			CommandService.stack.push(String.valueOf(i));
		}
    	
    }  
	public void execute() {
		// 循环取得所有VLC进程，遍历释放N分钟未调用的。
		// 超时N秒则关闭
		long secondOutTime = 5*60;
		// 当前系统时间
		String nowTime = DateUtils.getNow();
		Iterator<Entry<String, VideoProcess>> it = CommandService.getVedioProcessMap().entrySet().iterator();
		while (it.hasNext()) {
			
			Entry<String, VideoProcess> entry = it.next();
			VideoProcess vedioProcess = entry.getValue();
			//System.out.println(vedioProcess.getProcess().isAlive());
			// VLC线程最后调用时间
			String oldTime = vedioProcess.getLastConnectTime();
			long second = DateUtils.getSubstractSecond(nowTime, oldTime);
			System.out.println(entry.getKey() + "VLC未访问超过秒：" + second);
			// 超时未调用或者进程已死
			if ((second >= secondOutTime) || !vedioProcess.getProcess().isAlive()) {
				vedioProcess.getErrorGobbler().interrupt();
				vedioProcess.getOutputGobbler().interrupt();
				vedioProcess.getProcess().destroy();
				CommandService.stack.push(vedioProcess.getServerPort());
				System.out.println("释放端口："+vedioProcess.getServerPort());
				it.remove();
			}
		}

	}
}
