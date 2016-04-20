package com.kamesuta.mc.worldpictures.handler.client;

import com.kamesuta.mc.worldpictures.component.Position;
import com.kamesuta.mc.worldpictures.renderer.Renderer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class OverlayHandler {
	private final Minecraft minecraft = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onText(RenderGameOverlayEvent.Text event) {
		if (this.minecraft.gameSettings.showDebugInfo) {
			event.left.add("");
			Position v1 = new Position((float)minecraft.thePlayer.posX, (float)minecraft.thePlayer.posY-1, (float)minecraft.thePlayer.posZ);
			Position v2 = new Position((float)minecraft.thePlayer.posX, (float)minecraft.thePlayer.posY+1, (float)minecraft.thePlayer.posZ);
			event.left.add("[§6motionX§r] " + minecraft.thePlayer.motionX);
			event.left.add("[§6motionY§r] " + minecraft.thePlayer.motionY);
			event.left.add("[§6motionZ§r] " + minecraft.thePlayer.motionZ);
			boolean b = Renderer.INSTANCE.cut.takeashot(System.currentTimeMillis()).collisionWithLine(v1, v2);
			event.left.add("[§6cross square§r] " + b);
//			if (b) {
//				minecraft.thePlayer.motionY = 0;
//			}
			// final MovingObjectPosition mop =
			// ClientProxy.movingObjectPosition;
			// if (mop != null && mop.typeOfHit ==
			// MovingObjectPosition.MovingObjectType.BLOCK) {
			// final Block block = schematic.getBlock(mop.blockX,
			// mop.blockY, mop.blockZ);
			// final int metadata = schematic.getBlockMetadata(mop.blockX,
			// mop.blockY, mop.blockZ);
			//
			// event.right.add("");
			// event.right.add(BLOCK_REGISTRY.getNameForObject(block) + " :
			// " + metadata + " [§6S§r]");
			// }
		}
	}
}