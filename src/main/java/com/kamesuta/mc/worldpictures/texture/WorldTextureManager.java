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
			picture = this.loadTexture(location);
		}

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, picture.getGlTextureId());
	}

	public IWorldTexture loadTexture(WorldResource location) {
		IWorldTexture picture;

		try {
			picture = new WorldTexture(theResourceManager, location);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load texture: " + location);
			Reference.logger.debug(e);
			picture = WorldTexture.NULL;
		}

		this.mapTextureObjects.put(location, picture);
		return picture;
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
