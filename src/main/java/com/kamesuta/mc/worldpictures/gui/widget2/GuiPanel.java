package com.kamesuta.mc.worldpictures.gui.widget2;

import java.util.ArrayList;
import java.util.List;

public class GuiPanel extends GuiComponent implements GuiContainer {
	private final ArrayList<GuiCommon> widgets = new ArrayList<GuiCommon>();

	public GuiPanel(final int x, final int y, final int width, final int height) {
		super(x, y, width, height);
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
	public void init(final GuiGraphics g, final GuiEvent event) {
		initWidgets();
	}

	@Override
	public void draw(final GuiGraphics g, final int mousex, final int mousey, final float frame) {
		super.draw(g, mousex, mousey, frame);
		for (final GuiCommon widget : this.widgets)
			widget.draw(g, mousex, mousey, frame);
	}

	@Override
	public void update(final GuiGraphics g, final GuiEvent event, final int mousex, final int mousey) {
		super.update(g, event, mousex, mousey);
		for (final GuiCommon widget : this.widgets)
			widget.update(g, event, mousex, mousey);
	}

	@Override
	public void keyTyped(final GuiGraphics g, final GuiEvent event, final char c, final int keycode) {
		super.keyTyped(g, event, c, keycode);
		for (final GuiCommon widget : this.widgets)
			widget.keyTyped(g, event, c, keycode);
	}

	@Override
	public void mouseScrolled(final GuiGraphics g, final GuiEvent event, final int x, final int y, final int scroll) {
		super.mouseScrolled(g, event, x, y, scroll);
		for (final GuiCommon widget : this.widgets)
			widget.mouseScrolled(g, event, x, y, scroll);
	}

	@Override
	public void mouseDragged(final GuiGraphics g, final GuiEvent event, final int x, final int y, final int button, final long time) {
		super.mouseDragged(g, event, x, y, button, time);
		for (final GuiCommon widget : this.widgets)
			widget.mouseDragged(g, event, x, y, button, time);
	}

	@Override
	public void mouseMovedOrUp(final GuiGraphics g, final GuiEvent event, final int x, final int y, final int button) {
		super.mouseMovedOrUp(g, event, x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseMovedOrUp(g, event, x, y, button);
	}

	@Override
	public void mouseClicked(final GuiGraphics g, final GuiEvent event, final int x, final int y, final int button) {
		super.mouseClicked(g, event, x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseClicked(g, event, x, y, button);
	}

	protected void initWidgets() {
	}
}
