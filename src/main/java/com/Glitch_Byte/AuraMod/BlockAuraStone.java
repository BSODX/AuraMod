package com.Glitch_Byte.AuraMod;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAuraStone extends Block
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockAuraStone.BlockType.class);

	String name = "AuraStone";

	public BlockAuraStone()
	{
		super(Material.rock);
		GameRegistry.registerBlock(this, ItemAuraStone.class, name);
		setUnlocalizedName(AuraMod.MODID + "_" + name);

		setHardness(2F);
		setResistance(5F);
		setStepSound(soundTypeStone);
		setHarvestLevel("pickaxe", 2);

		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (state.getValue(VARIANT) == BlockType.AURAORE)
			return AuraMod.orb;
		else
			return super.getItemDropped(state, rand, fortune);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < BlockAuraStone.BlockType.values().length; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}

	public String getName()
	{
		return name;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockAuraStone.BlockType.byID(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockAuraStone.BlockType) state.getValue(VARIANT)).getID();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, VARIANT);
	}
	
	public static enum BlockType implements IStringSerializable
	{
		AURAORE(0, "AuraOre");

		private final int id;
		private final String name;

		private BlockType(int id, String name)
		{
			this.id = id;
			this.name = name;
		}

		static
		{
			BlockAuraStone.BlockType[] values = values();

			for (BlockType type : values)
			{
				values()[type.getID()] = type;
			}
		}

		@Override
		public String getName()
		{
			return name;
		}

		public int getID()
		{
			return id;
		}

		public static BlockAuraStone.BlockType byID(int id)
		{
			if (id < 0 || id >= values().length)
			{
				id = 0;
			}

			return values()[id];
		}

		private String getResouceLocation()
		{
			return AuraMod.MODID + ":" + name;
		}

		public static void registerVariants()
		{
			String[] variants = new String[values().length];

			for (int i = 0; i < values().length; i++)
			{
				variants[i] = values()[i].getResouceLocation();
			}

			ModelBakery.addVariantName(Item.getItemFromBlock(AuraMod.AuraStone), variants);
		}

		public static void registerRenders()
		{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();

			for (int i = 0; i < values().length; i++)
			{
				renderItem.getItemModelMesher().register(Item.getItemFromBlock(AuraMod.AuraStone), i, new ModelResourceLocation(values()[i].getResouceLocation(), "inventory"));
			}
		}
	}
}