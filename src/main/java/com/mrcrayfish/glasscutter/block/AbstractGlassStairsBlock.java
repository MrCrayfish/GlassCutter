package com.mrcrayfish.glasscutter.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

/**
 * Author: MrCrayfish
 */
public class AbstractGlassStairsBlock extends StairsBlock
{
    private final BlockState state;

    public AbstractGlassStairsBlock(BlockState state, Settings settings)
    {
        super(state, settings.nonOpaque().allowsSpawning((a, b, c, d) -> false));
        this.state = state;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentState, Direction side)
    {
        if(adjacentState.getBlock() == this && adjacentState.get(HALF) == state.get(HALF))
        {
            if(adjacentState.get(FACING) != state.get(FACING).getOpposite())
            {
                return true;
            }
        }
        if(adjacentState.getBlock() == this.state.getBlock())
        {
            return true;
        }
        return super.isSideInvisible(state, adjacentState, side);
    }

    // Help, what should I use instead???!
    @Override
    @Environment(EnvType.CLIENT)
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    public boolean canMobSpawnInside() {
        return true;
    }
}
