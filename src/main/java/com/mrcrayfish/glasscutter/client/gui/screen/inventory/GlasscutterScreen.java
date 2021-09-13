package com.mrcrayfish.glasscutter.client.gui.screen.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.inventory.container.GlasscutterContainer;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

/**
 * Author: MrCrayfish
 */
@OnlyIn(Dist.CLIENT)
public class GlasscutterScreen extends AbstractContainerScreen<GlasscutterContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/glasscutter.png");
    private float sliderProgress;
    private boolean clickOnScrollBar;
    private int recipeIndexOffset;
    private boolean hasItemsInInputSlot;

    public GlasscutterScreen(GlasscutterContainer container, Inventory playerInventory, Component titleIn)
    {
        super(container, playerInventory, titleIn);
        container.setInventoryUpdateListener(this::onInventoryUpdate);
    }


    @Override
    public void render(PoseStack poses, int mouseX, int mouseY, float partialTicks) {
        super.render(poses, mouseX, mouseY, partialTicks);
        this.renderTooltip(poses, mouseX, mouseY); // Render Tooltip
    }

    protected void renderBg(PoseStack poses, float partialTicks, int mouseX, int mouseY) {
        this.renderBackground(poses);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int guiLeft = this.leftPos;
        int guiTop = this.topPos;

        this.blit(poses, guiLeft, guiTop, 0, 0, this.imageWidth, this.imageHeight);
        int scrollBarPos = (int) (41.0F * this.sliderProgress);

        this.blit(poses, guiLeft + 119, guiTop + 15 + scrollBarPos,
                176 + (this.canScroll() ? 0 : 12), 0, 12, 15);
        int offsetX = this.leftPos + 52;
        int offsetY = this.topPos + 14;
        int indexOffset = this.recipeIndexOffset + 12;
        this.drawRecipeBackgrounds(poses, mouseX, mouseY, offsetX, offsetY, indexOffset);
        this.drawRecipeItems(offsetX, offsetY, indexOffset);
    }

    private void drawRecipeBackgrounds(PoseStack poses, int mouseX, int mouseY, int offsetX, int offsetY, int indexOffset)
    {
        for(int i = this.recipeIndexOffset; i < indexOffset && i < this.menu.getRecipeListSize(); ++i)
        {
            int index = i - this.recipeIndexOffset;
            int recipeOffsetX = offsetX + index % 4 * 16;
            int recipeOffsetY = offsetY + (index / 4) * 18 + 2;
            int vOffset = this.imageHeight;
            if(i == this.menu.getSelectedRecipe())
            {
                vOffset += 18;
            }
            else if(mouseX >= recipeOffsetX && mouseY >= recipeOffsetY && mouseX < recipeOffsetX + 16 && mouseY < recipeOffsetY + 18)
            {
                vOffset += 36;
            }

            this.blit(poses, recipeOffsetX, recipeOffsetY - 1, 0, vOffset, 16, 18);
        }
    }

    private void drawRecipeItems(int offsetX, int offsetY, int indexOffset) {
        List<GlasscuttingRecipe> list = this.menu.getRecipeList();
        for(int i = this.recipeIndexOffset; i < indexOffset && i < this.menu.getRecipeListSize(); ++i)
        {
            int index = i - this.recipeIndexOffset;
            int recipeOffsetX = offsetX + index % 4 * 16;
            int recipeOffsetY = offsetY + (index / 4) * 18 + 2;
            this.minecraft.getItemRenderer().renderAndDecorateItem(list.get(i).getResultItem(), recipeOffsetX, recipeOffsetY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.clickOnScrollBar = false;
        if(this.hasItemsInInputSlot)
        {
            int recipeOffsetX = this.leftPos + 52;
            int recipeOffsetY = this.topPos + 14;
            int recipeOffset = this.recipeIndexOffset + 12;

            for(int i = this.recipeIndexOffset; i < recipeOffset; ++i)
            {
                int index = i - this.recipeIndexOffset;
                double mouseRelativeX = mouseX - (double) (recipeOffsetX + index % 4 * 16);
                double mouseRelativeY = mouseY - (double) (recipeOffsetY + index / 4 * 18);
                if(mouseRelativeX >= 0.0D && mouseRelativeY >= 0.0D && mouseRelativeX < 16.0D && mouseRelativeY < 18.0D && this.menu.clickMenuButton(this.minecraft.player, i))
                {
                    Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, i);
                    return true;
                }
            }

            recipeOffsetX = this.leftPos + 119;
            recipeOffsetY = this.topPos + 9;
            if(mouseX >= (double) recipeOffsetX && mouseX < (double) (recipeOffsetX + 12) && mouseY >= (double) recipeOffsetY && mouseY < (double) (recipeOffsetY + 54))
            {
                this.clickOnScrollBar = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(this.clickOnScrollBar && this.canScroll())
        {
            int offsetX = this.topPos + 14;
            int width = offsetX + 54;
            this.sliderProgress = ((float) mouseY - (float) offsetX - 7.5F) / ((float) (width - offsetX) - 15.0F);
            this.sliderProgress = Mth.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) this.getHiddenRows()) + 0.5D) * 4;
            return true;
        }
        else
        {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double distance) {
        if(this.canScroll())
        {
            int hiddenRowsCount = this.getHiddenRows();
            this.sliderProgress = (float) ((double) this.sliderProgress - distance / (double) hiddenRowsCount);
            this.sliderProgress = Mth.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) hiddenRowsCount) + 0.5D) * 4;
        }

        return true;
    }

    private boolean canScroll()
    {
        return this.hasItemsInInputSlot && this.menu.getRecipeListSize() > 12;
    }

    private int getHiddenRows()
    {
        return (this.menu.getRecipeListSize() + 4 - 1) / 4 - 3;
    }

    private void onInventoryUpdate()
    {
        this.hasItemsInInputSlot = this.menu.hasItemsInInputSlot();
        if(!this.hasItemsInInputSlot)
        {
            this.sliderProgress = 0.0F;
            this.recipeIndexOffset = 0;
        }
    }
}
