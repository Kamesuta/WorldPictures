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
	public void init(final GuiTools tools) {
		initWidgets();
	}

	@Override
	public void draw(final GuiTools tools, final int mousex, final int mousey, final float frame) {
		super.draw(tools, mousex, mousey, frame);
		for (final GuiCommon widget : this.widgets)
			widget.draw(tools, mousex, mousey, frame);
	}

	@Override
	public void update(final GuiTools tools, final int mousex, final int mousey) {
		super.update(tools, mousex, mousey);
		for (final GuiCommon widget : this.widgets)
			widget.update(tools, mousex, mousey);
	}

	@Override
	public void keyTyped(final GuiTools tools, final char c, final int keycode) {
		super.keyTyped(tools, c, keycode);
		for (final GuiCommon widget : this.widgets)
			widget.keyTyped(tools, c, keycode);
	}

	@Override
	public void mouseScrolled(final GuiTools tools, final int x, final int y, final int scroll) {
		super.mouseScrolled(tools, x, y, scroll);
		for (final GuiCommon widget : this.widgets)
			widget.mouseScrolled(tools, x, y, scroll);
	}

	@Override
	public void mouseDragged(final GuiTools tools, final int x, final int y, final int button, final long time) {
		super.mouseDragged(tools, x, y, button, time);
		for (final GuiCommon widget : this.widgets)
			widget.mouseDragged(tools, x, y, button, time);
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final int x, final int y, final int button) {
		super.mouseMovedOrUp(tools, x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseMovedOrUp(tools, x, y, button);
	}

	@Override
	public void mouseClicked(final GuiTools tools, final int x, final int y, final int button) {
		super.mouseClicked(tools, x, y, button);
		for (final GuiCommon widget : this.widgets)
			widget.mouseClicked(tools, x, y, button);
	}

	protected void initWidgets() {
	}
}
