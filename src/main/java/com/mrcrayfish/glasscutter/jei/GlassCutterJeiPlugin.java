package com.mrcrayfish.glasscutter.jei;

import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.DyeColor;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class GlassCutterJeiPlugin implements IModPlugin {
    private GlasscuttingRecipeCategory glasscuttingCategory;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(this.glasscuttingCategory = new GlasscuttingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ErrorUtil.checkNotNull(this.glasscuttingCategory, "glasscuttingCategory");
        registration.addRecipes(getGlasscuttingRecipes(), new ResourceLocation(Reference.MOD_ID, "glasscutting"));
        LOGGER.debug("Added JEI Recipes.");
    }

    private List<GlasscuttingRecipe> getGlasscuttingRecipes() {
        List<GlasscuttingRecipe> recipes = new ArrayList<>();
        for (String type : new String[]{"stairs", "slab", "pane"}) {
            for (DyeColor dye : DyeColor.values()) {
                String path = "data/" + Reference.MOD_ID + "/recipes/cut_" + dye.toString()
                            + "_stained_glass_to_" + dye.toString() + "_stained_glass_" + type + ".json";
                ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, "cut_" + dye.toString()
                        + "_stained_glass_to_" + dye.toString() + "_stained_glass_" + type + ".json");
                addRecipe(recipes, resourceLocation, path);
            }
            String path = "data/" + Reference.MOD_ID + "/recipes/cut_glass_to_glass_" + type + ".json";
            ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, "cut_glass_to_glass_" + type + ".json");
            addRecipe(recipes, resourceLocation, path);
        }
        String path = "data/" + Reference.MOD_ID + "/recipes/cut_glass_to_glass_bottle.json";
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, "cut_glass_to_glass_bottle.json");
        addRecipe(recipes, resourceLocation, path);
        return recipes;
    }

    private JsonObject getObjectFromPath(String path) {
        String jsonString;
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
            jsonString = CharStreams.toString(new InputStreamReader(stream));
        }
        catch (IOException e) {
            LOGGER.error("Error while trying to get json object from ResourceLocation.", e);
            return null;
        }
        return new JsonParser().parse(jsonString).getAsJsonObject();
    }

    private void addRecipe(List<GlasscuttingRecipe> recipes, ResourceLocation resourceLocation, String path) {
        JsonObject object = getObjectFromPath(path);
        if (object == null) return;

        IRecipe<?> recipe = RecipeManager.deserializeRecipe(resourceLocation, object);
        if (!(recipe instanceof GlasscuttingRecipe)) {
            LOGGER.error("Wrong recipe type.");
            return;
        }
        recipes.add((GlasscuttingRecipe)recipe);
    }
}
