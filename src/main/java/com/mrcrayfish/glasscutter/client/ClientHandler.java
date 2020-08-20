package com.mrcrayfish.glasscutter.client;

import com.mrcrayfish.glasscutter.init.ModBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

/**
 * Author: MrCrayfish
 */
public class ClientHandler
{
    public static void setup()
    {
        // Make the slabs transparency render correctly.
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASSCUTTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_STAIRS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_SLAB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WHITE_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAGENTA_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YELLOW_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAY_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CYAN_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BROWN_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACK_STAINED_GLASS_STAIRS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WHITE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAGENTA_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YELLOW_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAY_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CYAN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BROWN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACK_STAINED_GLASS_SLAB, RenderLayer.getTranslucent());
    }
}
