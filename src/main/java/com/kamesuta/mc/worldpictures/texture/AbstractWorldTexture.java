package com.kamesuta.mc.worldpictures.texture;

import net.minecraft.client.renderer.texture.TextureUtil;

public abstract class AbstractWorldTexture implements IWorldTexture {

	public static final WorldTextureDynamic NULL_PIECE;
	public static WorldTextureDynamic NULL_PICTURE;

	protected int glTextureId = -1;

	public AbstractWorldTexture() {
		super();
	}

	@Override
	public int getGlTextureId() {
		if (this.glTextureId == -1) {
			this.glTextureId = TextureUtil.glGenTextures();
		}

		return this.glTextureId;
	}

	public void deleteGlTexture() {
		if (this.glTextureId != -1) {
			TextureUtil.deleteTexture(this.glTextureId);
			this.glTextureId = -1;
		}
	}

	static {
		NULL_PIECE = new WorldTextureDynamic(16, 16);
		int[] null_picture_data = NULL_PIECE.getTextureData();

		int[] colorA = new int[] { -524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040 };
		int[] colorB = new int[] { -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216,
				-16777216 };
		int length = colorA.length;

		for (int i = 0; i < 16; ++i) {
			System.arraycopy(i < length ? colorA : colorB, 0, null_picture_data, 16 * i, length);
			System.arraycopy(i < length ? colorB : colorA, 0, null_picture_data, 16 * i + length, length);
		}
		NULL_PIECE.updateDynamicTexture();
	}
}