package com.eling.elcms.video.webapp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;


import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eling.elcms.video.model.Monitor;
import com.eling.elcms.video.model.VideoProcess;
import com.eling.elcms.video.service.impl.CommandService;
import com.eling.elcms.video.timer.PropertiesLoader;
import com.eling.elcms.video.util.DateUtils;
import com.eling.elcms.video.util.EncrypDES;

/**
 * 调用VLC视频
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/video")
public class VideoController {
	
	/**
	 * 摄像头页面
	 * @param caremaIp
	 * @param caremaPort
	 * @param caremaAccount
	 * @param caremaSize
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/player/{cameraPara}")
	public String vdeio(@PathVariable String cameraPara,  Model model,HttpServletRequest request) {
		//String url = request.getScheme()+"://"+ request.getServerName()+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString();
		//取得参数并解码
		String para = null;
		try {
			para = new String(Base64.getUrlDecoder().decode(cameraPara),"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] paras = para.split("/");
		String caremaIp = paras[0];
		String caremaPort = paras[1];
		String caremaAccount = paras[2];
		String caremaPassword = paras[3];
		String caremaSize = paras[4];
		//String caremaName = paras[1];
		String serverIp  = request.getServerName();
		//String serverPort=CommandService.stack.pop();
		model.addAttribute("ip", caremaIp);
		model.addAttribute("port", caremaPort);
		model.addAttribute("size", caremaSize);
		model.addAttribute("serverIp", serverIp);  
		try {
			String serverPort = new CommandService().exeVlc(serverIp,caremaIp, caremaPort,caremaAccount+":"+caremaPassword,caremaSize);
			model.addAttribute("serverPort", serverPort);
			//等待VLC服务启动后 返回页面
			int i=0;
			while ((!CommandService.isVlcOpen("http://" + serverIp + ":" + serverPort + "/" + caremaIp + caremaSize))
					&& i <= 6) {
				i++;
				Thread.sleep(500);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "video";
	}
	
	/**
	 * 多个摄像头页面
	 * @param caremaIp
	 * @param caremaPort
	 * @param caremaAccount
	 * @param caremaSize
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/players/{cameraPara}")
	public String multipeVideo(@PathVariable String cameraPara,  Model model,HttpServletRequest request) {
		//String url = request.getScheme()+"://"+ request.getServerName()+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString();
		List<VideoProcess> videos = new ArrayList<VideoProcess>();
		//进程检测用
		List<VideoProcess> videosVlc = new ArrayList<VideoProcess>();
		try {
			String para = new String(Base64.getUrlDecoder().decode(cameraPara),"UTF-8");
			String[] paras = para.split("/");
			
			String cameraIps = paras[0];
			String[] cameraIpsArrays = cameraIps.split(",");
			
			String cameraPorts = paras[1];
			String[] cameraPortsArrays = cameraPorts.split(",");
			
			String cameraAccounts = paras[2];
			String[] cameraAccountsArrays = cameraAccounts.split(",");
			
			String cameraPasswords = paras[3];
			String[] cameraPasswordsArrays = cameraPasswords.split(",");
			
			String cameraSizes = paras[4];
			String[] cameraSizesArrays = cameraSizes.split(",");
			
			String cameraNames = paras[5];
			String[] cameraNamesArrays = cameraNames.split(",");
			
			for (int i = 0; i < cameraIpsArrays.length; i++) {
				String cameraIp = cameraIpsArrays[i];
				String cameraPort = cameraPortsArrays[i];
				String cameraAccount = cameraAccountsArrays[i];
				String cameraPassword = cameraPasswordsArrays[i];
				String cameraSize = cameraSizesArrays[i];
				String cameraName = cameraNamesArrays[i];
				VideoProcess video = new VideoProcess();
				video.setCaremaIp(cameraIp);
				video.setCaremaPort(cameraPort);
				video.setCaremaAccount(cameraAccount+":"+cameraPassword);
				video.setCaremaSize(cameraSize);
				video.setCaremaName(cameraName);
				videos.add(video);
				videosVlc.add(video);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "newMultipeVideo";
		} 
		//获取调用的路径
		String serverIp  = request.getServerName();
		model.addAttribute("serverIp", serverIp); 
		try {
			for (int i = 0; i < videos.size(); i++) {
				VideoProcess ve = videos.get(i);
				String serverPort = new CommandService().exeVlc(serverIp,ve.getCaremaIp(), ve.getCaremaPort(),ve.getCaremaAccount(),ve.getCaremaSize());
				ve.setServerPort(serverPort);
			}
			model.addAttribute("videos",videos);
			List<VideoProcess> videosReverse = new ArrayList<VideoProcess>();
			for (int i = videos.size()-1; i >=0; i--) {
				videosReverse.add(videos.get(i));
			}
			model.addAttribute("videosReverse",videosReverse);
			//等待VLC服务启动后 返回页面
			int i=0;
			while(videosVlc.size()!=0 && i<=5){
				i++;
				Iterator<VideoProcess> it = videosVlc.iterator();
				while (it.hasNext()) {
					VideoProcess vp= it.next();
					if(CommandService.isVlcOpen("http://"+serverIp+":"+vp.getServerPort()+"/"+vp.getCaremaIp()+vp.getCaremaSize())){
						 it.remove();
					}
				}
				//所有VLC已启动 返回页面
				if(videosVlc.size()==0){
					break;
				}else{
					//System.out.println("sleep  剩余" +videosVlc.size());
					//N秒后再次链接剩下的VLC服务
					Thread.sleep(500);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "newMultipeVideo";
	}
	/**
	 * 多个摄像头页面
	 * @param caremaIp
	 * @param caremaPort
	 * @param caremaAccount
	 * @param caremaSize
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/newplayers/{cameraPara}")
	public String multipeVideos(@PathVariable String cameraPara,  Model model,HttpServletRequest request) {
		return "newMultipeVideo";
	}
	
	/**
	 * 摄像头页面
	 * @param caremaIp
	 * @param caremaPort
	 * @param caremaAccount
	 * @param caremaSize
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/properties")
	public String initProperties(Model model) {
		//String url = request.getScheme()+"://"+ request.getServerName()+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString();
		model.addAttribute("vlcPath", PropertiesLoader.vlcPath);
		model.addAttribute("portStart", PropertiesLoader.portStart);
		model.addAttribute("portEnd", PropertiesLoader.portEnd);
		String vlcPath = PropertiesLoader.vlcPath;
		
		return "properties";
	}
	@RequestMapping(value="/saveProperties")
	public String saveProperties(HttpServletRequest request,Model model) {
		//String url = request.getScheme()+"://"+ request.getServerName()+request.getServerPort()+request.getRequestURI()+"?"+request.getQueryString();
		String vlcPath = (String) request.getParameter("vlcPath");
		String portStart = (String) request.getParameter("portStart");
		String portEnd = (String) request.getParameter("portEnd");
		if(StringUtils.isNotBlank(vlcPath)){
			PropertiesLoader.changeProperties("vlcPath", vlcPath);
			PropertiesLoader.vlcPath = vlcPath;
		}
		if(StringUtils.isNotBlank(portStart)){
			PropertiesLoader.changeProperties("portStart", portStart);
			PropertiesLoader.portStart = Integer.valueOf(portStart);
		}
		if(StringUtils.isNotBlank(portEnd)){
			PropertiesLoader.changeProperties("portEnd", portEnd);
			PropertiesLoader.portEnd = Integer.valueOf(portEnd);
		}
		model.addAttribute("message", "修改成功！");
		model.addAttribute("vlcPath", PropertiesLoader.vlcPath);
		model.addAttribute("portStart", PropertiesLoader.portStart);
		model.addAttribute("portEnd", PropertiesLoader.portEnd);
		return "properties";
	}
	/**
	 * VLC调用心跳
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("/videoAlive.json")
	public String videoAlive(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String vedioId = request.getParameter("videoId");
		String[] vedioIds = vedioId.split(",");
		if(vedioIds!=null && vedioIds.length>0){
			for (int i = 0; i < vedioIds.length; i++) {
				if (StringUtils.isNotBlank(vedioIds[i])) {
					VideoProcess vedioProcess = CommandService.getVedioProcessMap().get(vedioIds[i]);
					if (vedioProcess != null) {
						vedioProcess.setLastConnectTime(DateUtils.getNow());
					}
				}
			}
		}
		
		//model.addAttribute("msg", "good");
		return "jsonView";
	}
}
