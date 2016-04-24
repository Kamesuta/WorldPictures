package com.kamesuta.mc.worldpictures.component;

import org.apache.commons.lang3.Validate;

import com.kamesuta.mc.worldpictures.component.util.ComponentBounds;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;

public class Component {
	public final Scene scene;
	public final AxisAlignedBB bounds;

	public Component(final Scene scene) {
		Validate.notNull(scene);
		this.scene = scene;
		this.bounds = ComponentBounds.boundsScene(scene);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.bounds == null) ? 0 : this.bounds.hashCode());
		result = prime * result + ((this.scene == null) ? 0 : this.scene.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Component))
			return false;
		final Component other = (Component) obj;
		if (this.bounds == null) {
			if (other.bounds != null)
				return false;
		} else if (!this.bounds.equals(other.bounds))
			return false;
		if (this.scene == null) {
			if (other.scene != null)
				return false;
		} else if (!this.scene.equals(other.scene))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Component[scene:%s, bounds:%s]", this.scene, this.bounds);
	}

	/**
	 * NBTから作成
	 */
	public static Component fromNBT(final NBTTagCompound nbt) {
		if (nbt != null) {
			final NBTTagList nbtlist = nbt.getTagList("scene", Constants.NBT.TAG_COMPOUND);
			final Scene scene = Scene.fromNBT(nbtlist);
			if (scene != null)
				return new Component(scene);
		}
		return null;
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound toNBT(final Component component) {
		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("scene", Scene.toNBT(component.scene));
		return nbt;
	}
}
