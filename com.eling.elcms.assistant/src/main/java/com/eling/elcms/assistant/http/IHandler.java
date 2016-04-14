package com.eling.elcms.assistant.http;

import java.io.IOException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public interface IHandler {
	void handle(HttpExchange httpExchange, Map<String, String> parameters) throws IOException;
}