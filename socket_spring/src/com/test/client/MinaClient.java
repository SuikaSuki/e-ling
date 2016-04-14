package com.test.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * �ͻ���
 * 
 * @author yan
 */
public class MinaClient {
	public void start() throws IOException {
		NioSocketConnector connector = new NioSocketConnector();
		// connector.getFilterChain().addLast( "logger", new LoggingFilter() );
		// ���ñ��������
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		connector.setConnectTimeoutMillis(10000);
		connector.setHandler(new MinaClientHandler());
		ConnectFuture cf = connector.connect(new InetSocketAddress(
				"172.16.165.35", 10000));
		// �ȴ����Ӵ������
		cf.awaitUninterruptibly();
		IoSession session = cf.getSession();

		System.out.println("�ͻ���׼�����");
		// �����ǵȴ�����̨������
		while (true) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String line = in.readLine();
			try {
				if (!"".equals(line)) {
					session.write(line);
				} else if ("quit".equals(line)) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("����˹ر�������");
			}
		}
		session.getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}

	public static void main(String[] args) {
		try {
			new MinaClient().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
