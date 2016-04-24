package com.kamesuta.mc.worldpictures.component.util;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.component.Component;
import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.Square;

import net.minecraft.client.renderer.Tessellator;

public class ComponentRender {
	public static void drawComponent(final Component component, final long time) {
		if (component != null)
			drawScene(component.scene, time);
	}

	public static void drawComponentOutline(final Component component, final long time) {
		if (component != null)
			drawSceneOutline(component.scene, time);
	}

	public static void drawScene(final Scene scene, final long time) {
		if (scene != null)
			drawKeyframes(scene.keyframes, time);
	}

	public static void drawSceneOutline(final Scene scene, final long time) {
		if (scene != null)
			drawKeyframesOutline(scene.keyframes, time);
	}

	public static void drawKeyframes(final List<Keyframe> kfs, final long time) {
		if (kfs != null)
			drawSquare(ComponentUtil.getSquareFromKeyframes(kfs, time));
	}

	public static void drawKeyframesOutline(final List<Keyframe> kfs, final long time) {
		if (kfs != null)
			drawSquareOutline(ComponentUtil.getSquareFromKeyframes(kfs, time));
	}

	public static void drawSquare(final Square square) {
		if (square != null) {
			final Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(square.lt.x, square.lt.y, square.lt.z, Square.U_lt, Square.V_lt);
			tessellator.addVertexWithUV(square.lb.x, square.lb.y, square.lb.z, Square.U_lb, Square.V_lb);
			tessellator.addVertexWithUV(square.rb.x, square.rb.y, square.rb.z, Square.U_rb, Square.V_rb);
			tessellator.addVertexWithUV(square.rt.x, square.rt.y, square.rt.z, Square.U_rt, Square.V_rt);
			tessellator.draw();
		}
	}

	public static void drawSquareOutline(final Square square) {
		if (square != null) {
			GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex3f(square.lt.x, square.lt.y, square.lt.z);
			GL11.glVertex3f(square.lb.x, square.lb.y, square.lb.z);
			GL11.glVertex3f(square.rb.x, square.rb.y, square.rb.z);
			GL11.glVertex3f(square.rt.x, square.rt.y, square.rt.z);
			GL11.glEnd();
		}
	}
}
