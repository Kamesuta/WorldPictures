package com.kamesuta.mc.worldpictures.gui.widget2.position;

public interface IPositionAbsolute extends IPosition {

	int x();

	int y();

	int w();

	int h();

	boolean pointInside(final int px, final int py);

}
