package com.kamesuta.mc.worldpictures.handler;

import com.kamesuta.mc.worldpictures.entity.EntityWorldPictures;
import com.kamesuta.mc.worldpictures.gui.GuiEntityWorldPictures;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int entityId, int y, int z) {
		if (ID == GuiEntityWorldPictures.GUI_ID) {
			Entity entity = player.worldObj.getEntityByID(entityId);
			if (entity instanceof EntityWorldPictures) {
				return new GuiEntityWorldPictures(player, (EntityWorldPictures)entity);
			}
		}
		return null;
	}
}