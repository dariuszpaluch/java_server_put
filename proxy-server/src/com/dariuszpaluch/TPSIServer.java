package com.dariuszpaluch;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import sun.misc.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		}

		public void handle(final HttpExchange exchange) throws IOException {
			String path = exchange.getRequestURI().toString();
			String method = exchange.getRequestMethod(); //type of call: GET, PUT, ..
			Map<String, List<String>> headers = exchange.getRequestHeaders();
			String host = headers.get("HOST").get(0);

			this.addPathToStatistics(host);


			System.out.println(path);
//			if(statistics.get(path) > 0) {
//				statistics.replace(path, statistics.get(path), statistics.get(path) + 1);
//			}
//			statistics.put(path, 1);

			URL u = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod(method);

			Set<String> keys = exchange.getRequestHeaders().keySet();
			for(String key: keys) {
				List<String> values = exchange.getRequestHeaders().get(key);
				for(String value: values) {
					connection.setRequestProperty(key, value);
				}
			}
			connection.connect();

			BufferedReader in = new BufferedReader(
					new InputStreamReader(
						connection.getInputStream()
					)
			);

			int status = connection.getResponseCode();
			InputStream response = connection.getInputStream();
			byte[] body = IOUtils.readFully(response, -1, true);

			OutputStream responseBody = exchange.getResponseBody();
//			exchange.sendResponseHeaders(status, body.length);

//			responseBody.write(body);
//			responseBody.close();
//			String response = "Hello World!";
//			exchange.getResponseHeaders().set("Content-Type", "text/plain");
//			exchange.sendResponseHeaders(200, response.length());
//			OutputStream os = exchange.getResponseBody();
//			os.write(response.getBytes());
//			os.close();
			addPathToStatistics(path);
		}
	}
}
