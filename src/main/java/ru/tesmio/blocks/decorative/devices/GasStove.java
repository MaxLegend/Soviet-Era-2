package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import javax.annotation.Nullable;
import java.util.List;

public class GasStove extends BlockSideDevice {
    public static final IntegerProperty VARIANT = IntegerProperty.create("var",0,1);
    public GasStove(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("info.control_table", Integer.toString(1000)));
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
    VoxelShape SHP = Block.makeCuboidShape(0,0,0,16,15,14);
    public VoxelShape getFacingShape(BlockState s) {

        return SHP;
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        if(this == RegBlocks.ELECTRO_STOVE.get()) {
            return new ItemStack[] {
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1,4)),
                    new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), 1),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2,5))
            };
        }
        return new ItemStack[] {
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1,4)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1,3))
        };
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,VARIANT, WATERLOGGED);
    }
}
