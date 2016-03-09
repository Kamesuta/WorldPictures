package com.kamesuta.mc.worldpictures.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.resource.WorldResource;
import com.kamesuta.mc.worldpictures.resource.WorldResourceManager;

import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class WorldTexture implements IWorldTexture {

	public static WorldTexture NULL;

	protected int glTextureId = -1;

	public WorldTexture(BufferedImage image) {
		TextureUtil.allocateTexture(this.getGlTextureId(), image.getWidth(), image.getHeight());
		TextureUtil.uploadTextureImage(this.getGlTextureId(), image);
	}

	public WorldTexture(int width, int height, int[] data) {
		TextureUtil.allocateTexture(this.getGlTextureId(), width, height);
		TextureUtil.uploadTexture(this.getGlTextureId(), data, width, height);
	}

	public WorldTexture(File file) throws IOException {
		this(ImageIO.read(file));
	}

	public WorldTexture(WorldResourceManager manager, WorldResource location) throws IOException {
		this(manager.getResource(location));
	}

	public WorldTexture(IResourceManager manager, ResourceLocation location) throws IOException {
		this(ImageIO.read(manager.getResource(location).getInputStream()));
	}

	@Deprecated
	public WorldTexture(TextureManager manager, ResourceLocation location) {
		ITextureObject texture = manager.getTexture(location);
		if (texture == null) {
			texture = new SimpleTexture(location);
			manager.loadTexture(location, texture);
		}
		this.glTextureId = texture.getGlTextureId();
	}

	@Override
	public int getGlTextureId() {
		if (this.glTextureId == -1) {
			this.glTextureId = TextureUtil.glGenTextures();
		}

		return this.glTextureId;
	}

	@Override
	public void deleteGlTexture() {
		if (this.glTextureId != -1) {
			TextureUtil.deleteTexture(this.glTextureId);
			this.glTextureId = -1;
		}
	}

	static {
		try {
			IResourceManager r = ClientProxy.MINECRAFT.getResourceManager();
			NULL = new WorldTexture(r, new ResourceLocation(Reference.MODID.toLowerCase(), "missing.png"));
		} catch (Exception e) {
			Reference.logger.warn("Missing Missing Texture :-(");
			Reference.logger.debug(e);
			NULL = new WorldTexture(16, 16, TextureUtil.missingTexture.getTextureData());
		}
	}
}