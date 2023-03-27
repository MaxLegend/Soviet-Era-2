package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockForFacing;
import ru.tesmio.reg.RegSounds;

public class SmallButton extends BlockForFacing {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public SmallButton(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(ENABLE, false).with(WATERLOGGED, false));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ENABLE, WATERLOGGED);
    }
    public boolean canProvidePower(BlockState state) {
        return true;
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
            worldIn.playSound(null, pos, RegSounds.SOUND_SNAP.get(), SoundCategory.BLOCKS, 0.5F, 1F);
            state = state.cycleValue(ENABLE);
            worldIn.setBlockState(pos, state);
            return ActionResultType.SUCCESS;

    }
    public int getWeakPower(BlockState s, IBlockReader br, BlockPos p, Direction side) {
        if(s.get(ENABLE)){
            return 15;
        }
        return 0;
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] SHAPES = new VoxelShape[] {
                VoxelShapes.create(0.3725D, 0.3725D, 0.9D, 0.6275D, 0.6275D, 1D),
                VoxelShapes.create(0.3725D, 0.3725D, 0D, 0.6275D, 0.6275D, 0.1D),
                VoxelShapes.create(0.1D, 0.3725D, 0.3725D, 0D, 0.6275D, 0.6275D),
                VoxelShapes.create(0.9D, 0.3725D, 0.3725D, 1D, 0.6275D, 0.6275D),
                VoxelShapes.create(0.3725D, 0D, 0.3725D, 0.6275D, 0.1D, 0.6275D),
                VoxelShapes.create(0.3725D, 0.9D, 0.3725D, 0.6275D, 1D, 0.6275D)
        };
        switch (state.get(FACING)) {
            case SOUTH:
                return SHAPES[0];
            case NORTH:
                return SHAPES[1];
            case WEST:
                return SHAPES[2];
            case EAST:
                return SHAPES[3];
            case DOWN:
                return SHAPES[4];
            case UP:
                return SHAPES[5];

        }
        return VoxelShapes.fullCube();
    }
}
