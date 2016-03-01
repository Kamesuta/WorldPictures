package com.kamesuta.mc.worldpictures.gui;

import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.renderer.Renderer;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexCompound;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexObj;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiWorldPictures extends GuiScreenBase {
	private int centerX = 0;
	private int centerY = 0;

	private GuiButton btnNew = null;

	private final String strTitle = I18n.format(Names.Gui.TITLE);

	public GuiWorldPictures(GuiScreen guiScreen) {
		super(guiScreen);
	}

	@Override
	public void initGui() {
		this.centerX = this.width / 2;
		this.centerY = this.height / 2;

		this.buttonList.clear();

		int id = 0;

		this.btnNew = new GuiButton(id++, 10, this.height - 30, 50, 20, I18n.format(Names.Gui.NEW));
		this.buttonList.add(this.btnNew);
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) {
		if (guiButton.enabled) {
			if (guiButton.id == btnNew.id) {
				float h = 90;
				int split = 16;
				int split4 = split/4;
				double d45 = (360/split) * Math.PI / 180;
				float r = 10;
				WorldVertexObj[] v = new WorldVertexObj[split+1];
				for (int i = 0; i < split; i++) {
					v[i] = new WorldVertexObj(i,
							new Vector3f((float)(Math.sin((i+0*split4)*d45) * r), h, (float)(Math.cos((i+0*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+1*split4)*d45) * r), h, (float)(Math.cos((i+1*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+2*split4)*d45) * r), h, (float)(Math.cos((i+2*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+3*split4)*d45) * r), h, (float)(Math.cos((i+3*split4)*d45) * r)));
				}
				v[split] = new WorldVertexObj(split, v[0]);

				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.picture, new WorldVertexCompound(2, v));

//				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.picture,
//						new WorldVertexCompound(4,
//								new WorldVertexObj(0, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0), new Vector3f(0,100,0)),
//								new WorldVertexObj(1, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0), new Vector3f(0,100,0)),
//								new WorldVertexObj(2, new Vector3f(0,100,0), new Vector3f(0,100,0), new Vector3f(0,100,10), new Vector3f(0,100,10)),
//								new WorldVertexObj(3, new Vector3f(0,100,0), new Vector3f(0,100,0), new Vector3f(0,100,10), new Vector3f(0,100,10)),
//								new WorldVertexObj(4, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0))
//								));
			}
		}
	}

	@Override
	protected void keyTyped(char character, int code) {
		super.keyTyped(character, code);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		// drawDefaultBackground();

		drawString(this.fontRendererObj, this.strTitle, this.width - 205, this.height - 45, 0xFFFFFF);

		super.drawScreen(par1, par2, par3);
	}

	@Override
	public void onGuiClosed() {
		ConfigurationHandler.loadConfiguration();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
