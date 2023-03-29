package ru.tesmio.blocks.baseblock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class BlockRotatedAxisCustomModel extends BlockRotatedAxis {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private float shadingInside;
    public BlockRotatedAxisCustomModel(float shadingInside) {
        super(AbstractBlock.Properties.create(Material.ANVIL)
                .setRequiresTool()
                .hardnessAndResistance(7f,4f)
                .notSolid().harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL));
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    this.shadingInside = shadingInside;

    }
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        if(this == RegBlocks.TUBING_HORIZONTAL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(4,6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3,8)),
            };
        } else
        if(this == RegBlocks.TUBING_VERTICAL.get()) {
            return new ItemStack[]{
                    new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(4,6)),
                    new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(3,8)),
            };
        } else       return new ItemStack[]{
                ItemStack.EMPTY
        };
    }
    public BlockRotatedAxisCustomModel(Properties p, float shadingInside) {
        super(p);
        this.shadingInside = shadingInside;
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {

                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing())).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
            } else {

                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, direction)).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
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
