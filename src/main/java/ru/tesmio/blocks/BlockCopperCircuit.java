package ru.tesmio.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class BlockCopperCircuit extends Block {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DISSECTION = IntegerProperty.create("diss", 0,5);
    public BlockCopperCircuit(Properties properties) {
        super(properties);

        this.setDefaultState(this.stateContainer.getBaseState().with(DISSECTION, 0).with(WATERLOGGED, Boolean.FALSE));
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(worldIn.getBlockState(pos).isAir()) {
            if (state.get(DISSECTION) == 0) {
                state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), 1));
            }
        }
    }

        @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(player.getHeldItemMainhand().getItem() == RegItems.WIRE_CUTTERS.get()) {
               if (worldIn instanceof ServerWorld) {
                   switch (state.get(DISSECTION)) {
                   case 0:
                       worldIn.setBlockState(pos, state.with(DISSECTION, 1));
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.ORANGE_CONDENSER.get(), 20));
                       return ActionResultType.SUCCESS;
                   case 1:
                       worldIn.setBlockState(pos, state.with(DISSECTION, 2));
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.GREEN_CONDENSER.get(), 2));
                       return ActionResultType.SUCCESS;
                   case 2:
                       worldIn.setBlockState(pos, state.with(DISSECTION, 3));
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_TRANSISTOR.get(), 5));
                       return ActionResultType.SUCCESS;
                   case 3:
                       worldIn.setBlockState(pos, state.with(DISSECTION, 4));
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_RED_MICRO.get(), 9));
                       return ActionResultType.SUCCESS;
                   case 4:
                       worldIn.setBlockState(pos, state.with(DISSECTION, 5));
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.BLACK_MICRO.get(), 36));
                       return ActionResultType.SUCCESS;
                   case 5:
                       worldIn.setBlockState(pos, RegBlocks.COPPER_CIRCUIT_EMPTY.get().getDefaultState());
                       state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_MICRO.get(), 1));
                       return ActionResultType.SUCCESS;
               }

           }
        }
        return ActionResultType.SUCCESS;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISSECTION, WATERLOGGED);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
