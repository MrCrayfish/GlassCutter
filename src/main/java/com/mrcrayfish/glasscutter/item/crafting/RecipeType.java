package com.mrcrayfish.glasscutter.item.crafting;

import com.mrcrayfish.glasscutter.Reference;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

/**
 * Author: MrCrayfish
 */
public class RecipeType
{
    public static final IRecipeType<GlasscuttingRecipe> GLASSCUTTING = register(new ResourceLocation(Reference.MOD_ID, "glasscutting"));

    private static <T extends IRecipe<?>> net.minecraft.item.crafting.IRecipeType<T> register(final ResourceLocation resource)
    {
        return Registry.register(Registry.RECIPE_TYPE, resource, new IRecipeType<T>()
        {
            @Override
            public String toString()
            {
                return resource.toString();
            }
        });
    }
}
