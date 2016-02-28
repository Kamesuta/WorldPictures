package com.kamesuta.mc.worldpictures.proxy;

import java.io.File;
import java.io.IOException;

import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.server.MinecraftServer;

public class ServerProxy extends CommonProxy {
    @Override
    public File getDataDirectory() {
        final File file = MinecraftServer.getServer().getFile(".");
        try {
            return file.getCanonicalFile();
        } catch (IOException e) {
            Reference.logger.info("Could not canonize path!", e);
        }
        return file;
    }
}
