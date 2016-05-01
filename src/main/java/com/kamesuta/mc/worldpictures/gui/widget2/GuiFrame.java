package com.kamesuta.mc.worldpictures.gui.widget2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;
import com.kamesuta.mc.worldpictures.gui.widget2.position.RelativePosition;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiFrame extends GuiScreen implements GuiContainer {
	private final ArrayList<GuiCommon> widgets = new ArrayList<GuiCommon>();
	private final IPositionRelative position = new RelativePosition(3, 3, -10, -10);
	private final GuiPosition gp = new GuiPosition(null, this.position);

	protected final GuiTools tools;

	public GuiFrame(final GuiTools tools) {
		this.tools = tools;
	}

	public GuiFrame() {
		this(new GuiTools());
	}

	@Override
	public void initGui() {
		super.initGui();
		for (final GuiCommon widget : this.widgets)
			widget.init(this.tools, this.gp);
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
			widget.draw(this.tools, this.gp, mousex, mousey, f);
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
			widget.mouseClicked(this.tools, this.gp, x, y, button);
	}

	@Override
	protected void mouseMovedOrUp(final int x, final int y, final int button) {
		super.mouseMovedOrUp(x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseMovedOrUp(this.tools, this.gp, x, y, button);
	}

	@Override
	protected void mouseClickMove(final int x, final int y, final int button, final long time) {
		super.mouseClickMove(x, y, button, time);
		for (final GuiCommon widget : this.widgets)
			widget.mouseDragged(this.tools, this.gp, x, y, button, time);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (this.mc.currentScreen == this) {
			final Point p = this.tools.getAbsoluteMousePosition();
			for (final GuiCommon widget : this.widgets) {
				widget.update(this.tools, this.gp, p.x, p.y);
			}
		}
	}

	@Override
	public void keyTyped(final char c, final int keycode) {
		super.keyTyped(c, keycode);
		for (final GuiCommon widget : this.widgets)
			widget.keyTyped(this.tools, this.gp, c, keycode);
	}

	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		final int i = Mouse.getEventDWheel();
		if (i != 0) {
			final Point p = this.tools.getAbsoluteMousePosition();
			final int scroll = i > 0 ? 1 : -1;
			for (final GuiCommon widget : this.widgets)
				widget.mouseScrolled(this.tools, this.gp, p.x, p.y, scroll);
		}
	}

	protected void onResized() {

	}

	protected void initWidgets() {
	}
}
