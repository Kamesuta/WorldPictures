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
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;
import com.kamesuta.mc.worldpictures.vertex.square.Square;

import net.minecraft.client.renderer.Tessellator;

public class WorldVertexManager {
	public static final int MilliPerTime = 1000;

	private Tessellator tessellator = Tessellator.instance;
	private final Map<WorldResource, OneCut> mapVertexObjects = Maps.newHashMap();
	private WorldResourceManager theResourceManager;

	public WorldVertexManager(WorldResourceManager manager) {
		this.theResourceManager = manager;
	}

	private Square vectorpool = new Square(new Vector3f(), new Vector3f(), new Vector3f(), new Vector3f());
	private Vector3f vectorpoollocal = new Vector3f();

	protected void draw(OneCut vertexes) {
		if (!vertexes.vertexes.isEmpty()) {
			long timeall = Math.max(
					(long)(vertexes.time * MilliPerTime),
					(long)(OneCut.MinTimeLength * MilliPerTime)
			);
			long now = System.currentTimeMillis() % timeall;

			Scene
				prevlast = vertexes.vertexes.getLast(),
				nextlast = vertexes.vertexes.getLast();

			int maxframe = 0;
			for (Scene vertex : vertexes.vertexes) {
				maxframe += vertex.getLength();
			}

			long oneFrameTime = timeall / maxframe;
			long nowFrameTime = 0;
			for (Scene vertex : vertexes.vertexes) {
				nowFrameTime += oneFrameTime * vertex.getLength();
				if (now > nowFrameTime) {
					prevlast = vertex;
				} else {
					nextlast = vertex;
					break;
				}
			}

			long betweentime = oneFrameTime * prevlast.getLength();
			long betweennow = now - (nowFrameTime - oneFrameTime * prevlast.getLength());
			float progress = (float) betweennow / betweentime;

			vectorpool.lt.set(prevlast.v.lt).add(vectorpoollocal.set(nextlast.v.lt).sub(prevlast.v.lt).scale(progress));
			vectorpool.lb.set(prevlast.v.lb).add(vectorpoollocal.set(nextlast.v.lb).sub(prevlast.v.lb).scale(progress));
			vectorpool.rb.set(prevlast.v.rb).add(vectorpoollocal.set(nextlast.v.rb).sub(prevlast.v.rb).scale(progress));
			vectorpool.rt.set(prevlast.v.rt).add(vectorpoollocal.set(nextlast.v.rt).sub(prevlast.v.rt).scale(progress));

			vectorpool.draw(tessellator);
		}
	}

	public void drawVertex(WorldResource location) {
		draw(getVertex(location));
	}

	public OneCut loadVertex(WorldResource location) {
		OneCut vertex = null;
		try {
			vertex = this.readVertex(location);
		} catch (IOException e) {
			Reference.logger.warn("Failed to load vertex: " + location + ": " + e.getMessage());
			Reference.logger.debug(e);
			vertex = OneCut.NULL;
		}

		mapVertexObjects.put(location, vertex);
		return vertex;
	}

	public OneCut getVertex(WorldResource location) {
		OneCut vertex = this.mapVertexObjects.get(location);

		if (vertex == null) {
			vertex = this.loadVertex(location);
		}

		return vertex;
	}

	public void deleteVertex(WorldResource location) {
		this.mapVertexObjects.remove(location);
	}

	public boolean saveVertex(WorldResource location, OneCut vertex) {
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
		OneCut v = this.mapVertexObjects.get(location);
		if (v != null) {
			return saveVertex(location, v);
		}

		return false;
	}

	public OneCut readVertex(WorldResource location) throws IOException {
		OneCut vertex;
		try {
			File resource = theResourceManager.getResource(location, Names.Formats.NAME_VERTEX);
			JsonReader jsr = new JsonReader(new InputStreamReader(new FileInputStream(resource)));
			vertex = new Gson().fromJson(jsr, OneCut.class);
			jsr.close();
		} catch (JsonSyntaxException e) {
			throw new IOException("Syntax error has occured. Is it right format?", e);
		} catch (JsonIOException e) {
			throw new IOException(e);
		}
		return vertex;
	}

	public void writeVertex(WorldResource location, OneCut vertexes) throws IOException {
		try {
			File resource = theResourceManager.getResource(location, Names.Formats.NAME_VERTEX);
			JsonWriter jsw = new JsonWriter(new OutputStreamWriter(new FileOutputStream(resource)));
			new Gson().toJson(vertexes, OneCut.class, jsw);
			jsw.close();
		} catch (JsonIOException e) {
			throw new IOException(e);
		}
	}
}
