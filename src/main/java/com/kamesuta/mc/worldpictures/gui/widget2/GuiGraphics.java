package com.kamesuta.mc.worldpictures.gui.widget2;

import java.awt.Dimension;
import java.awt.Point;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiGraphics extends Gui {
	public static final ResourceLocation guiTex = new ResourceLocation("textures/gui/widgets.png");

	private final Minecraft mc;
	public final TextureManager renderEngine;
	public final FontRenderer fontRenderer;

	public GuiGraphics() {
		this.mc = Minecraft.getMinecraft();
		this.renderEngine = this.mc.renderEngine;
		this.fontRenderer = this.mc.fontRenderer;
	}

	public void drawString(final String text, final int x, final int y, final int colour, final boolean shadow) {
		if (shadow)
			this.fontRenderer.drawStringWithShadow(text, x, y, colour);
		else
			this.fontRenderer.drawString(text, x, y, colour);
	}

	public void drawString(final String text, final int x, final int y, final int colour) {
		drawString(text, x, y, colour, true);
	}

	public void drawStringC(final String text, final int x, final int y, final int w, final int h, final int colour, final boolean shadow) {
		drawString(text, x + (w - getStringWidth(text)) / 2, y + (h - 8) / 2, colour, shadow);
	}

	public void drawStringC(final String text, final int x, final int y, final int w, final int h, final int colour) {
		drawStringC(text, x, y, w, h, colour, true);
	}

	public void drawStringC(final String text, final int x, final int y, final int colour, final boolean shadow) {
		drawString(text, x - getStringWidth(text) / 2, y, colour, shadow);
	}

	public void drawStringC(final String text, final int x, final int y, final int colour) {
		drawStringC(text, x, y, colour, true);
	}

	public void drawStringR(final String text, final int x, final int y, final int colour, final boolean shadow) {
		drawString(text, x - getStringWidth(text), y, colour, shadow);
	}

	public void drawStringR(final String text, final int x, final int y, final int colour) {
		drawStringR(text, x, y, colour, true);
	}

	public int getStringWidth(final String s) {
		if (StringUtils.isEmpty(s))
			return 0;
		return this.fontRenderer.getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes(s));
	}

	public Dimension displaySize() {
		return new Dimension(this.mc.displayWidth, this.mc.displayHeight);
	}

	public Dimension resolutionSize() {
		final ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		return new Dimension(res.getScaledWidth(), res.getScaledHeight());
	}

	public Point getMousePosition(final Dimension resolution, final int eventX, final int eventY) {
		final Dimension disp = displaySize();
		return new Point(eventX * resolution.width / disp.width, resolution.height - eventY * resolution.height / disp.height - 1);
	}

	public Point getMousePosition(final Dimension resolution) {
		return getMousePosition(resolution, Mouse.getX(), Mouse.getY());
	}

	public Point getResolutionMousePosition(final int eventX, final int eventY) {
		final Dimension reso = resolutionSize();
		return getMousePosition(reso, eventX, eventY);
	}

	public Point getResolutionMousePosition() {
		return getResolutionMousePosition(Mouse.getX(), Mouse.getY());
	}

	public Point getScreenMousePosition(final GuiScreen resolution, final int eventX, final int eventY) {
		final Dimension reso = new Dimension(resolution.width, resolution.height);
		return getMousePosition(reso);
	}

	public Point getScreenMousePosition(final GuiScreen resolution) {
		return getScreenMousePosition(resolution, Mouse.getX(), Mouse.getY());
	}

	public Point getAbsoluteMousePosition(final int eventX, final int eventY) {
		final Dimension disp = displaySize();
		return new Point(eventX, disp.height - eventY - 1);
	}

	public Point getAbsoluteMousePosition() {
		return getAbsoluteMousePosition(Mouse.getX(), Mouse.getY());
	}
}
