package ru.tesmio.blocks.decorative.windows;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class FactoryWindow extends BlockSideCustomModel {
    public FactoryWindow(float shadingInside) {
        super(AbstractBlock.Properties.create(Material.GLASS)
                .setRequiresTool()
                .hardnessAndResistance(1f,2f)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.GLASS), shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(!(e instanceof LivingEntity)) {
            if(!(e instanceof ItemEntity)) {
                BlockState s2 = w.getBlockState(p);
                if (s2.getBlock() == RegBlocks.FACTORY_WINDOW.get()) {
                    w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    w.setBlockState(p, RegBlocks.FACTORY_WINDOW_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(WATERLOGGED, s.get(WATERLOGGED)));
                }
            }
        }
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand hand, BlockRayTraceResult hit) {
        Item is = pl.getHeldItem(hand).getItem();
        if(is == Items.BLACK_STAINED_GLASS_PANE) {
            if(s.getBlock() == RegBlocks.FACTORY_WINDOW_EMPTY.get()) {
                w.setBlockState(p, RegBlocks.FACTORY_WINDOW.get().getDefaultState().with(FACING, s.get(FACING)));
                if(!pl.isCreative()) pl.getHeldItem(hand).shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {
        if(s.getBlock() == RegBlocks.FACTORY_WINDOW.get()) {
            switch (s.get(FACING)) {
                case SOUTH:
                    return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D));
                case WEST:
                    return VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D));
                case NORTH:
                    return VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D)));
                case EAST:
                    return Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 16D);
            }
        }
        //настроить шейпы для разбитого окна
        if(s.getBlock() == RegBlocks.FACTORY_WINDOW_EMPTY.get()) {
            switch (s.get(FACING)) {
                case SOUTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 0.5D, 16D)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 15.5D, 0D, 2D, 16D, 16D)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D)),
                            VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D)));
                case WEST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 0D, 0D, 2D, 0.5D, 16D)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 15.5D, 0D, 2D, 16D, 16D)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D)),
                            VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D)));
                case NORTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 0.5D, 16D))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 15.5D, 0D, 2D, 16D, 16D))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D))),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D))));
                case EAST:
                    return VoxelShapes.or(
                            Block.makeCuboidShape(0D, 0D, 0D, 2D, 0.5D, 16D),
                            Block.makeCuboidShape(0D, 15.5D, 0D, 2D, 16D, 16D),
                            Block.makeCuboidShape(0D, 0D, 0D, 2D, 16D, 0.5D),
                            Block.makeCuboidShape(0D, 0D, 15.5D, 2D, 16D, 16D));
            }
        }
    return VoxelShapes.fullCube();
    }
}
