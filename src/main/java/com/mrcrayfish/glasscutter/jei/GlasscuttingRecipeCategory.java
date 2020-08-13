package com.mrcrayfish.glasscutter.jei;

import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.config.Constants;
import mezz.jei.plugins.vanilla.stonecutting.StoneCuttingRecipeCategory;
import mezz.jei.recipes.ExtendableRecipeCategoryHelper;
import mezz.jei.util.Translator;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class GlasscuttingRecipeCategory implements IExtendableRecipeCategory<GlasscuttingRecipe, ICraftingCategoryExtension> {
    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    private static final ResourceLocation CATEGORY_GLASSCUTTING = new ResourceLocation(Reference.MOD_ID, "glasscutting");

    public static final int width = 82;
    public static final int height = 34;

    private final IDrawable background;
    private final IDrawable icon;
    private final String localizedName;

    private final ExtendableRecipeCategoryHelper<IRecipe<?>, ICraftingCategoryExtension> extendableHelper = new ExtendableRecipeCategoryHelper<>(GlasscuttingRecipe.class);
    public GlasscuttingRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation location = Constants.RECIPE_GUI_VANILLA;
        background = guiHelper.createDrawable(location, 0, 220, width, height);
        icon = guiHelper.createDrawableIngredient(new ItemStack(Blocks.STONECUTTER));
        localizedName = Translator.translateToLocal("gui.jei.category.glassCutter");
    }

    @Override
    public <R extends GlasscuttingRecipe> void addCategoryExtension(Class<? extends R> recipeClass, Function<R, ? extends ICraftingCategoryExtension> extensionFactory) {
        extendableHelper.addRecipeExtensionFactory(recipeClass, extensionFactory);
    }

    @Override
    public ResourceLocation getUid() {
        return CATEGORY_GLASSCUTTING;
    }

    @Override
    public Class<? extends GlasscuttingRecipe> getRecipeClass() {
        return GlasscuttingRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(GlasscuttingRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, GlasscuttingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(inputSlot, true, 0, 8);
        guiItemStacks.init(outputSlot, false, 60, 8);

        guiItemStacks.set(ingredients);
    }
}