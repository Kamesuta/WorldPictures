package com.kamesuta.mc.worldpictures.vertex;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.vertex.square.Square;

/**
 * 1フレームの状態を保持します
 * @author Kamesuta
 */
public class FilmFrame implements Serializable {
	/**
	 * デフォルトアニメーション時間
	 * ミリ秒
	 */
	public static final long DefaultLength = 1000;

	private long l = DefaultLength;

	private Square v;

	public FilmFrame(Square square) {
		this.v = square;
	}

	public FilmFrame setLength(long l) {
		this.l = l;
		return this;
	}

	public long getLength() {
		return this.l;
	}

	public FilmFrame setSquare(Square v) {
		this.v = v;
		return this;
	}

	public Square getSquare() {
		return this.v;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (l ^ (l >>> 32));
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmFrame other = (FilmFrame) obj;
		if (l != other.l)
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [length=" + l + ", vertex=" + v + "]";
	}
}
