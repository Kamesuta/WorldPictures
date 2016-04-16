package com.kamesuta.mc.worldpictures.entity;

import com.kamesuta.mc.worldpictures.reference.Reference;
import com.kamesuta.mc.worldpictures.vertex.Scene;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class EntitySample extends Entity {
	public Scene scene = new Scene();

	public EntitySample(World world, Scene scene) {
		super(world);
		super.setSize(5, 5);
		this.scene = scene;
	}

	// @Override
	// public String getLivingSound() { return /* MOBが生きている時の音のファイルパスを返す。 */ ; }
	//
	// @Override
	// public String getHurtSound() { return /* MOBがダメージを受けた時の音のファイルパスを返す。 */ ;
	// }
	//
	// @Override
	// public String getDeathSound() { return /* MOBが死亡した時の音のファイルパスを返す。*/ ; }

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

	}

	@Override
	public boolean attackEntityFrom(DamageSource damage, float strong) {
		Reference.logger.info("dead");
		this.setDead();
		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setTag("scene", this.scene.toNBT());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		NBTTagList nbtscene = nbt.getTagList("scene", Constants.NBT.TAG_COMPOUND);
		scene.fromNBT(nbtscene);
		if ((scene == null || scene.isEmpty()) || !(nbtscene.tagCount() > 0)) {
			this.setDead();
		}
	}

}