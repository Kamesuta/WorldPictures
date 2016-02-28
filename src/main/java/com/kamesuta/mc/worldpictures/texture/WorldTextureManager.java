package com.kamesuta.mc.worldpictures.texture;

import java.io.IOException;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

public class WorldTextureManager {

	private final Map<WorldResource, IWorldTexture> mapTextureObjects = Maps.newHashMap();
    private WorldResourceManager theResourceManager;

	public WorldTextureManager(WorldResourceManager manager) {
		this.theResourceManager = manager;
	}

	public void bindTexture(WorldResource location) {
		IWorldTexture picture = this.mapTextureObjects.get(location);

		if (picture == null) {
			picture = new WorldTextureLink(location);
			this.loadTexture(location, picture);
		}

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, picture.getGlTextureId());
	}

	public boolean loadTexture(WorldResource location, IWorldTexture picture) {
		boolean flag = true;
		try {
			picture.loadTexture(this.theResourceManager);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load texture: " + location, e);
			picture = AbstractWorldTexture.NULL_PIECE;
			this.mapTextureObjects.put(location, picture);
			flag = false;
		}

		this.mapTextureObjects.put(location, picture);
		return flag;
	}

	public IWorldTexture getTexture(WorldResource location) {
		return this.mapTextureObjects.get(location);
	}

	public void deleteTexture(WorldResource location) {
		IWorldTexture itextureobject = this.getTexture(location);

		if (itextureobject != null) {
			GL11.glDeleteTextures(itextureobject.getGlTextureId());
		}

		this.mapTextureObjects.remove(location);
	}
}
