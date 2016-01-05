package com.Glitch_Byte.AuraMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = AuraMod.MODID, version = AuraMod.VERSION)
public class AuraMod
{
	public static final String MODID = "glitch_byte_auramod";
	public static final String VERSION = "1.0";

	//Blocks
	public static Block AuraStone;
	public static Block auramPlant;
	
	//Items
	public static Item orb;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Blocks
		auramPlant = new BlockAuramPlant();
		AuraStone = new BlockAuraStone();
		//Items
		orb = new ItemAuraOrb();
		

	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if(event.getSide() == Side.CLIENT)
		{
			ItemAuraOrb.registerVariants();
			BlockAuraStone.BlockType.registerVariants();
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();


			BlockAuraStone.BlockType.registerRenders();
			

			renderItem.getItemModelMesher().register(orb, 0, new ModelResourceLocation(MODID + ":" + ((ItemAuraOrb) orb).getNameFromDamage(0), "inventory"));
			renderItem.getItemModelMesher().register(orb, 1, new ModelResourceLocation(MODID + ":" + ((ItemAuraOrb) orb).getNameFromDamage(1), "inventory"));
			renderItem.getItemModelMesher().register(Item.getItemFromBlock(auramPlant), 0 , new ModelResourceLocation(MODID + ":" + (((BlockAuramPlant) auramPlant).getName()), "inventory"));
			
		}
	}
}