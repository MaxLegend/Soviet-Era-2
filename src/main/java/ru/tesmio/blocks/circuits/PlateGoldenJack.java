package ru.tesmio.blocks.circuits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockCircuit;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class PlateGoldenJack extends BlockCircuit {
    protected static VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PlateGoldenJack(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.FALSE));
    }

    public IItemProvider getDrop() {
        return this;
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(player.getHeldItemMainhand().getItem() == RegItems.WIRE_CUTTERS.get()) {
            if (worldIn instanceof ServerWorld) {
                player.getHeldItemMainhand().getItem().damageItem(player.getHeldItemMainhand(), 14, player, (entity) -> {
                    player.sendBreakAnimation(EquipmentSlotType.MAINHAND);});
                        worldIn.setBlockState(pos, RegBlocks.PLATE_GOLDEN_JACKS_EMPTY.get().getDefaultState());
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.YELLOW_JACK.get(), 14));
                player.getHeldItemMainhand().damageItem(14, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;


            }
        }
        return ActionResultType.SUCCESS;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}