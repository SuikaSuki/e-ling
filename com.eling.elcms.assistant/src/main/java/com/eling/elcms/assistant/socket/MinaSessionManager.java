package com.eling.elcms.assistant.socket;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mina服务端的的事件处理类
 * 
 * @author swq
 */
@Service
public class MinaSessionManager extends IoHandlerAdapter implements InitializingBean, IAssistantSender {
	private static final Logger logger = LoggerFactory.getLogger(MinaSessionManager.class);

	@Autowired
	private IAssistantHandler handler;

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		removeSession(session);
		logger.error("客户端 异常:{}关闭了连接, error is {}", session.getRemoteAddress(), ExceptionUtils.getStackTrace(cause));
	}

	/**
	 * 服务端接收消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message) {
		if (StringUtils.isBlank(message.toString())) {
			return;
		}
		logger.debug("服务器接收到：" + message);
		String mes = (String) message;
		// 登录特殊处理 记录机器的IMEI号
		// TODO 增加注释
		if (mes.startsWith("IWAP00")) {
			String imei = mes.substring(6, 21);

			IMEIsessions.put(imei, session.getRemoteAddress());
			sessionsIMEI.put(session.getRemoteAddress(), imei);
			// session.write("IWBP00,20160301164200,8");
		}

		try {
			String result = handler.messageReceived(sessionsIMEI.get(session.getRemoteAddress()), message.toString());
			if (StringUtils.isNotBlank(result)) {
				session.write(result);
			} else {
				// TODO 增加异常处理
			}
			logger.debug("服务器返回:{}", result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 客户端连接的会话创建
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		logger.debug("客户端:{}:{}连接进来了", isa.getAddress().getHostAddress(), isa.getPort());
		sessions.put(session.getRemoteAddress(), session);
	}

	/**
	 * 客户端连接的会话关闭
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		logger.debug("客户端session端:{}:{}关闭了", isa.getAddress().getHostAddress(), isa.getPort());
		super.sessionClosed(session);

		removeSession(session);
	}

	private void removeSession(IoSession session) {
		// 同时删除3个MAP中记录的session和imei
		IMEIsessions.remove(sessionsIMEI.get(session.getRemoteAddress()));
		sessions.remove(session.getRemoteAddress());
		sessionsIMEI.remove(session.getRemoteAddress());
	}

	private ConcurrentMap<SocketAddress, IoSession> sessions = new ConcurrentHashMap<>();
	// IMEI为key sessionId为value
	private ConcurrentMap<String, SocketAddress> IMEIsessions = new ConcurrentHashMap<>();
	// sessionId地址为key IMEI为value
	private ConcurrentMap<SocketAddress, String> sessionsIMEI = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		handler.setSender(this);
	}

	@Override
	public boolean sendToAssistant(String imei, String message) {
		boolean flag = true;
		// 此IEME号的机器没有连接服务器
		if (!IMEIsessions.containsKey(imei)) {
			return false;
		} else {
			SocketAddress sessionId = IMEIsessions.get(imei);
			// 机器注册后 离线了
			if (!sessions.containsKey(sessionId)) {
				return false;
			}
			IoSession session = sessions.get(sessionId);
			if (StringUtils.isNotEmpty(message)) {
				session.write(message);// 发送信息到客户端
			}
			return flag;
		}
	}

	@Override
	public List<String> allImei() {
		return new ArrayList<String>(IMEIsessions.keySet());
	}
}
