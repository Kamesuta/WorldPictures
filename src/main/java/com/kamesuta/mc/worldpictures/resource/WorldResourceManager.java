package com.kamesuta.mc.worldpictures.resource;

import java.io.File;
import java.io.FileNotFoundException;

public class WorldResourceManager {

	private File dir;

	public WorldResourceManager(File dir) {
		this.dir = dir;
	}

	public File getResource(WorldResource location, String name) throws FileNotFoundException {
		File file = new File(new File(dir, location.getId()), name);
		if (location.exists(name))
			return file;
		else
			throw new FileNotFoundException("Out of resource");
	}

	public File[] getResources(WorldResource location) throws FileNotFoundException {
		File file = new File(dir, location.getId());
		if (file.isDirectory())
			return file.listFiles();
		else
			throw new FileNotFoundException("Out of resource");
	}

}
