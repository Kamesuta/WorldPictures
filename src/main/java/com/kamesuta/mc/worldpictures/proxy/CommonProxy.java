package com.kamesuta.mc.worldpictures.proxy;

import java.io.File;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.entity.EntitySample;
import com.kamesuta.mc.worldpictures.entity.RenderSample;
import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.item.WildAnimalsMonsterPlacer;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;

public abstract class CommonProxy {
	public WorldResourceManager resource;

	public void preInit(FMLPreInitializationEvent event) {
		Reference.logger = event.getModLog();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		resource = new WorldResourceManager(getWorldPicturesDirectory());
	}

	public void init(FMLInitializationEvent event) {

		// GenericItem genericItem = new GenericItem();
		// GameRegistry.registerItem(genericItem, "genericItem");

		EntityRegistry.registerModEntity(EntitySample.class, "SampleEntity", 0, WorldPictures.instance, 250, 1, false);
		// EntityRegistry.addSpawn(EntitySample.class, 20, 1, 4,
		// EnumCreatureType.creature, BiomeGenBase.plains);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			RenderingRegistry.registerEntityRenderingHandler(EntitySample.class, new RenderSample(new ModelBiped(), 0));
		}

		String parSpawnName = "SampleEgg";
		Item itemSpawnEgg = new WildAnimalsMonsterPlacer("SampleEntity", 0xE18519, 0x000000)
				.setUnlocalizedName("spawn_egg_" + parSpawnName.toLowerCase()).setTextureName("minecraft:spawn_egg");
		GameRegistry.registerItem(itemSpawnEgg, "spawnEgg" + parSpawnName);
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
