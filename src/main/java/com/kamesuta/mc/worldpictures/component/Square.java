package com.kamesuta.mc.worldpictures.component;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import org.apache.commons.lang3.Validate;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;

import net.minecraft.util.AxisAlignedBB;

@Immutable
public final class Square implements Serializable {

	public static final int U_lt = 0;
	public static final int V_lt = 0;
	public static final int U_lb = 0;
	public static final int V_lb = 1;
	public static final int U_rb = 1;
	public static final int V_rb = 1;
	public static final int U_rt = 1;
	public static final int V_rt = 0;

	public final Position lt;
	public final Position lb;
	public final Position rb;
	public final Position rt;

	public Square(final Position lt, final Position lb, final Position rb, final Position rt) {
		Validate.notNull(lt);
		Validate.notNull(lb);
		Validate.notNull(rb);
		Validate.notNull(rt);
		this.lt = lt;
		this.lb = lb;
		this.rb = rb;
		this.rt = rt;
	}

	public Square(final Square square) {
		this(square.lt, square.lb, square.rb, square.rt);
	}

	public boolean collisionWithLine(final Position a, final Position b) {
		final Vector3f ab = this.lb.toVec(this.lt);
		final Vector3f ad = this.rt.toVec(this.lt);
		final Vector3f n = ab.cross(ad);

		final float a_lt = a.toVec(this.lt).dot(n);
		final float b_lt = b.toVec(this.lt).dot(n);
		if (a_lt * b.toVec(this.lt).dot(n) <= 0) {
			final float d1 = Math.abs(a_lt);
			final float d2 = Math.abs(b_lt);
			final float d = d1 / (d1 + d2);
			final Vector3f p = (a.toVec().scale(1 - d)).add(b.toVec().scale(d));

			final boolean ua = this.lb.toVec(this.lt).cross(this.lt.toVec().sub(p)).dot(n) > 0;
			final boolean ub = this.rb.toVec(this.lb).cross(this.lb.toVec().sub(p)).dot(n) > 0;
			final boolean uc = this.rt.toVec(this.rb).cross(this.rb.toVec().sub(p)).dot(n) > 0;
			final boolean ud = this.lt.toVec(this.rt).cross(this.rt.toVec().sub(p)).dot(n) > 0;

			return ua && ub && uc && ud;
		}
		return false;
	}

	public AxisAlignedBB expandAABB(final AxisAlignedBB aabb) {
		aabb.addCoord(this.lt.x, this.lt.y, this.lt.z);
		aabb.addCoord(this.lb.x, this.lb.y, this.lb.z);
		aabb.addCoord(this.rb.x, this.rb.y, this.rb.z);
		aabb.addCoord(this.rt.x, this.rt.y, this.rt.z);
		return aabb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.lb == null) ? 0 : this.lb.hashCode());
		result = prime * result + ((this.lt == null) ? 0 : this.lt.hashCode());
		result = prime * result + ((this.rb == null) ? 0 : this.rb.hashCode());
		result = prime * result + ((this.rt == null) ? 0 : this.rt.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Square))
			return false;
		final Square other = (Square) obj;
		if (this.lb == null) {
			if (other.lb != null)
				return false;
		} else if (!this.lb.equals(other.lb))
			return false;
		if (this.lt == null) {
			if (other.lt != null)
				return false;
		} else if (!this.lt.equals(other.lt))
			return false;
		if (this.rb == null) {
			if (other.rb != null)
				return false;
		} else if (!this.rb.equals(other.rb))
			return false;
		if (this.rt == null) {
			if (other.rt != null)
				return false;
		} else if (!this.rt.equals(other.rt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Square[lt:%s, lb:%s, rb:%s, rt:%s]", this.lt, this.lb, this.rb, this.rt);
	}
}
