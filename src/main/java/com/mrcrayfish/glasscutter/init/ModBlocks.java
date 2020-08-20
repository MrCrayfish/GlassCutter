package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.block.*;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: MrCrayfish
 *
 * <strong>Do not change the field names!</strong>
 */
public class ModBlocks
{
    public static Block GLASSCUTTER;
    public static Block GLASS_STAIRS;
    public static Block GLASS_SLAB;
    public static Block WHITE_STAINED_GLASS_STAIRS;
    public static Block ORANGE_STAINED_GLASS_STAIRS;
    public static Block MAGENTA_STAINED_GLASS_STAIRS;
    public static Block LIGHT_BLUE_STAINED_GLASS_STAIRS;
    public static Block YELLOW_STAINED_GLASS_STAIRS;
    public static Block LIME_STAINED_GLASS_STAIRS;
    public static Block PINK_STAINED_GLASS_STAIRS;
    public static Block GRAY_STAINED_GLASS_STAIRS;
    public static Block LIGHT_GRAY_STAINED_GLASS_STAIRS;
    public static Block CYAN_STAINED_GLASS_STAIRS;
    public static Block PURPLE_STAINED_GLASS_STAIRS;
    public static Block BLUE_STAINED_GLASS_STAIRS;
    public static Block BROWN_STAINED_GLASS_STAIRS;
    public static Block GREEN_STAINED_GLASS_STAIRS;
    public static Block RED_STAINED_GLASS_STAIRS;
    public static Block BLACK_STAINED_GLASS_STAIRS;
    public static Block WHITE_STAINED_GLASS_SLAB;
    public static Block ORANGE_STAINED_GLASS_SLAB;
    public static Block MAGENTA_STAINED_GLASS_SLAB;
    public static Block LIGHT_BLUE_STAINED_GLASS_SLAB;
    public static Block YELLOW_STAINED_GLASS_SLAB;
    public static Block LIME_STAINED_GLASS_SLAB;
    public static Block PINK_STAINED_GLASS_SLAB;
    public static Block GRAY_STAINED_GLASS_SLAB;
    public static Block LIGHT_GRAY_STAINED_GLASS_SLAB;
    public static Block CYAN_STAINED_GLASS_SLAB;
    public static Block PURPLE_STAINED_GLASS_SLAB;
    public static Block BLUE_STAINED_GLASS_SLAB;
    public static Block BROWN_STAINED_GLASS_SLAB;
    public static Block GREEN_STAINED_GLASS_SLAB;
    public static Block RED_STAINED_GLASS_SLAB;
    public static Block BLACK_STAINED_GLASS_SLAB;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Try to register the blocks via reflection. (not that repetitive). Trows tons of reflection exceptions.
     * @throws NoSuchFieldException when there are no fields found. probably someone has renamed the fields...
     * @throws NoSuchMethodException when a method wasn't found.
     * @throws InstantiationException when an error occurs during the {@link StainedGlassSlabBlock} or {@link StainedGlassStairsBlock} constructor call.
     */
    public static void registerAll() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        // Glasscutter
        GLASSCUTTER = register("glasscutter", new GlasscutterBlock(AbstractBlock.Settings.of(Material.STONE).strength(3.5F)), ItemGroup.DECORATIONS);
        // Glass Stairs
        GLASS_STAIRS = register("glass_stairs", new GlassStairsBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));
        // Glass Slab
        GLASS_SLAB = register("glass_slab", new GlassSlabBlock(AbstractBlock.Settings.copy(Blocks.GLASS)));

        for (DyeColor color: DyeColor.values()) {
            String[] types = new String[] { "stairs", "slab" };
            for (String type : types) {
                // Get the field.
                Field blockField = ModBlocks.class.getField(color.getName().toUpperCase() + "_STAINED_GLASS_" + type.toUpperCase());
                // Get the StainedGlassStairsBlock or the StainedGlassSlabBlock constructor.
                Constructor<?> constructor = type.equals("stairs") ? StainedGlassStairsBlock.class.getConstructor(DyeColor.class, BlockState.class,
                        AbstractBlock.Settings.class) : StainedGlassSlabBlock.class.getConstructor(DyeColor.class, BlockState.class, AbstractBlock.Settings.class);
                // Get the corresponding block in the Blocks class.
                Block block = (Block) Blocks.class.getDeclaredField(color.getName().toUpperCase() + "_STAINED_GLASS").get(Blocks.class);
                // Set the field to the (registered) block.
                blockField.set(ModBlocks.class, register(color.getName() + "_stained_glass_" + type,
                        (Block) constructor.newInstance(color, block.getDefaultState(), AbstractBlock.Settings.copy(block))));
            }
        }
    }

    /**
     * Calls the {@link #registerAll()} method and catches the reflection exceptions when thrown.
     */
    public static void tryRegisterAll() {
        try {
            registerAll();
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            LOGGER.error("Error while trying to register blocks via reflection.", e);
        }
    }

    /**
     * Register a block and the corresponding block item.
     * @param name the name of the block, e.g. for {@link #WHITE_STAINED_GLASS_SLAB} "white_stained_glass_slab"
     * @param block the actual {@link ItemGroup}
     * @param group the {@link ItemGroup}
     * @return the (registered) block
     */
    private static Block register(final String name, final Block block, final ItemGroup group) {
        Registry.register(Registry.ITEM, Reference.MOD_ID + ":" + name, new BlockItem(block, new Item.Settings().group(group)));
        return Registry.register(Registry.BLOCK, Reference.MOD_ID + ":" + name, block);
    }

    /**
     * Same as {@link #register(String, Block, ItemGroup)}. Just with the default item group: {@link ItemGroup#BUILDING_BLOCKS}.
     */
    private static Block register(final String name, final Block block) {
        return register(name, block, ItemGroup.BUILDING_BLOCKS);
    }
}
