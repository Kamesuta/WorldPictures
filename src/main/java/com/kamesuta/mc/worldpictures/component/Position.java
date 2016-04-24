package com.kamesuta.mc.worldpictures.component;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;

import net.minecraft.nbt.NBTTagCompound;

@Immutable
public final class Position implements Serializable {

	/**
	 * X座標
	 */
	public final float x;
	/**
	 * Y座標
	 */
	public final float y;
	/**
	 * Z座標
	 */
	public final float z;

	/**
	 * ベクトルから作成
	 */
	public Position(final Vector3f vec) {
		this(vec.x, vec.y, vec.z);
	}

	/**
	 * 座標から作成
	 */
	public Position(final float x, final float y, final float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * X成分取得
	 */
	public final float getX() {
		return this.x;
	}

	/**
	 * Y成分取得
	 */
	public final float getY() {
		return this.y;
	}

	/**
	 * Z成分取得
	 */
	public final float getZ() {
		return this.z;
	}

	/**
	 * 位置ベクトル作成
	 * @return
	 */
	public final Vector3f toVec() {
		return new Vector3f(this);
	}

	/**
	 * 位置ベクトル作成 →OA
	 * @return
	 */
	public final Vector3f toVec(final Position o) {
		return this.toVec().sub(new Vector3f(o));
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.x);
		result = prime * result + Float.floatToIntBits(this.y);
		result = prime * result + Float.floatToIntBits(this.z);
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		final Position other = (Position) obj;
		if (Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	@Override
	public final String toString() {
		return String.format("[%s, %s, %s]", this.x, this.y, this.z);
	}

	/**
	 * NBTから作成
	 */
	public static Position fromNBT(final NBTTagCompound nbt) {
		if (nbt != null)
			if (nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z"))
				return new Position(nbt.getFloat("x"), nbt.getFloat("y"), nbt.getFloat("z"));
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound toNBT(final Position pos) {
		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("x", pos.x);
		nbt.setFloat("y", pos.y);
		nbt.setFloat("z", pos.z);
		return nbt;
	}

}
