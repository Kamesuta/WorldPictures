package com.kamesuta.mc.worldpictures.objects;

import com.kamesuta.mc.worldpictures.resource.WorldResource;

public class WorldObj {
	public WorldResource picture;

	public WorldObj(String id) {
//		this.picture = new WorldResource(id, WorldObjFiles);
	}

	public WorldObj(WorldObj obj) {
		this.picture = obj.picture;
	}
}
