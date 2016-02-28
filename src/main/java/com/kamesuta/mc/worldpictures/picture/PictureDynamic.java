package com.kamesuta.mc.worldpictures.picture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.kamesuta.mc.worldpictures.resource.PicturesResourceManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureUtil;

@SideOnly(Side.CLIENT)
public class PictureDynamic extends AbstractPicture {
	private final int[] dynamicTextureData;
	/** width of this icon in pixels */
	private final int width;
	/** height of this icon in pixels */
	private final int height;

	public PictureDynamic(BufferedImage image) {
		this(image.getWidth(), image.getHeight());
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.dynamicTextureData, 0,
				image.getWidth());
		this.updateDynamicTexture();
	}

	public PictureDynamic(int width, int height) {
		this.width = width;
		this.height = height;
		this.dynamicTextureData = new int[width * height];
		TextureUtil.allocateTexture(this.getGlTextureId(), width, height);
	}

	public void loadTexture(PicturesResourceManager manager) throws IOException {
	}

	public void updateDynamicTexture() {
		TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
	}

	public int[] getTextureData() {
		return this.dynamicTextureData;
	}
}