package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

public class ElectronicaClock extends BlockSideCustomModel {
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");


    public ElectronicaClock(Properties properties) {
        super(properties,1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ENABLE, false).with(WATERLOGGED, false));
    }
    @Override
    public int  getLightValue(BlockState s, IBlockReader br, BlockPos pos) {
        if(s.get(ENABLE)) {
            return 2;
        }
        return 0;
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 5.75D, 0D, 16D, 10.75D, 2D));
            case SOUTH:
                return Block.makeCuboidShape(0D, 5.75D, 0D, 16D, 10.75D, 2D);
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 5.75D, 0D, 16D, 10.75D, 2D));
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 5.75D, 0D, 16D, 10.75D, 2D));
        }
        return VoxelShapes.fullCube();
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        if(worldIn instanceof WorldGenRegion) return stateIn;
        return updateState((World) worldIn, currentPos,stateIn);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (w.isBlockPowered(p)) {
            w.playSound(null, p, RegSounds.SOUND_SPARKING.get(), SoundCategory.BLOCKS, 0.05f, 1f);
            s = s.with(ENABLE, true);
        } else {
            s = s.with(ENABLE, false);
        }
        return s;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ENABLE, WATERLOGGED);
    }
}
