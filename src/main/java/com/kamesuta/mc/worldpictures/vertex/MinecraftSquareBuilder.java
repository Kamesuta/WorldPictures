package com.kamesuta.mc.worldpictures.vertex;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

public class MinecraftSquareBuilder extends BaseSquareBuilder {

	/**
	 * 空の状態から作成を開始します。
	 */
	public MinecraftSquareBuilder() {
		super(Lists.<Vector3f>newArrayList());
	}

	/**
	 * 完成品の修正を開始します
	 * @param square 完成品
	 */
	public MinecraftSquareBuilder(Square square) {
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
	public MinecraftSquareBuilder(Vector3f... preinit) {
		super(Lists.<Vector3f>newArrayList(preinit));
	}

	/**
	 * 編集方法を切り替えます
	 * @param builder 別の編集方法
	 */
	public MinecraftSquareBuilder(ISquareBuilder builder) {
		this(builder.export());
	}

	@Override
	public void set(int pos, Vector3f vec) {
	}

	@Override
	public void add(int pos, Vector3f vec) {
		int size = listSize();
		if (pos <= 0 || size == 0) {
			super.set(0, vec);
		} else {
			Vector3f basepoint = get(0);
			if (pos == 1 && 1 <= size) {
				// Re-use Vector3f instance
				Vector3f reUseVector = get(1);

				Vector3f extend = reUseVector.set(basepoint).negate();
				if (!basepoint.equals(vec)) {
					toLine(extend.add(vec));
					super.add(1, extend.add(basepoint));
				}
			} else {
				// Re-use Vector3f instance
				Vector3f reUseVector1 = get(2);
				Vector3f reUseVector2 = get(3);

				Vector3f extend = reUseVector1.set(basepoint).negate();
				extend.add(vec);
				Vector3f BtoA = reUseVector2.set(get(1)).sub(basepoint);
				toLineExcept(extend, BtoA).add(basepoint);
				super.set(2, BtoA.add(extend));
				super.set(3, extend);
			}
		}
	}

	/**
	 * 一番長いベクトル以外を0にします
	 * @param target 操作対象
	 * @return 操作対象
	 */
	public Vector3f toLine(Vector3f target) {
		float dx = Math.abs(target.x);
		float dy = Math.abs(target.y);
		float dz = Math.abs(target.z);

		if (dx >= dy && dx >= dz) {
			target.y = 0;
			target.z = 0;
		} else if (dy >= dx && dy >= dz) {
			target.x = 0;
			target.z = 0;
		} else if (dz >= dx && dz >= dy) {
			target.x = 0;
			target.y = 0;
		}

		return target;
	}

	/**
	 * except方向以外の一番長いベクトル以外を0にします
	 * @param target 操作対象
	 * @param except 除外方向
	 * @return 操作対象
	 */
	public Vector3f toLineExcept(Vector3f target, Vector3f except) {
		float edx = Math.abs(except.x);
		float edy = Math.abs(except.y);
		float edz = Math.abs(except.z);

		if (edx > edy && edx > edz) {
			target.x = 0;
		} else if (edy > edx && edy > edz) {
			target.y = 0;
		} else if (edz > edx && edz > edy) {
			target.z = 0;
		}

		return toLine(target);
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
		int size = listSize();
		if (0 < size) {
			Vector3f vec = get(0);
			if (size == 1) {

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
			} else if (2 <= size) {
				Vector3f vec2 = get(1);
				GL11.glBegin(GL11.GL_LINE_LOOP);
				GL11.glVertex3f(vec.x, vec.y, vec.z);
				GL11.glVertex3f(vec2.x, vec2.y, vec2.z);
				GL11.glVertex3f(vec.x, vec.y, vec.z);
				GL11.glVertex3f(vec.x, vec.y, vec.z);
				GL11.glEnd();
			}
		}
	}

	@Override
	public Square build() throws IllegalStateException {
		if (isReady()) {
			return new Square(get(0), get(1), get(2), get(3));
		} else {
			throw new IllegalStateException("Not Ready");
		}
	}

	@Override
	public Vector3f[] export() {
		return (Vector3f[]) data.toArray();
	}

}
