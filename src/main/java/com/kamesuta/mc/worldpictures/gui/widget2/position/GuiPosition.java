package com.kamesuta.mc.worldpictures.gui.widget2.position;

import javax.annotation.Nullable;

public class GuiPosition {
	public final GuiPosition parent;
	public final IPositionRelative position;

	public GuiPosition(@Nullable final GuiPosition parent, final IPositionRelative position) {
		this.parent = parent;
		this.position = position;
	}

	public GuiPosition child(final IPositionRelative position) {
		return new GuiPosition(this, position);
	}

	public IPositionAbsolute getAbsolute(final IPositionAbsolute absolute) {
		return getAbsolute(absolute, this);
	}

	private IPositionAbsolute getAbsolute(final IPositionAbsolute absolute, @Nullable final GuiPosition parent) {
		if (parent != null) {
			return this.position.getAbsolute(parent.getAbsolute(absolute, parent.parent));
		} else
			return this.position.getAbsolute(absolute);
	}
}
