package com.mrcrayfish.glasscutter.client;

import com.mrcrayfish.glasscutter.client.gui.screen.inventory.GlasscutterScreen;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

/**
 * Author: MrCrayfish
 */
public class ClientHandler
{
    public static void setup()
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.GLASSCUTTER, RenderType.cutout());
        ScreenManager.registerFactory(ModContainers.GLASSCUTTER, GlasscutterScreen::new);
    }
}
