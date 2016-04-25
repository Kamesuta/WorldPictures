package com.kamesuta.mc.worldpictures.net;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import com.kamesuta.mc.worldpictures.reference.Reference;

public class DownloadTask implements NetTask {
	public RemoteResource remote;
	public LocalResource local;

	public DownloadTask(final RemoteResource remote, final LocalResource local) {
		this.remote = remote;
		this.local = local;
	}

	@Override
	public void processTask(final HttpClient client) {
		try {
			final HttpUriRequest req = new HttpGet(this.remote.url);

			final HttpEntity entity = client.execute(req).getEntity();
			Reference.logger.info(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		} catch (final Exception e) {
			Reference.logger.warn("Failed to download :", e);
		}
	}

}
