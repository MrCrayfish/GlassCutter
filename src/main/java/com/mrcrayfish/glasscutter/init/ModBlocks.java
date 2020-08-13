package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    private static final List<Block> BLOCKS = new ArrayList<>();
    private static final List<Item> ITEMS = new ArrayList<>();

    public static final Block GLASSCUTTER = register(new ResourceLocation(Reference.MOD_ID, "glasscutter"), new GlasscutterBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F)), ItemGroup.DECORATIONS);
    public static final Block GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "glass_stairs"), new GlassStairsBlock(Blocks.GLASS::getDefaultState, Block.Properties.from(Blocks.GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "glass_slab"), new GlassSlabBlock(Block.Properties.from(Blocks.GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WHITE_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "white_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.WHITE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "orange_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.ORANGE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "magenta_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.MAGENTA_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "light_blue_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIGHT_BLUE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "yellow_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.YELLOW_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "lime_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.LIME, Blocks.LIME_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIME_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "pink_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.PINK, Blocks.PINK_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.PINK_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "gray_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.GRAY_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "light_gray_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIGHT_GRAY_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "cyan_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.CYAN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "purple_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.PURPLE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "blue_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BLUE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "brown_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BROWN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "green_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.GREEN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "red_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.RED, Blocks.RED_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.RED_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_STAINED_GLASS_STAIRS = register(new ResourceLocation(Reference.MOD_ID, "black_stained_glass_stairs"), new StainedGlassStairsBlock(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BLACK_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block WHITE_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "white_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.WHITE, Blocks.WHITE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.WHITE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block ORANGE_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "orange_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.ORANGE, Blocks.ORANGE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.ORANGE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block MAGENTA_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "magenta_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.MAGENTA, Blocks.MAGENTA_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.MAGENTA_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_BLUE_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "light_blue_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIGHT_BLUE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block YELLOW_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "yellow_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.YELLOW, Blocks.YELLOW_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.YELLOW_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIME_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "lime_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.LIME, Blocks.LIME_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIME_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PINK_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "pink_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.PINK, Blocks.PINK_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.PINK_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GRAY_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "gray_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.GRAY, Blocks.GRAY_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.GRAY_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block LIGHT_GRAY_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "light_gray_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.LIGHT_GRAY_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block CYAN_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "cyan_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.CYAN, Blocks.CYAN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.CYAN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block PURPLE_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "purple_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.PURPLE, Blocks.PURPLE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.PURPLE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLUE_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "blue_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.BLUE, Blocks.BLUE_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BLUE_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BROWN_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "brown_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.BROWN, Blocks.BROWN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BROWN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block GREEN_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "green_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.GREEN, Blocks.GREEN_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.GREEN_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block RED_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "red_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.RED, Blocks.RED_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.RED_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);
    public static final Block BLACK_STAINED_GLASS_SLAB = register(new ResourceLocation(Reference.MOD_ID, "black_stained_glass_slab"), new StainedGlassSlabBlock(DyeColor.BLACK, Blocks.BLACK_STAINED_GLASS::getDefaultState, Block.Properties.from(Blocks.BLACK_STAINED_GLASS)), ItemGroup.BUILDING_BLOCKS);

    private static Block register(ResourceLocation key, Block block, ItemGroup group)
    {
        block.setRegistryName(key);
        BLOCKS.add(block);
        BlockItem item = new BlockItem(block, new Item.Properties().group(group));
        item.setRegistryName(key);
        ITEMS.add(item);
        return block;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();
        BLOCKS.forEach(registry::register);
        BLOCKS.clear();
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();
        ITEMS.forEach(registry::register);
        ITEMS.clear();
    }
}
