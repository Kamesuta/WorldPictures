package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.worldpictures.gui.widget2.GuiFrame;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPanel;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPosition;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiTools;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.Point;
import com.kamesuta.mc.worldpictures.gui.widget2.position.RelativePosition;
import com.kamesuta.mc.worldpictures.net.NetManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiNet extends GuiFrame {
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

	@Override
	public void drawForeground(final GuiTools t, final GuiPosition gp, final Point p) {
		final GuiPosition gp1 = gp.child(new RelativePosition(-50, -10, -1, -1));
		final IPositionAbsolute pos = t.getAbsolute(gp1);
		t.g.drawString(p.x*2 + ":" + p.y*2, pos.x1(), pos.y1(), 0xffffff);
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
		final GuiPanel p = new GuiPanel(new RelativePosition(5, 5, 100, 100));
		p.add(new GuiDirect(new RelativePosition(5, 5, 10, 10)));
		p.add(new GuiDirect(new RelativePosition(5, -5, 10, -10)));
		p.add(new GuiDirect(new RelativePosition(-5, -5, -10, -10)));
		p.add(new GuiDirect(new RelativePosition(-5, 5, -10, 10)));
		add(p);
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		super.setWorldAndResolution(mc, i, j);
	}

	@Override
	protected void onResized() {
	}
}
