package ru.tesmio.blocks.doors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.reg.RegSounds;

import javax.annotation.Nullable;

public class RailingDoorBlock extends LockedDoor implements IWaterLoggable{

    public RailingDoorBlock(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, Boolean.valueOf(false)).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(LOCKED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED, WATERLOGGED);
    }
    public SoundEvent getSoundOpen() {
        return RegSounds.SOUND_METAL_DOOR.get();
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        spawnDrops(state, worldIn, pos, te, player, stack);
    }

   final VoxelShape upperFrame = VoxelShapes.or(Block.makeCuboidShape(7D, 16D, 0D, 9D, 15.5D, 16D),
            Block.makeCuboidShape(7D, 0D, 15.5D, 9D, 16D, 16D),
            Block.makeCuboidShape(7D, 0D, 0D, 9D, 16D, 0.5D));
    final VoxelShape lowerFrame = VoxelShapes.or(Block.makeCuboidShape(7D, 0D, 0D, 9D, 0.5D, 16D),
            Block.makeCuboidShape(7D, 0D, 15.5D, 9D, 16D, 16D),
            Block.makeCuboidShape(7D, 0D, 0D, 9D, 16D, 0.5D));

    final  VoxelShape lowerFrame2 = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 9D, 16D, 0.5D, 7D),
            Block.makeCuboidShape(15.5D, 0D, 9D, 16D, 16D, 7D),
            Block.makeCuboidShape(0D, 0D, 9D, 0.5D, 16D, 7D));

    final VoxelShape upperFrame2 = VoxelShapes.or(Block.makeCuboidShape(0D, 16D, 9D, 16D, 15.5D, 7D),
            Block.makeCuboidShape(15.5D, 0D, 9D, 16D, 16D, 7D),
            Block.makeCuboidShape(0D, 0D, 9D, 0.5D, 16D, 7D));
    VoxelShape door;
    final VoxelShape def = VoxelShapes.or(Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 16D));
    final VoxelShape def2 = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 7D, 16D, 16.0D, 9D));

        @Override
        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
            Direction direction = state.get(FACING);
            boolean isOpen = state.get(OPEN);
            boolean isHingeRight = state.get(HINGE) == DoorHingeSide.RIGHT;
           boolean isLower = state.get(HALF) == DoubleBlockHalf.LOWER;
            switch (direction) {
                case EAST:
                    if (isOpen) {
                        if (isHingeRight) {
                            door = Block.makeCuboidShape(7D, 0D, 11.5D, 9D, 16.0D, 16D);
                        } else {
                            door = Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 4.5D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame, door);
                        } else {
                            return VoxelShapes.or(upperFrame, door);
                        }
                    } else {
                        return def;
                    }
                case WEST:
                default:
                    if (isOpen) {
                        if (!isHingeRight) {
                            door = Block.makeCuboidShape(7D, 0D, 11.5D, 9D, 16.0D, 16D);
                        } else {
                            door = Block.makeCuboidShape(7D, 0D, 0D, 9D, 16.0D, 4.5D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame, door);
                        } else {
                            return VoxelShapes.or(upperFrame, door);
                        }
                    } else {
                        return def;
                    }
                case SOUTH:
                    if (isOpen) {
                        if (!isHingeRight) {
                            door = Block.makeCuboidShape(11.5D, 0D, 7D, 16D, 16.0D, 9D);
                        } else {
                            door = Block.makeCuboidShape(0D, 0D, 7D, 4.5D, 16.0D, 9D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame2, door);
                        } else {
                            return VoxelShapes.or(upperFrame2, door);
                        }
                    } else {
                        return def2;
                    }
                case NORTH:
                    if (isOpen) {
                        if (isHingeRight) {
                            door = Block.makeCuboidShape(11.5D, 0D, 7D, 16D, 16.0D, 9D);
                        } else {
                            door = Block.makeCuboidShape(0D, 0D, 7D, 4.5D, 16.0D, 9D);
                        }
                        if (isLower) {
                            return VoxelShapes.or(lowerFrame2, door);
                        } else {
                            return VoxelShapes.or(upperFrame2, door);
                        }
                    } else {
                        return def2;
                    }
            }
        }
}
