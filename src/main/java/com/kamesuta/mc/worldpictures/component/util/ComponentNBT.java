package com.kamesuta.mc.worldpictures.component.util;

import java.util.ArrayList;

import com.kamesuta.mc.worldpictures.component.Component;
import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.Position;
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.Square;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ComponentNBT {
	/**
	 * NBTから作成
	 */
	public static Component ComponentFromNBT(final NBTTagCompound nbt) {
		if (nbt != null) {
			final NBTTagList nbtlist = nbt.getTagList("scene", Constants.NBT.TAG_COMPOUND);
			final Scene scene = SceneFromNBT(nbtlist);
			if (scene != null)
				return new Component(scene);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound ComponentToNBT(final Component component) {
		if (component != null) {
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("scene", SceneToNBT(component.scene));
			return nbt;
		}
		return null;
	}

	/**
	 * NBTから作成
	 */
	public static Scene SceneFromNBT(final NBTTagList nbtlist) {
		if (nbtlist != null) {
			final ArrayList<Keyframe> kfs = new ArrayList<Keyframe>();
			final int count = nbtlist.tagCount();
			for (int i = 0; i < count; i++) {
				final Keyframe kf = KeyframeFromNBT(nbtlist.getCompoundTagAt(i));
				if (kf != null)
					kfs.add(kf);
			}
			if (!kfs.isEmpty())
				return new Scene(kfs);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagList SceneToNBT(final Scene scene) {
		if (scene != null) {
			final NBTTagList nbtlist = new NBTTagList();
			for (final Keyframe v : scene.keyframes) {
				nbtlist.appendTag(KeyframeToNBT(v));
			}
			return nbtlist;
		}
		return null;
	}

	/**
	 * NBTから作成
	 */
	public static Keyframe KeyframeFromNBT(final NBTTagCompound nbt) {
		if (nbt != null) {
			final long length = nbt.getLong("length");
			final Square square = SquareFromNBT(nbt.getCompoundTag("square"));
			if (square != null && nbt.hasKey("length"))
				return new Keyframe(length, square);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound KeyframeToNBT(final Keyframe kf) {
		if (kf != null) {
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setLong("length", kf.length);
			nbt.setTag("square", SquareToNBT(kf.square));
			return nbt;
		}
		return null;
	}

	/**
	 * NBTから作成
	 */
	public static Square SquareFromNBT(final NBTTagCompound nbt) {
		if (nbt != null) {
			final Position lt = PositionFromNBT(nbt.getCompoundTag("lt"));
			final Position lb = PositionFromNBT(nbt.getCompoundTag("lb"));
			final Position rb = PositionFromNBT(nbt.getCompoundTag("rb"));
			final Position rt = PositionFromNBT(nbt.getCompoundTag("rt"));
			if (lt != null && lb != null && rb != null && rt != null)
				return new Square(lt, lb, rb, rt);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound SquareToNBT(final Square square) {
		if (square != null) {
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("lt", PositionToNBT(square.lt));
			nbt.setTag("lb", PositionToNBT(square.lb));
			nbt.setTag("rb", PositionToNBT(square.rb));
			nbt.setTag("rt", PositionToNBT(square.rt));
			return nbt;
		}
		return null;
	}

	/**
	 * NBTから作成
	 */
	public static Position PositionFromNBT(final NBTTagCompound nbt) {
		if (nbt != null)
			if (nbt.hasKey("x") && nbt.hasKey("y") && nbt.hasKey("z"))
				return new Position(nbt.getFloat("x"), nbt.getFloat("y"), nbt.getFloat("z"));
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound PositionToNBT(final Position pos) {
		if (pos != null) {
			final NBTTagCompound nbt = new NBTTagCompound();
			nbt.setFloat("x", pos.x);
			nbt.setFloat("y", pos.y);
			nbt.setFloat("z", pos.z);
			return nbt;
		}
		return null;
	}
}
