package com.kamesuta.mc.worldpictures.entity;

import com.kamesuta.mc.worldpictures.handler.PacketHandler;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class SyncMessageEntityWorldPictures implements IMessage {
	private int entityId;
	private NBTTagCompound entitySyncDataCompound;

	// this constructor is required otherwise you'll get errors (used
	// somewhere in fml through reflection)
	public SyncMessageEntityWorldPictures() {
		this.entityId = -1;
		this.entitySyncDataCompound = new NBTTagCompound();
	}

	public SyncMessageEntityWorldPictures(int parEntityId, NBTTagCompound parTagCompound) {
		this.entityId = parEntityId;
		this.entitySyncDataCompound = parTagCompound;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityId = ByteBufUtils.readVarInt(buf, 4);
		this.entitySyncDataCompound = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, this.entityId, 4);
		ByteBufUtils.writeTag(buf, this.entitySyncDataCompound);
	}

	public static class Handler implements IMessageHandler<SyncMessageEntityWorldPictures, IMessage> {
		@Override
		public IMessage onMessage(SyncMessageEntityWorldPictures message, MessageContext ctx) {
			if (ctx.side.isClient()) {
				EntityPlayer thePlayer = Minecraft.getMinecraft().thePlayer;
				Entity theEntity = thePlayer.worldObj.getEntityByID(message.entityId);
				if (theEntity != null && theEntity instanceof EntityWorldPictures) {
					((EntityWorldPictures) theEntity).setSyncDataCompound(message.entitySyncDataCompound);
				}
			}
			if (ctx.side.isServer()) {
				EntityPlayerMP thePlayer = ctx.getServerHandler().playerEntity;
				Entity theEntity = thePlayer.worldObj.getEntityByID(message.entityId);
				if (theEntity != null && theEntity instanceof EntityWorldPictures) {
					((EntityWorldPictures) theEntity).setSyncDataCompound(message.entitySyncDataCompound);
					PacketHandler.net.sendTo(new SyncMessageEntityWorldPictures(message.entityId, message.entitySyncDataCompound), thePlayer);
				}
			}
			return null; // no response in this case
		}
	}
}