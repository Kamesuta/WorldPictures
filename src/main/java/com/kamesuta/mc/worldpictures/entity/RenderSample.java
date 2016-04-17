package com.kamesuta.mc.worldpictures.entity;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSample extends Render {
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/items/missing.png");

	public RenderSample(ModelBase b, float f) {
		//super(b, f);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		final long ticks = entity.ticksExisted * 1000 / 20;
//		if (entity instanceof EntitySample) {
//			((EntitySample)entity).scene.takeashot(ticks);
//		}

		GL11.glTranslated(x, y, z);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);

        RenderGlobal.drawOutlinedBoundingBox(entity.boundingBox.getOffsetBoundingBox(-entity.posX, -entity.posY, -entity.posZ), -1);

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return texture;
	}
}