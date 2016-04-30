package com.kamesuta.mc.worldpictures.gui.widget2;

import java.util.ArrayList;
import java.util.List;

import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;

public class GuiPanel extends GuiComponent implements GuiContainer {
	private final ArrayList<GuiCommon> widgets = new ArrayList<GuiCommon>();

	public GuiPanel(final IPositionRelative position) {
		super(position);
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
	public void init(final GuiTools tools, final GuiPosition pgp) {
		initWidgets();
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey, final float frame) {
		super.draw(tools, pgp, mousex, mousey, frame);
		final GuiPosition gp = pgp.child(this.position);
		for (final GuiCommon widget : this.widgets)
			widget.draw(tools, gp, mousex, mousey, frame);
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey) {
		super.update(tools, pgp, mousex, mousey);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.update(tools, gp, mousex, mousey);
	}

	@Override
	public void keyTyped(final GuiTools tools, final GuiPosition pgp, final char c, final int keycode) {
		super.keyTyped(tools, pgp, c, keycode);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.keyTyped(tools, gp, c, keycode);
	}

	@Override
	public void mouseScrolled(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int scroll) {
		super.mouseScrolled(tools, pgp, x, y, scroll);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseScrolled(tools, gp, x, y, scroll);
	}

	@Override
	public void mouseDragged(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button, final long time) {
		super.mouseDragged(tools, pgp, x, y, button, time);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseDragged(tools, gp, x, y, button, time);
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button) {
		super.mouseMovedOrUp(tools, pgp, x, y, button);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseMovedOrUp(tools, gp, x, y, button);
	}

	@Override
	public void mouseClicked(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button) {
		super.mouseClicked(tools, pgp, x, y, button);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseClicked(tools, gp, x, y, button);
	}

	protected void initWidgets() {
	}
}
