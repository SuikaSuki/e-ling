package com.test.server;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.test.action.SomeServer;

/**
 * mina服务端的的事件处理类
 * 
 * @author yan
 */
public class MinaServerHandler extends IoHandlerAdapter {
	private SomeServer someServer;

	public void setSomeServer(SomeServer someServer) {
		this.someServer = someServer;
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		try {
			System.out.println("客户端 " + session.getRemoteAddress() + " 关闭了连接");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 服务端接收消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		someServer.doSome();
		String msg = message.toString();
		if ("quit".equals(msg)) {
			session.close(false);
			return;
		}
		// 对客户端做出的响应
		for (Iterator<String> it = sessions.keySet().iterator(); it.hasNext();) {
			IoSession ss = sessions.get(it.next());
			ss.write("message from server:: " + session.getRemoteAddress()
					+ " 说: " + msg);// 返回客户端发送过来的消息
		}
	}

	/**
	 * 客户端连接的会话创建
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		System.out.println("客户端:" + isa.getAddress().getHostAddress() + ":"
				+ isa.getPort() + " 连接进来了");
		sessions.put(session.getRemoteAddress().toString(), session);
	}

	private ConcurrentMap<String, IoSession> sessions = new ConcurrentHashMap<String, IoSession>();
}
