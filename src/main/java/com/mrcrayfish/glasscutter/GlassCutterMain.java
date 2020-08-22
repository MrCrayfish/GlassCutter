package com.mrcrayfish.glasscutter;

import com.mrcrayfish.glasscutter.client.ClientHandler;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModRecipeSerializers;
import com.mrcrayfish.glasscutter.init.ModRecipeTypes;
import com.mrcrayfish.glasscutter.init.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

/**
 * Main Mod Class.
 * @author justAm0dd3r
 */
public class GlassCutterMain implements ModInitializer
{
    @Override
    public void onInitialize() {
        ModBlocks.tryRegisterAll();
        ModScreenHandlers.registerAll();
        ModRecipeTypes.registerAll();
        ModRecipeSerializers.registerAll();

        ClientHandler.setup();
    }
}
