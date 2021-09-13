package com.mrcrayfish.glasscutter.client;

import com.mrcrayfish.glasscutter.client.gui.screen.inventory.GlasscutterScreen;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

/**
 * Author: MrCrayfish
 */
public class ClientHandler
{
    public static void setup()
    {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASSCUTTER, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS_STAIRS, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS_SLAB, RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGENTA_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_BLUE_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIME_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAY_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_GRAY_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CYAN_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BROWN_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_STAINED_GLASS_STAIRS, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WHITE_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGENTA_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_BLUE_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.YELLOW_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIME_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRAY_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LIGHT_GRAY_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CYAN_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PURPLE_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BROWN_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_STAINED_GLASS_SLAB, RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACK_STAINED_GLASS_SLAB, RenderType.translucent());
        MenuScreens.register(ModContainers.GLASSCUTTER, GlasscutterScreen::new);
    }
}
