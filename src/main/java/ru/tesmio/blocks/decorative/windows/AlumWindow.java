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
import ru.tesmio.blocks.baseblock.BlockSideConnectUDLR;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.HashMap;
import java.util.Map;
//добавить фичу с вставкой\выниманием окон с помощью спецпредмета
public class AlumWindow extends BlockSideConnectUDLR {
    final VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16D, 3.04D);

    VoxelShape[] EMPTY_BOXES = new VoxelShape[] {
            Block.makeCuboidShape(0D, 8.25D, 0.75D, 16D, 10.25D, 2.29D), //bridge
            Block.makeCuboidShape(0D, 0D, 0D, 16D, 1D, 3.04D), //up
            Block.makeCuboidShape(0D, 0D, 0D, 1D, 16D, 3.04D), //right
            Block.makeCuboidShape(15D, 0D, 0D, 16D, 16D, 3.04D), //left
            Block.makeCuboidShape(0D, 15D, 0D, 16D, 16D, 3.04D), //down
            Block.makeCuboidShape(0D, 6.75D, 0.75D, 16D, 8.75D, 2.29D)
    };
    Map<String, VoxelShape> sm = new HashMap<>();
    public AlumWindow(Properties properties) {
        super(properties);
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(e instanceof ProjectileEntity) {
            BlockState s2 = w.getBlockState(p);
            if(s2.getBlock() == RegBlocks.ALUM_WINDOW.get()) {
                w.playSound(null, p, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 1, 1);
                w.setBlockState(p, RegBlocks.ALUM_WINDOW_EMPTY.get().getDefaultState().with(FACING, s2.get(FACING)).with(PANE_DOWN, s2.get(PANE_DOWN)).with(PANE_UP, s2.get(PANE_UP))
                        .with(PANE_LEFT, s2.get(PANE_LEFT)).with(PANE_RIGHT, s2.get(PANE_RIGHT)));
            }
        }
    }
    public void putMapVoxelShape() {
        sm.put("bridge", EMPTY_BOXES[0]);
        sm.put("up", EMPTY_BOXES[1]);
        sm.put("right", EMPTY_BOXES[2]);
        sm.put("left", EMPTY_BOXES[3]);
        sm.put("down", EMPTY_BOXES[4]);
        sm.put("bridge_c", EMPTY_BOXES[5]);
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext context) {
        BlockState bs = w.getBlockState(p);
        putMapVoxelShape();
        if (bs.getBlock() == RegBlocks.ALUM_WINDOW_EMPTY.get()) {
            boolean isR = bs.get(PANE_RIGHT);
            boolean isL = bs.get(PANE_LEFT);
            boolean isU = bs.get(PANE_UP);
            boolean isD = bs.get(PANE_DOWN);
            switch (s.get(FACING)) {
                case NORTH:
                    if (isR && isL && isU && isD) {
                        return VoxelShapes.empty();
                    }
                    if (isL && isU && isD) {
                        return VoxelShapeUtil.shapeRot180(sm.get("left"));
                    }
                    if (isR && isU && isD) {
                        return sm.get("right");
                    }
                    if (isR && isL && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180(sm.get("down")));
                    }
                    if (isR && isL && isU) {
                        return VoxelShapeUtil.shapeRot180(sm.get("up"));
                    }
                    if (isU && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("up")),
                                VoxelShapeUtil.shapeRot180(sm.get("right")));
                    }
                    if (isU && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("up")),
                                VoxelShapeUtil.shapeRot180(sm.get("left")));
                    }
                    if (isD && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180( sm.get("down")),
                                VoxelShapeUtil.shapeRot180( sm.get("right")));
                    }
                    if (isD && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180( sm.get("down")),
                                VoxelShapeUtil.shapeRot180( sm.get("left")));
                    }
                    if (isU && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("left")),
                                VoxelShapeUtil.shapeRot180(sm.get("right")));
                    }
                    if (isR && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180( sm.get("down")),
                                VoxelShapeUtil.shapeRot180(sm.get("up")));
                    }
                    if (isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180(sm.get("down")),
                                VoxelShapeUtil.shapeRot180(sm.get("left")),
                                VoxelShapeUtil.shapeRot180( sm.get("up")));
                    }
                    if (isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180( sm.get("down")),
                                VoxelShapeUtil.shapeRot180(sm.get("right")),
                                VoxelShapeUtil.shapeRot180(sm.get("up")));
                    }
                    if (isU) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("left")),
                                VoxelShapeUtil.shapeRot180(sm.get("right")),
                                VoxelShapeUtil.shapeRot180(sm.get("up")));
                    }
                    if (isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRot180(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRot180( sm.get("left")),
                                VoxelShapeUtil.shapeRot180(sm.get("right")),
                                VoxelShapeUtil.shapeRot180(sm.get("down")));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(sm.get("down")),
                            VoxelShapeUtil.shapeRot180(sm.get("bridge")),
                            VoxelShapeUtil.shapeRot180( sm.get("left")),
                            VoxelShapeUtil.shapeRot180( sm.get("right")),
                            VoxelShapeUtil.shapeRot180(sm.get("up")));
                case SOUTH:
                    if (isR && isL && isU && isD) {
                        return VoxelShapes.empty();
                    }
                    if (isL && isU && isD) {
                        return sm.get("right");
                    }
                    if (isR && isU && isD) {
                        return sm.get("left");
                    }
                    if (isR && isL && isD) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"));
                    }
                    if (isR && isL && isU) {
                        return sm.get("up");
                    }
                    if (isU && isR) {
                        return VoxelShapes.or(
                                sm.get("up"),
                                sm.get("left"));
                    }
                    if (isU && isL) {
                        return VoxelShapes.or(
                                sm.get("up"),
                                sm.get("right"));
                    }
                    if (isD && isR) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"),
                                sm.get("left"));
                    }
                    if (isD && isL) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"),
                                sm.get("right"));
                    }
                    if (isU && isD) {
                        return VoxelShapes.or(
                                sm.get("left"),
                                sm.get("right"));
                    }
                    if (isR && isL) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"),
                                sm.get("up"));
                    }
                    if (isL) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"),
                                sm.get("right"),
                                sm.get("up"));
                    }
                    if (isR) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("down"),
                                sm.get("left"),
                                sm.get("up"));
                    }
                    if (isU) {
                        return VoxelShapes.or(
                                sm.get("left"),
                                sm.get("right"),
                                sm.get("up"));
                    }
                    if (isD) {
                        return VoxelShapes.or(
                                sm.get("bridge_c"),
                                sm.get("left"),
                                sm.get("right"),
                                sm.get("down"));
                    }
                    return VoxelShapes.or(
                            sm.get("down"),
                            sm.get("bridge"),
                            sm.get("left"),
                            sm.get("right"),
                            sm.get("up"));
                case WEST:
                    if (isR && isL && isU && isD) {
                        return VoxelShapes.empty();
                    }
                    if (isL && isU && isD) {
                        return VoxelShapeUtil.shapeRotCCW90(sm.get("right"));
                    }
                    if (isR && isU && isD) {
                        return sm.get("left");
                    }
                    if (isR && isL && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("down")));
                    }
                    if (isR && isL && isU) {
                        return VoxelShapeUtil.shapeRotCCW90(sm.get("up"));
                    }
                    if (isU && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("up")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("left")));
                    }
                    if (isU && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("up")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("right")));
                    }
                    if (isD && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("left")));
                    }
                    if (isD && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("right")));
                    }
                    if (isU && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("right")));
                    }
                    if (isR && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("up")));
                    }
                    if (isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("down")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("up")));
                    }
                    if (isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("up")));
                    }
                    if (isU) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("up")));
                    }
                    if (isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCCW90( sm.get("left")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCCW90(sm.get("down")));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCCW90(sm.get("down")),
                            VoxelShapeUtil.shapeRotCCW90(sm.get("bridge")),
                            VoxelShapeUtil.shapeRotCCW90( sm.get("left")),
                            VoxelShapeUtil.shapeRotCCW90( sm.get("right")),
                            VoxelShapeUtil.shapeRotCCW90(sm.get("up")));
                case EAST:
                    if (isR && isL && isU && isD) {
                        return VoxelShapes.empty();
                    }
                    if (isL && isU && isD) {
                        return VoxelShapeUtil.shapeRotCW90(sm.get("right"));
                    }
                    if (isR && isU && isD) {
                        return sm.get("left");
                    }
                    if (isR && isL && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("down")));
                    }
                    if (isR && isL && isU) {
                        return VoxelShapeUtil.shapeRotCW90(sm.get("up"));
                    }
                    if (isU && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("up")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("left")));
                    }
                    if (isU && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("up")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("right")));
                    }
                    if (isD && isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("left")));
                    }
                    if (isD && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("right")));
                    }
                    if (isU && isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("right")));
                    }
                    if (isR && isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("up")));
                    }
                    if (isL) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90( sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("down")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("up")));
                    }
                    if (isR) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("down")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("up")));
                    }
                    if (isU) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("left")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("up")));
                    }
                    if (isD) {
                        return VoxelShapes.or(
                                VoxelShapeUtil.shapeRotCW90(sm.get("bridge_c")),
                                VoxelShapeUtil.shapeRotCW90( sm.get("left")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("right")),
                                VoxelShapeUtil.shapeRotCW90(sm.get("down")));
                    }
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(sm.get("down")),
                            VoxelShapeUtil.shapeRotCW90(sm.get("bridge")),
                            VoxelShapeUtil.shapeRotCW90( sm.get("left")),
                            VoxelShapeUtil.shapeRotCW90( sm.get("right")),
                            VoxelShapeUtil.shapeRotCW90(sm.get("up")));
            }

        } else {
            switch (s.get(FACING)) {
                case SOUTH:
                    return BOX;
                case NORTH:
                    return VoxelShapeUtil.shapeRot180(BOX);
                case WEST:
                    return VoxelShapeUtil.shapeRotCCW90(BOX);
                case EAST:
                    return VoxelShapeUtil.shapeRotCW90(BOX);
            }
        }
        return BOX;
    }
}
