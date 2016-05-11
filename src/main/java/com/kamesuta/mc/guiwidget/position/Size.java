package com.kamesuta.mc.guiwidget.position;

public class Size implements java.io.Serializable {

	public int w;

	public int h;

	private static final long serialVersionUID = -5276940640259749850L;

	public Size() {
		this(0, 0);
	}

	public Size(final Size p) {
		this(p.w, p.h);
	}

	public Size(final int w, final int h) {
		this.w = w;
		this.h = h;
	}

	public double getW() {
		return this.w;
	}

	public double getH() {
		return this.h;
	}

	@Override
	public String toString() {
		return String.format("Size[w=%s, h=%s]", this.w, this.h);
	}
}
