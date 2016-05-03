package com.kamesuta.mc.worldpictures.experimental;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.guiwidget.GuiComponent;
import com.kamesuta.mc.guiwidget.GuiPosition;
import com.kamesuta.mc.guiwidget.GuiTools;
import com.kamesuta.mc.guiwidget.position.IPositionAbsolute;
import com.kamesuta.mc.guiwidget.position.Point;
import com.kamesuta.mc.guiwidget.position.RelativeSizedPosition;

public class GuiComponent1 extends GuiComponent {
//	private int mode = -1;
//	private Animation ani = new Animation(3);
	public RelativeSizedPosition position;
//	private double x;

	public GuiComponent1(final RelativeSizedPosition position) {
		this.position = position;
	}

	@Override
	public void init(final GuiTools tools, final GuiPosition pgp) {
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final Point p, final int button) {
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final Point p) {
		final GuiPosition gp = pgp.child(this.position);
		position.x = p.x;
		position.y = p.y;
		final IPositionAbsolute pos = gp.getAbsolute();


//		if (pos.pointInside(p)) {
//			if (mode != 0) {ani = new Animation(3); mode = 0;}
//			x = ani.addElapsedByTime().easingBetween(Easings.easeInOutQuad, 0, 1);
//		} else {
//			if (mode != 1) {ani = new Animation(3); mode = 1;}
//			x = ani.addElapsedByTime().easingBetween(Easings.easeInOutQuad, 1, 0);
//		}
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
		final GuiPosition gp = pgp.child(this.position);
		tools.drawDebug(gp);
		final IPositionAbsolute pos = gp.getAbsolute();

		GL11.glColor4d(0, 1, 0, 1);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(pos.x1(), pos.y1(), 0);
		GL11.glVertex3f(pos.x1(), pos.y2(), 0);
		GL11.glVertex3f(pos.x2(), pos.y2(), 0);
		GL11.glVertex3f(pos.x2(), pos.y1(), 0);
		GL11.glEnd();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
