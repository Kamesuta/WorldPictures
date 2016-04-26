package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.worldpictures.net.NetManager;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiEntityWorldPictures extends GuiScreen {
	private final NetManager netmanager;

	public GuiEntityWorldPictures(final NetManager netmanager) {
		this.netmanager = netmanager;
	}

	@Override
	public void initGui() {
	}

	@Override
	protected void actionPerformed(final GuiButton guiButton) {
	}

	@Override
	protected void keyTyped(final char character, final int code) {
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
}
