package com.kamesuta.mc.worldpictures.entity;

import java.util.List;

import com.kamesuta.mc.worldpictures.WorldPictures;
import com.kamesuta.mc.worldpictures.component.Component;
import com.kamesuta.mc.worldpictures.component.util.ComponentNBT;
import com.kamesuta.mc.worldpictures.gui.GuiEntityWorldPictures;
import com.kamesuta.mc.worldpictures.handler.PacketHandler;
import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityWorldPictures extends Entity {
	public static final int SyncSceneId = 20;
	public static final int SyncTextureId = 21;

	private Component component;
	public transient long currentTimeOffset;

	public EntityWorldPictures(final World world) {
		super(world);
		super.setSize(15, 15);

	}

	public Component getComponent() {
		return this.component;
	}

	public void setComponent(final Component component) {
		this.component = component;
		//if (!this.worldObj.isRemote) {
		final AxisAlignedBB b = component.bounds;
		setSize((float) Math.max(b.maxX - b.minX, b.maxZ - b.minZ), (float) (b.maxY - b.minY));
		setPosition((b.maxX + b.minX) / 2, b.minY, (b.maxZ + b.minZ) / 2);
		Reference.logger.info(String.format("x:%s y:%s z:%s w:%s h:%s b:%s", this.posX, this.posY, this.posZ, this.width, this.height, b));

		//moveEntity((b.maxX + b.minX) / 2, b.minY, (b.maxZ + b.minZ) / 2);
		//}
	}

	public void setSyncDataCompound(final NBTTagCompound nbt) {
		Reference.logger.info("set side sync");
		Reference.logger.info(this.component);
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

	@Override
	public float getCollisionBorderSize() {
		return 0.1f;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		//		if (this.component != null)
		//			return this.component.bounds;
		return null;
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		//if (!this.worldObj.isRemote)
		{
			final List<?> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox);
			for (final Object o : entities) {
				final Entity entity = (Entity)o;
				entity.setPosition(entity.posX, this.boundingBox.maxY+entity.yOffset, entity.posZ);
			}
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
		//entity.addVelocity(0, 1, 0);
		entity.applyEntityCollision(this);
		//super.applyEntityCollision(entity);
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
		Reference.logger.info("hit");
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
		if (this.component != null)
			nbt.setTag("component", ComponentNBT.ComponentToNBT(this.component));
		return nbt;
	}

	public void fromNBT(final NBTTagCompound nbt) {
		final Component component = ComponentNBT.ComponentFromNBT(nbt.getCompoundTag("component"));
		if (component != null)
			setComponent(component);
	}

	@Override
	public void writeEntityToNBT(final NBTTagCompound nbt) {
		Reference.logger.info("saveNBT: " + getEntityId() + " this:" + this);
		if (this.component != null)
			toNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(final NBTTagCompound nbt) {
		Reference.logger.info("loadNBT");
		fromNBT(nbt);
		Reference.logger.info(this.component);
	}

}