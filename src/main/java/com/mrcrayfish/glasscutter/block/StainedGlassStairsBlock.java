package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

/**
 * Author: MrCrayfish
 */
public class StainedGlassStairsBlock extends AbstractGlassStairsBlock implements Stainable
{
    private final DyeColor color;

    public StainedGlassStairsBlock(DyeColor color, BlockState state, Settings settings)
    {
        super(state, settings);
        this.color = color;
    }

    @Override
    public DyeColor getColor()
    {
        return this.color;
    }
}
