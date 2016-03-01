package com.kamesuta.mc.worldpictures.vertex;

import java.util.Arrays;
import java.util.LinkedList;

public class WorldVertexCompound {
	public float time;
	public LinkedList<WorldVertexObj> vertexes;

	public WorldVertexCompound(float time, WorldVertexObj... vertexes) {
		this(time, new LinkedList<WorldVertexObj>(Arrays.asList(vertexes)));
	}

	public WorldVertexCompound(float time, LinkedList<WorldVertexObj> vertexes) {
		this.time = time;
		this.vertexes = vertexes;
	}
}
