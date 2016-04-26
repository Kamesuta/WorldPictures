package com.kamesuta.mc.worldpictures.net;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.Validate;

import com.kamesuta.mc.worldpictures.reference.Reference;

public class RemoteResource {
	public final String url;

	public RemoteResource(final String url) {
		Validate.notNull(url);
		this.url = url;
	}

	public URI getURI() {
		try {
			return new URI(this.url);
		} catch (final URISyntaxException e) {
			Reference.logger.warn("Invaild URL :" + this.url);
		}
		return null;
	}
}
