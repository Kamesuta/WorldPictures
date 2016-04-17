package com.kamesuta.mc.worldpictures.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class SimpleMessage implements IMessage {
	public NBTTagCompound nbt;

	// this constructor is required otherwise you'll get errors (used
	// somewhere in fml through reflection)
	public SimpleMessage() {
		this.nbt = new NBTTagCompound();
	}

	public SimpleMessage(NBTTagCompound nbt) {
		this.nbt = nbt;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// the order is important
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}
}