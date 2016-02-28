package com.kamesuta.mc.worldpictures.renderer;

import java.util.Arrays;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.texture.WorldTextureManager;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexCompound;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexManager;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexObj;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderWorldLastEvent;

@SideOnly(Side.CLIENT)
public class Renderer {
	public static final Renderer INSTANCE = new Renderer();
	public WorldTextureManager pictureManager = new WorldTextureManager(WorldPictures.proxy.resource);
	public WorldVertexManager vertexManager = new WorldVertexManager(WorldPictures.proxy.resource);
	private final Profiler profiler = ClientProxy.MINECRAFT.mcProfiler;

	public WorldResource picture = new WorldResource("abc", Arrays.asList(Names.Formats.NAME_PICTURE, Names.Formats.NAME_VERTEX));
	public WorldVertexCompound vertex = new WorldVertexCompound(
			2,
			new WorldVertexObj(0, new Vector3f(10, 90, 10), new Vector3f(10, 90, 0), new Vector3f(0, 100, 0), new Vector3f(0, 100, 10)),
			new WorldVertexObj(1, new Vector3f(10, 100, 10), new Vector3f(10, 100, 0), new Vector3f(0, 110, 0), new Vector3f(0, 110, 10))
	);

	private Renderer() {
	}

	@SubscribeEvent
	public void onRender(RenderWorldLastEvent event) {
		EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
		if (player != null) {
			this.profiler.startSection("worldpictures");
			render(event.partialTicks, player);
			this.profiler.endSection();
		}
	}

	public void render(float partialTicks, EntityPlayerSP player) {
		double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
		double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
		double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;

		GL11.glPushMatrix();
		GL11.glTranslated(-x, -y, -z);

		// GL11.glDisable(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_LIGHTING);
		pictureManager.bindTexture(picture);
		vertexManager.drawVertex(picture);
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

		// GL11.glEnable(GL11.GL_TEXTURE_2D);
		// cleanup
		GL11.glPopMatrix();
	}
}
