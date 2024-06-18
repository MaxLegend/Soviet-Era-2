package ru.tesmio.soviet.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.soviet.blocks.baseblock.subtype.BlockRailing;
import ru.tesmio.soviet.blocks.decorative.props.WindProofPanel;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class BlockRotatedAxisCustomModel extends BlockRotatedAxis {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final float shadingInside;

    public BlockRotatedAxisCustomModel(Properties p, float shadingInside) {
        super(p);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, BaseEnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
        this.shadingInside = shadingInside;

    }

    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if (!w.isRemote) {
            if (!pl.isCreative() && !(s.getBlock() instanceof WindProofPanel) && !(s.getBlock() instanceof BlockRailing)) {
                getDropsWithBlock(w, p, pl);
                getAdditionDrops(w, p, getStackAddDrop(pl));
            }
        }
    }

    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }

    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ThreadLocalRandom tr = ThreadLocalRandom.current();

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p, PlayerEntity pl) {
        for (ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }


    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for (Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {

                return this.getDefaultState().with(FACING, BaseEnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing())).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            } else {

                return this.getDefaultState().with(FACING, BaseEnumOrientation.forFacing(direction, direction)).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            }
        }
        return this.getDefaultState();
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.shadingInside;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }
}
