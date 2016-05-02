package com.kamesuta.mc.worldpictures.gui.widget2;

import static com.kamesuta.mc.worldpictures.gui.widget2.position.FlexiblePosition.EnumAbsolute.*;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionAbsolute;
import com.kamesuta.mc.worldpictures.gui.widget2.position.IPositionRelative;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

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

	@Deprecated
	public IPositionRelative getResolution() {
		final ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		return new com.kamesuta.mc.worldpictures.gui.widget2.position.FlexiblePosition(EnumSet.of(ABSOLUTE_W, ABSOLUTE_H), 0.5f, 0.5f, res.getScaledWidth(), res.getScaledHeight());
	}

	public void drawDebug(final GuiPosition gposition) {
		final IPositionAbsolute p = gposition.getAbsolute();
		GL11.glColor4f(1, 1, 1, 0.2f);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex3f(p.x1(), p.y1(), 0);
		GL11.glVertex3f(p.x1(), p.y2(), 0);
		GL11.glVertex3f(p.x2(), p.y2(), 0);
		GL11.glVertex3f(p.x2(), p.y1(), 0);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
