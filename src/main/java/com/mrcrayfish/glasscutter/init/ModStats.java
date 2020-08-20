package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * Mod stats holder class.
 * @author justAm0dd3r
 */
public class ModStats {
    public static final Identifier INTERACT_WITH_GLASSCUTER = register("interact_with_glasscutter", StatFormatter.DEFAULT);

    @SuppressWarnings("SameParameterValue")
    private static Identifier register(String string, StatFormatter statFormatter) {
        Identifier identifier = new Identifier(Reference.MOD_ID, string);
        Registry.register(Registry.CUSTOM_STAT, string, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, statFormatter);
        return identifier;
    }
}
