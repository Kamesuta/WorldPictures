package com.kamesuta.mc.worldpictures.proxy;

import java.io.File;
import java.io.IOException;

import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.handler.client.InputHandler;
import com.kamesuta.mc.worldpictures.handler.client.OverlayHandler;
import com.kamesuta.mc.worldpictures.handler.client.TickHandler;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.renderer.Renderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	public static final Minecraft MINECRAFT = Minecraft.getMinecraft();

	@Override
	public File getDataDirectory() {
		final File file = MINECRAFT.mcDataDir;
		try {
			return file.getCanonicalFile();
		} catch (IOException e) {
			Reference.logger.debug("Could not canonize path!", e);
		}
		return file;
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		for (KeyBinding keyBinding : InputHandler.KEY_BINDINGS) {
			ClientRegistry.registerKeyBinding(keyBinding);
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		FMLCommonHandler.instance().bus().register(InputHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(TickHandler.INSTANCE);
		FMLCommonHandler.instance().bus().register(ConfigurationHandler.INSTANCE);

		MinecraftForge.EVENT_BUS.register(Renderer.INSTANCE);
		MinecraftForge.EVENT_BUS.register(new OverlayHandler());
	}
}