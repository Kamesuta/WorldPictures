package com.kamesuta.mc.worldpictures.proxy;

import java.io.File;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.entity.EntityWorldPictures;
import com.kamesuta.mc.worldpictures.entity.RenderEntityWorldPictures;
import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.handler.GuiHandler;
import com.kamesuta.mc.worldpictures.handler.PacketHandler;
import com.kamesuta.mc.worldpictures.item.WildAnimalsMonsterPlacer;
import com.kamesuta.mc.worldpictures.net.LocalManager;
import com.kamesuta.mc.worldpictures.net.NetManager;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;

public abstract class CommonProxy {
	public NetManager netmanager;
	public LocalManager localmanager;
	public WorldResourceManager resource;

	public void preInit(final FMLPreInitializationEvent event) {
		Reference.logger = event.getModLog();
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		this.localmanager = new LocalManager(new File(getDataDirectory(), "objects"));
		this.netmanager = new NetManager(5);
		this.resource = new WorldResourceManager(getWorldPicturesDirectory());
	}

	public void init(final FMLInitializationEvent event) {

		// GenericItem genericItem = new GenericItem();
		// GameRegistry.registerItem(genericItem, "genericItem");

		EntityRegistry.registerModEntity(EntityWorldPictures.class, "SampleEntity", 0, WorldPictures.instance, 250, 1, false);
		// EntityRegistry.addSpawn(EntitySample.class, 20, 1, 4,
		// EnumCreatureType.creature, BiomeGenBase.plains);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			RenderingRegistry.registerEntityRenderingHandler(EntityWorldPictures.class, new RenderEntityWorldPictures(new ModelBiped(), 0));
		}

		PacketHandler.initPackets();
		NetworkRegistry.INSTANCE.registerGuiHandler(WorldPictures.instance, new GuiHandler());

		final String parSpawnName = "SampleEgg";
		final Item itemSpawnEgg = new WildAnimalsMonsterPlacer("SampleEntity", 0xE18519, 0x000000)
				.setUnlocalizedName("spawn_egg_" + parSpawnName.toLowerCase()).setTextureName("minecraft:spawn_egg");
		GameRegistry.registerItem(itemSpawnEgg, "spawnEgg" + parSpawnName);
	}

	public void postInit(final FMLPostInitializationEvent event) {
	}

	public void serverStarting(final FMLServerStartingEvent event) {
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
