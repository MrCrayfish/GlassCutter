package com.mrcrayfish.glasscutter;

import com.mrcrayfish.glasscutter.client.ClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Author: MrCrayfish
 */
@Mod(Reference.MOD_ID)
public class GlassCutter
{
    public GlassCutter()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onSetupClient);
    }

    private void onSetupClient(FMLClientSetupEvent event)
    {
        ClientHandler.setup();
    }
}
