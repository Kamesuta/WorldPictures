package com.kamesuta.mc.worldpictures.item;

import com.kamesuta.mc.worldpictures.entity.EntitySample;
import com.kamesuta.mc.worldpictures.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class GenericItem extends Item {

	public GenericItem() {
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("genericItem");
		setTextureName(Reference.MODID.toLowerCase() + ":missing");
	}

	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_,
			int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (p_77648_3_.isRemote) {
			return true;
		} else {
			Block block = p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
			p_77648_4_ += Facing.offsetsXForSide[p_77648_7_];
			p_77648_5_ += Facing.offsetsYForSide[p_77648_7_];
			p_77648_6_ += Facing.offsetsZForSide[p_77648_7_];
			double d0 = 0.0D;

			if (p_77648_7_ == 1 && block.getRenderType() == 11) {
				d0 = 0.5D;
			}

			Entity entity = spawnCreature(p_77648_3_, p_77648_1_.getItemDamage(), (double) p_77648_4_ + 0.5D,
					(double) p_77648_5_ + d0, (double) p_77648_6_ + 0.5D);

			if (entity != null) {
				if (entity instanceof EntityLivingBase && p_77648_1_.hasDisplayName()) {
					((EntityLiving) entity).setCustomNameTag(p_77648_1_.getDisplayName());
				}

				if (!p_77648_2_.capabilities.isCreativeMode) {
					--p_77648_1_.stackSize;
				}
			}

			return true;
		}
	}

	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		if (p_77659_2_.isRemote) {
			return p_77659_1_;
		} else {
			MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(p_77659_2_, p_77659_3_,
					true);

			if (movingobjectposition == null) {
				return p_77659_1_;
			} else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					int i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (!p_77659_2_.canMineBlock(p_77659_3_, i, j, k)) {
						return p_77659_1_;
					}

					if (!p_77659_3_.canPlayerEdit(i, j, k, movingobjectposition.sideHit, p_77659_1_)) {
						return p_77659_1_;
					}

					if (p_77659_2_.getBlock(i, j, k) instanceof BlockLiquid) {
						Entity entity = spawnCreature(p_77659_2_, p_77659_1_.getItemDamage(), (double) i, (double) j,
								(double) k);

						if (entity != null) {
							if (entity instanceof EntityLivingBase && p_77659_1_.hasDisplayName()) {
								((EntityLiving) entity).setCustomNameTag(p_77659_1_.getDisplayName());
							}

							if (!p_77659_3_.capabilities.isCreativeMode) {
								--p_77659_1_.stackSize;
							}
						}
					}
				}

				return p_77659_1_;
			}
		}
	}

	/**
	 * Spawns the creature specified by the egg's type in the location specified
	 * by the last three parameters. Parameters: world, entityID, x, y, z.
	 */
	public static Entity spawnCreature(World world, int p_77840_1_, double p_77840_2_, double p_77840_4_,
			double p_77840_6_) {
		Entity entity = new EntitySample(world);
		world.spawnEntityInWorld(entity);

		return entity;
	}

}