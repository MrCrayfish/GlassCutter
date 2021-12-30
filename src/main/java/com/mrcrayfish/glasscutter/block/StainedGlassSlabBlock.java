package com.mrcrayfish.glasscutter.block;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class StainedGlassSlabBlock extends AbstractGlassSlabBlock implements BeaconBeamBlock
{
    private final DyeColor color;

    public StainedGlassSlabBlock(DyeColor color, Supplier<BlockState> state, Properties properties)
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
