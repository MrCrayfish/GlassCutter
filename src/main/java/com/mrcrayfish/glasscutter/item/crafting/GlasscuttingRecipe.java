package com.mrcrayfish.glasscutter.item.crafting;

import com.google.gson.JsonObject;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModRecipeSerializers;
import com.mrcrayfish.glasscutter.init.ModRecipeTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.CuttingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

/**
 * @author justAm0dd3r
 */
public class GlasscuttingRecipe extends CuttingRecipe {
   public GlasscuttingRecipe(Identifier id, String group, Ingredient input, ItemStack output) {
      super(ModRecipeTypes.GLASSCUTTING, ModRecipeSerializers.GLASSCUTTING, id, group, input, output);
   }

   @Override
   public boolean matches(Inventory inv, World world) {
      return this.input.test(inv.getStack(0));
   }

   @Override
   @Environment(EnvType.CLIENT)
   public ItemStack getRecipeKindIcon() {
      return new ItemStack(ModBlocks.GLASSCUTTER);
   }

   public static class Serializer implements RecipeSerializer<GlasscuttingRecipe> {
      private final RecipeFactory<GlasscuttingRecipe> recipeFactory;

      public Serializer(RecipeFactory<GlasscuttingRecipe> recipeFactory) {
         this.recipeFactory = recipeFactory;
      }

      public GlasscuttingRecipe read(Identifier identifier, JsonObject jsonObject) {
         String string = JsonHelper.getString(jsonObject, "group", "");
         Ingredient ingredient2;
         if (JsonHelper.hasArray(jsonObject, "ingredient")) {
            ingredient2 = Ingredient.fromJson(JsonHelper.getArray(jsonObject, "ingredient"));
         } else {
            ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "ingredient"));
         }

         String string2 = JsonHelper.getString(jsonObject, "result");
         int i = JsonHelper.getInt(jsonObject, "count");
         ItemStack itemStack = new ItemStack(Registry.ITEM.get(new Identifier(string2)), i);
         return this.recipeFactory.create(identifier, string, ingredient2, itemStack);
      }

      public GlasscuttingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
         String string = packetByteBuf.readString(32767);
         Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
         ItemStack itemStack = packetByteBuf.readItemStack();
         return this.recipeFactory.create(identifier, string, ingredient, itemStack);
      }

      public void write(PacketByteBuf packetByteBuf, GlasscuttingRecipe recipe) {
         packetByteBuf.writeString(recipe.group);
         recipe.input.write(packetByteBuf);
         packetByteBuf.writeItemStack(recipe.output);
      }

      public interface RecipeFactory<GlasscuttingRecipe> {
         GlasscuttingRecipe create(Identifier identifier, String string, Ingredient ingredient, ItemStack itemStack);
      }
   }

}