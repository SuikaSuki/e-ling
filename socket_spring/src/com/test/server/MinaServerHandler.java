package com.test.server;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.test.action.SomeServer;

/**
 * mina����˵ĵ��¼�������
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
			System.out.println("�ͻ��� " + session.getRemoteAddress() + " �ر�������");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * ����˽�����Ϣ
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
		// �Կͻ�����������Ӧ
		for (Iterator<String> it = sessions.keySet().iterator(); it.hasNext();) {
			IoSession ss = sessions.get(it.next());
			ss.write("message from server:: " + session.getRemoteAddress()
					+ " ˵: " + msg);// ���ؿͻ��˷��͹�������Ϣ
		}
	}

	/**
	 * �ͻ������ӵĻỰ����
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		InetSocketAddress isa = (InetSocketAddress) session.getRemoteAddress();
		System.out.println("�ͻ���:" + isa.getAddress().getHostAddress() + ":"
				+ isa.getPort() + " ���ӽ�����");
		sessions.put(session.getRemoteAddress().toString(), session);
	}

	private ConcurrentMap<String, IoSession> sessions = new ConcurrentHashMap<String, IoSession>();
}
