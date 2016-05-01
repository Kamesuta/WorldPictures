package com.kamesuta.mc.worldpictures.gui.widget2;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;

import net.minecraft.client.gui.Gui;

public class GuiComponent extends Gui implements GuiCommon {
	protected IPositionRelative position;

	public GuiComponent(final IPositionRelative position) {
		setPosition(position);
	}

	public void setPosition(final IPositionRelative position) {
		this.position = position;
	}

	@Override
	public void init(final GuiTools tools, final GuiPosition pgp) {
	}

	@Override
	public void draw(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey, final float frame) {
		if (tools.isDebug) {
			final IPositionAbsolute p = tools.getAbsolute(pgp);
			GL11.glColor4f(1, 1, 1, 0.2f);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex3f(p.x(), p.y(), 1);
			GL11.glVertex3f(p.x(), p.y()+p.h(), 1);
			GL11.glVertex3f(p.x()+p.w(), p.y()+p.h(), 1);
			GL11.glVertex3f(p.x()+p.w(), p.y(), 1);
			GL11.glEnd();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

	@Override
	public void update(final GuiTools tools, final GuiPosition pgp, final int mousex, final int mousey) {
	}

	@Override
	public void keyTyped(final GuiTools tools, final GuiPosition pgp, final char c, final int keycode) {
	}

	@Override
	public void mouseScrolled(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int scroll) {
	}

	@Override
	public void mouseDragged(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button, final long time) {
	}

	@Override
	public void mouseMovedOrUp(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button) {
	}

	@Override
	public void mouseClicked(final GuiTools tools, final GuiPosition pgp, final int x, final int y, final int button) {
	}
}
