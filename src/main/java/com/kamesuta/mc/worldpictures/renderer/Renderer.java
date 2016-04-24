package com.kamesuta.mc.worldpictures.renderer;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.builder.Vector3f;
import com.kamesuta.mc.worldpictures.component.builder.WorldVertexManager;
import com.kamesuta.mc.worldpictures.component.builder.square.ISquareBuilder;
import com.kamesuta.mc.worldpictures.component.builder.square.ParallelogramSquareBuilder;
import com.kamesuta.mc.worldpictures.component.util.ComponentRender;
import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.texture.WorldTextureManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderWorldLastEvent;

@SideOnly(Side.CLIENT)
public class Renderer {
	public static final Renderer INSTANCE = new Renderer();
	public WorldTextureManager textureManager = new WorldTextureManager(WorldPictures.proxy.resource);
	@SuppressWarnings("deprecation")
	public WorldVertexManager vertexManager = new WorldVertexManager(WorldPictures.proxy.resource);
	private final Profiler profiler = ClientProxy.MINECRAFT.mcProfiler;

	public ISquareBuilder squarebuilder = new ParallelogramSquareBuilder();
	public ArrayList<Keyframe> cut = new ArrayList<Keyframe>();

	//	public WorldObjClient picture = new WorldObjClient("abcd");
	public WorldResource vertex = new WorldResource("abcd", "vertex");
	public WorldResource texture = new WorldResource("abc", "picture");

	private Renderer() {
	}

	@SubscribeEvent
	public void onRender(final RenderWorldLastEvent event) {
		final EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
		if (player != null) {
			this.profiler.startSection("worldpictures");
			render(event.partialTicks, player);
			this.profiler.endSection();
		}
	}

	public void render(final float partialTicks, final EntityPlayerSP player) {
		final double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		final double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		final double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z);

		GL11.glDisable(GL11.GL_CULL_FACE);
		//		GL11.glDisable(GL11.GL_LIGHTING);
		this.textureManager.bindTexture(this.texture);
		//this.vertexManager.drawVertex(this.vertex);
		ComponentRender.drawKeyframesOutline(this.cut, System.currentTimeMillis());

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if (this.squarebuilder != null) {
			GL11.glColor4f(1f, 1f, 0.5f, 1f);
			this.squarebuilder.renderAssist();
			GL11.glColor4f(0.5f, 0.5f, 1f, 0.5f);
			this.squarebuilder.renderAssistLine(new Vector3f((float)x, (float)y, (float)z));
		}

		/*		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(10, 90, 10, 0, 1);
		tessellator.addVertexWithUV(10, 90, 0, 1, 1);
		tessellator.addVertexWithUV(0, 100, 0, 1, 0);
		tessellator.addVertexWithUV(0, 100, 10, 0, 0);
		tessellator.draw();
		 */


		//		 GL11.glBegin(GL11.GL_LINE_LOOP);
		//
		//		 GL11.glVertex3f(10f, 90f, 10f);
		//
		//		 GL11.glVertex3f(10f, 90f, 0f);
		//
		//		 GL11.glVertex3f(0f, 100f, 0f);
		//
		//		 GL11.glVertex3f(0f, 100f, 10f);
		//
		//		 GL11.glEnd();

		// glEnableClientState(GL_VERTEX_ARRAY);
		//
		// glDisableClientState(GL_VERTEX_ARRAY);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		// cleanup
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
}
