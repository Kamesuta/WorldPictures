package com.kamesuta.mc.worldpictures.component.builder.square;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;

public class PendingSquare {

	/**
	 * arraylist
	 */
	private ArrayList<Vector3f> data = new ArrayList<Vector3f>();

	/**
	 * position
	 */
	private int pos = 0;

	public PendingSquare() {
		super();
	}

	 /**
	 * 範囲内の位置
	 * @param min 最小
	 * @param max 最大
	 * @param pos 位置
	 * @return 範囲内の位置
	 */
	 public static int inRangePos(int min, int max, int pos) {
		 return Math.max(min, Math.min(max, pos));
	 }

	 /**
	 * 範囲内であるか
	 * @param min 最小
	 * @param max 最大
	 * @param pos 位置
	 * @return 範囲内であるか
	 */
	 public static boolean inRange(int min, int max, int pos) {
		 return min <= pos && pos <= max;
	 }

	/**
	 * 要素の全消去
	 */
	public void clear(PendingSquareController ctr) {
		ctr.clear(data);
	}

	/**
	 * 要素の取得
	 *
	 * @param pos
	 *            位置
	 * @return 要素
	 */
	public Vector3f get(PendingSquareController ctr, int pos) {
		return ctr.get(data, pos);
	}

	/**
	 * 要素の設定
	 *
	 * @param pos
	 *            位置
	 * @param vec
	 *            要素
	 */
	public void set(PendingSquareController ctr, int pos, Vector3f vec) {
		ctr.set(data, pos, vec);
	}

	/**
	 * 要素の追加
	 *
	 * @param vec
	 *            要素
	 */
	public void add(PendingSquareController ctr, int pos, Vector3f vec) {
		ctr.add(data, pos, vec);
	}

	/**
	 * 要素の削除
	 */
	public Vector3f remove(PendingSquareController ctr, int pos) {
		return ctr.remove(data, pos);
	}

	/**
	 * 現在位置の要素の取得
	 *
	 * @return 要素
	 */
	public Vector3f get(PendingSquareController ctr) {
		return get(ctr, pos);
	}

	/**
	 * 現在位置への要素の設定
	 *
	 * @param vec
	 *            要素
	 */
	public void set(PendingSquareController ctr, Vector3f vec) {
		set(ctr, pos, vec);
	}

	/**
	 * 現在位置への要素の追加
	 *
	 * @param vec
	 *            要素
	 */
	public void add(PendingSquareController ctr, Vector3f vec) {
		add(ctr, pos, vec);
	}

	/**
	 * 現在位置の要素の削除
	 */
	public void remove(PendingSquareController ctr) {
		remove(ctr, pos);
	}

	/**
	 * 完成することが可能か
	 */
	public boolean isReady(PendingSquareController ctr) {
		return ctr.isReady(data);
	}

	/**
	 * 要素のサイズ
	 *
	 * @return 要素のサイズ
	 */
	public int size() {
		return data.size();
	}

	/**
	 * 現在位置の取得
	 *
	 * @return 位置
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * 現在位置の設定
	 *
	 * @param setpos
	 *            位置
	 * @return 修正された位置
	 */
	public int setPos(int setpos) {
		return pos = setpos;
	}

	/**
	 * 始端位置に移動
	 *
	 * @return 始端位置
	 */
	public int setPosFirst() {
		return pos = 0;
	}

	/**
	 * 終端位置に移動
	 *
	 * @return 終端位置
	 */
	public int setPosLast() {
		return pos = size();
	}

	/**
	 * 次へ
	 *
	 * @return
	 */
	public int next() {
		return setPos((pos + 1) % size());
	}

	/**
	 * 前へ
	 *
	 * @return
	 */
	public int prev() {
		int size = size();
		return setPos((pos + size - 1) % size);
	}

	/**
	 * いてれーた
	 * @param ctr
	 * @return
	 */
	public Iterator<Vector3f> iterator(PendingSquareController ctr) {
		return new Itr(ctr);
	}

	private class Itr implements Iterator<Vector3f> {
		PendingSquareController ctr;

		int cursor; // index of next element to return

		public Itr(PendingSquareController ctr) {
			this.ctr = ctr;
		}

		public boolean hasNext() {
			return cursor != size();
		}

		public Vector3f next() {
			int i = cursor;
			if (i >= size())
				throw new NoSuchElementException();
			cursor = i + 1;
			return ctr.get(data, i);
		}
	}

}
