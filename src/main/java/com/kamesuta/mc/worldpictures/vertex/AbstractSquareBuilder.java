package com.kamesuta.mc.worldpictures.vertex;

/**
 * 編集データの抽象的な操作を定義します
 * @author Kamesuta
 */
public abstract class AbstractSquareBuilder implements ISquareBuilder {

	/**
	 * 一つ以上の要素がありますか？
	 * @return 要素存在
	 */
	public abstract boolean hasData();

	/**
	 * 空きがありますか？
	 * @return 空き存在
	 */
	public abstract boolean hasSpace();

	/**
	 * 補助線の長さ
	 * @return 補助線の長さ
	 */
	public float getAssistLength() {
		// TODO Configuration Handler
		return 64f;
	}

	/**
	 * 四角形の終端位置
	 * @return 四角形の終端位置
	 */
	public int lastSquarePos() {
		return squareSize() - 1;
	}

	/**
	 * 要素の終端位置
	 * @return
	 */
	public int lastListPos() {
		return Math.max(0, listSize() - 1);
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
	 * 四角形の両端であるか
	 * @param pos 位置
	 * @return 両端であるか
	 */
	public boolean isSquareEnds(int pos) {
		return isEnds(0, lastSquarePos(), pos);
	}

	/**
	 * 要素の両端であるか
	 * @param pos 位置
	 * @return 要素であるか
	 */
	public boolean isListEnds(int pos) {
		return isEnds(0, lastListPos(), pos);
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
	 * 四角形の範囲内の位置
	 * @param pos 位置
	 * @return 範囲内の位置
	 */
	public int inSquareRangePos(int pos) {
		return inRangePos(0, lastSquarePos(), pos);
	}

	/**
	 * 要素の範囲内の位置
	 * @param pos 位置
	 * @return 要素の位置
	 */
	public int inListRangePos(int pos) {
		return inRangePos(0, lastListPos(), pos);
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

	/**
	 * 四角形の範囲内であるか
	 * @param pos 位置
	 * @return 四角形の範囲内であるか
	 */
	public boolean inSquareRange(int pos) {
		return inRange(0, lastSquarePos(), pos);
	}

	/**
	 * 要素の範囲内であるか
	 * @param pos 位置
	 * @return 要素の範囲内であるか
	 */
	public boolean inListRange(int pos) {
		return inRange(0, lastListPos(), pos);
	}

	/**
	 * 完成することが可能か
	 */
	@Override
	public boolean isReady() {
		return !hasSpace();
	}

}