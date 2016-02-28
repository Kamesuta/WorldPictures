package com.kamesuta.mc.worldpictures.handler;

import java.io.File;
import java.io.IOException;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHandler {
	public static final ConfigurationHandler INSTANCE = new ConfigurationHandler();

	public static final String VERSION = "1";

	public static Configuration configuration;

	public static final String WorldPictures_DIRECTORY_STR = "worldpictures";
	public static final File WorldPictures_DIRECTORY_DEFAULT = new File(WorldPictures.proxy.getDataDirectory(), WorldPictures_DIRECTORY_STR);
	public static final int PLAYER_QUOTA_KILOBYTES_DEFAULT = 8192;

	public static File worldpicturesDirectory = WorldPictures_DIRECTORY_DEFAULT;
	public static int playerQuotaKilobytes = PLAYER_QUOTA_KILOBYTES_DEFAULT;

	public static Property propWorldpicturesDirectory = null;
	public static Property propPlayerQuotaKilobytes = null;

	public static void init(File configFile) {
		if (configuration == null) {
			configuration = new Configuration(configFile, VERSION);
			loadConfiguration();
		}
	}

	public static void loadConfiguration() {
		propWorldpicturesDirectory = configuration.get(Names.Config.Category.GENERAL, Names.Config.WorldPictures_DIRECTORY,
				WorldPictures_DIRECTORY_STR, Names.Config.WorldPictures_DIRECTORY_DESC);
		propWorldpicturesDirectory.setLanguageKey(Names.Config.LANG_PREFIX + "." + Names.Config.WorldPictures_DIRECTORY);
		worldpicturesDirectory = new File(propWorldpicturesDirectory.getString());

		try {
			worldpicturesDirectory = worldpicturesDirectory.getCanonicalFile();
			final String worldpicturesPath = worldpicturesDirectory.getAbsolutePath();
			final String dataPath = WorldPictures.proxy.getDataDirectory().getAbsolutePath();
			if (worldpicturesPath.contains(dataPath)) {
				propWorldpicturesDirectory
						.set(worldpicturesPath.substring(dataPath.length()).replace("\\", "/").replaceAll("^/+", ""));
			} else {
				propWorldpicturesDirectory.set(worldpicturesPath.replace("\\", "/"));
			}
		} catch (IOException e) {
			Reference.logger.warn("Could not canonize path!", e);
		}

		propPlayerQuotaKilobytes = configuration.get(Names.Config.Category.SERVER, Names.Config.PLAYER_QUOTA_KILOBYTES,
				PLAYER_QUOTA_KILOBYTES_DEFAULT, Names.Config.PLAYER_QUOTA_KILOBYTES_DESC);
		propPlayerQuotaKilobytes.setLanguageKey(Names.Config.LANG_PREFIX + "." + Names.Config.PLAYER_QUOTA_KILOBYTES);
		playerQuotaKilobytes = propPlayerQuotaKilobytes.getInt(PLAYER_QUOTA_KILOBYTES_DEFAULT);

		WorldPictures.proxy.createFolders();

		if (configuration.hasChanged()) {
			configuration.save();
		}
	}

	private ConfigurationHandler() {
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MODID)) {
			loadConfiguration();
		}
	}
}