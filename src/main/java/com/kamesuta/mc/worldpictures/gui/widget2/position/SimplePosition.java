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
		final int wid = Math.max(parent.getAbsoluteW() - this.x, 0);
		final int hei = Math.max(parent.getAbsoluteH() - this.y, 0);
		return new PositionAbsolute(
				parent.getAbsoluteX() + this.x,
				parent.getAbsoluteY() + this.y,
				Math.min(wid, this.w),
				Math.min(hei, this.h));
	}

	@Override
	public boolean isVaild() {
		return this.w > 0 && this.h > 0;
	}
}
