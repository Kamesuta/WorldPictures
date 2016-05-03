package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.guiwidget.GuiFrame;
import com.kamesuta.mc.guiwidget.GuiPanel;
import com.kamesuta.mc.guiwidget.position.RelativePosition;
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
		super.drawScreen(par1, par2, par3);
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
		final GuiPanel p = new GuiPanel(new RelativePosition(0, 0, -1, -1));
		p.add(new GuiDirect(new RelativePosition(-1, 50, -5, -51)));
		add(p);
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		super.setWorldAndResolution(mc, i, j);
	}
}
