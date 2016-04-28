package com.kamesuta.mc.worldpictures.gui.widget2;

import java.util.List;

public interface GuiContainer {

	boolean remove(final GuiComponent widget);

	boolean add(final GuiComponent widget);

	List<GuiCommon> getContainer();

}
