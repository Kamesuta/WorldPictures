package com.kamesuta.mc.worldpictures.gui.widget2;

public interface GuiCommon {

	void init(GuiTools tools);

	void draw(GuiTools tools, int mousex, int mousey, float frame);

	void update(GuiTools tools, int mousex, int mousey);

	void keyTyped(GuiTools tools, char c, int keycode);

	void mouseScrolled(GuiTools tools, int mousex, int mousey, int scroll);

	void mouseDragged(GuiTools tools, int mousex, int mousey, int button, long time);

	void mouseMovedOrUp(GuiTools tools, int mousex, int mousey, int button);

	void mouseClicked(GuiTools tools, int mousex, int mousey, int button);

}
