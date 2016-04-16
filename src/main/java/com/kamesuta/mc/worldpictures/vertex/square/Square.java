package com.kamesuta.mc.worldpictures.vertex.square;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.vertex.Vector3f;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class Square implements Serializable {

	public static final int U_lt = 0;
	public static final int V_lt = 0;
	public static final int U_lb = 0;
	public static final int V_lb = 1;
	public static final int U_rb = 1;
	public static final int V_rb = 1;
	public static final int U_rt = 1;
	public static final int V_rt = 0;

	public final Vector3f lt = new Vector3f();
	public final Vector3f lb = new Vector3f();
	public final Vector3f rb = new Vector3f();
	public final Vector3f rt = new Vector3f();

	public Square() {

	}

	@Deprecated
	public Square(Square square) {
		set(square);
	}

	@Deprecated
	public Square(Vector3f lt, Vector3f lb, Vector3f rb, Vector3f rt) {
		set(lt, lb, rb, rt);
	}

	@Deprecated
	public void set(Square square) {
		this.lt.set(square.lt);
		this.lb.set(square.lb);
		this.rb.set(square.rb);
		this.rt.set(square.rt);
	}

	public void set(Vector3f lt, Vector3f lb, Vector3f rb, Vector3f rt) {
		this.lt.set(lt);
		this.lb.set(lb);
		this.rb.set(rb);
		this.rt.set(rt);
	}

	public void draw(Tessellator tessellator) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(this.lt.x, this.lt.y, this.lt.z, Square.U_lt, Square.V_lt);
		tessellator.addVertexWithUV(this.lb.x, this.lb.y, this.lb.z, Square.U_lb, Square.V_lb);
		tessellator.addVertexWithUV(this.rb.x, this.rb.y, this.rb.z, Square.U_rb, Square.V_rb);
		tessellator.addVertexWithUV(this.rt.x, this.rt.y, this.rt.z, Square.U_rt, Square.V_rt);
		tessellator.draw();
	}

	public boolean collisionWithLine(Vector3f v1, Vector3f v2) {
		Vector3f ab = new Vector3f(lt).sub(lb);
		Vector3f ad = new Vector3f(lt).sub(rt);
		Vector3f n = ab.cross(ad);

		if (new Vector3f(v1).sub(lt).dot(n) * new Vector3f(v2).sub(lt).dot(n) <= 0) {
			float d1 = Math.abs(new Vector3f(lt).sub(v1).dot(n));
			float d2 = Math.abs(new Vector3f(lt).sub(v2).dot(n));
			float d = d1 / (d1 + d2);
			Vector3f p = (new Vector3f(v1).scale(1-d)).add(new Vector3f(v2).scale(d));

			boolean ua = (new Vector3f(lt).sub(lb)).cross(new Vector3f(lt).sub(p)).dot(n) > 0;
			boolean ub = (new Vector3f(lb).sub(rb)).cross(new Vector3f(lb).sub(p)).dot(n) > 0;
			boolean uc = (new Vector3f(rb).sub(rt)).cross(new Vector3f(rb).sub(p)).dot(n) > 0;
			boolean ud = (new Vector3f(rt).sub(lt)).cross(new Vector3f(rt).sub(p)).dot(n) > 0;

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

	public NBTTagCompound toNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("lt", lt.toNBT());
		nbt.setTag("lb", lb.toNBT());
		nbt.setTag("rb", rb.toNBT());
		nbt.setTag("rt", rt.toNBT());
		return nbt;
	}

	public void fromNBT(NBTTagCompound nbt) {
		lt.fromNBT(nbt.getCompoundTag("lt"));
		lb.fromNBT(nbt.getCompoundTag("lb"));
		rb.fromNBT(nbt.getCompoundTag("rb"));
		rt.fromNBT(nbt.getCompoundTag("rt"));
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
		if (getClass() != obj.getClass())
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
		return "Square [" + lt + ", " + lb + ", " + rb + ", " + rt + "]";
	}

}
