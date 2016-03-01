package com.kamesuta.mc.worldpictures.objects;

import java.util.Arrays;
import java.util.List;

import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.resource.WorldResource;

public class WorldObj {
	public static final List<String> WorldObjFiles = Arrays.asList(Names.Formats.NAME_PICTURE, Names.Formats.NAME_VERTEX);

	protected WorldResource picture;

	public WorldObj(String id) {
		this.picture = new WorldResource(id, WorldObjFiles);
	}

	public WorldObj(WorldObj obj) {
		this.picture = obj.picture;
	}
}
