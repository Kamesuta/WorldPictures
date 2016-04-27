package com.kamesuta.mc.worldpictures.gui.net;

import com.kamesuta.mc.worldpictures.gui.widget.GuiScreenWidget;
import com.kamesuta.mc.worldpictures.net.NetManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiNet extends GuiScreenWidget {
	private final NetManager netmanager;

	public GuiNet(final NetManager netmanager) {
		super();
		this.netmanager = netmanager;
	}

	@Override
	public void initGui() {
		this.xSize = this.width;
		this.ySize = this.height;
		this.guiTop = 0;
		this.guiLeft = 0;
		if (!this.widgets.isEmpty())
			resize();
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
	public void addWidgets() {
		add(new GuiDirect(0, 0, 10, 10));
		add(new GuiDirect(0, this.height, 10, 10));
		add(new GuiDirect(this.width, this.height, 10, 10));
		add(new GuiDirect(this.width, 0, 10, 10));
	}

	@Override
	public void setWorldAndResolution(final Minecraft mc, final int i, final int j) {
		super.setWorldAndResolution(mc, i, j);
	}

	@Override
	public void resize() {
		this.xSize = this.width;
		this.ySize = this.height;
	}
}
