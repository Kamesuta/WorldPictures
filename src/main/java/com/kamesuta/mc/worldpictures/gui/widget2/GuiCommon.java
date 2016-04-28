package com.kamesuta.mc.worldpictures.gui.widget2;

public interface GuiCommon {

	void init(GuiGraphics g, GuiEvent e);

	void draw(GuiGraphics g, int mousex, int mousey, float frame);

	void update(GuiGraphics g, GuiEvent e, int mousex, int mousey);

	void keyTyped(GuiGraphics g, GuiEvent e, char c, int keycode);

	void mouseScrolled(GuiGraphics g, GuiEvent e, int x, int y, int scroll);

	void mouseDragged(GuiGraphics g, GuiEvent e, int x, int y, int button, long time);

	void mouseMovedOrUp(GuiGraphics g, GuiEvent e, int x, int y, int button);

	void mouseClicked(GuiGraphics g, GuiEvent e, int x, int y, int button);

}
