package com.kamesuta.mc.worldpictures.component.builder.square;

import java.util.ArrayList;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;
import com.kamesuta.mc.worldpictures.reference.Names;

/**
 * 2つの頂点と一つの向きから長方形を作成します。
 * @author Kamesuta
 */
public class RectangleSquareBuilder extends BaseSquareBuilder {

	public static PendingSquareController ctr = new PendingSquareController() {
		@Override
		public void set(ArrayList<Vector3f> data, int pos, Vector3f vec) {
			int size = data.size();
			super.set(data, PendingSquare.inRangePos(0, 2, pos), vec);
			if (2 <= size) {
				Vector3f vecA = data.get(0);
				Vector3f vecB = data.get(1);
				Vector3f vecC = data.get(2);
				Vector3f vecD = (3 < size) ? data.get(3) : new Vector3f();
				Vector3f vecP = new Vector3f(vecC);
				makeRectangleSquare(vecA, vecB, vecP, vecC, vecD);
				super.set(data, 2, vecC);
				super.set(data, 3, vecD);
			}
		}

		@Override
		public void add(ArrayList<Vector3f> data, int pos, Vector3f vec) {
			set(data, pos, vec);
		}
	};

	protected PendingSquareController getCtr() {
		return ctr;
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
	public static void makeRectangleSquare(Vector3f vecA, Vector3f vecB, Vector3f vecP, Vector3f vecC, Vector3f vecD) {
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
	public String getName() {
		return Names.SquareBuilder.Rectangle;
	}

}
