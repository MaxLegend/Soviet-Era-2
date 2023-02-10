package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import javax.annotation.Nullable;
import java.util.List;

public class ControlTable extends BlockSideCustomModel implements IWaterLoggable {
    final VoxelShape BOXS[] = new VoxelShape[] {
            Block.makeCuboidShape(8D, 0D, 0D, 16D, 16D, 16D), Block.makeCuboidShape(0D, 0D, 0D, 8D, 16D, 16D)
    ,Block.makeCuboidShape(0D, 0D, 8D, 16D, 16D, 16D),Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 8D), VoxelShapes.or(Block.makeCuboidShape(8D, 0D, 0D, 16D, 16D, 16D),
            Block.makeCuboidShape(0.5D, 10D, 0D, 16D, 13D, 16D)),VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 0D, 8D, 16D, 16D),
            Block.makeCuboidShape(0.5D, 10D, 0D, 15.5D, 13D, 16D)), VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 8D, 16D, 16D, 16D),
            Block.makeCuboidShape(0D, 10D, 0.5D, 16D, 13D, 16D)),VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 8D),
            Block.makeCuboidShape(0D, 10D, 0D, 16D, 13D, 15.5D)) };
    final VoxelShape LOWER = Block.makeCuboidShape(0D, 0D, 0D, 16D, 15.5D, 16D);
    public static final IntegerProperty VARIANT = IntegerProperty.create("var",0,2);
    public ControlTable(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(VARIANT, Integer.valueOf(0)).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("info.control_table", Integer.toString(1000)));
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return  stateIn;
          }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(state.getBlock() == RegBlocks.CONTROL_PANEL_UP.get()) {
            switch (state.get(FACING)) {
                case WEST:
                    return BOXS[0];
                case EAST:
                    return BOXS[1];
                case NORTH:
                    return BOXS[2];
                case SOUTH:
                    return BOXS[3];
            }
        }
        if(state.getBlock() == RegBlocks.CONTROL_PANEL_DOWN.get()) {
            switch (state.get(FACING)) {
                case WEST:
                    return  BOXS[4];
                case EAST:
                    return BOXS[5];
                case NORTH:
                    return BOXS[6];
                case SOUTH:
                    return BOXS[7];
            }
        }
        return BOXS[0];
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item activeItemRight = player.getHeldItemMainhand().getItem();
        Item activeItemLeft = player.getHeldItemOffhand().getItem();
        if(activeItemRight == RegItems.VARIANT_ITEM.get() || activeItemLeft == RegItems.VARIANT_ITEM.get()) {
            state = state.cycleValue(VARIANT);
            worldIn.setBlockState(pos, state);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,VARIANT, WATERLOGGED);
    }
}
