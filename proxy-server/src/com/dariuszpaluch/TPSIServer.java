package com.dariuszpaluch;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import sun.misc.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TPSIServer {

	public static void main(String[] args) throws Exception {
		int port = 8013;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		RootHandler proxyHandler = new RootHandler();
		proxyHandler.initialization();

		server.createContext("/", proxyHandler);
		System.out.println("Starting server on port: " + port);
		server.start();
	}



	static class RootHandler implements HttpHandler {

		public class StatisticItem {
			long dataSize;
			String host;
			int amount;

			public int getAmount() {
				return amount;
			}

			public long getDataSize() {
				return dataSize;
			}
			public String getHost() {
				return host;
			}

			StatisticItem(String host, int amount, int dataSize){
				this.amount = amount;
				this.dataSize = dataSize;
				this.host = host;
			}

			StatisticItem(String host, int dataSize){
				this.amount = 1;
				this.dataSize = dataSize;
				this.host = host;
			}

			public StatisticItem add(int dataSize) {
				this.amount++;
				this.dataSize+=dataSize;
				return this;
			}

		}
//		private final HashMap<String, StatisticItem> statistics;
		private List<String> blackList;
		private final  List<StatisticItem> statistics;
		private boolean preparedData = false;
		public RootHandler() {

			this.statistics = new ArrayList<>();

			this.readBlackListFile();
		}

		public void readFromCSV() {
			// use comma as separator
			String csvFile = "statistics2.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ";";
			try {
				br = new BufferedReader(new FileReader(csvFile));
				br.readLine(); //remove first title line
			while ((line = br.readLine()) != null) {
				String[] data = line.split(cvsSplitBy);
				this.statistics.add(new StatisticItem(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
//				System.out.println("host= " + data[0] + " , amount=" + data[1] + " , dataSize=" + data[2]);

			}
				br.close();

			} catch (FileNotFoundException e) {
//				e.printStackTrace();
			} catch (IOException e) {
//				e.printStackTrace();
			}

		}

		public void saveStatisticsToCSV() {
			PrintWriter pw = null;
			try {
				File file = new File("statistics2.csv");
				PrintWriter writer = new PrintWriter(file);
				writer.print("");
				writer.close();
				pw = new PrintWriter(file);
				StringBuilder sb = new StringBuilder();
				sb.append("host");
				sb.append(";");
				sb.append("amount");
				sb.append(";");
				sb.append("dataSize");
				sb.append("\n");

				for(StatisticItem statisticItem : this.statistics) {
					sb.append(statisticItem.getHost().replace(";",""));
					sb.append(";");
					sb.append(Integer.toString(statisticItem.getAmount()));
					sb.append(";");
					sb.append(Long.toString(statisticItem.getDataSize()));
					sb.append('\n');
				}


				pw.write(sb.toString());
				pw.close();
//				System.out.println("done!");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public void addPathToStatistics(String host, int dataSize) {

			boolean added = false;
			for(StatisticItem item: this.statistics) {
				if(item.getHost().equals(host)) {
					item.add(dataSize);
					added = true;
				}
			}

			if(!added) {
				this.statistics.add(new StatisticItem(host, dataSize));
			}

			this.saveStatisticsToCSV();

//			i
//			statistics.merge(host, new StatisticItem(host, dataSize), (a, b) -> a.add(dataSize)); //add with 1, or  value++
//			System.out.println("Add to statistics: " + path);

//			if(statistics.get(host) == null ){
//				statistics.put(host, 1);
//			}
//				else {
//			statistics.put(host, statistics.get(path) + 1);
//			}
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

		private void readBlackListFile(){
			this.blackList = new ArrayList<String>();
			try {
				FileReader file = new FileReader("black_list.txt");
				Scanner blackListFile = new Scanner(file);
				while (blackListFile.hasNext()) {
					this.blackList.add(blackListFile.next());
				}
			}
			catch (Exception e) {
				System.out.println(e);
				System.out.println("Brak czarnej listy");
			}
		}

		private boolean checkLinkInBlackList(String host) {
			System.out.println(host);
			if (this.blackList.size() > 0){
				for (String blackPath : this.blackList) {
					String blackPathwww = "www." + blackPath;
					if (blackPath.equals(host) || blackPathwww.equals(host)) {
						System.out.println("This page is on black list");
						return true;
					}
				}
			}
			return false;
		}

		public void initialization() {
			this.readFromCSV();
			this.readBlackListFile();
			this.preparedData = true;
		}
		public void handle(final HttpExchange exchange) throws IOException {

			if(!this.preparedData) {
				this.initialization();
			}

			//get request from client
			String path = exchange.getRequestURI().toString();
			String method = exchange.getRequestMethod(); //type of call: GET, PUT, ..
			Map<String, List<String>> headers = exchange.getRequestHeaders();
			URL myUrl = new URL(path);
//			System.out.println(myUrl.getHost());
//			System.out.println("TUTAJ");
			String host = headers.get("HOST").get(0);

//			System.out.println("host: " + host);
//			System.out.println(path);



			byte[] requestBody = null;
			if(method.equals("POST") || method.equals("PUT")) {
				requestBody = readFully(exchange.getRequestBody());
			}

			//check on blackList
			if(this.checkLinkInBlackList(host)) {
				exchange.sendResponseHeaders(403, -1);
			}
			else {
				//set request from client to server
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod(method);

				headers.forEach((key, values) -> {
					for(String value : values) {
						connection.setRequestProperty(key, value);
					}
				});

				if(requestBody != null) {
					connection.setDoOutput(true); //inform to I want send output a request body
					OutputStream requestBodyStream = connection.getOutputStream();
					requestBodyStream.write(requestBody);
					requestBodyStream.close();
				}

				//connect to server
				connection.connect();


				//get response from server
				int responseCode = connection.getResponseCode();

				InputStream response;
				try {
					response = connection.getInputStream();
				} catch(Exception e) {
					response = connection.getErrorStream();
				}

				byte[] responseBytes = readFully(response);
				Map<String, List<String>> responseHeaders = connection.getHeaderFields();



				//set response from server to client
				responseHeaders.forEach((key, values) -> {
					if(key != null && !Objects.equals(key, "Transfer-Encoding")) {
						for(String value : values) {
							exchange.getResponseHeaders().set(key, value);
						}
					}
				});

				int responseLength;
				if(responseCode == HttpURLConnection.HTTP_NO_CONTENT
						|| responseCode == HttpURLConnection.HTTP_NOT_MODIFIED) {
					responseLength = -1;
					//add statistics
					this.addPathToStatistics(host, 0);
				} else {
					responseLength = responseBytes.length;
					this.addPathToStatistics(host, responseLength);
				}


				OutputStream responseBody = exchange.getResponseBody();
				exchange.sendResponseHeaders(responseCode, responseLength);
				responseBody.write(responseBytes);
				responseBody.close();
			}

		}
	}
}
