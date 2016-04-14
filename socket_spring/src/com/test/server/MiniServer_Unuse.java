package com.test.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mina�����. ����಻����,������Ҫ�ֶ�������.
 * 
 * @author yan
 * 
 */
public class MiniServer_Unuse {
	private static final int port = 10001;

	public void start() throws IOException {
		// ���ڴ�������˵ļ���
		IoAcceptor acceptor = new NioSocketAcceptor();
		// acceptor.getFilterChain().addLast("logger", new LoggingFilter() );
		// ���������
		// acceptor.getFilterChain().addLast("encode", new
		// ProtocolCodecFilter(new
		// TextLineCodecFactory(Charset.forName("UTF-8"))));
		acceptor.getFilterChain().addLast("encode",
				new ProtocolCodecFilter(new MyCodecFactory()));
		// �����¼�������\
		acceptor.setHandler(new MinaServerHandler());
		// ���õ�ַ�Ͷ˿�
		acceptor.setDefaultLocalAddress(new InetSocketAddress("172.16.165.35",
				port));
		acceptor.bind();
		System.out.println("�����׼�����");
	}

	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext-minaServer.xml");
		try {
			new MiniServer_Unuse().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
