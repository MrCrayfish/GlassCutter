package com.mrcrayfish.glasscutter.inventory.container;

import com.google.common.collect.Lists;
import com.mrcrayfish.glasscutter.init.ModBlocks;
import com.mrcrayfish.glasscutter.init.ModContainers;
import com.mrcrayfish.glasscutter.item.crafting.GlasscuttingRecipe;
import com.mrcrayfish.glasscutter.item.crafting.ModRecipeType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

/**
 * Author: MrCrayfish
 */
public class GlasscutterContainer extends AbstractContainerMenu
{
    private final ContainerLevelAccess worldPosCallable;
    private final DataSlot selectedRecipe;
    private final Level world;
    private List<GlasscuttingRecipe> recipes;
    private ItemStack itemStackInput;
    private long lastOnTake;
    private final Slot inputInventorySlot;
    private final Slot outputInventorySlot;
    private Runnable inventoryUpdateListener = () -> {};
    private final Container inputInventory;
    private final ResultContainer inventory;

    public GlasscutterContainer(int windowId, Inventory playerInventory)
    {
        this(windowId, playerInventory, ContainerLevelAccess.NULL);
    }

    public GlasscutterContainer(int windowId, Inventory playerInventory, final ContainerLevelAccess callable)
    {
        super(ModContainers.GLASSCUTTER, windowId);
        this.selectedRecipe = DataSlot.standalone();
        this.recipes = Lists.newArrayList();
        this.itemStackInput = ItemStack.EMPTY;
        this.inputInventory = new SimpleContainer(1)
        {
            @Override
            public void setChanged()
            {
                super.setChanged();
                GlasscutterContainer.this.slotsChanged(this);
                GlasscutterContainer.this.inventoryUpdateListener.run();
            }
        };
        this.inventory = new ResultContainer();
        this.worldPosCallable = callable;
        this.world = playerInventory.player.level;
        this.inputInventorySlot = this.addSlot(new Slot(this.inputInventory, 0, 20, 33));
        this.outputInventorySlot = this.addSlot(new Slot(this.inventory, 1, 143, 33)
        {
            @Override
            public boolean mayPlace(ItemStack stack)
            {
                return false;
            }

            @Override
            public void onTake(Player playerEntity, ItemStack stack)
            {
                ItemStack slotStack = GlasscutterContainer.this.inputInventorySlot.remove(1);
                if(!slotStack.isEmpty())
                {
                    GlasscutterContainer.this.updateRecipeResultSlot();
                }

                stack.getItem().onCraftedBy(stack, playerEntity.level, playerEntity);
                callable.execute((world, pos) ->
                {
                    long gameTime = world.getGameTime();
                    if(GlasscutterContainer.this.lastOnTake != gameTime)
                    {
                        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        GlasscutterContainer.this.lastOnTake = gameTime;
                    }
                });
                super.onTake(playerEntity, stack);
            }
        });

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addDataSlot(this.selectedRecipe);
    }

    @OnlyIn(Dist.CLIENT)
    public int getSelectedRecipe()
    {
        return this.selectedRecipe.get();
    }

    @OnlyIn(Dist.CLIENT)
    public List<GlasscuttingRecipe> getRecipeList()
    {
        return this.recipes;
    }

    @OnlyIn(Dist.CLIENT)
    public int getRecipeListSize()
    {
        return this.recipes.size();
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasItemsInInputSlot()
    {
        return this.inputInventorySlot.hasItem() && !this.recipes.isEmpty();
    }

    @Override
    public boolean stillValid(Player playerEntity)
    {
        return stillValid(this.worldPosCallable, playerEntity, ModBlocks.GLASSCUTTER);
    }

    @Override
    public boolean clickMenuButton(Player playerEntity, int index)
    {
        if(index >= 0 && index < this.recipes.size())
        {
            this.selectedRecipe.set(index);
            this.updateRecipeResultSlot();
        }
        return true;
    }

    @Override
    public void slotsChanged(Container inventory)
    {
        ItemStack stack = this.inputInventorySlot.getItem();
        if(stack.getItem() != this.itemStackInput.getItem())
        {
            this.itemStackInput = stack.copy();
            this.updateAvailableRecipes(inventory, stack);
        }
    }

    private void updateAvailableRecipes(Container inventory, ItemStack stack)
    {
        this.recipes.clear();
        this.selectedRecipe.set(-1);
        this.outputInventorySlot.set(ItemStack.EMPTY);
        if(!stack.isEmpty())
        {
            this.recipes = this.world.getRecipeManager().getRecipesFor(ModRecipeType.GLASSCUTTING, inventory, this.world);
        }
    }

    private void updateRecipeResultSlot()
    {
        if(!this.recipes.isEmpty())
        {
            //TODO fix this
            GlasscuttingRecipe recipe = this.recipes.get(this.selectedRecipe.get());
            this.outputInventorySlot.set(recipe.assemble(this.inputInventory));
        }
        else
        {
            this.outputInventorySlot.set(ItemStack.EMPTY);
        }
        this.broadcastChanges();
    }

    @OnlyIn(Dist.CLIENT)
    public void setInventoryUpdateListener(Runnable runnable)
    {
        this.inventoryUpdateListener = runnable;
    }

    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot)
    {
        return slot.container != this.inventory && super.canTakeItemForPickAll(stack, slot);
    }

    public ItemStack quickMoveStack(Player playerEntity, int index)
    {
        ItemStack copyStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem())
        {
            ItemStack slotStack = slot.getItem();
            Item item = slotStack.getItem();
            copyStack = slotStack.copy();
            if(index == 1)
            {
                item.onCraftedBy(slotStack, playerEntity.level, playerEntity);
                if(!this.moveItemStackTo(slotStack, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(slotStack, copyStack);
            }
            else if(index == 0)
            {
                if(!this.moveItemStackTo(slotStack, 2, 38, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(this.world.getRecipeManager().getRecipeFor(ModRecipeType.GLASSCUTTING, new SimpleContainer(slotStack), this.world).isPresent())
            {
                if(!this.moveItemStackTo(slotStack, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(index < 29)
            {
                if(!this.moveItemStackTo(slotStack, 29, 38, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if(index < 38 && !this.moveItemStackTo(slotStack, 2, 29, false))
            {
                return ItemStack.EMPTY;
            }

            if(slotStack.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }

            slot.setChanged();
            if(slotStack.getCount() == copyStack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerEntity, slotStack);
            this.broadcastChanges();
        }

        return copyStack;
    }

    public void removed(Player playerEntity)
    {
        super.removed(playerEntity);
        this.inventory.removeItemNoUpdate(1);
        this.worldPosCallable.execute((world, pos) -> this.clearContainer(playerEntity, playerEntity.level, this.inputInventory));
    }

    private void clearContainer(Player playerEntity, Level level, Container inputInventory) {
    }
}
