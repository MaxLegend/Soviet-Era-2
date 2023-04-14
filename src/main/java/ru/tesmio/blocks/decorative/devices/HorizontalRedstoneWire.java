package ru.tesmio.blocks.decorative.devices;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.enums.EnumPlace;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

//доделать
public class HorizontalRedstoneWire extends BlockCustomModel {
    public static final EnumProperty<EnumPlace> PLACEMENT = EnumProperty.create("place", EnumPlace.class);
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
    private boolean canProvidePower = true;
    public HorizontalRedstoneWire(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(POWER, 0).with(PLACEMENT, EnumPlace.FLOOR)
                .with(NORTH, false).with(SOUTH, false).with(WEST, false).with(EAST, false).with(POWER, 0));

    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWER, PLACEMENT,NORTH,SOUTH,WEST, EAST,WATERLOGGED);
    }
    public VoxelShape compileShape(BlockState s, VoxelShape def, VoxelShape side) {
        VoxelShape finalShape = def;
        if(s.get(SOUTH)) {
            finalShape = VoxelShapes.or(def, side);
        }
        if(s.get(NORTH)) {
            finalShape = VoxelShapes.or(finalShape, VoxelShapeUtil.shapeRot180(side));
        }
        if(s.get(WEST)) {
            finalShape = VoxelShapes.or(finalShape, VoxelShapeUtil.shapeRotCCW90(side));
        }
        if(s.get(EAST)) {
            finalShape = VoxelShapes.or(finalShape, VoxelShapeUtil.shapeRotCW90(side));
        }
        return finalShape;
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] SHP = new VoxelShape[] {
                Block.makeCuboidShape(7,0,7,9,2,9),
                Block.makeCuboidShape(7,14,7,9,16,9),
                Block.makeCuboidShape(7,0,7,9,2,16),
                Block.makeCuboidShape(7,14,7,9,16,16)
        };
        if(state.get(PLACEMENT) == EnumPlace.FLOOR) {
            return compileShape(state, SHP[0], SHP[2]);
        }
        if(state.get(PLACEMENT) == EnumPlace.CEILING) {
            return compileShape(state, SHP[1], SHP[3]);
        }
        return VoxelShapes.fullCube();
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        Direction[] a = context.getNearestLookingDirections();
        for(Direction d : a) {
            if(d == Direction.UP) {
                return this.getDefaultState().with(PLACEMENT, EnumPlace.CEILING);
            } else  return this.getDefaultState();
        }

        return this.getDefaultState();
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World)w,p,s);
    }

    public Block getBlockForDirection(World w, BlockPos p, Direction d) {
        return w.getBlockState(p.offset(d)).getBlock();
    }

    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
           if(getBlockForDirection(w,p,Direction.NORTH) instanceof HorizontalRedstoneWire || getBlockForDirection(w,p,Direction.NORTH) instanceof BlockRedstoneWire
                   || getBlockForDirection(w,p,Direction.NORTH) instanceof IRedstoneDevice) {
               s = s.with(NORTH, true);
           } else s = s.with(NORTH, false);
           if(getBlockForDirection(w,p,Direction.SOUTH) instanceof HorizontalRedstoneWire || getBlockForDirection(w,p,Direction.SOUTH) instanceof BlockRedstoneWire
                   || getBlockForDirection(w,p,Direction.SOUTH) instanceof IRedstoneDevice) {
                s = s.with(SOUTH, true);
           } else s = s.with(SOUTH, false);
           if(getBlockForDirection(w,p,Direction.WEST) instanceof HorizontalRedstoneWire || getBlockForDirection(w,p,Direction.WEST) instanceof BlockRedstoneWire
                   || getBlockForDirection(w,p,Direction.WEST) instanceof IRedstoneDevice) {
                s = s.with(WEST, true);
           } else s = s.with(WEST, false);
           if(getBlockForDirection(w,p,Direction.EAST) instanceof HorizontalRedstoneWire || getBlockForDirection(w,p,Direction.EAST) instanceof BlockRedstoneWire
                   || getBlockForDirection(w,p,Direction.EAST) instanceof IRedstoneDevice) {
                s = s.with(EAST, true);
           } else s = s.with(EAST, false);
        }
        return s;
    }
    private int getPower(BlockState state) {
        return state.matchesBlock(this) ? state.get(POWER) : 0;
    }
    private void updatePower(World world, BlockPos pos, BlockState state) {
        int i = this.getStrongestSignal(world, pos);
        if (state.get(POWER) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, Integer.valueOf(i)), 2);
            }
            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);

            for(Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }

            for(BlockPos blockpos : set) {
                world.notifyNeighborsOfStateChange(blockpos, this);
            }
        }
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!w.isRemote) {
            this.updatePower(w, p, s);
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
        }
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("info.horiz_redstone_wire", Integer.toString(1000)));
    }
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return !this.canProvidePower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if (this.canProvidePower) {
            int i = blockState.get(POWER);
            if (i == 0) {
                return 0;
            } else {

                return i - 1;
            }
        } else {
            return 0;
        }
    }
    public boolean canProvidePower(BlockState state) {
        return this.canProvidePower;
    }

    private int getStrongestSignal(World world, BlockPos pos) {
        this.canProvidePower = false;
        int i = world.getRedstonePowerFromNeighbors(pos);
        this.canProvidePower = true;
        int j = 0;
        if (i < 15) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos blockpos = pos.offset(direction);
                BlockState blockstate = world.getBlockState(blockpos);
                j = Math.max(j, this.getPower(blockstate));
                if (blockstate.isNormalCube(world, blockpos)) {
                    j = Math.max(j, this.getPower(world.getBlockState(blockpos.up())));
                } else if (!blockstate.isNormalCube(world, blockpos)) {
                    j = Math.max(j, this.getPower(world.getBlockState(blockpos.down())));
                }
            }
        }

        return Math.max(i, j - 1);
    }
}
