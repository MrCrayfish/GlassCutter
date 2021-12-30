package com.mrcrayfish.glasscutter.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class AbstractGlassStairsBlock extends StairBlock
{
    private final Supplier<BlockState> state;

    public AbstractGlassStairsBlock(Supplier<BlockState> state, Properties properties)
    {
        super(state, properties);
        this.state = state;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state)
    {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side)
    {
        if(adjacentState.getBlock() == this && adjacentState.getValue(HALF) == state.getValue(HALF))
        {
            if(adjacentState.getValue(FACING) != state.getValue(FACING).getOpposite())
            {
                return true;
            }
        }
        if(adjacentState.getBlock() == this.state.get().getBlock())
        {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isPossibleToRespawnInThis() {
        return false;
    }
}
