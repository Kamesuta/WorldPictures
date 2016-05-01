package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.worldpictures.gui.widget2.GuiComponent;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPosition;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiTools;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;

public class GuiDirect extends GuiComponent {
	private int i;
	public IPositionRelative position;

	public GuiDirect(final IPositionRelative position) {
		this.position = position;
	}

	@Override
	public void init(final GuiTools tools, final GuiPosition pgp) {
		this.i = 0;
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button) {
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey) {
		final IPositionAbsolute p = tools.getAbsolute(pgp);
		if (p.pointInside(mousex, mousey))
			this.i += 1;
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey, final float frame) {
		super.draw(tools, pgp, mousex, mousey, frame);
		tools.drawDebug(pgp.child(this.position));
		//		GL11.glColor4f(0.5f, 1, 1, 1);
		//tools.g.renderEngine.bindTexture(GuiGraphics.guiTex);
		//		final IPositionAbsolute p = tools.getAbsolute(pgp);
		//		final int x = p.x();
		//		final int y = p.y();
		//		final int w = p.w();
		//		final int h = p.h();
		//		GL11.glEnable(GL11.GL_BLEND);
		//		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//		GL11.glBegin(GL11.GL_LINE_LOOP);
		//		GL11.glVertex3f(x, y, 0);
		//		GL11.glVertex3f(x, y+h, 0);
		//		GL11.glVertex3f(x+w, y+h, 0);
		//		GL11.glVertex3f(x+w, y, 0);
		//		GL11.glEnd();
		//		final Tessellator t = Tessellator.instance;
		//		t.startDrawingQuads();
		//		t.addVertex(x, y, 0);
		//		t.addVertex(x, y+h, 0);
		//		t.addVertex(x+w, y+h, 0);
		//		t.addVertex(x+w, y, 0);
		//		t.draw();
		//		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//		GL11.glDisable(GL11.GL_BLEND);
		//		drawRect(x, y, w, h, 0x000000);
	}

}
