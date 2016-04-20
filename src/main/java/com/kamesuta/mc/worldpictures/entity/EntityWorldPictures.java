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

	public final Scene scene = new Scene();

	public EntityWorldPictures(World world) {
		super(world);
		super.setSize(5, 5);
	}

	public void setSyncDataCompound(NBTTagCompound nbt) {
		Reference.logger.info("set side sync");
		Reference.logger.info(scene);
		this.fromNBT(nbt);
	}

	public NBTTagCompound getSyncDataCompound() {
		Reference.logger.info("get side sync");
		return this.toNBT(new NBTTagCompound());
	}

	public void upload(NBTTagCompound sync) {
		Reference.logger.info("upload");
		if (worldObj.isRemote) {
			PacketHandler.net.sendToServer(new SyncMessageEntityWorldPictures(this.getEntityId(), sync));
		} else {
			this.fromNBT(sync);
		}
	}

	/*
	 * このMobが動いているときの音のファイルパスを返す. 引数のblockはMobの下にあるBlock.
	 */
	@Override
	protected void func_145780_a(int x, int y, int z, Block block) {
		this.playSound("mob.skeleton.step", 0.15F, 1.0F);
	}

	@Override
	public void applyEntityCollision(Entity entity) {
		//this.moveEntity(entity.posX, entity.posY+1, entity.posZ);
		//Reference.logger.info("hit2");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer p_70100_1_) {
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
    public boolean hitByEntity(Entity entity)
    {
        return false;
    }

	@Override
    public boolean interactFirst(EntityPlayer player)
    {
		if (worldObj.isRemote)
			PacketHandler.net.sendToServer(new RequestMessageEntityWorldPictures(this.getEntityId()));
		player.openGui(WorldPictures.instance, GuiEntityWorldPictures.GUI_ID, this.worldObj, this.getEntityId(), 0, 0);
        return false;
    }

	@Override
	public boolean attackEntityFrom(DamageSource damage, float strong) {
		if(!this.worldObj.isRemote) {
			Reference.logger.info("dead");
			this.setDead();
		}
		return true;
	}

	public NBTTagCompound toNBT(NBTTagCompound nbt) {
		nbt.setTag("scene", this.scene.toNBT());
		return nbt;
	}

	public void fromNBT(NBTTagCompound nbt) {
		NBTTagList nbtscene = nbt.getTagList("scene", Constants.NBT.TAG_COMPOUND);
		this.scene.fromNBT(nbtscene);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		Reference.logger.info("saveNBT");
		if (this.scene != null)
			nbt.setTag("scene", this.scene.toNBT());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		Reference.logger.info("loadNBT");
		this.fromNBT(nbt);
	}

}