package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.worldpictures.gui.widget2.GuiFrame;
import com.kamesuta.mc.worldpictures.gui.widget2.GuiPanel;
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
	public void onGuiClosed() {

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void initWidgets() {
		final GuiPanel p = new GuiPanel(5, 5, 100, 100);
		p.add(new GuiDirect(0, 0, 10, 10));
		p.add(new GuiDirect(0, this.height, 10, 10));
		p.add(new GuiDirect(this.width, this.height, 10, 10));
		p.add(new GuiDirect(this.width, 0, 10, 10));
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
