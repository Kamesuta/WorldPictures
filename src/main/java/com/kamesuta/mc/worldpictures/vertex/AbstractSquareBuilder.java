package com.kamesuta.mc.worldpictures.vertex;

/**
 * 編集データの抽象的な操作を定義します
 * @author Kamesuta
 */
public abstract class AbstractSquareBuilder implements ISquareBuilder {

	/**
	 * 補助線の長さ
	 * @return 補助線の長さ
	 */
	public float getAssistLength() {
		// TODO Configuration Handler
		return 64f;
	}

	/**
	 * 両端であるか (始端 or 終端)
	 * @param min 最小
	 * @param max 最大
	 * @param pos 位置
	 * @return 両端であるか
	 */
	public final boolean isEnds(int min, int max, int pos) {
		return min == pos || max == pos;
	}

	/**
	 * 範囲内の位置
	 * @param min 最小
	 * @param max 最大
	 * @param pos 位置
	 * @return 範囲内の位置
	 */
	public final int inRangePos(int min, int max, int pos) {
		return Math.max(min, Math.min(max, pos));
	}

	/**
	 * 範囲内であるか
	 * @param min 最小
	 * @param max 最大
	 * @param pos 位置
	 * @return 範囲内であるか
	 */
	public final boolean inRange(int min, int max, int pos) {
		return min <= pos && pos <= max;
	}

}