package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.client.gui.screen.inventory.GlasscutterScreen;
import com.mrcrayfish.glasscutter.screen_handler.GlasscutterScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

/**
 * @author justAm0dd3r
 */
public class ModScreenHandlers {
    public static ScreenHandlerType<GlasscutterScreenHandler> GLASSCUTTER;

    public static void registerAll() {
        GLASSCUTTER = ScreenHandlerRegistry.registerSimple(new Identifier(Reference.MOD_ID, "glasscutter"),
                GlasscutterScreenHandler::new);
        ScreenRegistry.register(GLASSCUTTER, GlasscutterScreen::new);
    }
}
