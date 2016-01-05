package com.Glitch_Byte.AuraMod;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class BlockAuramPlant extends BlockBush implements IGrowable
{
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 2);
	
	String name = "auramPlant";

	public BlockAuramPlant()
	{
		super(Material.plants);
		GameRegistry.registerBlock(this,name);
		setUnlocalizedName(AuraMod.MODID + "_" + name);
		

		setStepSound(soundTypeGrass);
		setCreativeTab(null);
		setHardness(0.0F);
		setBlockBounds(0F, 0.0F, 0F, 1F, 0.25F, 1F);
	}

	public String getName()
	{
		return name;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(AGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return (Integer) state.getValue(AGE);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, AGE);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.getLightFromNeighbors(pos.up()) >= 9)
		{
			int i = (Integer) state.getValue(AGE);

			if (i < 2)
			{
				if (rand.nextInt(5) == 0)
				{
					worldIn.setBlockState(pos, state.withProperty(AGE, i + 1), 2);
				}
			}
		}
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		return (Integer) state.getValue(AGE) < 2;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		if (rand.nextInt(3) == 0)
		{
			int newAge = (Integer) state.getValue(AGE) + rand.nextInt(3);

			if (newAge > 2)
			{
				newAge = 2;
			}

			worldIn.setBlockState(pos, state.withProperty(AGE, newAge), 2);
		}
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		if (block == Blocks.dirt || block == Blocks.grass)
			return true;
		else
			return false;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if ((Integer) state.getValue(AGE) >= 2)
		{
			for (int i = 0; i < 3 + fortune; ++i)
			{
				if (rand.nextInt(15) <= 7)
				{
					ret.add(new ItemStack(AuraMod.orb, 1, 1));
				}
			}
		}
		return ret;
	}
}