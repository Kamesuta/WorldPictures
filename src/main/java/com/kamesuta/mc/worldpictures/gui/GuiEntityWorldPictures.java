package com.kamesuta.mc.worldpictures.gui;

import java.util.List;

import com.kamesuta.mc.worldpictures.component.Keyframe;
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.component.Square;
import com.kamesuta.mc.worldpictures.component.builder.MinecraftSquareBuilder;
import com.kamesuta.mc.worldpictures.component.builder.Vector3f;
import com.kamesuta.mc.worldpictures.entity.EntityWorldPictures;
import com.kamesuta.mc.worldpictures.handler.ConfigurationHandler;
import com.kamesuta.mc.worldpictures.proxy.ClientProxy;
import com.kamesuta.mc.worldpictures.reference.Names;
import com.kamesuta.mc.worldpictures.renderer.Renderer;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class GuiEntityWorldPictures extends GuiScreen {
	public static final int GUI_ID = 20;
//	private int centerX = 0;
//	private int centerY = 0;
	protected List<GuiButton> buttonList = super.buttonList;

	private GuiButton btnAction = null;
	private GuiButton btnNew = null;
	private GuiButton btnBuild = null;
	private GuiButton btnAdd = null;
	private GuiButton btnNext = null;
	private GuiButton btnPrev = null;
	private GuiButton btnPos = null;

	private final String strTitle = I18n.format(Names.Gui.TITLE);

	private final EntityWorldPictures entity;

	public GuiEntityWorldPictures(EntityPlayer player, EntityWorldPictures entity) {
		this.entity = entity;
	}

	@Override
	public void initGui() {
//		this.centerX = this.width / 2;
//		this.centerY = this.height / 2;

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
				this.entity.scene = new Scene(Renderer.INSTANCE.cut);
				this.entity.upload();
			} else if (guiButton.id == btnNew.id) {
				Renderer.INSTANCE.cut = new Scene();
				Renderer.INSTANCE.squarebuilder = new MinecraftSquareBuilder();
			} else if (guiButton.id == btnBuild.id) {
				Renderer.INSTANCE.vertexManager.saveVertex(Renderer.INSTANCE.vertex, Renderer.INSTANCE.cut);
			} else if (guiButton.id == btnAdd.id) {
				Square square = Renderer.INSTANCE.squarebuilder.build();
				if (square != null) {
					Keyframe as = new Keyframe().setSquare(square);
					Renderer.INSTANCE.cut.add(as);
				}
				Renderer.INSTANCE.squarebuilder.clear();
			} else if (guiButton.id == btnNext.id) {
				Square square = Renderer.INSTANCE.squarebuilder.build();
				if (square != null) {
					Keyframe as = new Keyframe().setSquare(square);
					Renderer.INSTANCE.cut.add(as);
				}
			} else if (guiButton.id == btnPrev.id) {
			} else if (guiButton.id == btnPos.id) {
				EntityPlayerSP player = ClientProxy.MINECRAFT.thePlayer;
				if (player != null)
				{
					Vector3f now = new Vector3f(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
					Renderer.INSTANCE.squarebuilder.setPosLast();
					Renderer.INSTANCE.squarebuilder.add(now);
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
