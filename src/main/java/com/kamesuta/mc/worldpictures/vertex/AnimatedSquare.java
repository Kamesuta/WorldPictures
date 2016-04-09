package com.kamesuta.mc.worldpictures.vertex;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.vertex.square.Square;

/**
 * 1フレームの状態を保持します
 * @author Kamesuta
 */
public class AnimatedSquare extends Square implements Serializable {
	/**
	 * デフォルトアニメーション時間
	 * ミリ秒
	 */
	public static final long DefaultLength = 1000;

	private long l = -1;

	public AnimatedSquare() {
		super();
	}

	public AnimatedSquare(Square square) {
		super(square);
	}

	public AnimatedSquare(Vector3f lt, Vector3f lb, Vector3f rb, Vector3f rt) {
		super(lt, lb, rb, rt);
	}

	public AnimatedSquare setLength(long l) {
		if (l > 0) this.l = l;
		else this.l = -1;

		return this;
	}

	public long getLength() {
		if (this.l > 0) return l;
		else return DefaultLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (l ^ (l >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimatedSquare other = (AnimatedSquare) obj;
		if (l != other.l)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FilmFrame [l=" + l + ", lt=" + lt + ", lb=" + lb + ", rb=" + rb + ", rt=" + rt + "]";
	}
}
