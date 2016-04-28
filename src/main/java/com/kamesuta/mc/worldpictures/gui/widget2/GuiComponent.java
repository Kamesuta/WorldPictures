package com.kamesuta.mc.worldpictures.gui.widget2;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public class GuiComponent extends Gui implements GuiCommon {
	protected static final ResourceLocation guiTex = new ResourceLocation("textures/gui/widgets.png");

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

	public final boolean pointInside(final int px, final int py) {
		return px >= this.x && px < this.x + this.width && py >= this.y && py < this.y + this.height;
	}

	@Override
	public void mouseClicked(final GuiEvent event, final int x, final int y, final int button) {
	}

	@Override
	public void mouseMovedOrUp(final GuiEvent event, final int x, final int y, final int button) {
	}

	@Override
	public void mouseDragged(final GuiEvent event, final int x, final int y, final int button, final long time) {
	}

	@Override
	public void update(final GuiEvent event) {
	}

	@Override
	public void draw(final GuiGraphics g, final int mousex, final int mousey, final float frame) {
	}

	@Override
	public void keyTyped(final GuiEvent event, final char c, final int keycode) {
	}

	@Override
	public void mouseScrolled(final GuiEvent event, final int x, final int y, final int scroll) {
	}
}
