package com.dariuszpaluch;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import sun.misc.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.*;

public class TPSIServer {

	public static void main(String[] args) throws Exception {
		int port = 8000;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/", new RootHandler());
		System.out.println("Starting server on port: " + port);
		server.start();
	}

	static class RootHandler implements HttpHandler {
		private final HashMap<String, Integer> statistics;

		public RootHandler() {
			this.statistics = new HashMap<>();
		}

		public void addPathToStatistics(String path) {
			if(statistics.get(path) == null ){
				statistics.put(path, 1);
			}
			else {
				statistics.put(path, statistics.get(path) + 1);
			}
			System.out.println("Add to statistics: " + path);
		}

		public void handle(final HttpExchange exchange) throws IOException {
			String path = exchange.getRequestURI().toString();
			String method = exchange.getRequestMethod(); //type of call: GET, PUT, ..
			Map<String, List<String>> headers = exchange.getRequestHeaders();
			String host = headers.get("HOST").get(0);

			this.addPathToStatistics(host);

			//request
			URL url = new URL(path);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod(method);

			headers.forEach((k,v) -> {
				String name = k;
				List<String> values = v;

				for(String value : values) {
					httpCon.setRequestProperty(name, value);
				}
			});

//			connection.connect();

//			BufferedReader in = new BufferedReader(
//					new InputStreamReader(
//						connection.getInputStream()
//					)
//			);
//
//			int status = connection.getResponseCode();
//			InputStream response = connection.getInputStream();
//			byte[] body = IOUtils.readFully(response, -1, true);
//
//			OutputStream responseBody = exchange.getResponseBody();
//			exchange.sendResponseHeaders(status, body.length);

//			responseBody.write(body);
//			responseBody.close();
//			String response = "Hello World!";
//			exchange.getResponseHeaders().set("Content-Type", "text/plain");
//			exchange.sendResponseHeaders(200, response.length());
//			OutputStream os = exchange.getResponseBody();
//			os.write(response.getBytes());
//			os.close();
		}
	}
}
