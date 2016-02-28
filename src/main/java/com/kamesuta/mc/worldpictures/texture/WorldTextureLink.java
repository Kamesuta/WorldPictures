package com.kamesuta.mc.worldpictures.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureUtil;

@SideOnly(Side.CLIENT)
public class WorldTextureLink extends AbstractWorldTexture {
	protected final WorldResource location;

	public WorldTextureLink(WorldResource location) {
		this.location = location;
	}

	@Override
	public void loadTexture(WorldResourceManager manager) throws IOException {
		this.deleteGlTexture();
		InputStream inputstream = null;

		try {
			File resource = manager.getResource(this.location, Names.Formats.NAME_PICTURE);
			BufferedImage bufferedimage = ImageIO.read(resource);
			TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, false, false);
		} finally {
			if (inputstream != null) {
				inputstream.close();
			}
		}
	}
}