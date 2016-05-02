package com.kamesuta.mc.worldpictures.gui.widget2;

import com.kamesuta.mc.worldpictures.gui.widget2.position.Point;

public interface GuiCommon {

	void init(GuiTools tools, GuiPosition pgp);

	void draw(GuiTools tools, GuiPosition pgp, Point mouse, float frame);

	void update(GuiTools tools, GuiPosition pgp, Point mouse);

	void keyTyped(GuiTools tools, GuiPosition pgp, Point mouse, char c, int keycode);

	void mouseScrolled(GuiTools tools, GuiPosition pgp, Point mouse, int scroll);

	void mouseDragged(GuiTools tools, GuiPosition pgp, Point mouse, int button, long time);

	void mouseMovedOrUp(GuiTools tools, GuiPosition pgp, Point mouse, int button);

	void mouseClicked(GuiTools tools, GuiPosition pgp, Point mouse, int button);

}
