package com.kamesuta.mc.worldpictures.experimental;

import com.kamesuta.mc.guiwidget.GuiFrame;
import com.kamesuta.mc.guiwidget.GuiPanel;
import com.kamesuta.mc.guiwidget.GuiPosition;
import com.kamesuta.mc.guiwidget.GuiTools;
import com.kamesuta.mc.guiwidget.position.Point;
import com.kamesuta.mc.guiwidget.position.RelativePosition;
import com.kamesuta.mc.guiwidget.position.RelativeSizedPosition;

public class GuiFrame1 extends GuiFrame {
	public GuiFrame1() {
	}

	@Override
	protected void initWidgets() {
		GuiPanel p = new GuiPanel(new RelativePosition(5, 5, -6, -6)) {
			@Override
			public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
				super.draw(tools, pgp, p, frame);
				GuiPosition gp = pgp.child(this.position);
				tools.drawDebug(gp);
			}
		};
		p.add(new GuiComponent1(new RelativeSizedPosition(-10, -10, 100, 100)));
		this.add(p);
	}
}
