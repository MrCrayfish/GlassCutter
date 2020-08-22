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
        ModBlocks.getTranslucentBlocks().forEach((block -> BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent())));
    }
}
