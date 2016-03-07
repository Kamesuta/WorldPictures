package com.kamesuta.mc.worldpictures.vertex;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

/**
 * 2つの頂点と一つの向きから長方形を作成します。
 * @author Kamesuta
 */
public class RectangleSquareBuilder extends BaseSquareBuilder {

	/**
	 * 空の状態から作成を開始します。
	 */
	public RectangleSquareBuilder() {
		super(Lists.<Vector3f>newArrayList());
	}

	/**
	 * 完成品の修正を開始します
	 * @param square 完成品
	 */
	public RectangleSquareBuilder(Square square) {
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
	public RectangleSquareBuilder(Vector3f... preinit) {
		super(Lists.<Vector3f>newArrayList(preinit));
	}

	/**
	 * 編集方法を切り替えます
	 * @param builder 別の編集方法
	 */
	public RectangleSquareBuilder(ISquareBuilder builder) {
		this(builder.export());
	}

	@Override
	public void set(int pos, Vector3f vec) {
		int size = listSize();
		if (0 < size && 1 <= pos) {
			Vector3f vecA = get(0);
			if (2 <= size && 2 <= pos) {
				Vector3f vecB = get(1);
				Vector3f vecC = (2 < size) ? get(2) : new Vector3f();
				Vector3f vecD = (3 < size) ? get(3) : new Vector3f();
				makeRectangleSquare(vecA, vecB, vec, vecC, vecD);
				super.set(2, vecC);
				super.set(3, vecD);
			} else if (!vecA.equals(vec)) {
				Vector3f vecB = (1 < size) ? get(1) : new Vector3f();
				vecB.set(vec);
				super.set(1, vecB);
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
	 * 頂点A、頂点Bと、補助点Pを使い、長方形ABCDとなるような頂点C、頂点Dを求めます
	 * @see <a href="https://teratail.com/questions/29051">teratail</a>
	 * @param vecA 頂点A
	 * @param vecB 頂点B
	 * @param vecP 補助点P
	 * @param vecD 頂点C 書き換え対象
	 * @param vecC 頂点D 書き換え対象
	 */
	public void makeRectangleSquare(Vector3f vecA, Vector3f vecB, Vector3f vecP, Vector3f vecC, Vector3f vecD) {
		// ① ベクトルABとベクトルAPの内積を求める (=ABの長さ×APのAB方向の長さ(符号はABと同じ方向か否か))
		Vector3f vecAB = vecC.set(vecB).sub(vecA);
		Vector3f vecAP = vecD.set(vecP).sub(vecA);
		float dotABAP = vecAB.dot(vecAP);

		// ② (1.の結果)÷(ABの長さ)^2を求める (=APのAB方向の長さ/ABの長さ)
		double divAPAB = dotABAP / vecA.lengthSquaredTo(vecB);

		// ③ ベクトルP - (2.の結果)×ベクトルAB がA'の位置ベクトル、すなわちA'の座標
		//    (第2項はAPのAB方向の長さ×AB方向の長さ1のベクトル)
		vecD.set(vecP).sub(vecAB.scale(divAPAB));
		vecC.set(vecD).add(vecB).sub(vecA);
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
}
