package com.kamesuta.mc.worldpictures.gui.widget2.position;

public interface IPositionRelative extends IPosition {

	IPositionAbsolute getAbsolute(IPositionAbsolute parent);

}