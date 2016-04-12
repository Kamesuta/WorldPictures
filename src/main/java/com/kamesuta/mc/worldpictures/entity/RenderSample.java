package com.kamesuta.mc.worldpictures.entity;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSample extends Render {
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/items/missing.png");

	public RenderSample(ModelBase b, float f) {
		//super(b, f);
	}

	@Override
	public void doRender(Entity p_76986_1_, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		final double corner = 3;

//		GL11.glDisable(GL11.GL_TEXTUREE);a
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);

		GL11.glColor4f(1f, 1f, 0f, 1f);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(0-corner, 0, 0);
		GL11.glVertex3d(0+corner, 0, 0);
		GL11.glVertex3d(0, 0-corner, 0);
		GL11.glVertex3d(0, 0+corner, 0);
		GL11.glVertex3d(0, 0, 0-corner);
		GL11.glVertex3d(0, 0, 0+corner);
		GL11.glEnd();

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
}