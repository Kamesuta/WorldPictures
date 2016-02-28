package com.kamesuta.mc.worldpictures.texture;

import java.io.IOException;

import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

public interface IWorldTexture {

	int getGlTextureId();

	void loadTexture(WorldResourceManager manager) throws IOException;

}