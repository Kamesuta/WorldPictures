package com.kamesuta.mc.worldpictures.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import com.kamesuta.mc.worldpictures.reference.Reference;

public class NetManager {
	public final PoolingHttpClientConnectionManager manager;
	public HttpClient client;

	public final NetProcessor[] processor;
	public final Queue<NetTask> tasks;

	public NetManager(final int max) {
		this.manager = new PoolingHttpClientConnectionManager();
		this.manager.setMaxTotal(max);
		this.manager.setDefaultMaxPerRoute(max);

		final RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(15 * 1000)
				.setSocketTimeout(15 * 1000)
				.build();

		final List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Accept-Charset","utf-8"));
		headers.add(new BasicHeader("Accept-Language","ja, en;q=0.8"));
		headers.add(new BasicHeader("User-Agent", Reference.MODID));

		this.client = HttpClientBuilder.create()
				.setConnectionManager(this.manager)
				.setDefaultRequestConfig(requestConfig)
				.setDefaultHeaders(headers)
				.build();

		this.processor = new NetProcessor[max];
		this.tasks = new LinkedBlockingQueue<NetTask>();
	}

	public void addTask(final NetTask task) {
		this.tasks.offer(task);
		for (int i = 0; i < this.processor.length; i++) {
			if (this.processor[i] == null || !this.processor[i].isAlive()) {
				this.processor[i] = new NetProcessor(Reference.MODID, this);
				this.processor[i].start();
				break;
			}
		}
	}
}
