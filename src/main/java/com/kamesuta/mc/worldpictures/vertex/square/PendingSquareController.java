package com.kamesuta.mc.worldpictures.vertex.square;

import java.util.ArrayList;

import com.kamesuta.mc.worldpictures.vertex.Vector3f;

public class PendingSquareController {

	/**
	 * 要素の全消去
	 */
	public void clear(ArrayList<Vector3f> data) {
		data.clear();
	}

	/**
	 * 要素の取得
	 * @param pos 位置
	 * @return 要素
	 */
	public Vector3f get(ArrayList<Vector3f> data, int pos) {
		if (0 <= pos)
			if (pos < data.size())
				return data.get(pos);
		return null;
	}

	/**
	 * 要素の設定
	 * @param pos 位置
	 * @param vec 要素
	 */
	public void set(ArrayList<Vector3f> data, int pos, Vector3f vec) {
		if (0 <= pos)
			if (pos < data.size())
				data.set(pos, vec);
			else
				data.add(vec);
	}

	/**
	 * 要素の追加
	 * @param vec 要素
	 */
	public void add(ArrayList<Vector3f> data, int pos, Vector3f vec) {
		boolean hasSpace = data.size() < 4;
		if (0 <= pos)
			if (pos < data.size())
				data.add(pos, vec);
			else
				data.add(vec);
		if (!hasSpace)
			remove(data, data.size()-1);
	}

	/**
	 * 要素の削除
	 */
	public Vector3f remove(ArrayList<Vector3f> data, int pos) {
		return data.remove(pos);
	}

	/**
	 * 完成することが可能か
	 */
	public boolean isReady(ArrayList<Vector3f> data) {
		return data.size() < 4;
	}

}
