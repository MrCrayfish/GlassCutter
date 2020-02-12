package com.mrcrayfish.glasscutter;

import com.mrcrayfish.glasscutter.init.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

/**
 * Author: MrCrayfish
 */
public class ClientHandler
{
    public static void setup()
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.GLASS_CUTTER, RenderType.func_228643_e_());
    }
}
