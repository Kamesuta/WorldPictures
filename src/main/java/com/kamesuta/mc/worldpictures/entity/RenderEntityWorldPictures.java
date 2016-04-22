package com.kamesuta.mc.worldpictures.entity;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.Square;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.renderer.Renderer;
import com.kamesuta.mc.worldpictures.resource.WorldResource;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityWorldPictures extends Render {
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/items/missing.png");
	public static WorldResource scenetexture = new WorldResource("abc", "picture");

	public RenderEntityWorldPictures(final ModelBase b, final float f) {
		//super(b, f);
	}

	@Override
	public void doRender(final Entity entity, final double x, final double y, final double z, final float p_76986_8_, final float p_76986_9_) {
		GL11.glPushMatrix();
		GL11.glTranslated(x-entity.posX, y-entity.posY, z-entity.posZ);
		GL11.glDisable(GL11.GL_CULL_FACE);

		if (entity instanceof EntityWorldPictures) {
			final int millisPerTick = 1000 / 20;
			final EntityWorldPictures entitywp = (EntityWorldPictures)entity;
			final long systime = System.currentTimeMillis();
			final long ticktime = entitywp.ticksExisted * millisPerTick;
			final long offset = systime - ticktime;
			final long diffoffset = offset - entitywp.currentTimeOffset;
			if (!(0 < diffoffset && diffoffset < millisPerTick))
				entitywp.currentTimeOffset = offset;
			final long nowtime = ticktime + diffoffset;

			final Scene scene = entitywp.scene;
			if (scene != null) {
				Renderer.INSTANCE.textureManager.bindTexture(scenetexture);
				final Square square = Scene.takeashot(scene, nowtime);
				if (square != null)
					Square.draw(square, Tessellator.instance);
			}
		}

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();


		GL11.glPushMatrix();
		GL11.glTranslated(x-entity.posX, y-entity.posY, z-entity.posZ);

		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(false);

		RenderGlobal.drawOutlinedBoundingBox(entity.boundingBox, -1);

		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
		return texture;
	}
}