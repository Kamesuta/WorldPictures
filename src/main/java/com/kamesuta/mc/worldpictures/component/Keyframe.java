package com.kamesuta.mc.worldpictures.component;

import java.io.Serializable;

import net.minecraft.nbt.NBTTagCompound;

/**
 * 1フレームの状態を保持します
 * @author Kamesuta
 */
public class Keyframe implements Serializable {
	/**
	 * デフォルトアニメーション時間
	 * ミリ秒
	 */
	public static final long DefaultLength = 1000;

	private long length = DefaultLength;

	private Square square;

	public long getLength() {
		return length;
	}

	public Keyframe setLength(long l) {
		if (l > 0) this.length = l;
		return this;
	}

	public Square getSquare() {
		return square;
	}

	public Keyframe setSquare(Square s) {
		this.square = s;
		return this;
	}

	public NBTTagCompound toNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setLong("length", this.getLength());
		Square square = this.getSquare();
		if (square != null) nbt.setTag("square", Square.toNBT(square));
		return nbt;
	}

	public Keyframe fromNBT(NBTTagCompound nbt) {
		long length = nbt.getLong("length");
		this.setLength(length);
		Square square = Square.fromNBT(nbt.getCompoundTag("square"));
		if (square != null) this.setSquare(square);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (length ^ (length >>> 32));
		result = prime * result + ((square == null) ? 0 : square.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Keyframe))
			return false;
		Keyframe other = (Keyframe) obj;
		if (length != other.length)
			return false;
		if (square == null) {
			if (other.square != null)
				return false;
		} else if (!square.equals(other.square))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Keyframe [length:%s, square:%s]", length, square);
	}

}
