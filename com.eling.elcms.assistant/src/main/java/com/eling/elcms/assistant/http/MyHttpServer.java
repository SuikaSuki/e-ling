package com.eling.elcms.assistant.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

public class MyHttpServer {

	private static final Logger log = LoggerFactory.getLogger(MyHttpServer.class);

	private ConcurrentMap<String, IHandler> handlers = new ConcurrentHashMap<>();

	public void createContext(String contextPath, IHandler handler) {
		handlers.put(contextPath, handler);
	}

	public void start(int port) throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(port), 100);
		handlers.entrySet().stream().forEach(e->httpserver.createContext(e.getKey(), new HandlerWrapper(e.getValue())));
		httpserver.start();
		log.debug("http server started");
	}

	private class HandlerWrapper implements HttpHandler {
		private IHandler handler;
		HandlerWrapper(IHandler handler) {
			this.handler = handler;
		}
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			Map<String, String> parameters = new HashMap<>();
			URI requestedUri = httpExchange.getRequestURI();
			String query = requestedUri.getRawQuery();
			parseQuery(query, parameters);
			parsePostParameters(httpExchange, parameters);
			handler.handle(httpExchange, parameters);
		}
	}

	private void parsePostParameters(HttpExchange exchange, Map<String, String> parameters) throws IOException {
		if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
			parseQuery(IOUtils.toString(exchange.getRequestBody(), "utf-8"), parameters);
		}
	}

	private void parseQuery(String query, Map<String, String> parameters) throws UnsupportedEncodingException {
		if (query != null) {
			String pairs[] = query.split("[&]");

			for (String pair : pairs) {
				String param[] = pair.split("[=]");

				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], "utf-8");
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], "utf-8");
				}
				parameters.put(key, value);
			}
		}
	}
}
