package com.kamesuta.mc.worldpictures.gui.widget2;

public interface GuiCommon {

	void init(GuiTools tools, GuiPosition pgp);

	void draw(GuiTools tools, GuiPosition pgp, int mousex, int mousey, float frame);

	void update(GuiTools tools, GuiPosition pgp, int mousex, int mousey);

	void keyTyped(GuiTools tools, GuiPosition pgp, char c, int keycode);

	void mouseScrolled(GuiTools tools, GuiPosition pgp, int mousex, int mousey, int scroll);

	void mouseDragged(GuiTools tools, GuiPosition pgp, int mousex, int mousey, int button, long time);

	void mouseMovedOrUp(GuiTools tools, GuiPosition pgp, int mousex, int mousey, int button);

	void mouseClicked(GuiTools tools, GuiPosition pgp, int mousex, int mousey, int button);

}
