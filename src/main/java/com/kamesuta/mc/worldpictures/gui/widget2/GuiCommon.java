package com.kamesuta.mc.worldpictures.gui.widget2;

public interface GuiCommon {

	void draw(GuiGraphics g, int mousex, int mousey, float frame);

	void update(GuiEvent e);

	void keyTyped(GuiEvent e, char c, int keycode);

	void mouseScrolled(GuiEvent e, int x, int y, int scroll);

	void mouseDragged(GuiEvent e, int x, int y, int button, long time);

	void mouseMovedOrUp(GuiEvent e, int x, int y, int button);

	void mouseClicked(GuiEvent e, int x, int y, int button);

}
