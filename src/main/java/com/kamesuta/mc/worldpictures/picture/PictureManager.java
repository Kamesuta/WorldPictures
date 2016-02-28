package com.kamesuta.mc.worldpictures.picture;

import java.io.IOException;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Maps;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.PicturesResource;
import com.kamesuta.mc.worldpictures.resource.PicturesResourceManager;

public class PictureManager {

	private final Map<PicturesResource, IPicture> mapTextureObjects = Maps.newHashMap();
    private PicturesResourceManager thePicturesManager;

	public PictureManager(PicturesResourceManager manager) {
		this.thePicturesManager = manager;
	}

	public void bindTexture(PicturesResource location) {
		IPicture picture = this.mapTextureObjects.get(location);

		if (picture == null) {
			picture = new PictureLink(location);
			this.loadTexture(location, picture);
		}

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, picture.getGlTextureId());
	}

	public boolean loadTexture(PicturesResource location, IPicture picture) {
		boolean flag = true;
		try {
			picture.loadTexture(this.thePicturesManager);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load texture: " + location, e);
			picture = AbstractPicture.NULL_PIECE;
			this.mapTextureObjects.put(location, picture);
			flag = false;
		}

		this.mapTextureObjects.put(location, picture);
		return flag;
	}

	public IPicture getTexture(PicturesResource location) {
		return this.mapTextureObjects.get(location);
	}

	public void deleteTexture(PicturesResource location) {
		IPicture itextureobject = this.getTexture(location);

		if (itextureobject != null) {
			GL11.glDeleteTextures(itextureobject.getGlTextureId());
		}
	}
}
