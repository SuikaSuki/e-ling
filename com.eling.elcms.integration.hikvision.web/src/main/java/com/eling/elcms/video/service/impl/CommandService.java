package com.eling.elcms.video.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.eling.elcms.video.model.VideoProcess;
import com.eling.elcms.video.timer.PropertiesLoader;
import com.eling.elcms.video.timer.StreamGobbler;
import com.eling.elcms.video.util.DateUtils;

public class CommandService {

	public static Map<String, VideoProcess> vedioProcessMap = new HashMap<String, VideoProcess>();
	public static Stack<String> stack = new Stack<String>() ;
	
	public static Map<String, VideoProcess> getVedioProcessMap() {
		return vedioProcessMap;
	}

	public static void setVedioProcessMap(Map<String, VideoProcess> vedioProcessMap) {
		CommandService.vedioProcessMap = vedioProcessMap;
	}
	

	/**
	 * 打开VLC进程 返回服务器视频流端口号
	 * @param caremaIp
	 * @param caremaPort
	 * @param caremaAccount
	 * @param caremaSize
	 * @param serverPort
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String exeVlc(String  serverIp, String caremaIp,String caremaPort,String caremaAccount,String caremaSize) throws IOException, InterruptedException {
		String vedioId = caremaIp+caremaPort+caremaSize;
		//TODO 同一摄像头不可同时输出sub和main 若存在sub则需要关掉main
		VideoProcess oldVedioProcess = vedioProcessMap.get(vedioId);
		//不存在VLC进程则新开启 否则刷新进程调用时间
		if(oldVedioProcess!=null){
			//vlc正常连接
			if(isVlcOpen("http://"+serverIp+":"+oldVedioProcess.getServerPort()+"/"+oldVedioProcess.getCaremaIp()+oldVedioProcess.getCaremaSize())){
				oldVedioProcess.setLastConnectTime(DateUtils.getNow());
				return oldVedioProcess.getServerPort();
			}else{
				String nowTime = DateUtils.getNow();
				long second = DateUtils.getSubstractSecond(nowTime, oldVedioProcess.getLastConnectTime());
				if(second<60){
					//60秒内启动的VLC 默认正在创建服务 
					return oldVedioProcess.getServerPort();
				}
				//不能读取到VLC提供的数据
				oldVedioProcess.getErrorGobbler().interrupt();
				oldVedioProcess.getOutputGobbler().interrupt();
				oldVedioProcess.getProcess().destroy();
				//返还端口
				CommandService.stack.push(oldVedioProcess.getServerPort());
				vedioProcessMap.remove(vedioId);
			}
			
		}
		String serverPort=CommandService.stack.pop();
		//String cmd = "cd  "+PropertiesLoader.getProperties("vlcPath")+" \r";
		String cmd = PropertiesLoader.vlcPath+" -vvv \"rtsp://"+caremaAccount+"@"+caremaIp+":"+caremaPort+"/h264/ch1/"+caremaSize+"/av_stream\" --sout \"#standard{access=http,mux=flv,dst=:"+serverPort+"/"+caremaIp+caremaSize+"}\"";
		
		System.out.println(cmd);
		Process proc = Runtime.getRuntime().exec(cmd);
		
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");
		//errorGobbler.readStream();
		//outputGobbler.readStream();
		errorGobbler.start();
		outputGobbler.start();
		//保存进程
		VideoProcess newVedioProcess = new VideoProcess();
		newVedioProcess.setCaremaIp(caremaIp);
		newVedioProcess.setCaremaPort(caremaPort);
		newVedioProcess.setServerPort(serverPort);
		newVedioProcess.setCaremaSize(caremaSize);
		newVedioProcess.setLastConnectTime(DateUtils.getNow());
		newVedioProcess.setProcess(proc);
		newVedioProcess.setErrorGobbler(errorGobbler);
		newVedioProcess.setOutputGobbler(outputGobbler);
		vedioProcessMap.put(vedioId, newVedioProcess);
		return serverPort;
	}
	
	/**
	 * 关闭所有VLC进程
	 * @throws IOException 
	 */
	public static void shutDownVlc() throws IOException{
		String cmd = "taskkill /im VLC.exe";
		Process proc = Runtime.getRuntime().exec(cmd);
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "Error");
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "Output");
		errorGobbler.start();
		outputGobbler.start();
	}
	
	/**
	 * 确认当前进程的VLC是否返回视频流
	 * @return
	 */
	public static boolean isVlcOpen(String address){
		boolean isOpen = true;
		try {
			URL url = new URL(address);
			// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
			URLConnection uc = url.openConnection();
			// 打开的连接读取的输入流。
			InputStream in = uc.getInputStream();
			// 如果能读到URL中的stream则说明VLC服务争产
			if (in.read() != -1) {
				//System.out.print("读取到视频流");
				isOpen = true;
			} else {
				isOpen = false;
				//System.out.print("未读取到视频流");
			}
			in.close();
		} catch (Exception e) {
			isOpen = false;
		}
		return isOpen;
	}
/*
	public static List<Process> processList = new ArrayList<Process>();

	public static void exeCmd(String commandStr) {
		BufferedReader br = null;
		try {
			Process p = Runtime.getRuntime().exec(commandStr);
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void exeCmd2(String cmd) {
		Runtime run = Runtime.getRuntime();// �����뵱ǰ Java Ӧ�ó�����ص�����ʱ����
		try {
			System.out.println(1);
			Process p = run.exec(cmd);// ������һ��������ִ������
			processList.add(p);
			System.out.println(2);
			
			 * if (p.waitFor() != 0) { System.out.println(3); if (p.exitValue()
			 * == 1)//p.exitValue()==0��ʾ����������1������������
			 * System.err.println("����ִ��ʧ��!"); } System.out.println(4);
			 
			
			 * BufferedInputStream in = new
			 * BufferedInputStream(p.getInputStream()); BufferedReader inBr =
			 * new BufferedReader(new InputStreamReader(in)); String lineStr;
			 * while ((lineStr = inBr.readLine()) != null) //�������ִ�к��ڿ���̨�������Ϣ
			 * System.out.println(lineStr);// ��ӡ�����Ϣ //��������Ƿ�ִ��ʧ�ܡ� if
			 * (p.waitFor() != 0) { if (p.exitValue() ==
			 * 1)//p.exitValue()==0��ʾ����������1������������ System.err.println("����ִ��ʧ��!");
			 * } inBr.close(); in.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exeCmd3(String cmd) {
		ProcessBuilder pb = new ProcessBuilder(cmd);
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String commandStr = "vlc -vvv \"rtsp://admin:abc12345@192.168.1.64:554/h264/ch1/sub/av_stream\" --sout #standard{access=http,mux=flv,dst=:8081/test}";
		// String commandStr = "cmd /c copy d:\\tt.mp4 e:\\" ;
		// String commandStr = "ipconfig -all";
		// String commandStr = "ipconfig";
		// Command.exeCmd(commandStr);
		Command.exeCmd2(commandStr);
	}*/
	public static void main (String args[]){
		System.out.println(isVlcOpen("http://127.0.0.1:49999/192.168.1.64sub"));
	}
}
