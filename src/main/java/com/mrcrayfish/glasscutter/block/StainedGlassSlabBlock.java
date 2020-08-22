package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

/**
 * Author: MrCrayfish
 */
public class StainedGlassSlabBlock extends AbstractGlassSlabBlock implements Stainable
{
    private final DyeColor color;

    public StainedGlassSlabBlock(DyeColor color, BlockState state, Settings settings)
    {
        super(state, settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor() {
        return color;
    }
}
