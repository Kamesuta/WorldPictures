package com.kamesuta.mc.worldpictures.gui.widget2.position;

import com.kamesuta.mc.worldpictures.lib.MathHelper;

public class FlexiblePosition extends PercentagePosition {

	public FlexiblePosition(final float per_x, final float per_y, final float per_w, final float per_h) {
		super(per_x, per_y, per_w, per_h);
	}

	@Override
	public IPositionAbsolute getAbsolute(final IPositionAbsolute parent) {
		final int pw = parent.getAbsoluteW();
		final int ph = parent.getAbsoluteH();

		final double clipped_per_w = MathHelper.clip(this.per_w, 0, 1);
		final double clipped_per_h = MathHelper.clip(this.per_h, 0, 1);

		final int w = (int) (pw * clipped_per_w);
		final int max_x = pw - w;
		final int x = (int) (max_x * MathHelper.clip(this.per_x, 0, 1));

		final int h = (int) (ph * clipped_per_h);
		final int max_y = ph - h;
		final int y = (int) (max_y * MathHelper.clip(this.per_y, 0, 1));

		return new PositionAbsolute(
				parent.getAbsoluteX() + x,
				parent.getAbsoluteY() + y,
				w,
				h);
	}

}
