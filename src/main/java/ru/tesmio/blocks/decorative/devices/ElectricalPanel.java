package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class ElectricalPanel extends BlockSideCustomModel {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public ElectricalPanel(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, false).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    if(player.isCrouching()) {
        state = state.cycleValue(OPEN);
        worldIn.setBlockState(pos, state);
        return ActionResultType.SUCCESS;
    }
    return ActionResultType.FAIL;
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(1,2)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1,2))
        };
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        VoxelShape SHP = Block.makeCuboidShape(0,0,0,16,16,3.5);
        return SHP;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, WATERLOGGED);
    }
}
