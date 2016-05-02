package com.kamesuta.mc.worldpictures.experimental;

import com.kamesuta.mc.guiwidget.GuiComponent;
import com.kamesuta.mc.guiwidget.GuiFrame;
import com.kamesuta.mc.guiwidget.GuiPanel;
import com.kamesuta.mc.guiwidget.GuiPosition;
import com.kamesuta.mc.guiwidget.GuiTools;
import com.kamesuta.mc.guiwidget.position.IPositionAbsolute;
import com.kamesuta.mc.guiwidget.position.Point;
import com.kamesuta.mc.guiwidget.position.RelativePosition;
import com.kamesuta.mc.guiwidget.position.RelativeSizedPosition;
import com.kamesuta.mc.worldpictures.net.NetManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiNet extends GuiFrame {
	@SuppressWarnings("unused")
	private final NetManager netmanager;

	public GuiNet(final NetManager netmanager) {
		super();
		this.netmanager = netmanager;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void actionPerformed(final GuiButton guiButton) {
		super.actionPerformed(guiButton);
	}

	@Override
	public void keyTyped(final char character, final int code) {
		super.keyTyped(character, code);
	}

	@Override
	public void drawScreen(final int par1, final int par2, final float par3) {
		// drawDefaultBackground();

		// drawString(this.fontRendererObj, this.strTitle, this.width - 205, this.height - 45, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}

	public void drawForeground(final GuiTools t, final IPositionAbsolute absolute, final GuiPosition gp, final Point p) {
		final GuiPosition gp1 = gp.child(new RelativePosition(-50, -10, -1, -1));
		final IPositionAbsolute pos = gp1.getAbsolute(absolute);
		t.g.drawString(p.x + ":" + p.y, pos.x1(), pos.y1(), 0xffffff);
	}

	@Override
	public void onGuiClosed() {

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void initWidgets() {
		final GuiPanel p = new GuiPanel(new RelativePosition(5, 5, -6, -6));
		p.add(new GuiDirect(new RelativePosition(5, 5, 10, 10)));
		p.add(new GuiDirect(new RelativePosition(5, -5, 10, -10)));
		p.add(new GuiDirect(new RelativePosition(-5, -5, -10, -10)));
		p.add(new GuiDirect(new RelativePosition(-5, 5, -10, 10)));
		p.add(new GuiComponent() {
			@Override
			public void draw(final GuiTools tools, final GuiPosition pgp, final Point p, final float frame) {
				final String str = p.x + ":" + p.y;
				final int w = tools.g.fontRenderer.getStringWidth(str);
				final int h = tools.g.fontRenderer.FONT_HEIGHT;
				final GuiPosition gp = pgp.child(new RelativeSizedPosition(-10, -10, w, h));
				tools.drawDebug(gp);
				final IPositionAbsolute pos = gp.getAbsolute();
				//				GL11.glPushMatrix();
				//				GL11.glTranslatef(pos.x1(), pos.y1(), 0);
				//				GL11.glScalef(0.5f, 0.5f, 1f);
				//				GL11.glRotated(90, 0, 0, 1);
				//				GL11.glTranslatef(-pos.x1(), -pos.y1(), 0);
				tools.g.drawStringR(str, pos.x2(), pos.y1(), 0xffffff);
				//				GL11.glPopMatrix();
			}
		});
		add(p);
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		super.setWorldAndResolution(mc, i, j);
	}
}
