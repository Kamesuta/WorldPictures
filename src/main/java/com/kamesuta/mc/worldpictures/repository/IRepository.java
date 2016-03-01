package com.kamesuta.mc.worldpictures.repository;

import java.util.List;

import com.kamesuta.mc.worldpictures.objects.WorldObj;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

public interface IRepository {
	public List<WorldObj> check(Agent agent) throws SynchronizeException;

	public void download(Agent agent, WorldResourceManager manager, WorldResource resource) throws SynchronizeException;

	public void upload(Agent agent, WorldResourceManager manager, WorldResource resource) throws SynchronizeException;
}
