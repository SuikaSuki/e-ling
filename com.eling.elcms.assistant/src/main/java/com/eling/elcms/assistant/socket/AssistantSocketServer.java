package com.eling.elcms.assistant.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class AssistantSocketServer implements InitializingBean, DisposableBean {
	@Autowired
	private MinaSessionManager msh;
	@Value("${socketPort}")
	private int socketPort;

	private NioSocketAcceptor nsa = new NioSocketAcceptor();

	void start() throws IOException {
		nsa.setDefaultLocalAddress(new InetSocketAddress("0.0.0.0", socketPort));
		nsa.setHandler(msh);
		nsa.setReuseAddress(true);

		DefaultIoFilterChainBuilder builder = new DefaultIoFilterChainBuilder();
		builder.addFirst("codecFilter", new ProtocolCodecFilter(new MyCodecFactory()));
		nsa.setFilterChainBuilder(builder);

		nsa.bind();
	}

	@Override
	public void destroy() throws Exception {
		nsa.unbind();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}

	class MyCodecFactory implements ProtocolCodecFactory {

	    private final TextLineEncoder encoder;

	    private final TextLineDecoder decoder;

	    /**
	     * Creates a new instance with the current default {@link Charset}.
	     */
	    public MyCodecFactory() {
	        encoder = new TextLineEncoder(Charset.forName("UTF-8"), "#");
	        decoder = new TextLineDecoder(Charset.forName("UTF-8"), "#");
	        decoder.setMaxLineLength(1024*1024);
	        encoder.setMaxLineLength(1024*1024);
	    }

	    public ProtocolEncoder getEncoder(IoSession session) {
	        return encoder;
	    }

	    public ProtocolDecoder getDecoder(IoSession session) {
	        return decoder;
	    }

	}
}
