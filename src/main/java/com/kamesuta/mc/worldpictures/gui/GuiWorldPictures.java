package com.kamesuta.mc.worldpictures.gui;

import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.renderer.Renderer;
import com.kamesuta.mc.worldpictures.vertex.OneCut;
import com.kamesuta.mc.worldpictures.vertex.ParallelogramSquareBuilder;
import com.kamesuta.mc.worldpictures.vertex.Scene;
import com.kamesuta.mc.worldpictures.vertex.Vector3f;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;

public class GuiWorldPictures extends GuiScreenBase {
	private int centerX = 0;
	private int centerY = 0;

	private GuiButton btnAction = null;
	private GuiButton btnNew = null;
	private GuiButton btnBuild = null;
	private GuiButton btnAdd = null;
	private GuiButton btnNext = null;
	private GuiButton btnPrev = null;
	private GuiButton btnPos = null;

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

		this.btnAction = new GuiButton(id++, 10, this.height - 30, 50, 20, I18n.format(Names.Gui.ACTION));
		this.buttonList.add(this.btnAction);

		this.btnNew = new GuiButton(id++, 10, this.height - 55, 50, 20, I18n.format(Names.Gui.NEW));
		this.buttonList.add(this.btnNew);

		this.btnBuild = new GuiButton(id++, 10, this.height - 80, 50, 20, I18n.format(Names.Gui.BUILD));
		this.buttonList.add(this.btnBuild);

		this.btnAdd = new GuiButton(id++, 10, this.height - 105, 50, 20, I18n.format(Names.Gui.ADD));
		this.buttonList.add(this.btnAdd);

		this.btnNext = new GuiButton(id++, 10, this.height - 130, 50, 20, I18n.format(Names.Gui.NEXT));
		this.buttonList.add(this.btnNext);

		this.btnPrev = new GuiButton(id++, 10, this.height - 155, 50, 20, I18n.format(Names.Gui.PREV));
		this.buttonList.add(this.btnPrev);

		this.btnPos = new GuiButton(id++, 10, this.height - 180, 50, 20, I18n.format(Names.Gui.POS));
		this.buttonList.add(this.btnPos);

		this.btnPos = new GuiButton(id++, 10, this.height - 180, 50, 20, I18n.format(Names.Gui.POS));
		this.buttonList.add(this.btnPos);
	}

	@Override
	protected void actionPerformed(GuiButton guiButton) {
		if (guiButton.enabled) {
			if (guiButton.id == btnAction.id) {
				float h = 90;
				int split = 16;
				int split4 = split/4;
				double d45 = (360/split) * Math.PI / 180;
				float r = 10;
				Scene[] v = new Scene[split];
				for (int i = 0; i < split; i++) {
					v[i] = new Scene(
							new Vector3f((float)(Math.sin((i+0*split4)*d45) * r), h, (float)(Math.cos((i+0*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+1*split4)*d45) * r), h, (float)(Math.cos((i+1*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+2*split4)*d45) * r), h, (float)(Math.cos((i+2*split4)*d45) * r)),
							new Vector3f((float)(Math.sin((i+3*split4)*d45) * r), h, (float)(Math.cos((i+3*split4)*d45) * r)));
				}

				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.picture.picture, new OneCut(2, v));

//				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.picture,
//						new WorldVertexCompound(4,
//								new WorldVertexObj(0, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0), new Vector3f(0,100,0)),
//								new WorldVertexObj(1, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0), new Vector3f(0,100,0)),
//								new WorldVertexObj(2, new Vector3f(0,100,0), new Vector3f(0,100,0), new Vector3f(0,100,10), new Vector3f(0,100,10)),
//								new WorldVertexObj(3, new Vector3f(0,100,0), new Vector3f(0,100,0), new Vector3f(0,100,10), new Vector3f(0,100,10)),
//								new WorldVertexObj(4, new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(10,100,0), new Vector3f(0,100,0))
//								));
			} else if (guiButton.id == btnNew.id) {
				Renderer.INSTANCE.cut = new OneCut(5);
				Renderer.INSTANCE.squarebuilder = new ParallelogramSquareBuilder();
			} else if (guiButton.id == btnBuild.id) {
				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.picture.picture, Renderer.INSTANCE.cut);
			} else if (guiButton.id == btnAdd.id) {
				Renderer.INSTANCE.cut.vertexes.add(new Scene(Renderer.INSTANCE.squarebuilder.build()));
				Renderer.INSTANCE.squarebuilder = new ParallelogramSquareBuilder();
			} else if (guiButton.id == btnNext.id) {
				Renderer.INSTANCE.cut.vertexes.add(new Scene(Renderer.INSTANCE.squarebuilder.build()));
			} else if (guiButton.id == btnPrev.id) {
				Renderer.INSTANCE.cut.vertexes.removeLast();
			} else if (guiButton.id == btnPos.id) {
				EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
				if (player != null)
				{
					Vector3f now = new Vector3f(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
					Renderer.INSTANCE.squarebuilder.addLast(now);
				}
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
