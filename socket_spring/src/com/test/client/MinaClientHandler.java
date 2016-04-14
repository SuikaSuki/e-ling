package com.test.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * �ͻ����¼�������
 * 
 * @author yan
 */
public class MinaClientHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("����˹ر�������");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("�ͻ��˽��յ�����Ϣ: " + message);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("�ͻ��������Ϸ�����");
	}
}
