package com.kamesuta.mc.worldpictures.vertex;

/**
 * 空間を操作します
 * @author Kamesuta
 */
public interface ISpaceOperation {

	/**
	 * 上書き
	 */
	ISpaceOperation set(Vector3f vec);

	/**
	 * 成分から上書き
	 */
	ISpaceOperation set(float x, float y, float z);

	/**
	 * 大きさ変更
	 * @param scale 大きさ(相対値)
	 */
	ISpaceOperation scale(double scale);

	/**
	 * 合成
	 */
	ISpaceOperation add(Vector3f vec);

	/**
	 * 成分から合成
	 */
	ISpaceOperation add(float x, float y, float z);

	/**
	 * 逆方向に合成
	 */
	ISpaceOperation sub(Vector3f vec);

	/**
	 * 成分から逆方向に合成
	 */
	ISpaceOperation sub(float x, float y, float z);

}