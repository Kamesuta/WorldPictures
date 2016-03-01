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
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import net.minecraft.client.renderer.Tessellator;

public class WorldVertexManager {
	private Tessellator tessellator = Tessellator.instance;
	private final Map<WorldResource, WorldVertexCompound> mapVertexObjects = Maps.newHashMap();
	private WorldResourceManager theResourceManager;

	public WorldVertexManager(WorldResourceManager manager) {
		this.theResourceManager = manager;
	}

	private Vector3f[] vectorpool = {
		new Vector3f(),
		new Vector3f(),
		new Vector3f(),
		new Vector3f(),
		new Vector3f(),
	};

	protected void draw(WorldVertexCompound vertexes) {
		long timeall = Math.max((long)(vertexes.time * 1000), 100);
		long now = System.currentTimeMillis() % timeall;

		WorldVertexObj prevlast = vertexes.vertexes.getFirst();
		WorldVertexObj nextlast = vertexes.vertexes.getLast();

		int maxframe = 0;
		for (WorldVertexObj vertex : vertexes.vertexes) {
			maxframe = Math.max(maxframe, vertex.frame);
		}

		long oneFrameTime = timeall / maxframe;
		for (WorldVertexObj vertex : vertexes.vertexes) {
			long nowFrameTime = oneFrameTime * vertex.frame;
			if (now > nowFrameTime) {
				prevlast = vertex;
			} else {
				nextlast = vertex;
				break;
			}
		}

		long betweentime = oneFrameTime * (nextlast.frame - prevlast.frame);
		long betweennow = now - (oneFrameTime * prevlast.frame);
		float progress = (float) betweennow / betweentime;
		Vector3f[] v = {
			vectorpool[0].set(prevlast.vertex[0]).add(vectorpool[4].set(nextlast.vertex[0]).sub(prevlast.vertex[0]).scale(progress)),
			vectorpool[1].set(prevlast.vertex[1]).add(vectorpool[4].set(nextlast.vertex[1]).sub(prevlast.vertex[1]).scale(progress)),
			vectorpool[2].set(prevlast.vertex[2]).add(vectorpool[4].set(nextlast.vertex[2]).sub(prevlast.vertex[2]).scale(progress)),
			vectorpool[3].set(prevlast.vertex[3]).add(vectorpool[4].set(nextlast.vertex[3]).sub(prevlast.vertex[3]).scale(progress))
		};

		draw(v);
	}

	protected void draw(Vector3f[] vertex) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(vertex[0].x, vertex[0].y, vertex[0].z, 0, 0);
		tessellator.addVertexWithUV(vertex[1].x, vertex[1].y, vertex[1].z, 0, 1);
		tessellator.addVertexWithUV(vertex[2].x, vertex[2].y, vertex[2].z, 1, 1);
		tessellator.addVertexWithUV(vertex[3].x, vertex[3].y, vertex[3].z, 1, 0);
		tessellator.draw();
	}

	public void drawVertex(WorldResource location) {
		draw(getVertex(location));
	}

	public WorldVertexCompound loadVertex(WorldResource location) {
		WorldVertexCompound vertex = null;
		try {
			vertex = this.readVertex(location);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location, e);
		}

		mapVertexObjects.put(location, vertex);
		return vertex;
	}

	public WorldVertexCompound getVertex(WorldResource location) {
		WorldVertexCompound vertex = this.mapVertexObjects.get(location);

		if (vertex == null) {
			vertex = this.loadVertex(location);
		}

		return vertex;
	}

	public void deleteVertex(WorldResource location) {
		this.mapVertexObjects.remove(location);
	}

	public boolean saveVertex(WorldResource location, WorldVertexCompound vertex) {
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
		WorldVertexCompound v = this.mapVertexObjects.get(location);
		if (v != null) {
			return saveVertex(location, v);
		}

		return false;
	}

	public WorldVertexCompound readVertex(WorldResource location) throws IOException {
		WorldVertexCompound vertex;
		try {
			File resource = theResourceManager.getResource(location, Names.Formats.NAME_VERTEX);
			JsonReader jsr = new JsonReader(new InputStreamReader(new FileInputStream(resource)));
			vertex = new Gson().fromJson(jsr, WorldVertexCompound.class);
			jsr.close();
		} catch (JsonParseException e) {
			throw new IOException(Names.Formats.LOAD_ERROR, e);
		}
		return vertex;
	}

	public void writeVertex(WorldResource location, WorldVertexCompound vertexes) throws IOException {
		try {
			File resource = theResourceManager.getResource(location, Names.Formats.NAME_VERTEX);
			JsonWriter jsw = new JsonWriter(new OutputStreamWriter(new FileOutputStream(resource)));
			new Gson().toJson(vertexes, WorldVertexCompound.class, jsw);
			jsw.close();
		} catch (JsonParseException e) {
			throw new IOException(Names.Formats.SAVE_ERROR, e);
		}
	}
}
