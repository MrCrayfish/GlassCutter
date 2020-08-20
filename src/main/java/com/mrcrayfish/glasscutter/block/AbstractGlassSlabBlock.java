package com.mrcrayfish.glasscutter.block;

import com.mrcrayfish.glasscutter.init.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

/**
 * Author: MrCrayfish
 */
public class AbstractGlassSlabBlock extends SlabBlock
{
    private final BlockState state;

    public AbstractGlassSlabBlock(BlockState state, Settings settings)
    {
        super(settings.nonOpaque().allowsSpawning((a, b, c, d) -> false));
        this.state = state;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    public boolean canMobSpawnInside() {
        return false;
    }
}
