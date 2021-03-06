package com.kamesuta.mc.worldpictures.component;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.Validate;

/**
 * 1フレームの状態を保持します
 * @author Kamesuta
 */
@Immutable
public final class Keyframe implements Serializable {
	/**
	 * デフォルトアニメーション時間
	 * ミリ秒
	 */
	public static final long DefaultLength = 1000;

	public final long length;
	public final Square square;

	public Keyframe(final long length, final Square square) {
		Validate.notNull(square);
		if (length > 0)
			this.length = length;
		else
			this.length = DefaultLength;
		this.square = square;
	}

	public Keyframe(final Square square) {
		this(DefaultLength, square);
	}

	public Keyframe length(final long length) {
		return new Keyframe(length, this.square);
	}

	public Keyframe square(final Square square) {
		return new Keyframe(this.length, square);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.length ^ (this.length >>> 32));
		result = prime * result + ((this.square == null) ? 0 : this.square.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Keyframe))
			return false;
		final Keyframe other = (Keyframe) obj;
		if (this.length != other.length)
			return false;
		if (this.square == null) {
			if (other.square != null)
				return false;
		} else if (!this.square.equals(other.square))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Keyframe[length:%s, square:%s]", this.length, this.square);
	}
}
