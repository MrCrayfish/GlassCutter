package com.mrcrayfish.glasscutter.item.crafting;

import com.google.gson.JsonObject;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModRecipeSerializers;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

/**
 * Author: MrCrayfish
 */
public class GlasscuttingRecipe extends SingleItemRecipe
{
    public GlasscuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack stack)
    {
        super(ModRecipeType.GLASSCUTTING, ModRecipeSerializers.GLASSCUTTING, id, group, ingredient, stack);
    }

    @Override
    public boolean matches(Container inventory, Level world)
    {
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack getToastSymbol()
    {
        return new ItemStack(ModBlocks.GLASSCUTTER);
    }

    public static class Serializer<T extends GlasscuttingRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<T>
    {
        private final GlasscuttingRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(GlasscuttingRecipe.Serializer.IRecipeFactory<T> factory)
        {
            this.factory = factory;
        }

        @Override
        public T fromJson(ResourceLocation recipeId, JsonObject json)
        {
            String group = GsonHelper.getAsString(json, "group", "");
            Ingredient ingredient;
            if(GsonHelper.isArrayNode(json, "ingredient"))
            {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "ingredient"));
            }
            else
            {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
            }
            String result = GsonHelper.getAsString(json, "result");
            int count = GsonHelper.getAsInt(json, "count");
            ItemStack stack = new ItemStack(Registry.ITEM.get(new ResourceLocation(result)), count);
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public T fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
        {
            String group = buffer.readUtf(32767);
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack stack = buffer.readItem();
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, T recipe)
        {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }

        public interface IRecipeFactory<T extends GlasscuttingRecipe>
        {
            T create(ResourceLocation id, String group, Ingredient ingredient, ItemStack stack);
        }
    }
}
