package com.kamesuta.mc.worldpictures.gui.widget2.position;

public class SimplePosition implements IPositionRelative {
	public final int x;
	public final int y;
	public final int w;
	public final int h;

	public SimplePosition(final int x, final int y, final int w, final int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	@Override
	public IPositionAbsolute getAbsolute(final IPositionAbsolute parent) {
		final int abs_x = parent.x1() + this.x;
		final int abs_y = parent.y1() + this.y;
		return new PositionAbsolute(
				abs_x,
				abs_y,
				abs_x + this.w,
				abs_y + this.h);
	}

	@Override
	public boolean isVaild() {
		return this.w > 0 && this.h > 0;
	}
}
