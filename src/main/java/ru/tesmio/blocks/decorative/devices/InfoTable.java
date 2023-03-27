package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegSounds;

public class InfoTable extends BlockSideDevice {

    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public static final EnumProperty<EnumType> TYPE = EnumProperty.create("type", EnumType.class);
    public InfoTable(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(TYPE, EnumType.OPEN).with(ENABLE, false).with(WATERLOGGED, false));
    }
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getShape(state, worldIn, pos, null);
    }
    final VoxelShape BOXS[] = new VoxelShape[] {
            Block.makeCuboidShape(0D, 5D, 0D, 16D, 11D, 2D), //north
            Block.makeCuboidShape(0D, 5D, 14D, 16D, 11D, 16D), //south
            Block.makeCuboidShape(14D, 5D, 0D, 16D, 11D, 16D), //east
            Block.makeCuboidShape(0D, 5D, 0D, 2D, 11D, 16D)}; //west
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return BOXS[1];
            case SOUTH:
                return BOXS[0];
            case WEST:
                return BOXS[2];
            case EAST:
                return BOXS[3];
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
            w.playSound(null, p, RegSounds.SOUND_RELAY.get(), SoundCategory.BLOCKS, 0.05f, 1f);
            s = s.with(ENABLE, true);
        } else {
            s = s.with(ENABLE, false);
        }
            return s;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, ENABLE, WATERLOGGED);
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(state.get(ENABLE)) {
            state = state.cycleValue(TYPE);
            worldIn.setBlockState(pos, state);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
    @Override
    public int  getLightValue(BlockState s, IBlockReader br, BlockPos pos) {
        if(s.get(ENABLE)) {
            return 2;
        }
        return 0;
    }
    public enum EnumType implements IStringSerializable {
        NOT_ACCESS("1"),
        CHECKOUT("2"),
        DANGER("3"),
        RADIATION("4"),
        HAZARD("5"),
        OPEN("6");
        private final String name;
        EnumType(String name) {this.name = name;}
        @Override
        public String getString() {
            return this.name;
        }
    }
}
