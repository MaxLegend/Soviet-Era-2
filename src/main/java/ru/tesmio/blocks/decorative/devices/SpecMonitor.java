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

public class SpecMonitor extends BlockSideCustomModel {

    final VoxelShape BOX = Block.makeCuboidShape(0,0,0,16,16,16);
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public SpecMonitor(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(ENABLE, false));
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case WEST:
                return VoxelShapes.or(
                        Block.makeCuboidShape(3,0,0,10,16,16),
                        Block.makeCuboidShape(3,0,0,13,15,16),
                        Block.makeCuboidShape(3,2,0,15,14,16),
                        Block.makeCuboidShape(3,4,1,16,13,15)
                );
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,0,0,10,16,16)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,0,0,13,15,16)),
                        VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(3,2,0,15,14,16)),
                        VoxelShapeUtil.shapeRot180( Block.makeCuboidShape(3,4,1,16,13,15))
                );
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,0,0,10,16,16)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,0,0,13,15,16)),
                        VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,2,0,15,14,16)),
                        VoxelShapeUtil.shapeRotCW90( Block.makeCuboidShape(3,4,1,16,13,15))
                );
            case SOUTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,0,0,10,16,16))),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,0,0,13,15,16))),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,2,0,15,14,16))),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(3,4,1,16,13,15)))
                );
        }
        return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENABLE, WATERLOGGED);
    }
}