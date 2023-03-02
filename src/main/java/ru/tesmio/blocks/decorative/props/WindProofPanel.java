package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;
import java.util.List;

public class WindProofPanel extends BlockSideCustomModel {
    String info;
    public WindProofPanel(Properties properties, float shadingInside, String info) {
        super(properties, shadingInside);
        this.info = info;
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(info));
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {


        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHAPE[] = new VoxelShape[] {
                Block.makeCuboidShape(0D, 0D, 4D, 4D, 16D, 12D),
                Block.makeCuboidShape(0D, 0D, 6D, 16D, 16D, 10D)
        };
        switch (state.get(FACING)) {
            case EAST:
                return VoxelShapes.or(SHAPE[0],SHAPE[1]);
            case WEST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRot180(SHAPE[0]),VoxelShapeUtil.shapeRot180(SHAPE[1]));
            case NORTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(SHAPE[0])),VoxelShapeUtil.shapeRotCW90(SHAPE[1]));
            case SOUTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(SHAPE[0]),VoxelShapeUtil.shapeRotCCW90(SHAPE[1]));

        }
        return VoxelShapes.fullCube();
    }
}
