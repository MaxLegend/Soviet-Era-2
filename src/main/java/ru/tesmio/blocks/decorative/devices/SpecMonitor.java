package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SpecMonitor extends BlockSideDevice {

    final VoxelShape BOXS[] = new VoxelShape[] {
            Block.makeCuboidShape(3,0,0,10,16,16),
            Block.makeCuboidShape(3,0,0,13,15,16),
            Block.makeCuboidShape(3,2,0,15,14,16),
            Block.makeCuboidShape(3,4,1,16,13,15)
    };
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public SpecMonitor(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(ENABLE, false));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), tr.nextInt(3,7)),
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(2,4)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(),  tr.nextInt(1,2))
        };
    }
    @Override
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        if(tr.nextInt(25) == 4) {
            return new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(),2);
        } else {
            return ItemStack.EMPTY;
        }
    }
    @Override
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if(!w.isRemote()) {
            if (w.isBlockPowered(p)) {
                w.getPendingBlockTicks().scheduleTick(p, this, 113);
                w.playSound(null, p, RegSounds.SOUND_DEVICE.get(), SoundCategory.BLOCKS, 0.10f, 1f);
            }
        }
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        w.getPendingBlockTicks().scheduleTick(p, this, 6);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            if(w.isBlockPowered(p)) {
                return s.with(ENABLE, true);
            } else {
                return s.with(ENABLE, false);
            }
        }
        return s;
    }
    @Override
    public int getLightValue(BlockState s, IBlockReader br, BlockPos p) {
        if(s.get(ENABLE)) {
            return 2;
        }
        return 0;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case WEST:
                return VoxelShapes.or(
                        BOXS[0],
                        BOXS[1],
                        BOXS[2],
                        BOXS[3]
                );
            case EAST:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(BOXS[0]),
                        VoxelShapeUtil.shapeRot180(BOXS[1]),
                        VoxelShapeUtil.shapeRot180(BOXS[2]),
                        VoxelShapeUtil.shapeRot180(BOXS[3])
                );
            case NORTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRotCW90(BOXS[0]),
                        VoxelShapeUtil.shapeRotCW90(BOXS[1]),
                        VoxelShapeUtil.shapeRotCW90(BOXS[2]),
                        VoxelShapeUtil.shapeRotCW90(BOXS[3])
                );
            case SOUTH:
                return VoxelShapes.or(
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(BOXS[0])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(BOXS[1])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(BOXS[2])),
                        VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(BOXS[3]))
                );
        }
        return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENABLE, WATERLOGGED);
    }
}