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
		int port = 8003;
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
			statistics.merge(path, 1, (a, b) -> a + b);
			System.out.println("Add to statistics: " + path);
		}

		public static byte[] readFully(InputStream input) throws IOException
		{
			byte[] buffer = new byte[8192];
			int bytesRead;
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			while ((bytesRead = input.read(buffer)) != -1)
			{
				output.write(buffer, 0, bytesRead);
			}
			return output.toByteArray();
		}

		public void handle(final HttpExchange exchange) throws IOException {
			//get request from client
			String path = exchange.getRequestURI().toString();
			String method = exchange.getRequestMethod(); //type of call: GET, PUT, ..
			Map<String, List<String>> headers = exchange.getRequestHeaders();
			String host = headers.get("HOST").get(0);

			this.addPathToStatistics(host);

			//set request from client to server
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);

			headers.forEach((key, values) -> {
				for(String value : values) {
					connection.setRequestProperty(key, value);
				}
			});

			if(method.equals("POST") || method.equals("PUT")) {
				connection.setDoOutput(true);

				byte[] tmp = IOUtils.readFully(exchange.getRequestBody(), -1, true);
				OutputStream requestBody = connection.getOutputStream();
				requestBody.write(tmp);
				requestBody.close();
			}

			connection.connect();

			//get response from server
			connection.getInputStream();
//			byte[] responseBytes = readFully(connection.getInputStream());
			InputStream response;
			try {
				response = connection.getInputStream();
				//System.out.println("Method: " + method + ", status: " + status);
			} catch(Exception e) {
				response = connection.getErrorStream();
				//System.out.println("EXCEPTION, Method: " + method + ", status: " + status);
			}

			byte[] responseBytes = IOUtils.readFully(response, -1, true);
			Map<String, List<String>> responseHeaders = connection.getHeaderFields();
			int responseCode = connection.getResponseCode();


			//set response from server to client
			responseHeaders.forEach((key, values) -> {
				if(key != null && !Objects.equals(key, "Transfer-Encoding")) {
					for(String value : values) {
						exchange.getResponseHeaders().set(key, value);
					}
				}
			});

			int responseLength;
			if(responseCode == HttpURLConnection.HTTP_NO_CONTENT || responseCode == HttpURLConnection.HTTP_NOT_MODIFIED) {
				responseLength = -1;
			} else {
				responseLength = responseBytes.length;
			}
			System.out.println(responseBytes.length);

			OutputStream responseBody = exchange.getResponseBody();
			exchange.sendResponseHeaders(responseCode, responseLength);
			responseBody.write(responseBytes);
			responseBody.close();
		}
	}
}
