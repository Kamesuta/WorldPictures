package com.kamesuta.mc.worldpictures.gui.widget2.position;

import com.kamesuta.mc.worldpictures.lib.MathHelper;

public class PercentagePosition implements IPositionRelative {
	public final float per_x;
	public final float per_y;
	public final float per_w;
	public final float per_h;

	public PercentagePosition(final float per_x, final float per_y, final float per_w, final float per_h) {
		this.per_x = per_x;
		this.per_y = per_y;
		this.per_w = per_w;
		this.per_h = per_h;
	}

	@Override
	public boolean isVaild() {
		return this.per_w > 0 && this.per_h > 0;
	}

	@Override
	public IPositionAbsolute getAbsolute(final IPositionAbsolute parent) {
		final int pw = parent.getAbsoluteW();
		final int ph = parent.getAbsoluteH();
		final int px = parent.getAbsoluteX();
		final int py = parent.getAbsoluteY();
		final int x = (int) (px * this.per_x);
		final int y = (int) (py * this.per_y);
		final int w = (int) (pw * this.per_w);
		final int h = (int) (ph * this.per_h);
		final int wid = (int) MathHelper.clip(pw - x, 0, w);
		final int hei = (int) MathHelper.clip(ph - y, 0, h);
		return new PositionAbsolute(
				px + x,
				py + y,
				wid,
				hei);
	}
}
