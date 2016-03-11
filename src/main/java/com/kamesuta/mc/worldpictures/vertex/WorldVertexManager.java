package com.kamesuta.mc.worldpictures.vertex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;
import com.kamesuta.mc.worldpictures.vertex.square.Square;

import net.minecraft.client.renderer.Tessellator;

public class WorldVertexManager {

	private Tessellator tessellator = Tessellator.instance;
	private final Map<WorldResource, Scene> mapVertexObjects = Maps.newHashMap();
	private WorldResourceManager theResourceManager;

	public WorldVertexManager(WorldResourceManager manager) {
		this.theResourceManager = manager;
	}

	private Square vectorpool = new Square(new Vector3f(), new Vector3f(), new Vector3f(), new Vector3f());

	protected void draw(Scene onecut) {
		if (onecut.takeashot(System.currentTimeMillis(), vectorpool)) {
			vectorpool.draw(tessellator);
		}
	}

	public void drawVertex(WorldResource location) {
		draw(getVertex(location));
	}

	public Scene loadVertex(WorldResource location) {
		Scene vertex = null;
		try {
			vertex = this.readVertex(location);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location + ": " + e.getMessage());
			Reference.logger.debug(e);
			vertex = Scene.NULL;
		}

		mapVertexObjects.put(location, vertex);
		return vertex;
	}

	public Scene getVertex(WorldResource location) {
		Scene vertex = this.mapVertexObjects.get(location);

		if (vertex == null) {
			vertex = this.loadVertex(location);
		}

		return vertex;
	}

	public void deleteVertex(WorldResource location) {
		this.mapVertexObjects.remove(location);
	}

	public boolean saveVertex(WorldResource location, Scene vertex) {
		boolean flag = true;
		try {
			writeVertex(location, vertex);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location, e);
			flag = false;
		}

		this.mapVertexObjects.put(location, vertex);
		return flag;
	}

	public boolean saveVertex(WorldResource location) {
		Scene v = this.mapVertexObjects.get(location);
		if (v != null) {
			return saveVertex(location, v);
		}

		return false;
	}

	public Scene readVertex(WorldResource location) throws IOException {
		Scene vertex;
		try {
			File resource = theResourceManager.getResource(location);
			JsonReader jsr = new JsonReader(new InputStreamReader(new FileInputStream(resource)));
			vertex = new Gson().fromJson(jsr, Scene.class);
			jsr.close();
		} catch (JsonSyntaxException e) {
			throw new IOException("Syntax error has occured. Is it right format?", e);
		} catch (JsonIOException e) {
			throw new IOException(e);
		}
		return vertex;
	}

	public void writeVertex(WorldResource location, Scene vertexes) throws IOException {
		try {
			File resource = theResourceManager.getResource(location);
			JsonWriter jsw = new JsonWriter(new OutputStreamWriter(new FileOutputStream(resource)));
			new Gson().toJson(vertexes, Scene.class, jsw);
			jsw.close();
		} catch (JsonIOException e) {
			throw new IOException(e);
		}
	}
}
