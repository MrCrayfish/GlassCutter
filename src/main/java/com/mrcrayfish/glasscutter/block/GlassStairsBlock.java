package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class GlassStairsBlock extends AbstractGlassStairsBlock
{
    public GlassStairsBlock(Settings settings)
    {
        super(Blocks.GLASS.getDefaultState(), settings);
    }
}
