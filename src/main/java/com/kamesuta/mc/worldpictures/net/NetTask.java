package com.kamesuta.mc.worldpictures.net;

import org.apache.http.client.HttpClient;

public interface NetTask {
	void processTask(HttpClient client) throws Exception;
}
