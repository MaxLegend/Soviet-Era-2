package ru.tesmio.blocks.decorative.windows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
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
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class FactoryWindow extends BlockSideCustomModel {
    public FactoryWindow(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(e instanceof ProjectileEntity) {
            BlockState s2 = w.getBlockState(p);
            if(s2.getBlock() == RegBlocks.FACTORY_WINDOW.get()) {
                w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                w.setBlockState(p, RegBlocks.FACTORY_WINDOW_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(WATERLOGGED, s.get(WATERLOGGED)));
            }
        }
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

        }
    return VoxelShapes.fullCube();
    }
}
