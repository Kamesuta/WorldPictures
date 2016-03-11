package com.kamesuta.mc.worldpictures.vertex.square;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.kamesuta.mc.worldpictures.vertex.ISpaceOperation;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;

/**
 * 編集データの具体的な操作、保持、管理をします。
 * @author Kamesuta
 */
public abstract class BaseSquareBuilder extends AbstractSquareBuilder {

	protected ArrayList<Vector3f> data;
	protected int pos = 0;

	/**
	 * リストから初期化します。
	 * @param list
	 */
	protected BaseSquareBuilder() {
		data = Lists.<Vector3f>newArrayList();
	}

	/**
	 * 完成品の修正を開始します
	 * @param square 完成品
	 */
	@Override
	public void load(Square square) {
		set(0, square.lt);
		set(1, square.lb);
		set(2, square.rb);
		set(3, square.rt);
	}

	/**
	 * 編集方法を切り替えます
	 * @param builder 別の編集方法
	 */
	@Override
	public void load(ISquareBuilder builder) {
		Vector3f[] vertex = builder.export();
		for (int i = 0; i < vertex.length && i < 4; i++) {
			set(i, vertex[i]);
		}
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public final int squareSize() {
		return 4;
	}

	@Override
	public int listSize() {
		return Math.min(squareSize(), data.size());
	}

	/**
	 * 一つ以上の要素がありますか？
	 * @return 要素存在
	 */
	public boolean hasSpace() {
		return lastListPos() < lastSquarePos();
	}

	/**
	 * 空きがありますか？
	 * @return 空き存在
	 */
	public boolean hasData() {
		return !data.isEmpty();
	}

	@Override
	public Vector3f get(int pos) {
		if (hasData())
			return data.get(inListRangePos(pos));
		else
			return null;
	}

	@Override
	public void set(int pos, Vector3f vec) {
		if (hasData() && inListRange(pos)) {
			int inpos = inSquareRangePos(pos);
			data.set(inpos, vec);
			setPos(inpos);
		} else {
			data.add(vec);
			setPosLast();
		}
	}

	@Override
	public void add(int pos, Vector3f vec) {
		boolean hasSpace = hasSpace();
		if (inListRange(pos)) {
			int inpos = inSquareRangePos(pos);
			data.add(inpos, vec);
			setPos(inpos);
		} else {
			data.add(vec);
			setPosLast();
		}
		if (!hasSpace) removeLast();
	}

	@Override
	public void remove(int pos) {
		if (hasData())
			data.remove(inListRangePos(pos));
	}

	@Override
	public ISpaceOperation get() {
		return get(pos);
	}

	@Override
	public void set(Vector3f vec) {
		set(pos, vec);
	}

	@Override
	public void add(Vector3f vec) {
		add(pos, vec);
	}

	@Override
	public void remove() {
		remove(pos);
	}

	@Override
	public ISpaceOperation getLast() {
		return get(lastListPos());
	}

	@Override
	public void setLast(Vector3f vec) {
		set(lastListPos(), vec);
	}

	@Override
	public void addLast(Vector3f vec) {
		add(lastListPos()+1, vec);
	}

	@Override
	public void removeLast() {
		remove(lastListPos());
	}

	@Override
	public int getPos() {
		return pos;
	}

	@Override
	public int setPos(int setpos) {
		return pos = inListRangePos(setpos);
	}

	/**
	 * 始端位置に移動
	 * @return 始端位置
	 */
	public int setPosFirst() {
		return pos = 0;
	}

	/**
	 * 終端位置に移動
	 * @return 終端位置
	 */
	public int setPosLast() {
		return pos = lastListPos();
	}

	@Override
	public int next() {
		return setPos((pos + 1) % listSize());
	}

	@Override
	public int prev() {
		int size = listSize();
		return setPos((pos + size - 1) % size);
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