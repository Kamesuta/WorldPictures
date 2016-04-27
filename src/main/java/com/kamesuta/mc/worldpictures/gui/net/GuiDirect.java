package com.kamesuta.mc.worldpictures.gui.net;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.gui.widget.GuiWidget;

public class GuiDirect extends GuiWidget {

	public GuiDirect(final int x, final int y, final int width, final int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(final int mousex, final int mousey, final float frame) {
		GL11.glColor4f(1, 1, 1, 1);
		this.renderEngine.bindTexture(guiTex);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(this.x-5, this.y-5, 0);
		GL11.glVertex3f(this.x-5, this.y+5, 0);
		GL11.glVertex3f(this.x+5, this.y+5, 0);
		GL11.glVertex3f(this.x+5, this.y-5, 0);
		GL11.glEnd();

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
