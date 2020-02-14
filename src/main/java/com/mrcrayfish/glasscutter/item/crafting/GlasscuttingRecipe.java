package com.mrcrayfish.glasscutter.item.crafting;

import com.google.gson.JsonObject;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModRecipeSerializers;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

/**
 * Author: MrCrayfish
 */
public class GlasscuttingRecipe extends SingleItemRecipe
{
    public GlasscuttingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack stack)
    {
        super(RecipeType.GLASSCUTTING, ModRecipeSerializers.GLASSCUTTING, id, group, ingredient, stack);
    }

    @Override
    public boolean matches(IInventory inventory, World world)
    {
        return this.ingredient.test(inventory.getStackInSlot(0));
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(ModBlocks.GLASSCUTTER);
    }

    public static class Serializer<T extends GlasscuttingRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>
    {
        private final GlasscuttingRecipe.Serializer.IRecipeFactory<T> factory;

        public Serializer(GlasscuttingRecipe.Serializer.IRecipeFactory<T> factory)
        {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json)
        {
            String group = JSONUtils.getString(json, "group", "");
            Ingredient ingredient;
            if(JSONUtils.isJsonArray(json, "ingredient"))
            {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonArray(json, "ingredient"));
            }
            else
            {
                ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            }
            String result = JSONUtils.getString(json, "result");
            int count = JSONUtils.getInt(json, "count");
            ItemStack stack = new ItemStack(Registry.ITEM.getOrDefault(new ResourceLocation(result)), count);
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public T read(ResourceLocation recipeId, PacketBuffer buffer)
        {
            String group = buffer.readString(32767);
            Ingredient ingredient = Ingredient.read(buffer);
            ItemStack stack = buffer.readItemStack();
            return this.factory.create(recipeId, group, ingredient, stack);
        }

        @Override
        public void write(PacketBuffer buffer, T recipe)
        {
            buffer.writeString(recipe.group);
            recipe.ingredient.write(buffer);
            buffer.writeItemStack(recipe.result);
        }

        public interface IRecipeFactory<T extends GlasscuttingRecipe>
        {
            T create(ResourceLocation id, String group, Ingredient ingredient, ItemStack stack);
        }
    }
}
