package com.Glitch_Byte.AuraMod;

import java.util.List;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAuraOrb extends Item
{
	private static String name = "orb";
	private static String[] metaNames = { "aura", "auram" };

	public ItemAuraOrb()
	{
		GameRegistry.registerItem(this, name);
		setUnlocalizedName(AuraMod.MODID + "_" + name);

		setHasSubtypes(true);
		setCreativeTab(CreativeTabs.tabMaterials);
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return super.getUnlocalizedName() + "." + metaNames[par1ItemStack.getItemDamage()];
	}

	public static String getNameFromDamage(int damage)
	{
		return name + metaNames[damage];
	}

	public static void registerVariants()
	{
		String[] variantNames = new String[metaNames.length];
		for (int i = 0; i < metaNames.length; i++)
		{
			variantNames[i] = AuraMod.MODID + ":" + getNameFromDamage(i);
		}
		ModelBakery.addVariantName(AuraMod.orb, variantNames);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		for (int i = 0; i < metaNames.length; ++i)
		{
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}
}