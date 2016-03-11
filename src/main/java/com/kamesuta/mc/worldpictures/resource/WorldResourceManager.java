package com.kamesuta.mc.worldpictures.resource;

import java.io.File;

public class WorldResourceManager {

	private File dir;

	public WorldResourceManager(File dir) {
		this.dir = dir;
	}

	public File getResource(WorldResource location) {
		return new File(new File(dir, location.getDomain()), location.getName());
	}

}
