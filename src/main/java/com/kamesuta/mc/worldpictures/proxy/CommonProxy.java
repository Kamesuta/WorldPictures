package com.kamesuta.mc.worldpictures.proxy;

import java.io.File;

import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.PicturesResourceManager;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public abstract class CommonProxy {
	public PicturesResourceManager resource;

	public void preInit(FMLPreInitializationEvent event) {
		Reference.logger = event.getModLog();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		resource = new PicturesResourceManager(getWorldPicturesDirectory());
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	public void serverStarting(FMLServerStartingEvent event) {
	}

	public void createFolders() {
		if (!ConfigurationHandler.worldpicturesDirectory.exists()) {
			if (!ConfigurationHandler.worldpicturesDirectory.mkdirs()) {
				Reference.logger.warn("Could not create ways directory [{}]!",
						ConfigurationHandler.worldpicturesDirectory.getAbsolutePath());
			}
		}
	}

	public abstract File getDataDirectory();

	public File getWorldPicturesDirectory() {
		return ConfigurationHandler.worldpicturesDirectory;
	}
}
