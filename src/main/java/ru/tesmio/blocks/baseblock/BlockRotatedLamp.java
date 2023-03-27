package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.reg.RegSounds;

public class BlockRotatedLamp extends BlockForFacing {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty LIT_VALUE = IntegerProperty.create("lit_power", 0, 4);
    public BlockRotatedLamp(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(LIT_VALUE, Integer.valueOf(0)).with(WATERLOGGED, false));
    }
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.getShape(state, worldIn, pos, null);
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public BlockState getStateForPlacement(BlockItemUseContext c) {
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        int powerLight = c.getWorld().getRedstonePower(c.getPos(), c.getFace());
        for(Direction direction : c.getNearestLookingDirections()) {

            if (direction.getAxis() == Direction.Axis.Y) {

                if (powerLight == 0)
                    return this.getDefaultState().with(LIT_VALUE, 0).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                if (powerLight <= 4 && powerLight > 0) {
                    return this.getDefaultState().with(LIT_VALUE, 1).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight <= 8 && powerLight > 4) {
                    return this.getDefaultState().with(LIT_VALUE, 2).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight <= 12 && powerLight > 8) {
                    return this.getDefaultState().with(LIT_VALUE, 3).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));
                }
                if (powerLight > 12) {
                    return this.getDefaultState().with(LIT_VALUE, 4).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, c.getPlacementHorizontalFacing()));


                }
            } else {

                if (powerLight == 0)
                    return this.getDefaultState().with(LIT_VALUE, 0).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                if (powerLight <= 4 && powerLight > 0) {
                    return this.getDefaultState().with(LIT_VALUE, 1).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight <= 8 && powerLight > 4) {
                    return this.getDefaultState().with(LIT_VALUE, 2).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight <= 12 && powerLight > 8) {
                    return this.getDefaultState().with(LIT_VALUE, 3).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));
                }
                if (powerLight > 12) {
                    return this.getDefaultState().with(LIT_VALUE, 4).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER)).with(FACING, EnumOrientation.forFacing(direction, direction));

                }

            }
        }
        return this.getDefaultState();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public int  getLightValue(BlockState s, IBlockReader br, BlockPos pos) {
        switch (s.get(LIT_VALUE)) {
            case 0:
            default:
                return 0;
            case 1:
                return 4;
            case 2:
                return 9;
            case 3:
                return 12;
            case 4:
                return 15;

        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT_VALUE, WATERLOGGED);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if(w instanceof WorldGenRegion) return s;
        return updateState((World)w,p,s);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (w.isBlockPowered(p)) {
            for (Direction facing : Direction.values()) {
                int powerLight = w.getRedstonePower(p.offset(facing), facing);
                if (powerLight <= 4 && powerLight > 0) {
                    return s.with(LIT_VALUE, 1);
                }
                if (powerLight <= 8 && powerLight > 4) {
                    return s.with(LIT_VALUE, 2);
                }
                if (powerLight <= 12 && powerLight > 8) {
                    return s.with(LIT_VALUE, 3);
                }
                if (powerLight > 12) {
                    return s.with(LIT_VALUE, 4);
                }
            }
            w.playSound(null, p, RegSounds.SOUND_FLUO_LAMP.get(), SoundCategory.BLOCKS, 0.40f, 1f);
        }
        return s.with(LIT_VALUE, 0);
    }
}
