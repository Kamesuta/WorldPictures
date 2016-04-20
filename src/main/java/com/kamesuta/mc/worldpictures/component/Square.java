package com.kamesuta.mc.worldpictures.component;

import java.io.Serializable;

import javax.annotation.concurrent.Immutable;

import com.kamesuta.mc.worldpictures.component.builder.Vector3f;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

@Immutable
public class Square implements Serializable {

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

	public Square(Position lt, Position lb, Position rb, Position rt) {
		this.lt = lt;
		this.lb = lb;
		this.rb = rb;
		this.rt = rt;
	}

	public Square(Square square) {
		this(square.lt, square.lb, square.rb, square.rt);
	}

	public void draw(Tessellator tessellator) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(this.lt.x, this.lt.y, this.lt.z, Square.U_lt, Square.V_lt);
		tessellator.addVertexWithUV(this.lb.x, this.lb.y, this.lb.z, Square.U_lb, Square.V_lb);
		tessellator.addVertexWithUV(this.rb.x, this.rb.y, this.rb.z, Square.U_rb, Square.V_rb);
		tessellator.addVertexWithUV(this.rt.x, this.rt.y, this.rt.z, Square.U_rt, Square.V_rt);
		tessellator.draw();
	}

	public boolean collisionWithLine(Position a, Position b) {
		Vector3f ab = lb.toVec(lt);
		Vector3f ad = rt.toVec(lt);
		Vector3f n = ab.cross(ad);

		final float a_lt = a.toVec(lt).dot(n);
		final float b_lt = b.toVec(lt).dot(n);
		if (a_lt * b.toVec(lt).dot(n) <= 0) {
			final float d1 = Math.abs(a_lt);
			final float d2 = Math.abs(b_lt);
			final float d = d1 / (d1 + d2);
			Vector3f p = (a.toVec().scale(1 - d)).add(b.toVec().scale(d));

			final boolean ua = lb.toVec(lt).cross(lt.toVec().sub(p)).dot(n) > 0;
			final boolean ub = rb.toVec(lb).cross(lb.toVec().sub(p)).dot(n) > 0;
			final boolean uc = rt.toVec(rb).cross(rb.toVec().sub(p)).dot(n) > 0;
			final boolean ud = lt.toVec(rt).cross(rt.toVec().sub(p)).dot(n) > 0;

			return ua && ub && uc && ud;
		}
		return false;
	}

	public AxisAlignedBB extendAABB(AxisAlignedBB aabb) {
		aabb.addCoord(lt.x, lt.y, lt.z);
		aabb.addCoord(lb.x, lb.y, lb.z);
		aabb.addCoord(rb.x, rb.y, rb.z);
		aabb.addCoord(rt.x, rt.y, rt.z);
		return aabb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lb == null) ? 0 : lb.hashCode());
		result = prime * result + ((lt == null) ? 0 : lt.hashCode());
		result = prime * result + ((rb == null) ? 0 : rb.hashCode());
		result = prime * result + ((rt == null) ? 0 : rt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Square))
			return false;
		Square other = (Square) obj;
		if (lb == null) {
			if (other.lb != null)
				return false;
		} else if (!lb.equals(other.lb))
			return false;
		if (lt == null) {
			if (other.lt != null)
				return false;
		} else if (!lt.equals(other.lt))
			return false;
		if (rb == null) {
			if (other.rb != null)
				return false;
		} else if (!rb.equals(other.rb))
			return false;
		if (rt == null) {
			if (other.rt != null)
				return false;
		} else if (!rt.equals(other.rt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Square [lt:%s, lb:%s, rb:%s, rt:%s]", lt, lb, rb, rt);
	}

	/**
	 * NBTから作成
	 */
	public static Square fromNBT(NBTTagCompound nbt) {
		return new Square(Position.fromNBT(nbt.getCompoundTag("lt")), Position.fromNBT(nbt.getCompoundTag("lb")),
				Position.fromNBT(nbt.getCompoundTag("rb")), Position.fromNBT(nbt.getCompoundTag("rt")));
	}

	/**
	 * NBTを作成
	 */
	public static NBTTagCompound toNBT(Square square) {
		final NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("lt", Position.toNBT(square.lt));
		nbt.setTag("lb", Position.toNBT(square.lb));
		nbt.setTag("rb", Position.toNBT(square.rb));
		nbt.setTag("rt", Position.toNBT(square.rt));
		return nbt;
	}

}
