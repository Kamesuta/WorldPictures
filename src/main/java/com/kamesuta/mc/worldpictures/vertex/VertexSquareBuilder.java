package com.kamesuta.mc.worldpictures.vertex;

import java.util.ListIterator;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

/**
 * 4つの頂点から四角形を作成します
 * @author Kamesuta
 */
public class VertexSquareBuilder extends BaseSquareBuilder {

	/**
	 * 空の状態から作成を開始します。
	 */
	public VertexSquareBuilder() {
		super(Lists.<Vector3f>newArrayList());
	}

	/**
	 * 完成品の修正を開始します
	 * @param square 完成品
	 */
	public VertexSquareBuilder(Square square) {
		super(
			Lists.<Vector3f>newArrayList(
				square.lt,
				square.lb,
				square.rb,
				square.rt
			)
		);
	}

	/**
	 * 頂点から編集を開始します
	 * @param preinit 頂点
	 */
	public VertexSquareBuilder(Vector3f... preinit) {
		super(Lists.<Vector3f>newArrayList(preinit));
	}

	/**
	 * 編集方法を切り替えます
	 * @param builder 別の編集方法
	 */
	public VertexSquareBuilder(ISquareBuilder builder) {
		this(builder.export());
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
		if (hasSpace() && hasData()) {
			for (ListIterator<Vector3f> it = data.listIterator(); it.hasNext();) {
				int i = it.nextIndex();
				Vector3f vec = it.next();
				if (isListEnds(i)) {
					float sx = target.x - vec.x;
					float sy = target.y - vec.y;
					float sz = target.z - vec.z;
					float dx = Math.abs(sx);
					float dy = Math.abs(sy);
					float dz = Math.abs(sz);
					GL11.glBegin(GL11.GL_LINES);
					float assistLength = getAssistLength();
					if (dx > dy && dx > dz) {
						GL11.glVertex3f(vec.x-(sx<0?assistLength:0), vec.y, vec.z);
						GL11.glVertex3f(vec.x+(sx>0?assistLength:0), vec.y, vec.z);
					} else if (dy > dx && dy > dz) {
						GL11.glVertex3f(vec.x, vec.y-(sy<0?assistLength:0), vec.z);
						GL11.glVertex3f(vec.x, vec.y+(sy>0?assistLength:0), vec.z);
					} else if (dz > dx && dz > dy) {
						GL11.glVertex3f(vec.x, vec.y, vec.z-(sz<0?assistLength:0));
						GL11.glVertex3f(vec.x, vec.y, vec.z+(sz>0?assistLength:0));
					}
					GL11.glEnd();
				}
			}
		}
	}

}
