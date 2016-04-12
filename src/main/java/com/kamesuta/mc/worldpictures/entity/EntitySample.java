package com.kamesuta.mc.worldpictures.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntitySample extends EntityLiving {
	public EntitySample(World world) {
		super(world);
		super.setSize(5,5);
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
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	public Item getDropItem() {
		return Items.ender_pearl;
	}
}