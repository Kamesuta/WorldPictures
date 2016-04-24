package com.kamesuta.mc.worldpictures.component.builder;

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
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.util.ComponentRender;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import net.minecraft.client.renderer.Tessellator;

@Deprecated
public class WorldVertexManager {

	private final Tessellator tessellator = Tessellator.instance;
	private final Map<WorldResource, Scene> mapVertexObjects = Maps.newHashMap();
	private final WorldResourceManager theResourceManager;

	public WorldVertexManager(final WorldResourceManager manager) {
		this.theResourceManager = manager;
	}

	protected void draw(final Scene onecut) {
		ComponentRender.drawSceneOutline(onecut, System.currentTimeMillis());
	}

	public void drawVertex(final WorldResource location) {
		draw(getVertex(location));
	}

	public Scene loadVertex(final WorldResource location) {
		Scene vertex = null;
		try {
			vertex = readVertex(location);
		} catch (final IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location + ": " + e.getMessage());
			Reference.logger.debug(e);
		}

		this.mapVertexObjects.put(location, vertex);
		return vertex;
	}

	public Scene getVertex(final WorldResource location) {
		Scene vertex = this.mapVertexObjects.get(location);

		if (vertex == null) {
			vertex = loadVertex(location);
		}

		return vertex;
	}

	public void deleteVertex(final WorldResource location) {
		this.mapVertexObjects.remove(location);
	}

	public boolean saveVertex(final WorldResource location, final Scene v) {
		boolean flag = true;
		try {
			writeVertex(location, v);
		} catch (final IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location, e);
			flag = false;
		}

		this.mapVertexObjects.put(location, v);
		return flag;
	}

	public boolean saveVertex(final WorldResource location) {
		final Scene v = this.mapVertexObjects.get(location);
		if (v != null) {
			return saveVertex(location, v);
		}

		return false;
	}

	public Scene readVertex(final WorldResource location) throws IOException {
		Scene vertex;
		try {
			final File resource = this.theResourceManager.getResource(location);
			final JsonReader jsr = new JsonReader(new InputStreamReader(new FileInputStream(resource)));
			vertex = new Gson().fromJson(jsr, Scene.class);
			jsr.close();
		} catch (final JsonSyntaxException e) {
			throw new IOException("Syntax error has occured. Is it right format?", e);
		} catch (final JsonIOException e) {
			throw new IOException(e);
		}
		return vertex;
	}

	public void writeVertex(final WorldResource location, final Scene vertexes) throws IOException {
		try {
			final File resource = this.theResourceManager.getResource(location);
			final JsonWriter jsw = new JsonWriter(new OutputStreamWriter(new FileOutputStream(resource)));
			new Gson().toJson(vertexes, Scene.class, jsw);
			jsw.close();
		} catch (final JsonIOException e) {
			throw new IOException(e);
		}
	}
}
