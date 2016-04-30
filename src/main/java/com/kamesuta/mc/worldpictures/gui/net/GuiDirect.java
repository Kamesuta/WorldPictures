package com.kamesuta.mc.worldpictures.gui.net;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.gui.widget2.GuiComponent;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPosition;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiTools;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;

import net.minecraft.client.renderer.Tessellator;

public class GuiDirect extends GuiComponent {
	private int i;
	public GuiDirect(final IPositionRelative position) {
		super(position);
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
		//super.draw(tools, pgp, mousex, mousey, frame);
		GL11.glColor4f(1, 1, 1, 1);
		//tools.g.renderEngine.bindTexture(GuiGraphics.guiTex);
		final IPositionAbsolute p = tools.getAbsolute(pgp);
		final int x = p.x();
		final int y = p.y();
		final int w = p.w();
		final int h = p.h();
		//		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//		GL11.glDisable(GL11.GL_TEXTURE);
		//		GL11.glBegin(GL11.GL_QUADS);
		//		GL11.glVertex3f(x, y, 0);
		//		GL11.glVertex3f(x, y+h, 0);
		//		GL11.glVertex3f(x+w, y+h, 0);
		//		GL11.glVertex3f(x+w, y, 0);
		//		GL11.glEnd();
		final Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		t.addVertex(x, y, 0);
		t.addVertex(x, y+h, 0);
		t.addVertex(x+w, y+h, 0);
		t.addVertex(x+w, y, 0);
		t.draw();
		//		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//		GL11.glEnable(GL11.GL_LIGHTING);
		drawRect(x, y, w, h, 1);

		//		final int state = 0;
		//		drawTexturedModalRect(this.x, this.y, 0, 46 + state * 20, this.width / 2, this.height / 2);// top
		//		// left
		//		drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + state * 20, this.width / 2, this.height / 2);// top
		//		// right
		//		drawTexturedModalRect(this.x, this.y + this.height / 2, 0, 46 + state * 20 + 20 - this.height / 2, this.width / 2, this.height / 2);// bottom
		//		// left
		//		drawTexturedModalRect(this.x + this.width / 2, this.y + this.height / 2, 200 - this.width / 2, 46 + state * 20 + 20 - this.height / 2,
		//				this.width / 2, this.height / 2);// bottom right

	}

}
