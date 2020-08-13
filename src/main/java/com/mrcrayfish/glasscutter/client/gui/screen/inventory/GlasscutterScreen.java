package com.mrcrayfish.glasscutter.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrcrayfish.glasscutter.Reference;
import com.mrcrayfish.glasscutter.inventory.container.GlasscutterContainer;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Author: MrCrayfish
 */
@OnlyIn(Dist.CLIENT)
public class GlasscutterScreen extends ContainerScreen<GlasscutterContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/glasscutter.png");
    private float sliderProgress;
    private boolean clickOnScrollBar;
    private int recipeIndexOffset;
    private boolean hasItemsInInputSlot;
    private static final Logger LOGGER = LogManager.getLogger();

    public GlasscutterScreen(GlasscutterContainer container, PlayerInventory playerInventory, ITextComponent titleIn)
    {
        super(container, playerInventory, titleIn);
        container.setInventoryUpdateListener(this::onInventoryUpdate);
        --this.field_238743_q_;
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
             // render
    {
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_238653_a_(matrixStack, null, mouseX, mouseY); // Render ToolTip
    }

/*
    @Override
    protected void func_230459_a_(MatrixStack matrixStack, int mouseX, int mouseY)
                // drawGuiContainerForegroundLayer
    {
        //   font renderer   drawString
        this.field_230712_o_.func_238407_a_(matrixStack, this.field_230704_d_.func_230532_e_(), 8.0F, 4.0F, 4210752);
        this.field_230712_o_.func_238407_a_(matrixStack, this.playerInventory.getDisplayName().func_230532_e_(), 8.0F, (float) (this.ySize - 94), 4210752);
    }
*/

    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
                // drawGuiContainerBackgroundLayer
    {
        // this.renderBackground();
        this.func_230446_a_(matrixStack);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int guiLeft = this.guiLeft;
        int guiTop = this.guiTop;

        // Blit
        this.func_238474_b_(matrixStack, guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
        int scrollBarPos = (int) (41.0F * this.sliderProgress);

        // Blit
        this.func_238474_b_(matrixStack, guiLeft + 119, guiTop + 15 + scrollBarPos,
                176 + (this.canScroll() ? 0 : 12), 0, 12, 15);
        int offsetX = this.guiLeft + 52;
        int offsetY = this.guiTop + 14;
        int indexOffset = this.recipeIndexOffset + 12;
        this.drawRecipeBackgrounds(matrixStack, mouseX, mouseY, offsetX, offsetY, indexOffset);
        this.drawRecipeItems(offsetX, offsetY, indexOffset);
    }

    private void drawRecipeBackgrounds(MatrixStack matrixStack, int mouseX, int mouseY, int offsetX, int offsetY, int indexOffset)
    {
        for(int i = this.recipeIndexOffset; i < indexOffset && i < this.container.getRecipeListSize(); ++i)
        {
            int index = i - this.recipeIndexOffset;
            int recipeOffsetX = offsetX + index % 4 * 16;
            int recipeOffsetY = offsetY + (index / 4) * 18 + 2;
            int vOffset = this.ySize;
            if(i == this.container.getSelectedRecipe())
            {
                vOffset += 18;
            }
            else if(mouseX >= recipeOffsetX && mouseY >= recipeOffsetY && mouseX < recipeOffsetX + 16 && mouseY < recipeOffsetY + 18)
            {
                vOffset += 36;
            }

            // Blit
            this.func_238468_a_(matrixStack, recipeOffsetX, recipeOffsetY - 1, 0, vOffset, 16, 18);
        }
    }

    private void drawRecipeItems(int offsetX, int offsetY, int indexOffset)
    {
        List<GlasscuttingRecipe> list = this.container.getRecipeList();
        for(int i = this.recipeIndexOffset; i < indexOffset && i < this.container.getRecipeListSize(); ++i)
        {
            int index = i - this.recipeIndexOffset;
            int recipeOffsetX = offsetX + index % 4 * 16;
            int recipeOffsetY = offsetY + (index / 4) * 18 + 2;
            this.field_230706_i_.getItemRenderer().renderItemAndEffectIntoGUI(list.get(i).getRecipeOutput(), recipeOffsetX, recipeOffsetY);
        }
    }

    @Override
    public boolean func_231044_a_(double mouseX, double mouseY, int button)
                // mouseClicked
    {
        this.clickOnScrollBar = false;
        if(this.hasItemsInInputSlot)
        {
            int recipeOffsetX = this.guiLeft + 52;
            int recipeOffsetY = this.guiTop + 14;
            int recipeOffset = this.recipeIndexOffset + 12;

            for(int i = this.recipeIndexOffset; i < recipeOffset; ++i)
            {
                int index = i - this.recipeIndexOffset;
                double mouseRelativeX = mouseX - (double) (recipeOffsetX + index % 4 * 16);
                double mouseRelativeY = mouseY - (double) (recipeOffsetY + index / 4 * 18);
                if(mouseRelativeX >= 0.0D && mouseRelativeY >= 0.0D && mouseRelativeX < 16.0D && mouseRelativeY < 18.0D && this.container.enchantItem(this.field_230706_i_.player, i))
                {
                    Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.field_230706_i_.playerController.sendEnchantPacket((this.container).windowId, i);
                    return true;
                }
            }

            recipeOffsetX = this.guiLeft + 119;
            recipeOffsetY = this.guiTop + 9;
            if(mouseX >= (double) recipeOffsetX && mouseX < (double) (recipeOffsetX + 12) && mouseY >= (double) recipeOffsetY && mouseY < (double) (recipeOffsetY + 54))
            {
                this.clickOnScrollBar = true;
            }
        }

        return super.func_231044_a_(mouseX, mouseY, button);
    }

    @Override
    public boolean func_231045_a_(double mouseX, double mouseY, int button, double deltaX, double deltaY)
                // mouseDragged
    {
        if(this.clickOnScrollBar && this.canScroll())
        {
            int offsetX = this.guiTop + 14;
            int width = offsetX + 54;
            this.sliderProgress = ((float) mouseY - (float) offsetX - 7.5F) / ((float) (width - offsetX) - 15.0F);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) this.getHiddenRows()) + 0.5D) * 4;
            return true;
        }
        else
        {
            return super.func_231045_a_(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    @Override
    public boolean func_231043_a_(double mouseX, double mouseY, double distance)
                // mouseScrolled
    {
        if(this.canScroll())
        {
            int hiddenRowsCount = this.getHiddenRows();
            this.sliderProgress = (float) ((double) this.sliderProgress - distance / (double) hiddenRowsCount);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) hiddenRowsCount) + 0.5D) * 4;
        }

        return true;
    }

    private boolean canScroll()
    {
        return this.hasItemsInInputSlot && this.container.getRecipeListSize() > 12;
    }

    private int getHiddenRows()
    {
        return (this.container.getRecipeListSize() + 4 - 1) / 4 - 3;
    }

    private void onInventoryUpdate()
    {
        this.hasItemsInInputSlot = this.container.hasItemsInInputSlot();
        if(!this.hasItemsInInputSlot)
        {
            this.sliderProgress = 0.0F;
            this.recipeIndexOffset = 0;
        }
    }
}
