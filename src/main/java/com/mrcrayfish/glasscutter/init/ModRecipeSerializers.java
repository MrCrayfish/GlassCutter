package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipeSerializers {
    public static RecipeSerializer<GlasscuttingRecipe> GLASSCUTTING;

    public static void registerAll() {
        GLASSCUTTING = Registry.register(Registry.RECIPE_SERIALIZER,
                new Identifier(Reference.MOD_ID, "glasscutting"), new GlasscuttingRecipe.Serializer(GlasscuttingRecipe::new));
    }
}
