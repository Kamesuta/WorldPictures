package com.kamesuta.mc.worldpictures.vertex;

import java.util.Arrays;
import java.util.LinkedList;

public class OneCut {
	public static final float DefaultTimeLength = 1f;
	public static final float MinTimeLength = 0.1f;
	public static final OneCut NULL = new OneCut(DefaultTimeLength);

	public float time;
	public LinkedList<Scene> vertexes;

	public OneCut(float time, Scene... vertexes) {
		this(time, new LinkedList<Scene>(Arrays.asList(vertexes)));
	}

	public OneCut(float time, LinkedList<Scene> vertexes) {
		this.time = time;
		this.vertexes = vertexes;
	}
}
