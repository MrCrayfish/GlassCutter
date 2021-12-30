package com.mrcrayfish.glasscutter.item.crafting;

import com.mrcrayfish.glasscutter.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

/**
 * Author: MrCrayfish
 */
public class ModRecipeType {
    public static final RecipeType<GlasscuttingRecipe> GLASSCUTTING = register(new ResourceLocation(Reference.MOD_ID, "glasscutting"));

    private static <T extends Recipe<?>> RecipeType<T> register(final ResourceLocation resource)
    {
        return Registry.register(Registry.RECIPE_TYPE, resource, new RecipeType<T>()
        {
            @Override
            public String toString()
            {
                return resource.toString();
            }
        });
    }
}
