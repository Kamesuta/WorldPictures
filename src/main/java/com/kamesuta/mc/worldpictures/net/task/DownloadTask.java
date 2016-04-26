package com.kamesuta.mc.worldpictures.net.task;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import com.kamesuta.mc.worldpictures.net.LocalResource;
import com.kamesuta.mc.worldpictures.net.RemoteResource;
import com.kamesuta.mc.worldpictures.reference.Reference;

public class DownloadTask implements ITask {
	public RemoteResource remote;
	public LocalResource local;

	private TaskState state;
	private long maxsize;
	private CountingOutputStream countoutput;

	public DownloadTask(final RemoteResource remote, final LocalResource local) {
		this.remote = remote;
		this.local = local;

		this.state = TaskState.READY;
	}

	@Override
	public void processTask(final HttpClient client) {
		if (this.state == TaskState.READY) {
			this.state = TaskState.INPROGRESS;

			InputStream input = null;
			try {
				final HttpUriRequest req = new HttpGet(this.remote.url);
				final HttpResponse response = client.execute(req);
				final HttpEntity entity = response.getEntity();

				this.maxsize = entity.getContentLength();
				input = entity.getContent();
				this.countoutput = new CountingOutputStream(new BufferedOutputStream(new FileOutputStream(this.local.prepare().getFile())));
				IOUtils.copy(input, this.countoutput);
				//Reference.logger.info(EntityUtils.toString(entity));
			} catch (final Exception e) {
				Reference.logger.warn("Failed to download :", e);
			} finally {
				IOUtils.closeQuietly(input);
				IOUtils.closeQuietly(this.countoutput);
			}

			this.state = TaskState.COMPLETED;
		}
	}

	@Override
	public float processProgress() {
		if (this.maxsize > 0)
			return Math.max(0, Math.min(1, (getRate() / (float) this.maxsize)));
		return 0;
	}

	@Override
	public String progressMessage() {
		switch (this.state) {
		case READY:
			return "initializing";
		case INPROGRESS:
			if (this.maxsize > 0) {
				final float f = processProgress() * 100;
				return String.format("%.1f%%", f);
			} else {
				return "downloading";
			}
		case COMPLETED:
			return "completed";
		default:
			return "loading";
		}
	}

	private long getRate() {
		if (this.countoutput != null)
			return this.countoutput.getByteCount();
		return 0;
	}

	@Override
	public TaskState getState() {
		return this.state;
	}

}
