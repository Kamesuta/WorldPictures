package com.kamesuta.mc.worldpictures.component;

import org.apache.commons.lang3.Validate;

import com.kamesuta.mc.worldpictures.component.util.ComponentBounds;

import net.minecraft.util.AxisAlignedBB;

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
}
