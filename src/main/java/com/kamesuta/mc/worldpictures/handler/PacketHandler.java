package com.kamesuta.mc.worldpictures.handler;

import com.kamesuta.mc.worldpictures.entity.RequestMessageEntityWorldPictures;
import com.kamesuta.mc.worldpictures.entity.SyncMessageEntityWorldPictures;
import com.kamesuta.mc.worldpictures.reference.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	public static SimpleNetworkWrapper net;

	public static void initPackets() {
		net = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID.toUpperCase());
		registerMessage(RequestMessageEntityWorldPictures.Handler.class, RequestMessageEntityWorldPictures.class);
		registerMessage(SyncMessageEntityWorldPictures.Handler.class, SyncMessageEntityWorldPictures.class);
	}

	private static int nextPacketId = 0;

private static <REQ extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, IMessage>> packet,
			Class<REQ> message) {
		net.registerMessage(packet, message, nextPacketId, Side.CLIENT);
		net.registerMessage(packet, message, nextPacketId, Side.SERVER);
		nextPacketId++;
	}
}