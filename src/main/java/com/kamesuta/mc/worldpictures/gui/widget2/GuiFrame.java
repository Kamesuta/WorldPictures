package com.kamesuta.mc.worldpictures.gui.widget2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiFrame extends GuiScreen implements GuiContainer {
	private final ArrayList<GuiCommon> widgets = new ArrayList<GuiCommon>();

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
		super.initGui();
		for (final GuiCommon widget : this.widgets)
			widget.init(this.graphics, this.event);
	}

	public void reset() {
		this.widgets.clear();
		initGui();
		initWidgets();
		onResized();
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		final boolean init = this.mc == null;
		super.setWorldAndResolution(mc, i, j);
		if (init) {
			initWidgets();
			onResized();
		}
	}

	@Override
	public List<GuiCommon> getContainer() {
		return this.widgets;
	}

	@Override
	public boolean add(final GuiComponent widget) {
		return this.widgets.add(widget);
	}

	@Override
	public boolean remove(final GuiComponent widget) {
		return this.widgets.remove(widget);
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
			widget.mouseClicked(this.graphics, this.event, x, y, button);
	}

	@Override
	protected void mouseMovedOrUp(final int x, final int y, final int button) {
		super.mouseMovedOrUp(x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseMovedOrUp(this.graphics, this.event, x, y, button);
	}

	@Override
	protected void mouseClickMove(final int x, final int y, final int button, final long time) {
		super.mouseClickMove(x, y, button, time);
		for (final GuiCommon widget : this.widgets)
			widget.mouseDragged(this.graphics, this.event, x, y, button, time);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (this.mc.currentScreen == this) {
			for (final GuiCommon widget : this.widgets) {
				final Point p = this.graphics.getScreenMousePosition(this);
				widget.update(this.graphics, this.event, p.x, p.y);
			}
		}
	}

	@Override
	public void keyTyped(final char c, final int keycode) {
		super.keyTyped(c, keycode);
		for (final GuiCommon widget : this.widgets)
			widget.keyTyped(this.graphics, this.event, c, keycode);
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		final int i = Mouse.getEventDWheel();
		if (i != 0) {
			final Point p = this.graphics.getScreenMousePosition(this);
			final int scroll = i > 0 ? 1 : -1;
			for (final GuiCommon widget : this.widgets)
				widget.mouseScrolled(this.graphics, this.event, p.x, p.y, scroll);
		}
	}

	protected void onResized() {
	}

	protected void initWidgets() {
	}
}
