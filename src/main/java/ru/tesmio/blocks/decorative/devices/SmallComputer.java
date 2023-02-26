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

public class SmallComputer extends BlockSideCustomModel {
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public SmallComputer(Properties properties, float shadingInside) {
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENABLE, WATERLOGGED);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHP[] = new VoxelShape[] {
          Block.makeCuboidShape(6,0,4,13,4,16),
                Block.makeCuboidShape(1,0.25,4.4,6,1,15.875),
                Block.makeCuboidShape(7,5,7,9,10.5,13),
                Block.makeCuboidShape(7,5.5,7.25,13.5,10.25,12.75),
                Block.makeCuboidShape(8.75,0,1,16,1,3)
        };
        switch (state.get(FACING)) {
            case SOUTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHP[0])),
                        VoxelShapeUtil.shapeRot180( VoxelShapeUtil.shapeRotCCW90(SHP[1])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHP[2])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHP[3])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(SHP[4])));
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCCW90(SHP[0]),
                        VoxelShapeUtil.shapeRotCCW90(SHP[1]),
                        VoxelShapeUtil.shapeRotCCW90(SHP[2]),
                        VoxelShapeUtil.shapeRotCCW90(SHP[3]),
                        VoxelShapeUtil.shapeRotCCW90(SHP[4]));
            case WEST:
                return VoxelShapes.or(SHP[0],SHP[1],SHP[2],SHP[3],SHP[4]);
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(SHP[0]),
                        VoxelShapeUtil.shapeRot180(SHP[1]),
                        VoxelShapeUtil.shapeRot180(SHP[2]),
                        VoxelShapeUtil.shapeRot180(SHP[3]),
                        VoxelShapeUtil.shapeRot180(SHP[4]));
        }
    return VoxelShapes.fullCube();
    }
}
