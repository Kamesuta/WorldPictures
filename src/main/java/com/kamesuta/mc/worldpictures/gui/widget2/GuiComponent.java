package com.kamesuta.mc.worldpictures.gui.widget2;

import net.minecraft.client.gui.Gui;

public class GuiComponent extends Gui implements GuiCommon {
	public int x;
	public int y;
	public int width;
	public int height;

	public GuiComponent(final int x, final int y, final int width, final int height) {
		setSize(x, y, width, height);
	}

	public void setSize(final int x, final int y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Deprecated
	public final boolean pointInside(final int px, final int py) {
		return px >= this.x && px < this.x + this.width && py >= this.y && py < this.y + this.height;
	}

	@Override
	public void init(final GuiTools tools) {
	}

	@Override
	public void draw(final GuiTools tools, final int mousex, final int mousey, final float frame) {
	}

	@Override
	public void update(final GuiTools tools, final int mousex, final int mousey) {
	}

	@Override
	public void keyTyped(final GuiTools tools, final char c, final int keycode) {
	}

	@Override
	public void mouseScrolled(final GuiTools tools, final int x, final int y, final int scroll) {
	}

	@Override
	public void mouseDragged(final GuiTools tools, final int x, final int y, final int button, final long time) {
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final int x, final int y, final int button) {
	}

	@Override
	public void mouseClicked(final GuiTools tools, final int x, final int y, final int button) {
	}
}
