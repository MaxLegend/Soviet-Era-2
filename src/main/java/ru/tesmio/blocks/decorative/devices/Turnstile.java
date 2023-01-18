package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class Turnstile extends BlockSideCustomModel {
    private static VoxelShape[] SHAPES = new VoxelShape[] {
            Block.makeCuboidShape(0D, 0D, 0.35D*16, 1D*16, 1D*16, 0.65D*16),
            Block.makeCuboidShape(0D, 0D, 0.35D*16, 1D*16, 1D*16, 0.65D*16),
            Block.makeCuboidShape(0.35D*16, 0D, 0D, 0.65*16D, 1D*16, 1D*16),
            Block.makeCuboidShape(0.35D*16, 0D, 0D, 0.65*16D, 1D*16, 1D*16),

    };

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        //отрегулировать шейпы, сделать покрасивее, проходимость
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPES[0];
            case SOUTH:
                return SHAPES[1];
            case WEST:
                return SHAPES[2];
            case EAST:
                return SHAPES[3];
        }
        //проходимость
        if(state.get(STATUS) == EnumStatus.OPEN) {
            switch (state.get(FACING)) {
//                case NORTH:
//                    return SHAPES[4];
//                case SOUTH:
//                    return SHAPES[5];
//                case WEST:
//                    return SHAPES[6];
//                case EAST:
//                    return SHAPES[7];
            }
        }
        return VoxelShapes.fullCube();
    }

    public static final EnumProperty<EnumStatus> STATUS = EnumProperty.create("status", EnumStatus.class);

    public Turnstile(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(STATUS,EnumStatus.OFF).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, STATUS, WATERLOGGED);
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        if(w.isBlockPowered(p)) {
            if(s.get(STATUS) == EnumStatus.OFF) {
                w.setBlockState(p, s.with(STATUS, EnumStatus.CLOSE));
            }
        } else {
            w.setBlockState(p, s.with(STATUS, EnumStatus.OFF));
        }
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand hand, BlockRayTraceResult hit) {
        if(s.get(STATUS) == EnumStatus.CLOSE) {
            w.setBlockState(p, s.with(STATUS, EnumStatus.OPEN));
            return ActionResultType.SUCCESS;
        } else if(s.get(STATUS) == EnumStatus.OPEN) {
            w.setBlockState(p, s.with(STATUS, EnumStatus.CLOSE));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    public enum EnumStatus implements IStringSerializable {
        OFF("off"),
        CLOSE("close"),
        OPEN("open");
        private final String name;
        EnumStatus(String name) {this.name = name;}
        @Override
        public String getString() {
            return this.name;
        }
    }
}
