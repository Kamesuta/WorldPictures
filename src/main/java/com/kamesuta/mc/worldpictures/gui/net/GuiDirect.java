package com.kamesuta.mc.worldpictures.gui.net;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.gui.widget2.GuiComponent;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPosition;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiTools;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;
import com.kamesuta.mc.worldpictures.gui.widget2.position.Point;
import com.kamesuta.mc.worldpictures.lib.MathHelper;

public class GuiDirect extends GuiComponent {
	private double i;
	public IPositionRelative position;

	public GuiDirect(final IPositionRelative position) {
		this.position = position;
	}

	@Override
	public void init(final GuiTools tools, final GuiPosition pgp) {
		this.i = 0;
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final Point p, final int button) {
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final Point p) {
		final GuiPosition gp = pgp.child(this.position);

		final IPositionAbsolute pos = gp.getAbsolute();
		if (pos.pointInside(p))
			this.i = MathHelper.clip(this.i+0.05, 0, 1);
		else
			this.i = MathHelper.clip(this.i-0.05, 0, 1);
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
		final GuiPosition gp = pgp.child(this.position);
		tools.drawDebug(gp);
		GL11.glColor4d(0.5, 1, 1, this.i);

		//tools.g.renderEngine.bindTexture(GuiGraphics.guiTex);
		final IPositionAbsolute pos = gp.getAbsolute();
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
