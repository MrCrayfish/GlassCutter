package com.mrcrayfish.glasscutter.block;

import com.mrcrayfish.glasscutter.inventory.container.GlasscutterContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/**
 * Author: MrCrayfish
 */
public class GlasscutterBlock extends Block
{
    private static final TranslatableComponent CONTAINER_TITLE = new TranslatableComponent("container.glasscutter.glasscutter");
    public static final DirectionProperty DIRECTION = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

    public GlasscutterBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DIRECTION, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(DIRECTION, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player playerEntity, InteractionHand hand, BlockHitResult result)
    {
        if(!world.isClientSide)
        {
            playerEntity.openMenu(state.getMenuProvider(world, pos));
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos)
    {
        return new SimpleMenuProvider((windowId, playerInventory, playerEntity) -> {
            return new GlasscutterContainer(windowId, playerInventory, ContainerLevelAccess.create(world, pos));
        }, CONTAINER_TITLE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state)
    {
        return true;
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(DIRECTION, rotation.rotate(state.getValue(DIRECTION)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(DIRECTION)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(DIRECTION);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType pathType)
    {
        return false;
    }
}
