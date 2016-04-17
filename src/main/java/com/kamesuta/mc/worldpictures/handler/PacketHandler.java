package com.kamesuta.mc.worldpictures.handler;

import com.kamesuta.mc.worldpictures.entity.SimpleMessage;
import com.kamesuta.mc.worldpictures.entity.SimplePacket;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
  public static SimpleNetworkWrapper net;

  public static void initPackets()
  {
    net = NetworkRegistry.INSTANCE.newSimpleChannel("YourModId".toUpperCase());
    registerMessage(SimplePacket.class, SimpleMessage.class);
  }

  private static int nextPacketId = 0;

  private static void registerMessage(Class<? extends IMessageHandler<SimpleMessage, IMessage>> packet, Class<SimpleMessage> message)
  {
    net.registerMessage(packet, message, nextPacketId, Side.CLIENT);
    net.registerMessage(packet, message, nextPacketId, Side.SERVER);
    nextPacketId++;
  }
}