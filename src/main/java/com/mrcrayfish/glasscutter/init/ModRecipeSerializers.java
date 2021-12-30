package com.mrcrayfish.glasscutter.init;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: MrCrayfish
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipeSerializers
{
    @SuppressWarnings("rawtypes")
    private static final List<RecipeSerializer> RECIPES = new ArrayList<>();

    public static final RecipeSerializer<GlasscuttingRecipe> GLASSCUTTING = register(Reference.MOD_ID + ":glasscutting", new GlasscuttingRecipe.Serializer<>(GlasscuttingRecipe::new));

    private static <T extends RecipeSerializer<? extends Recipe<?>>> T register(String name, T t)
    {
        t.setRegistryName(new ResourceLocation(name));
        RECIPES.add(t);
        return t;
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void registerItems(final RegistryEvent.Register<RecipeSerializer<?>> event)
    {
        IForgeRegistry<RecipeSerializer<?>> registry = event.getRegistry();
        RECIPES.forEach(item -> event.getRegistry().register(item));
        RECIPES.clear();
    }
}
