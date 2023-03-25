package ru.tesmio.blocks.decorative.windows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideConnectUpDown;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

public class ModernWindow extends BlockSideConnectUpDown {

   final VoxelShape EMPTY_BOXES[] = new VoxelShape[] {
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 1D, 3D),//down 1 0
            Block.makeCuboidShape(0D, 0D, 0D, 1D, 16D, 3D),//left 1 1
            Block.makeCuboidShape(15D, 0D, 0D, 16D, 16D, 3D), //right 1 2
            Block.makeCuboidShape(0D, 15D, 0D, 16D, 16D, 3D), //up 1 3

            Block.makeCuboidShape(0D, 14D, 0.5D, 16D, 16D, 2.5D), // up 2 4
            Block.makeCuboidShape(0D, 0D, 0.5D, 16D, 2D, 2.5D), // down 2 5
            Block.makeCuboidShape(0D, 0D, 0.5D, 2D, 16D, 2.5D), // left 2 6
            Block.makeCuboidShape(14D, 0D, 0.5D, 16D, 16D, 2.5D), // right 2 7

            Block.makeCuboidShape(0D, 6D, 0.5D, 16D, 7D, 2.5D),// 8
            Block.makeCuboidShape(0D, 7D, 0.75D, 16D, 7.75D, 2.25D),//9
            Block.makeCuboidShape(0D, 13.25D, 0.75D, 16D, 14D, 2.25D),//10
            Block.makeCuboidShape(0D, 7D, 0.75D, 2.75D, 14D, 2.25D),//11
            Block.makeCuboidShape(13.25D, 7D, 0.75D, 16D, 16D, 2.25D),//12

            Block.makeCuboidShape(0D, 5D, 0.75D, 16D, 6.75D, 2.25D),//13
            Block.makeCuboidShape(13.25D, 5D, 0.75D, 16D, 16D, 2.25D), //14
            Block.makeCuboidShape(0D, 5D, 0.75D, 2.75D, 14D, 2.25D), //15
            Block.makeCuboidShape(0D, 5D, 0.5D, 16D, 6D, 2.5D), //16

            Block.makeCuboidShape(0D, 10D, 0.75D, 16D, 11D, 2.25D),//17
            Block.makeCuboidShape(13.25D, 0D, 0.75D, 16D, 10D, 2.25D), //18
            Block.makeCuboidShape(0D, 0D, 0.75D, 2.75D, 10D, 2.25D), //19
            Block.makeCuboidShape(0D, 10D, 0.5D, 16D, 11D, 2.5D), //20
            Block.makeCuboidShape(0D, 9.25D, 0.75D, 16D, 11D, 2.25D), //21
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 3D)
    };
    public ModernWindow(Properties properties) {
        super(properties, 1F);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(!(e instanceof LivingEntity)) {
            if(!(e instanceof ItemEntity)) {
                BlockState s2 = w.getBlockState(p);
                if (s2.getBlock() == RegBlocks.MODERN_WINDOW.get()) {
                    w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    w.setBlockState(p, RegBlocks.MODERN_WINDOW_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(ENUM_VARIANT, s2.get(ENUM_VARIANT)).with(WATERLOGGED, s.get(WATERLOGGED)));
                }
                if (s2.getBlock() == RegBlocks.MODERN_WINDOW_LEAF.get()) {
                    w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    w.setBlockState(p, RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(ENUM_VARIANT, s2.get(ENUM_VARIANT)).with(WATERLOGGED, s.get(WATERLOGGED)));
                }
                if (s2.getBlock() == RegBlocks.WOOD_WINDOW.get()) {
                    w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    w.setBlockState(p, RegBlocks.WOOD_WINDOW_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(ENUM_VARIANT, s2.get(ENUM_VARIANT)).with(WATERLOGGED, s.get(WATERLOGGED)));
                }
                if (s2.getBlock() == RegBlocks.WOOD_WINDOW_LEAF.get()) {
                    w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                    w.setBlockState(p, RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(ENUM_VARIANT, s2.get(ENUM_VARIANT)).with(WATERLOGGED, s.get(WATERLOGGED)));
                }
            }
        }
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand hand, BlockRayTraceResult hit) {
        Item is = pl.getHeldItem(hand).getItem();
        if(is == Items.GLASS_PANE) {
            if(s.getBlock() == RegBlocks.MODERN_WINDOW_EMPTY.get()) {
                w.setBlockState(p, RegBlocks.MODERN_WINDOW.get().getDefaultState().with(ENUM_VARIANT, s.get(ENUM_VARIANT)).with(FACING, s.get(FACING)));
                if(!pl.isCreative()) pl.getHeldItem(hand).shrink(1);
                return ActionResultType.SUCCESS;
            }
            if(s.getBlock() == RegBlocks.WOOD_WINDOW_EMPTY.get()) {
                w.setBlockState(p, RegBlocks.WOOD_WINDOW.get().getDefaultState().with(ENUM_VARIANT, s.get(ENUM_VARIANT)).with(FACING, s.get(FACING)));
                if(!pl.isCreative()) pl.getHeldItem(hand).shrink(1);
                return ActionResultType.SUCCESS;
            }
            if(s.getBlock() == RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get()) {
                w.setBlockState(p, RegBlocks.MODERN_WINDOW_LEAF.get().getDefaultState().with(ENUM_VARIANT, s.get(ENUM_VARIANT)).with(FACING, s.get(FACING)));
                if(!pl.isCreative()) pl.getHeldItem(hand).shrink(1);
                return ActionResultType.SUCCESS;
            }
            if(s.getBlock() == RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get()) {
                w.setBlockState(p, RegBlocks.WOOD_WINDOW_LEAF.get().getDefaultState().with(ENUM_VARIANT, s.get(ENUM_VARIANT)).with(FACING, s.get(FACING)));
                if(!pl.isCreative()) pl.getHeldItem(hand).shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {



        switch (s.get(FACING)) {
            case SOUTH:
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_EMPTY.get()) {
                    //настройка шейпов для низа, верха, центра, аналогично для форточки
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[3],EMPTY_BOXES[4],EMPTY_BOXES[6],EMPTY_BOXES[7]);
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(EMPTY_BOXES[0],EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[5],EMPTY_BOXES[6],EMPTY_BOXES[7]);
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.CENTER) {
                        return VoxelShapes.or(EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[6],EMPTY_BOXES[7]);
                    }
                    return VoxelShapes.or(EMPTY_BOXES[0],EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[3],EMPTY_BOXES[4],EMPTY_BOXES[5],EMPTY_BOXES[6],EMPTY_BOXES[7]);
                }
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[0],EMPTY_BOXES[5]
                                ,EMPTY_BOXES[6],EMPTY_BOXES[7],EMPTY_BOXES[17],EMPTY_BOXES[18],EMPTY_BOXES[19],EMPTY_BOXES[20],EMPTY_BOXES[21]);

                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[3],EMPTY_BOXES[4]
                                ,EMPTY_BOXES[6],EMPTY_BOXES[7],EMPTY_BOXES[13],EMPTY_BOXES[10],EMPTY_BOXES[15],EMPTY_BOXES[14],EMPTY_BOXES[16]);
                    }
                    return VoxelShapes.or(EMPTY_BOXES[0],EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[3],EMPTY_BOXES[4]
                            ,EMPTY_BOXES[5],EMPTY_BOXES[6],EMPTY_BOXES[7],EMPTY_BOXES[8],EMPTY_BOXES[9],EMPTY_BOXES[10],EMPTY_BOXES[11],EMPTY_BOXES[12]);
                }
                return EMPTY_BOXES[22];
            case NORTH:
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_EMPTY.get()) {

                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.CENTER) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]));
                }
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[17]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[18]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[19]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[20]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[21]));

                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[13]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[10]),
                                VoxelShapeUtil.shapeRot180(EMPTY_BOXES[15]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[14]),
                                VoxelShapeUtil.shapeRot180( EMPTY_BOXES[16]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[7]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[8]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[9]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[10]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[11]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[12]));
                }
                return VoxelShapeUtil.shapeRot180(EMPTY_BOXES[22]);
            case EAST:
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.CENTER) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]));
                }
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[17]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[18]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[19]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[20]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[21]));

                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[13]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[10]),
                                VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[15]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[14]),
                                VoxelShapeUtil.shapeRotCW90( EMPTY_BOXES[16]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[7]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[8]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[9]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[10]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[11]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[12]));
                }
                return VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[22]);
            case WEST:
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]));
                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.CENTER) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]));
                }
                if(s.getBlock() == RegBlocks.MODERN_WINDOW_LEAF_EMPTY.get() || s.getBlock() == RegBlocks.WOOD_WINDOW_LEAF_EMPTY.get()) {
                    if(s.get(ENUM_VARIANT) == EnumVariant.DOWN_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[0]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[5]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[17]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[18]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[19]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[20]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[21]));

                    }
                    if(s.get(ENUM_VARIANT) == EnumVariant.UP_CONNECT) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[3]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[4]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[7]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[13]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[10]),
                                VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[15]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[14]),
                                VoxelShapeUtil.shapeRotCCW90( EMPTY_BOXES[16]));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[3]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[4]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[5]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[6]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[7]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[8]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[9]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[10]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[11]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[12]));
                }
                return VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[22]);
        }
        return VoxelShapes.fullCube();
    }
}
