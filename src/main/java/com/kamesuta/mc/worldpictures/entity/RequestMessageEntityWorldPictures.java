package com.kamesuta.mc.worldpictures.entity;

import com.kamesuta.mc.worldpictures.handler.PacketHandler;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class RequestMessageEntityWorldPictures implements IMessage {
	private int entityId;

	// this constructor is required otherwise you'll get errors (used
	// somewhere in fml through reflection)
	public RequestMessageEntityWorldPictures() {
		this.entityId = -1;
	}

	public RequestMessageEntityWorldPictures(int parEntityId) {
		this.entityId = parEntityId;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityId = ByteBufUtils.readVarInt(buf, 4);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, this.entityId, 4);
	}

	public static class Handler implements IMessageHandler<RequestMessageEntityWorldPictures, IMessage> {
		@Override
		public IMessage onMessage(RequestMessageEntityWorldPictures message, MessageContext ctx) {
			if (ctx.side.isServer()) {
				EntityPlayerMP thePlayer = ctx.getServerHandler().playerEntity;
				Entity theEntity = thePlayer.worldObj.getEntityByID(message.entityId);
				if (theEntity != null && theEntity instanceof EntityWorldPictures) {
					NBTTagCompound nbt = ((EntityWorldPictures)theEntity).getSyncDataCompound();
					PacketHandler.net.sendTo(new SyncMessageEntityWorldPictures(theEntity.getEntityId(), nbt), thePlayer);
				}
			}
			return null; // no response in this case
		}
	}
}