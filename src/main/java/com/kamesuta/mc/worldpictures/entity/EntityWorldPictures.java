package com.kamesuta.mc.worldpictures.entity;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.component.Scene;
import com.kamesuta.mc.worldpictures.gui.GuiEntityWorldPictures;
import com.kamesuta.mc.worldpictures.handler.PacketHandler;
import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class EntityWorldPictures extends Entity {
	public static final int SyncSceneId = 20;
	public static final int SyncTextureId = 21;

	public Scene scene;
	public transient long currentTimeOffset;

	public EntityWorldPictures(final World world) {
		super(world);
		super.setSize(5, 5);
	}

	public void setSyncDataCompound(final NBTTagCompound nbt) {
		Reference.logger.info("set side sync");
		Reference.logger.info(this.scene);
		fromNBT(nbt);
	}

	public NBTTagCompound getSyncDataCompound() {
		Reference.logger.info("get side sync");
		return toNBT(new NBTTagCompound());
	}

	public void upload() {
		final NBTTagCompound nbt = toNBT(new NBTTagCompound());
		Reference.logger.info("upload");
		if (this.worldObj.isRemote) {
			PacketHandler.net.sendToServer(new SyncMessageEntityWorldPictures(getEntityId(), nbt));
		}
	}

	/*
	 * このMobが動いているときの音のファイルパスを返す. 引数のblockはMobの下にあるBlock.
	 */
	@Override
	protected void func_145780_a(final int x, final int y, final int z, final Block block) {
		playSound("mob.skeleton.step", 0.15F, 1.0F);
	}

	@Override
	public void applyEntityCollision(final Entity entity) {
		//this.moveEntity(entity.posX, entity.posY+1, entity.posZ);
		//Reference.logger.info("hit2");
	}

	@Override
	public void onCollideWithPlayer(final EntityPlayer p_70100_1_) {
		//Reference.logger.info("hit");
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	protected void entityInit() {
		//		this.dataWatcher.addObject(SyncSceneId, null);
	}

	//	public void setScene(Scene scene) {
	//		this.dataWatcher.updateObject(SyncSceneId, scene);
	//	}
	//
	//	public Scene getScene() {
	//		this.dataWatcher.getWatchableObjectByte(SyncSceneId);
	//	}

	@Override
	public boolean hitByEntity(final Entity entity)
	{
		return false;
	}

	@Override
	public boolean interactFirst(final EntityPlayer player)
	{
		if (this.worldObj.isRemote)
			PacketHandler.net.sendToServer(new RequestMessageEntityWorldPictures(getEntityId()));
		player.openGui(WorldPictures.instance, GuiEntityWorldPictures.GUI_ID, this.worldObj, getEntityId(), 0, 0);
		return false;
	}

	@Override
	public boolean attackEntityFrom(final DamageSource damage, final float strong) {
		if(!this.worldObj.isRemote) {
			Reference.logger.info("dead");
			setDead();
		}
		return true;
	}

	public NBTTagCompound toNBT(final NBTTagCompound nbt) {
		nbt.setTag("scene", Scene.toNBT(this.scene));
		return nbt;
	}

	public void fromNBT(final NBTTagCompound nbt) {
		final NBTTagList nbtscene = nbt.getTagList("scene", Constants.NBT.TAG_COMPOUND);
		this.scene = Scene.fromNBT(nbtscene);
	}

	@Override
	public void writeEntityToNBT(final NBTTagCompound nbt) {
		Reference.logger.info("saveNBT" + this.scene);
		if (this.scene != null)
			toNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(final NBTTagCompound nbt) {
		Reference.logger.info("loadNBT");
		fromNBT(nbt);
		Reference.logger.info(this.scene);
	}

}