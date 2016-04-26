package com.kamesuta.mc.worldpictures.net.task;

import org.apache.http.client.HttpClient;

public interface ITask {
	void processTask(HttpClient client) throws Exception;
	float processProgress();
	String progressMessage();
	TaskState getState();
}
