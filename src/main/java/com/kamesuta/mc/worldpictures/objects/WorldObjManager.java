package com.kamesuta.mc.worldpictures.objects;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.repository.RepositoryManager;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;
import com.kamesuta.mc.worldpictures.texture.WorldTextureManager;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexManager;

public class WorldObjManager {
	public final WorldResourceManager manager;
	public final WorldTextureManager pictureManager;
	public final WorldVertexManager vertexManager;
	public final RepositoryManager repositoryManager ;

	public WorldObjManager(WorldResourceManager manager, RepositoryManager repomanager) {
		this.manager = manager;
		this.pictureManager = new WorldTextureManager(WorldPictures.proxy.resource);
		this.vertexManager = new WorldVertexManager(WorldPictures.proxy.resource);
		this.repositoryManager = repomanager;
	}

	public WorldObjClient getClient(WorldObj obj) {


		return new WorldObjClient(obj);
	}
}
