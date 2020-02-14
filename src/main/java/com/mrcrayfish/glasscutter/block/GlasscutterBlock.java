package com.mrcrayfish.glasscutter.block;

import com.mrcrayfish.glasscutter.inventory.container.GlasscutterContainer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class GlasscutterBlock extends Block
{
    private static final TranslationTextComponent CONTAINER_TITLE = new TranslationTextComponent("container.glasscutter.glasscutter");
    public static final DirectionProperty DIRECTION = HorizontalBlock.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public GlasscutterBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(DIRECTION, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(DIRECTION, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult result)
    {
        if(!world.isRemote)
        {
            playerEntity.openContainer(state.getContainer(world, pos));
        }
        return ActionResultType.SUCCESS;
    }

    @Nullable
    @Override
    public INamedContainerProvider getContainer(BlockState state, World world, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((windowId, playerInventory, playerEntity) -> {
            return new GlasscutterContainer(windowId, playerInventory, IWorldPosCallable.of(world, pos));
        }, CONTAINER_TITLE);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean isTransparent(BlockState state)
    {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.with(DIRECTION, rotation.rotate(state.get(DIRECTION)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.toRotation(state.get(DIRECTION)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(DIRECTION);
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader reader, BlockPos pos, PathType pathType)
    {
        return false;
    }
}
