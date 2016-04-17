package com.kamesuta.mc.worldpictures.entity;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class SimplePacket implements IMessageHandler<SimpleMessage, IMessage> {
	@Override
	public IMessage onMessage(SimpleMessage message, MessageContext ctx) {
		// just to make sure that the side is correct
		if (ctx.side.isClient()) {
		}
		return message;
	}
}