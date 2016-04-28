package com.kamesuta.mc.worldpictures.gui.widget2;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiFrame extends GuiScreen {
	public ArrayList<GuiComponent> widgets = new ArrayList<GuiComponent>();

	protected final GuiGraphics graphics;
	protected final GuiEvent event = new GuiEvent();

	public GuiFrame(final GuiGraphics g) {
		this.graphics = g;
	}

	public GuiFrame() {
		this(new GuiGraphics(Minecraft.getMinecraft()));
	}

	@Override
	public void initGui() {
		if (!this.widgets.isEmpty())
			resize();
	}

	public void reset() {
		this.widgets.clear();
		initGui();
		addWidgets();
		resize();
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		final boolean init = this.mc == null;
		super.setWorldAndResolution(mc, i, j);
		if (init) {
			addWidgets();
			resize();
		}
	}

	public void add(final GuiComponent widget) {
		this.widgets.add(widget);
	}

	@Override
	public void drawScreen(final int mousex, final int mousey, final float f) {
		drawBackground();
		for (final GuiCommon widget : this.widgets)
			widget.draw(this.graphics, mousex, mousey, f);
		drawForeground();
	}

	public void drawBackground() {
	}

	public void drawForeground() {
	}

	@Override
	protected void mouseClicked(final int x, final int y, final int button) {
		super.mouseClicked(x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseClicked(this.event, x, y, button);
	}

	@Override
	protected void mouseMovedOrUp(final int x, final int y, final int button) {
		super.mouseMovedOrUp(x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseMovedOrUp(this.event, x, y, button);
	}

	@Override
	protected void mouseClickMove(final int x, final int y, final int button, final long time) {
		super.mouseClickMove(x, y, button, time);
		for (final GuiCommon widget : this.widgets)
			widget.mouseDragged(this.event, x, y, button, time);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (this.mc.currentScreen == this)
			for (final GuiCommon widget : this.widgets)
				widget.update(this.event);
	}

	@Override
	public void keyTyped(final char c, final int keycode) {
		super.keyTyped(c, keycode);
		for (final GuiCommon widget : this.widgets)
			widget.keyTyped(this.event, c, keycode);
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		final int i = Mouse.getEventDWheel();
		if (i != 0) {
			final Point p = getMousePosition();
			final int scroll = i > 0 ? 1 : -1;
			for (final GuiCommon widget : this.widgets)
				widget.mouseScrolled(this.event, p.x, p.y, scroll);
		}
	}

	public void resize() {
	}

	public void addWidgets() {
	}

	public Point getMousePosition() {
		final int x = Mouse.getEventX() * this.width / this.mc.displayWidth;
		final int y = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		return new Point(x, y);
	}
}
