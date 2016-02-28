package com.kamesuta.mc.worldpictures.reference;

public final class Names {
	public static final class Config {
		public static final String SUFFIX = ".config";

		public static final class Category {
			public static final String DEBUG = "debug";
			public static final String RENDER = "render";
			public static final String GENERAL = "general";
			public static final String SERVER = "server";
		}

		public static final String WorldPictures_DIRECTORY = "worldpicturesDirectory";
		public static final String WorldPictures_DIRECTORY_DESC = "Worldpictures directory.";

		public static final String PLAYER_QUOTA_KILOBYTES = "playerQuotaKilobytes";
		public static final String PLAYER_QUOTA_KILOBYTES_DESC = "Amount of storage provided per-player for worldpictures on the server.";

		public static final String WAY_OFFSET = "wayOffset";
		public static final String WAY_OFFSET_DESC = "Offset of way line height.";

		public static final String LANG_PREFIX = Reference.MODID.toLowerCase() + SUFFIX;
	}

	public static final class Gui {
		public static final String TITLE = "worldpictures.gui.title";
		public static final String NEW = "worldpictures.gui.new";
		public static final String OFFSET_INC = "worldpictures.gui.offsetInc";
		public static final String OFFSET_DEC = "worldpictures.gui.offsetDec";
		public static final String ON = "worldpictures.gui.on";
		public static final String OFF = "worldpictures.gui.off";
		public static final String DONE = "worldpictures.gui.done";

	}

	public static final class Keys {
		public static final String CATEGORY = "worldpictures.key.category";
		public static final String GUI = "worldpictures.key.gui";
		public static final String ACTION = "worldpictures.key.action";
	}

	public static final class Formats {
		public static final String NAME_PICTURE = "picture";
		public static final String NAME_VERTEX = "vertex";
		public static final String SAVE_SUCCESS = "Successfully saving.";
		public static final String SAVE_ERROR = "Save error has occured.";
		public static final String LOAD_SUCCESS = "Successfully loading.";
		public static final String LOAD_ERROR = "Load error has occured.";
	}
}
