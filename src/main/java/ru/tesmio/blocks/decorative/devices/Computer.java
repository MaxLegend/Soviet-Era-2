package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

public class Computer extends BlockSideCustomModel {

    final VoxelShape BOX = Block.makeCuboidShape(0,0,0,16,16,16);
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public Computer(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(ENABLE, false));
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            if(w.isBlockPowered(p)) {
                return s.with(ENABLE, true);
            } else {
                return s.with(ENABLE, false);
            }
        }
        return s;
    }
    @Override
    public int getLightValue(BlockState s, IBlockReader br, BlockPos p) {

        if(s.get(ENABLE)) {
            return 2;
        }
        return 0;
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

            switch (state.get(FACING)) {
                case WEST:
                    return VoxelShapes.or(
                            Block.makeCuboidShape(2,3,1,15,15,15),
                            Block.makeCuboidShape(2,1,2,3,16,14),
                            Block.makeCuboidShape(4,15,3,15,16,13),
                            Block.makeCuboidShape(4,2,3,15,3,13),
                            Block.makeCuboidShape(4,0,4,12,1,12),
                            Block.makeCuboidShape(2,4,0,14,14,2),
                            Block.makeCuboidShape(2,4,14,14,14,16),
                            Block.makeCuboidShape(4,3,4,16,15,12),
                            Block.makeCuboidShape(4,4,2,16,14,14)
                    );
                case EAST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2,3,1,15,15,15)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2,1,2,3,16,14)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,15,3,15,16,13)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,2,3,15,3,13)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,0,4,12,1,12)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2,4,0,14,14,2)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(2,4,14,14,14,16)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,3,4,16,15,12)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4,4,2,16,14,14))
                    );
                case NORTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2,3,1,15,15,15)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2,1,2,3,16,14)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,15,3,15,16,13)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,2,3,15,3,13)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,0,4,12,1,12)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2,4,0,14,14,2)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(2,4,14,14,14,16)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,3,4,16,15,12)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4,4,2,16,14,14))
                    );
                case SOUTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2,3,1,15,15,15))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2,1,2,3,16,14))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,15,3,15,16,13))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,2,3,15,3,13))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,0,4,12,1,12))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2,4,0,14,14,2))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(2,4,14,14,14,16))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,3,4,16,15,12))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4,4,2,16,14,14)))
                    );
            }
            return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENABLE, WATERLOGGED);
    }
}
