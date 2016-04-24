package com.kamesuta.mc.worldpictures.handler.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class OverlayHandler {
	private final Minecraft minecraft = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onText(final RenderGameOverlayEvent.Text event) {
		if (this.minecraft.gameSettings.showDebugInfo) {
			event.left.add("");
			event.left.add("[§6motionX§r] " + this.minecraft.thePlayer.motionX);
			event.left.add("[§6motionY§r] " + this.minecraft.thePlayer.motionY);
			event.left.add("[§6motionZ§r] " + this.minecraft.thePlayer.motionZ);
			//			final Position v1 = new Position((float)this.minecraft.thePlayer.posX, (float)this.minecraft.thePlayer.posY-1, (float)this.minecraft.thePlayer.posZ);
			//			final Position v2 = new Position((float)this.minecraft.thePlayer.posX, (float)this.minecraft.thePlayer.posY+1, (float)this.minecraft.thePlayer.posZ);
			//			final Square square = Scene.takeashot(Renderer.INSTANCE.cut, (System.currentTimeMillis()));
			//			if (square != null) {
			//				final boolean b = square.collisionWithLine(v1, v2);
			//				event.left.add("[§6cross square§r] " + b);
			//			}
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