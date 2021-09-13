package com.mrcrayfish.glasscutter.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class AbstractGlassSlabBlock extends SlabBlock
{
    private final Supplier<BlockState> state;

    public AbstractGlassSlabBlock(Supplier<BlockState> state, Properties properties)
    {
        super(properties);
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
        return (adjacentState.getBlock() == this && adjacentState.getValue(TYPE) == state.getValue(TYPE)) || adjacentState.getBlock() == this.state.get().getBlock() || super.skipRendering(state, adjacentState, side);
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
