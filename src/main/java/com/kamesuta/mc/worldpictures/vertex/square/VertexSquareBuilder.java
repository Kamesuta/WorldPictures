package com.kamesuta.mc.worldpictures.vertex.square;

import com.kamesuta.mc.worldpictures.reference.Names;

/**
 * 4つの頂点から四角形を作成します
 * @author Kamesuta
 */
public class VertexSquareBuilder extends BaseSquareBuilder {

	public static PendingSquareController ctr = new PendingSquareController();

	protected PendingSquareController getCtr() {
		return ctr;
	}

	@Override
	public String getName() {
		return Names.SquareBuilder.Vertex;
	}

}
