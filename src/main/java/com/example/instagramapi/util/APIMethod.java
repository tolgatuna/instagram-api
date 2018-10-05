package com.example.instagramapi.util;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;

	HttpClient client;
	private List<BasicHeader> defaultHeaders = null;
	private int requestTimeout = 60 * 1000; // 60 sn.
	private int connectionTimeout = 10000; // 10 sn
	private int socketTimeout = 60000; // 60 sn

	abstract protected InputStream performRequest() throws Exception;

	public APIMethod() {
		HttpProcessorBuilder builder = HttpProcessorBuilder.create();

		builder.add(new RequestAcceptEncoding());
		builder.add(new RequestConnControl());
		builder.add(new RequestTargetHost());
		builder.add(new ResponseContentEncoding());
		builder.add(new RequestContent());
		builder.add(new RequestClientConnControl());

		if (defaultHeaders != null && !defaultHeaders.isEmpty()) {
			List<Header> headers = new ArrayList<Header>(defaultHeaders.size());
			for (Header header : defaultHeaders) {
				headers.add(header);
			}
			builder.add(new RequestDefaultHeaders(headers));
		}

		HttpProcessor processor = builder.build();

		SSLConnectionSocketFactory socketFactory = null;

		socketFactory = SSLConnectionSocketFactory.getSocketFactory();

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", socketFactory)
				.build();

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setDefaultMaxPerRoute(200);
		cm.setMaxTotal(400);

		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(connectionTimeout);
		requestBuilder = requestBuilder.setSocketTimeout(socketTimeout);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(requestTimeout);

		HttpClientBuilder clientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(requestBuilder.build());

		clientBuilder.setConnectionManager(cm);
		clientBuilder.setHttpProcessor(processor);

		client = clientBuilder.setRedirectStrategy(new LaxRedirectStrategy()).build();
		setMethodURI(methodUri);
		
	}

	public RequestResponse call() throws Exception {
		System.out.println(this.methodUri);
		StringBuilder sb = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(performRequest()));
		String chunk;
		while ((chunk = rd.readLine()) != null) {
			sb.append(chunk);
		}
		rd.close();
		return new RequestResponse(sb.toString());
	}

	public String getType() {
		return type;
	}

	public String getMethodUri() {
		return methodUri;
	}

	public APIMethod setMethodURI(String methodURI) {
		this.methodUri = methodURI;
		return this;
	}
}
