package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.IBeaconBeamColorProvider;
import net.minecraft.item.DyeColor;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class StainedGlassStairsBlock extends AbstractGlassStairsBlock implements IBeaconBeamColorProvider
{
    private DyeColor color;

    public StainedGlassStairsBlock(DyeColor color, Supplier<BlockState> state, Properties properties)
    {
        super(state, properties);
        this.color = color;
    }

    @Override
    public DyeColor getColor()
    {
        return this.color;
    }
}
