package com.kamesuta.mc.worldpictures.resource;

import java.io.File;
import java.io.FileNotFoundException;

public class PicturesResourceManager {

	private File dir;

	public PicturesResourceManager(File dir) {
		this.dir = dir;
	}

	public File getResource(PicturesResource location, String name) throws FileNotFoundException {
		File file = new File(new File(dir, location.getId()), name);
		if (location.exists(name) && file.exists())
			return file;
		else
			throw new FileNotFoundException("Out of resource");
	}

	public File[] getResources(PicturesResource location) throws FileNotFoundException {
		File file = new File(dir, location.getId());
		if (file.isDirectory())
			return file.listFiles();
		else
			throw new FileNotFoundException("Out of resource");
	}

}
