package com.kamesuta.mc.worldpictures.vertex.square;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;

/**
 * 3つの頂点から平行四辺形を作成します
 * @author Kamesuta
 */
public class ParallelogramSquareBuilder extends BaseSquareBuilder {

	@Override
	public void set(int pos, Vector3f vec) {
		int size = listSize();
		if (0 < size && 1 <= pos) {
			Vector3f vecA = get(0);
			if (2 <= size && 2 <= pos) {
				Vector3f vecB = get(1);
				Vector3f vecC = get(2);
				Vector3f vecD = (3 < size) ? get(3) : new Vector3f();
				makeParallelogramSquare(vecA, vecB, vecC, vecD);
				super.set(3, vecD);
			} else if (!vecA.equals(vec)) {
				super.set(pos, vec);
			}
		} else {
			super.set(0, vec);
		}
	}

	@Override
	public void add(int pos, Vector3f vec) {
		set(pos, vec);
	}

	/**
	 * 頂点A、頂点Bと、補助点Pを使い、2つのMinecraft軸と平行になる長方形ABCDとなるような頂点C、頂点Dを求めます
	 * @param vecA 頂点A
	 * @param vecB 頂点B
	 * @param vecC 頂点C
	 * @param vecD 頂点D 書き換え対象
	 */
	private void makeParallelogramSquare(Vector3f vecA, Vector3f vecB, Vector3f vecC, Vector3f vecD) {
		vecD.set(vecA).sub(vecB).add(vecC);
	}

	@Override
	public void renderAssist() {
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (Vector3f vec : data) {
			GL11.glVertex3f(vec.x, vec.y, vec.z);
		}
		GL11.glEnd();
	}

	@Override
	public void renderAssistLine(Vector3f target) {
		float corner = 0.5f;
		int size = listSize();
		if (0 < size) {
			if (size == 1) {
				Vector3f vec = get(0);
				GL11.glBegin(GL11.GL_LINES);
				GL11.glVertex3f(vec.x-corner, vec.y, vec.z);
				GL11.glVertex3f(vec.x+corner, vec.y, vec.z);
				GL11.glVertex3f(vec.x, vec.y-corner, vec.z);
				GL11.glVertex3f(vec.x, vec.y+corner, vec.z);
				GL11.glVertex3f(vec.x, vec.y, vec.z-corner);
				GL11.glVertex3f(vec.x, vec.y, vec.z+corner);
				GL11.glEnd();
			}
		}
	}

	@Override
	public String getName() {
		return Names.SquareBuilder.Parallelogram;
	}
}
