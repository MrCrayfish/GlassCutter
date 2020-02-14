package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.Blocks;

/**
 * Author: MrCrayfish
 */
public class GlassSlabBlock extends AbstractGlassSlabBlock
{
    public GlassSlabBlock(Properties properties)
    {
        super(Blocks.GLASS::getDefaultState, properties);
    }
}
