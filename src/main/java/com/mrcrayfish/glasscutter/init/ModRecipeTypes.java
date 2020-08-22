package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author justAm0dd3r
 */
public class ModRecipeTypes {
    public static RecipeType<GlasscuttingRecipe> GLASSCUTTING;

    public static void registerAll() {
        GLASSCUTTING = Registry.register(Registry.RECIPE_TYPE, new Identifier(Reference.MOD_ID, "glasscutting"), new RecipeType<GlasscuttingRecipe>() {
            @Override
            public String toString() {
                return "glasscutting";
            }
        });
    }
}
