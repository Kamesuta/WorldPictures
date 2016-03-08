package com.kamesuta.mc.worldpictures.vertex;

import java.io.Serializable;

import com.kamesuta.mc.worldpictures.vertex.square.Square;

public class Scene implements Serializable {
	public static final int DefaultLength = 1;

	public Integer l;
	public Square v;

	public Scene(Square square) {
		this.v = square;
	}

	public Scene(Vector3f lt, Vector3f lb, Vector3f rb, Vector3f rt) {
		this(new Square(lt, lb, rb, rt));
	}

	public Scene setLength(Integer l) {
		this.l = l;
		return this;
	}

	public int getLength() {
		if (this.l != null) return this.l;
		else return DefaultLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v == null) ? 0 : v.hashCode());
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
		Scene other = (Scene) obj;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [length=" + l + ", vertex=" + v + "]";
	}
}
