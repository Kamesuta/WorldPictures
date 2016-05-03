package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.guiwidget.GuiComponent;
import com.kamesuta.mc.guiwidget.GuiPosition;
import com.kamesuta.mc.guiwidget.GuiTools;
import com.kamesuta.mc.guiwidget.animation.Animation;
import com.kamesuta.mc.guiwidget.animation.Easings;
import com.kamesuta.mc.guiwidget.position.IPositionAbsolute;
import com.kamesuta.mc.guiwidget.position.Point;
import com.kamesuta.mc.guiwidget.position.RelativePosition;

public class GuiDirect extends GuiComponent {
	private int i = -1;
	private Animation anime;
	public RelativePosition position;

	public GuiDirect(final RelativePosition position) {
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

		final IPositionAbsolute pos = gp.getAbsolute();
		if (pos.pointInside(p)) {
			if (this.i != 0) {this.anime = new Animation(1); this.i = 0;}
			this.position.x2 = (int) this.anime.addElapsedByTime().easingBetween(Easings.easeOutExpo, -5, -100);
		} else {
			if (this.i != 1) {this.anime = new Animation(1); this.i = 1;}
			this.position.x2 = (int) this.anime.addElapsedByTime().easingBetween(Easings.easeOutExpo, -100, -5);
		}
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
		final GuiPosition gp = pgp.child(this.position);
		tools.drawDebug(gp);
		//		GL11.glColor4d(0.5, 1, 1, this.i);
		//
		//		//tools.g.renderEngine.bindTexture(GuiGraphics.guiTex);
		//		final IPositionAbsolute pos = gp.getAbsolute();
		//		GL11.glEnable(GL11.GL_BLEND);
		//		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//
		//		GL11.glBegin(GL11.GL_QUADS);
		//		GL11.glVertex3f(pos.x1(), pos.y1(), 0);
		//		GL11.glVertex3f(pos.x1(), pos.y2(), 0);
		//		GL11.glVertex3f(pos.x2(), pos.y2(), 0);
		//		GL11.glVertex3f(pos.x2(), pos.y1(), 0);
		//		GL11.glEnd();
		//
		//		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//		GL11.glDisable(GL11.GL_BLEND);
		//		final Tessellator t = Tessellator.instance;
		//		t.startDrawingQuads();
		//		t.addVertex(x, y, 0);
		//		t.addVertex(x, y+h, 0);
		//		t.addVertex(x+w, y+h, 0);
		//		t.addVertex(x+w, y, 0);
		//		t.draw();
		//		drawRect(x, y, w, h, 0x000000);
	}

}
