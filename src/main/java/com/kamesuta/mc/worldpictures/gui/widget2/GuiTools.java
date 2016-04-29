package com.kamesuta.mc.worldpictures.gui.widget2;

import net.minecraft.client.Minecraft;

public class GuiTools {
	public final Minecraft mc;

	public final GuiGraphics g;
	public final GuiEvent event;
	public final GuiState state;

	public GuiTools(final GuiGraphics g, final GuiEvent event, final GuiState state) {
		this.mc = Minecraft.getMinecraft();
		this.g = g;
		this.event = event;
		this.state = state;
	}

	public GuiTools() {
		this(new GuiGraphics(), new GuiEvent(), new GuiState());
	}

}
