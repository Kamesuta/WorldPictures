package com.kamesuta.mc.worldpictures.gui.widget2.position;

public interface IPositionAbsolute extends IPosition {

	int getAbsoluteX();

	int getAbsoluteY();

	int getAbsoluteW();

	int getAbsoluteH();

	boolean pointInside(final int px, final int py);

}
