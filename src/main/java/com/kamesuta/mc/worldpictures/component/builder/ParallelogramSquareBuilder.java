package com.kamesuta.mc.worldpictures.component.builder;

import java.util.ArrayList;

import com.kamesuta.mc.worldpictures.reference.Names;

/**
 * 3つの頂点から平行四辺形を作成します
 * @author Kamesuta
 */
public class ParallelogramSquareBuilder extends BaseSquareBuilder {

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
				makeParallelogramSquare(vecA, vecB, vecC, vecD);
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
	 * 頂点A、頂点Bと、補助点Pを使い、2つのMinecraft軸と平行になる長方形ABCDとなるような頂点C、頂点Dを求めます
	 * @param vecA 頂点A
	 * @param vecB 頂点B
	 * @param vecC 頂点C
	 * @param vecD 頂点D 書き換え対象
	 */
	private static void makeParallelogramSquare(Vector3f vecA, Vector3f vecB, Vector3f vecC, Vector3f vecD) {
		vecD.set(vecA).sub(vecB).add(vecC);
	}

	@Override
	public String getName() {
		return Names.SquareBuilder.Parallelogram;
	}
}
