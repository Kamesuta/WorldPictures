package com.kamesuta.mc.worldpictures.reference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {
    public static final String MODID = "WorldPictures";
    public static final String NAME = "WorldPictures";
    public static final String VERSION = "${version}";
    public static final String FORGE = "${forgeversion}";
    public static final String MINECRAFT = "${mcversion}";
    public static final String PROXY_SERVER = "com.kamesuta.mc.worldpictures.proxy.ServerProxy";
    public static final String PROXY_CLIENT = "com.kamesuta.mc.worldpictures.proxy.ClientProxy";
    public static final String GUI_FACTORY = "com.kamesuta.mc.worldpictures.client.gui.GuiFactory";

    public static Logger logger = LogManager.getLogger(Reference.MODID);
}
