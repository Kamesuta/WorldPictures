package com.kamesuta.mc.worldpictures.gui.widget2.position;

public class RelativePosition implements IPositionRelative {

	public int x1;
	public int y1;
	public int x2;
	public int y2;

	public RelativePosition(final int x1, final int y1, final int x2, final int y2) {
		set(x1, y1, x2, y2);
	}

	public RelativePosition set(final int x1, final int y1, final int x2, final int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		return this;
	}

	@Override
	public boolean isVaild() {
		return this.x1!=this.x2 && this.y1!=this.y2;
	}

	@Override
	public IPositionAbsolute getAbsolute(final IPositionAbsolute parent) {
		final int pw = parent.w();
		final int ph = parent.h();
		final int abs_x1 = (this.x1 >= 0) ? this.x1 : pw - this.x1;
		final int abs_y1 = (this.y1 >= 0) ? this.y1 : ph - this.y1;
		final int abs_x2 = (this.x2 >= 0) ? this.x2 : pw - this.x2;
		final int abs_y2 = (this.y2 >= 0) ? this.y2 : ph - this.y2;
		final int rx = Math.min(abs_x1, abs_x2);
		final int ry = Math.min(abs_y1, abs_y2);
		final int rw = Math.abs(abs_x1-abs_x2);
		final int rh = Math.abs(abs_y1-abs_y2);
		return new PositionAbsolute(rx, ry, rw, rh);
	}

}
