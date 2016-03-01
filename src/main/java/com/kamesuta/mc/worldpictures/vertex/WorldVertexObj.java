package com.kamesuta.mc.worldpictures.vertex;

import java.util.Arrays;

public class WorldVertexObj {
	public final int frame;
	public final Vector3f[] vertex = new Vector3f[4];

	public WorldVertexObj(int time, Vector3f... vertex) {
		this.frame = time;
		System.arraycopy(vertex, 0, this.vertex, 0, 4);
	}

	public WorldVertexObj(int time, WorldVertexObj vertex) {
		this.frame = time;
		System.arraycopy(vertex.vertex, 0, this.vertex, 0, 4);
	}

	@Override
	public String toString() {
		return "Vertex [frame=" + frame + ", vertex=" + Arrays.toString(vertex) + "]";
	}
}
