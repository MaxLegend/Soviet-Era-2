package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegItems;

import javax.annotation.Nullable;
import java.util.List;

public class ControlBlock extends BlockSideCustomModel {
    public static final IntegerProperty VARIANT = IntegerProperty.create("var",0,9);

    public ControlBlock(float shadingInside) {
        super(AbstractBlock.Properties.create(Material.IRON)
                .setRequiresTool()
                .hardnessAndResistance(1f,4f)
                .notSolid()
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL), shadingInside);
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

    public VoxelShape getFacingShape(BlockState s) {
        VoxelShape SHP = Block.makeCuboidShape(0, 0, 0, 16, 16, 14.5);
        VoxelShape SLIM_SHP =Block.makeCuboidShape(0, 0, 0, 16, 7, 14.5);
        if(s.get(VARIANT) == 3 || s.get(VARIANT) == 7|| s.get(VARIANT) == 8|| s.get(VARIANT) == 9) return SLIM_SHP;
        return SHP;
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,VARIANT, WATERLOGGED);
    }
}
