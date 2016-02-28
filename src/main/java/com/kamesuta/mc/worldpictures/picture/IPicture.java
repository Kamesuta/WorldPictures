package com.kamesuta.mc.worldpictures.picture;

import java.io.IOException;

import com.kamesuta.mc.worldpictures.resource.PicturesResourceManager;

public interface IPicture {
	String PictureName = "texture.picture";

	int getGlTextureId();

	void loadTexture(PicturesResourceManager manager) throws IOException;

}