package com.kamesuta.mc.worldpictures.gui.widget2.position;

public class PositionAbsolute implements IPositionAbsolute {
	public final int x;
	public final int y;
	public final int w;
	public final int h;

	public PositionAbsolute(final int x, final int y, final int w, final int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public int getAbsoluteX() {
		return this.x;
	}

	@Override
	public int getAbsoluteY() {
		return this.y;
	}

	@Override
	public int getAbsoluteW() {
		return this.w;
	}

	@Override
	public int getAbsoluteH() {
		return this.h;
	}

	@Override
	public boolean isVaild() {
		return this.w > 0 && this.h > 0;
	}

	@Override
	public final boolean pointInside(final int px, final int py) {
		return px >= this.x && px < this.x + this.w && py >= this.y && py < this.y + this.h;
	}

}
