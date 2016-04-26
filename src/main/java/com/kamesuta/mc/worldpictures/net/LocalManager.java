package com.kamesuta.mc.worldpictures.net;

import java.io.File;
import java.net.IDN;
import java.net.URI;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.kamesuta.mc.worldpictures.reference.Reference;

public class LocalManager {
	public final File local;

	public LocalManager(final File local) {
		this.local = local;
	}

	public LocalResource getLocalResource(final RemoteResource remote) {
		try {
			final URI i = remote.getURI();
			if (i != null) {
				final URL l = i.toURL();

				final String _scheme = l.getProtocol();
				final String _host = l.getHost();
				final int _port = l.getPort();
				final String _path = i.getPath();
				final String _query = i.getQuery();

				final String host = IDN.toUnicode(_host);
				final String port =
						(_port == -1 || (StringUtils.equals(_scheme, "http") && _port == 80)
						|| (StringUtils.equals(_scheme, "https") && _port == 443))
						? "" : ("#" + _port);
				final String path = (_path == null) ? "" : _path;
				final String query = (_query == null) ? "" : ("#"+_query);

				final String url = String.format("%s/%s%s/%s%s", _scheme, host, port, path, query);

				return new LocalResource(new File(this.local, url).getAbsolutePath());
			}
		} catch (final Exception e) {
			Reference.logger.warn("Invaild URL: " + remote.url);
		}
		return null;
	}
}
