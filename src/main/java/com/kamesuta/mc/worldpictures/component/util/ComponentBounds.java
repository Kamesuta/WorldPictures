package com.kamesuta.mc.worldpictures.component.util;

import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.Position;
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.Square;

import net.minecraft.util.AxisAlignedBB;

/**
 * Componentから
 * @author Kamesuta
 */
public class ComponentBounds {
	private float minX;
	private float minY;
	private float minZ;
	private float maxX;
	private float maxY;
	private float maxZ;

	private ComponentBounds(final float x, final float y, final float z) {
		this.minX = this.maxX = x;
		this.minY = this.maxY = y;
		this.minZ = this.maxZ = z;
	}

	private ComponentBounds(final Position p) {
		this(p.x, p.y, p.z);
	}

	private void addPosition(final float x, final float y, final float z) {
		this.minX = Math.min(this.minX, x);
		this.minY = Math.min(this.minY, y);
		this.minZ = Math.min(this.minZ, z);
		this.maxX = Math.max(this.maxX, x);
		this.maxY = Math.max(this.maxY, y);
		this.maxZ = Math.max(this.maxZ, z);
	}

	private void addPosition(final Position p) {
		addPosition(p.x, p.y, p.z);
	}

	private void addSquare(final Square square) {
		addPosition(square.lt);
		addPosition(square.lb);
		addPosition(square.rb);
		addPosition(square.rt);
	}

	private void addScene(final Scene scene) {
		for (final Keyframe kf : scene.keyframes) {
			addSquare(kf.square);
		}
	}

	private AxisAlignedBB getBounds() {
		return AxisAlignedBB.getBoundingBox(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
	}

	public static AxisAlignedBB boundsPosition(final Position p) {
		final ComponentBounds c = new ComponentBounds(p);
		return c.getBounds();
	}

	public static AxisAlignedBB boundsSquare(final Square square) {
		final ComponentBounds c = new ComponentBounds(square.lt);
		c.addSquare(square);
		return c.getBounds();
	}

	public static AxisAlignedBB boundsScene(final Scene scene) {
		final ComponentBounds c = new ComponentBounds(scene.keyframes.get(0).square.lt);
		c.addScene(scene);
		return c.getBounds();
	}
}
