package com.kamesuta.mc.worldpictures.net;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

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
		InputStream input = null;
		OutputStream output = null;
		try {
			final HttpUriRequest req = new HttpGet(this.remote.url);
			final HttpResponse response = client.execute(req);
			final HttpEntity entity = response.getEntity();

			input = entity.getContent();
			output = new BufferedOutputStream(new FileOutputStream(this.local.prepare().getFile()));

			IOUtils.copy(input, output);
			//Reference.logger.info(EntityUtils.toString(entity));
		} catch (final Exception e) {
			Reference.logger.warn("Failed to download :", e);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

}
