package ru.tesmio.blocks.decorative.windows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideConnectUpDown;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

/*
Добавить партиклы при разбитии
 */
public class AlumFrame extends BlockSideConnectUpDown {
    final VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 2.5D);
    final VoxelShape EMPTY_BOXES[] = new VoxelShape[] {
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 0.5D, 2.5D),
            Block.makeCuboidShape(0D, 0D, 0D, 0.5D, 16D, 2.5D),
            Block.makeCuboidShape(15.5D, 0D, 0D, 16D, 16D, 2.5D),
            Block.makeCuboidShape(0D, 15.5D, 0D, 16D, 16D, 2.5D)
    };
    public AlumFrame(Properties properties) {
        super(properties);
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(e instanceof ProjectileEntity) {
            BlockState s2 = w.getBlockState(p);
            if(s2.getBlock() == RegBlocks.ALUM_FRAMES.get()) {
                w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                w.setBlockState(p, RegBlocks.ALUM_FRAMES_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(ENUM_VARIANT, s2.get(ENUM_VARIANT)));
            }
        }
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {
        switch (s.get(FACING)) {
            case SOUTH:
                if(s.getBlock() == RegBlocks.ALUM_FRAMES_EMPTY.get()) {
                    return VoxelShapes.or(EMPTY_BOXES[0],EMPTY_BOXES[1],EMPTY_BOXES[2],EMPTY_BOXES[3]);
                }
                return BOX;
            case NORTH:
                if(s.getBlock() == RegBlocks.ALUM_FRAMES_EMPTY.get()) {
                    return VoxelShapes.or(VoxelShapeUtil.shapeRot180(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRot180(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRot180( EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRot180( EMPTY_BOXES[3]));
                }
                return VoxelShapeUtil.shapeRot180(BOX);
            case EAST:
                if(s.getBlock() == RegBlocks.ALUM_FRAMES_EMPTY.get()) {
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCW90(EMPTY_BOXES[3]));
                }
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case WEST:
                if(s.getBlock() == RegBlocks.ALUM_FRAMES_EMPTY.get()) {
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[0]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[1]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[2]),
                            VoxelShapeUtil.shapeRotCCW90(EMPTY_BOXES[3]));
                }
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
        return VoxelShapes.fullCube();
    }

}
