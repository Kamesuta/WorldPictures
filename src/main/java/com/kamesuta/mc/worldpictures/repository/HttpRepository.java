package com.kamesuta.mc.worldpictures.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.kamesuta.mc.worldpictures.objects.WorldObj;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

public class HttpRepository implements IRepository {
	public static final HttpClient httpClient = getHttpClient();
	public static final String FileDir = "files/";
	public static final String Core = "WorldPicture.php";

	private URI repository;

	public HttpRepository(String uri) throws SynchronizeException {
		try {
			this.repository = new URI(uri);
		} catch (URISyntaxException e) {
			throw new SynchronizeException(e);
		}
	}

	@Override
	public List<WorldObj> check(Agent agent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void download(Agent agent, WorldResourceManager manager, WorldResource resource) throws SynchronizeException {
		try {
			URI downrepository = repository.resolve(FileDir);

			HttpEntity entity = MultipartEntityBuilder.create()
			.addTextBody("worldpicture", resource.getId())
			.addTextBody("id", agent.id)
			.addTextBody("token", agent.token)
			.build();

			File[] downfiles = manager.getResources(resource);
			for (File downfile : downfiles) {
				HttpPost httpPost = new HttpPost(downrepository);
				httpPost.setEntity(entity);
				HttpResponse response = httpClient.execute(httpPost);
				entity = response.getEntity();
				
				IOUtils.copy(response.getEntity().getContent(), new FileOutputStream(downfile));
			}
		} catch (ClientProtocolException e) {
			throw new SynchronizeException(e);
		} catch (IllegalStateException e) {
			throw new SynchronizeException(e);
		} catch (IOException e) {
			throw new SynchronizeException(e);
		}
	}

	@Override
	public void upload(Agent agent, WorldResourceManager manager, WorldResource resource) throws SynchronizeException {
		try {
			URI uprepository = repository.resolve(Core);

			MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create()
			.addTextBody("worldpicture", resource.getId())
			.addTextBody("id", agent.id)
			.addTextBody("token", agent.token);

			File[] upfiles = manager.getResources(resource);
			for (File upfile : upfiles) {
				entitybuilder.addBinaryBody("upload_file", upfile, ContentType.DEFAULT_BINARY, upfile.getName());
			}

			HttpEntity entity = entitybuilder.build();

			HttpPost httpPost = new HttpPost(uprepository);
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpRepositoryResult result = new Gson()
					.fromJson(new JsonReader(new InputStreamReader(response.getEntity().getContent())),
							HttpRepositoryResult.class);
			if (!result.success) {
				throw new SynchronizeException(result.message);
			}
		} catch (ClientProtocolException e) {
			throw new SynchronizeException(e);
		} catch (IllegalStateException e) {
			throw new SynchronizeException(e);
		} catch (JsonParseException e) {
			throw new SynchronizeException(e);
		} catch (IOException e) {
			throw new SynchronizeException(e);
		}
	}

	public static HttpClient getHttpClient() {
		// request configuration
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
		// headers
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Accept-Charset", "utf-8"));
		headers.add(new BasicHeader("Accept-Language", "ja, en;q=0.8"));
		headers.add(new BasicHeader("User-Agent", Reference.MODID + " Downloader"));
		// create client
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setDefaultHeaders(headers).build();
	}

	class HttpRepositoryResult {
		public boolean success;
		public String message;
	}
}
