package com.Glitch_Byte.AuraMod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemAuraStone extends ItemBlock
{

	public ItemAuraStone(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name;
		switch (itemstack.getItemDamage())
		{
		case 0:
		{
			name = "ore";
			break;
		}
		default:
			name ="ore";
		}
		return getUnlocalizedName() + "." + name;
	}
	@Override
	public int getMetadata(int meta)
	{
			return meta;
	}
}
