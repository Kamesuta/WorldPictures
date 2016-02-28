package com.kamesuta.mc.worldpictures.picture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kamesuta.mc.worldpictures.resource.PicturesResource;
import com.kamesuta.mc.worldpictures.resource.PicturesResourceManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureUtil;

@SideOnly(Side.CLIENT)
public class PictureLink extends AbstractPicture {
	protected final PicturesResource location;

	public PictureLink(PicturesResource location) {
		this.location = location;
	}

	@Override
	public void loadTexture(PicturesResourceManager manager) throws IOException {
		this.deleteGlTexture();
		InputStream inputstream = null;

		try {
			File resource = manager.getResource(this.location, PictureName);
			BufferedImage bufferedimage = ImageIO.read(resource);
			TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, false, false);
		} finally {
			if (inputstream != null) {
				inputstream.close();
			}
		}
	}
}