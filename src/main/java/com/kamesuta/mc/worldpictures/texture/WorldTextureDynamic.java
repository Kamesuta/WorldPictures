package com.kamesuta.mc.worldpictures.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureUtil;

@SideOnly(Side.CLIENT)
public class WorldTextureDynamic extends AbstractWorldTexture {
	private final int[] dynamicTextureData;
	/** width of this icon in pixels */
	private final int width;
	/** height of this icon in pixels */
	private final int height;

	public WorldTextureDynamic(BufferedImage image) {
		this(image.getWidth(), image.getHeight());
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), this.dynamicTextureData, 0,
				image.getWidth());
		this.updateDynamicTexture();
	}

	public WorldTextureDynamic(int width, int height) {
		this.width = width;
		this.height = height;
		this.dynamicTextureData = new int[width * height];
		TextureUtil.allocateTexture(this.getGlTextureId(), width, height);
	}

	public void loadTexture(WorldResourceManager manager) throws IOException {
	}

	public void updateDynamicTexture() {
		TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
	}

	public int[] getTextureData() {
		return this.dynamicTextureData;
	}
}