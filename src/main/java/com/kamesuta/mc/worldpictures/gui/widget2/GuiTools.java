package com.kamesuta.mc.worldpictures.gui.widget2;

import static com.kamesuta.mc.worldpictures.gui.widget2.position.FlexiblePosition.EnumAbsolute.*;

import java.awt.Point;
import java.util.EnumSet;

import org.lwjgl.input.Mouse;

import com.kamesuta.mc.worldpictures.gui.widget2.position.FlexiblePosition;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;
import com.kamesuta.mc.worldpictures.gui.widget2.position.PositionAbsolute;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class GuiTools {
	public final Minecraft mc;

	public final GuiGraphics g;
	public final GuiEvent event;
	public final GuiState state;

	public boolean isDebug;

	public GuiTools(final GuiGraphics g, final GuiEvent event, final GuiState state) {
		this.mc = Minecraft.getMinecraft();
		this.g = g;
		this.event = event;
		this.state = state;
	}

	public GuiTools() {
		this(new GuiGraphics(), new GuiEvent(), new GuiState());
	}

	public IPositionAbsolute getAbsolute() {
		return new PositionAbsolute(0, 0, this.mc.displayWidth, this.mc.displayHeight);
	}

	public IPositionRelative getResolution() {
		final ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		return new FlexiblePosition(EnumSet.of(ABSOLUTE_W, ABSOLUTE_H), 0.5f, 0.5f, res.getScaledWidth(), res.getScaledHeight());
	}

	public IPositionAbsolute getAbsolute(final GuiPosition gp) {
		return gp.getAbsolute(getAbsolute());
	}

	public Point getAbsoluteMousePosition(final int eventX, final int eventY) {
		return new Point(eventX, this.mc.displayHeight - eventY - 1);
	}

	public Point getAbsoluteMousePosition() {
		return getAbsoluteMousePosition(Mouse.getX(), Mouse.getY());
	}

}
