package com.kamesuta.mc.worldpictures.gui.widget2;

import java.util.ArrayList;
import java.util.List;

import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;
import com.kamesuta.mc.worldpictures.gui.widget2.position.Point;

public class GuiPanel extends GuiComponent implements GuiContainer {
	private final ArrayList<GuiCommon> widgets = new ArrayList<GuiCommon>();
	protected IPositionRelative position;

	public GuiPanel(final IPositionRelative position) {
		this.position = position;
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
	public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
		super.draw(tools, pgp, p, frame);
		final GuiPosition gp = pgp.child(this.position);
		tools.drawDebug(gp);
		for (final GuiCommon widget : this.widgets)
			widget.draw(tools, gp, p, frame);
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final Point p) {
		super.update(tools, pgp, p);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.update(tools, gp, p);
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
	public void mouseScrolled(final GuiTools tools, final GuiPosition pgp, final Point p, final int scroll) {
		super.mouseScrolled(tools, pgp, p, scroll);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseScrolled(tools, gp, p, scroll);
	}

	@Override
	public void mouseDragged(final GuiTools tools, final GuiPosition pgp, final Point p, final int button, final long time) {
		super.mouseDragged(tools, pgp, p, button, time);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseDragged(tools, gp, p, button, time);
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final Point p, final int button) {
		super.mouseMovedOrUp(tools, pgp, p, button);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseMovedOrUp(tools, gp, p, button);
	}

	@Override
	public void mouseClicked(final GuiTools tools, final GuiPosition pgp, final Point p, final int button) {
		super.mouseClicked(tools, pgp, p, button);
		final GuiPosition gp = pgp.child(this.position);
		if (gp.position.isVaild())
			for (final GuiCommon widget : this.widgets)
				widget.mouseClicked(tools, gp, p, button);
	}

	protected void initWidgets() {
	}
}
