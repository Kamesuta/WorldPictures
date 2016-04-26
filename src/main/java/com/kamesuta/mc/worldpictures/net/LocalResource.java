package com.kamesuta.mc.worldpictures.net;

import java.io.File;

import org.apache.commons.lang3.Validate;

public class LocalResource {
	public final String url;

	public LocalResource(final String url) {
		Validate.notNull(url);
		this.url = url;
	}

	public LocalResource prepare() {
		final File file = getFile();
		final File parent = file.getParentFile();
		if (!parent.exists())
			parent.mkdirs();
		return this;
	}

	public File getFile() {
		return new File(this.url);
	}
}
