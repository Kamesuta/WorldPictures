package com.kamesuta.mc.worldpictures.vertex.square;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.vertex.ISpaceOperation;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;

import net.minecraft.client.renderer.Tessellator;

public class Square implements Serializable, ISpaceOperation{

	public static final int U_lt = 0;
	public static final int V_lt = 0;
	public static final int U_lb = 0;
	public static final int V_lb = 1;
	public static final int U_rb = 1;
	public static final int V_rb = 1;
	public static final int U_rt = 1;
	public static final int V_rt = 0;

	public final Vector3f lt;
	public final Vector3f lb;
	public final Vector3f rb;
	public final Vector3f rt;

	public Square(Vector3f lt, Vector3f lb, Vector3f rb, Vector3f rt) {
		this.lt = lt;
		this.lb = lb;
		this.rb = rb;
		this.rt = rt;
	}

	public void draw(Tessellator tessellator) {
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(this.lt.x, this.lt.y, this.lt.z, Square.U_lt, Square.V_lt);
		tessellator.addVertexWithUV(this.lb.x, this.lb.y, this.lb.z, Square.U_lb, Square.V_lb);
		tessellator.addVertexWithUV(this.rb.x, this.rb.y, this.rb.z, Square.U_rb, Square.V_rb);
		tessellator.addVertexWithUV(this.rt.x, this.rt.y, this.rt.z, Square.U_rt, Square.V_rt);
		tessellator.draw();
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

	@Override
	public Square set(Vector3f vec) {
		return set(vec.x, vec.y, vec.z);
	}

	@Override
	public Square set(float x, float y, float z) {
		float sx = x - lt.x;
		float sy = y - lt.y;
		float sz = z - lt.z;

		lt.add(sx, sy, sz);
		lb.add(sx, sy, sz);
		rb.add(sx, sy, sz);
		rt.add(sx, sy, sz);

		return this;
	}

	@Override
	public Square scale(double scale) {
		lt.scale(scale);
		lb.scale(scale);
		rb.scale(scale);
		rt.scale(scale);

		return this;
	}

	@Override
	public Square add(Vector3f vec) {
		lt.add(vec);
		lb.add(vec);
		rb.add(vec);
		rt.add(vec);

		return this;
	}

	@Override
	public Square add(float x, float y, float z) {
		lt.add(x, y, z);
		lb.add(x, y, z);
		rb.add(x, y, z);
		rt.add(x, y, z);

		return this;
	}

	@Override
	public Square sub(Vector3f vec) {
		lt.sub(vec);
		lb.sub(vec);
		rb.sub(vec);
		rt.sub(vec);

		return this;
	}

	@Override
	public Square sub(float x, float y, float z) {
		lt.sub(x, y, z);
		lb.sub(x, y, z);
		rb.sub(x, y, z);
		rt.sub(x, y, z);

		return this;
	}

}
