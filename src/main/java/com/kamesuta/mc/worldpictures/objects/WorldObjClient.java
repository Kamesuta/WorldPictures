package com.kamesuta.mc.worldpictures.objects;

import com.kamesuta.mc.worldpictures.texture.WorldTextureManager;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexManager;

@Deprecated
public class WorldObjClient extends WorldObj {
	public WorldObjClient(String id) {
		super(id);
	}

	public WorldObjClient(WorldObj obj) {
		super(obj);
	}

	public void render(WorldTextureManager textureManager, WorldVertexManager vertexManager) {
		textureManager.bindTexture(picture);
		vertexManager.drawVertex(picture);
	}
}
