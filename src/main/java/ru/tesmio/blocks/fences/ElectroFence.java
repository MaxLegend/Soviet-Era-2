package ru.tesmio.blocks.fences;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.blocks.tumbler.ElectroFenceTumbler;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class ElectroFence extends BlockSideCustomModel {
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty LEFT = BooleanProperty.create("left");
    public static final BooleanProperty LEFT_BACK = BooleanProperty.create("left_back");
    public static final BooleanProperty RIGHT = BooleanProperty.create("right");
    public static final BooleanProperty RIGHT_BACK = BooleanProperty.create("right_back");
    public ElectroFence(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(LEFT_BACK, false)
                .with(RIGHT_BACK, false).with(LEFT, false).with(RIGHT, false).with(POWERED, false));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(state.get(POWERED)) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 5.0F);

        }
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1,3))
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape defShape = Block.makeCuboidShape(7D, 0D, 7D, 9D, 16D, 9D);
        switch (state.get(FACING)) {
            case NORTH:
                if(state.get(LEFT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(0D, 2.75D, 5.5D, 8D, 15.25D, 6D), Block.makeCuboidShape(7D, 2D, 5D, 9D, 15.5D, 9D));
                }
                if(state.get(RIGHT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(8D, 2.75D, 5.5D, 16D, 15.25D, 6D), Block.makeCuboidShape(7D, 2D, 5D, 9D, 15.5D, 9D));
                }
                if(state.get(RIGHT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(7D, 2D, 7D, 11D, 15.5D, 9D), Block.makeCuboidShape(10D, 2.75D, 7D, 10.5D, 15.25D, 16D));
                }
                if(state.get(LEFT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(5D, 2D, 7D, 8D, 15.5D, 9D), Block.makeCuboidShape(5.5D, 2.75D, 8D, 6D, 15.25D, 16D));
                }
                return defShape;
            case SOUTH:
                if(state.get(LEFT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(8D, 2.75D, 10D, 16D, 15.25D, 10.5D), Block.makeCuboidShape(7D, 2D, 9D, 9D, 15.5D, 11D));
                }
                if(state.get(RIGHT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(0D, 2.75D, 10D, 8D, 15.25D, 10.5D), Block.makeCuboidShape(7D, 2D, 9D, 9D, 15.5D, 11D));
                }
                if(state.get(LEFT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(7D, 2D, 7D, 11D, 15.5D, 9D), Block.makeCuboidShape(10D, 2.75D, 0D, 10.5D, 15.25D, 9D));                }
                if(state.get(RIGHT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(5D, 2D, 7D, 8D, 15.5D, 9D), Block.makeCuboidShape(5.5D, 2.75D, 0D, 6D, 15.25D, 9D));

                }
                return defShape;
            case EAST:
                if(state.get(LEFT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(7D, 2D, 7D, 11D, 15.5D, 9D), Block.makeCuboidShape(10D, 2.75D, 0D, 10.5D, 15.25D, 9D));
                }
                if(state.get(RIGHT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(7D, 2D, 7D, 11D, 15.5D, 9D), Block.makeCuboidShape(10D, 2.75D, 7D, 10.5D, 15.25D, 16D));
                }
                if(state.get(LEFT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(0D, 2.75D, 5.5D, 8D, 15.25D, 6D), Block.makeCuboidShape(7D, 2D, 5D, 9D, 15.5D, 9D));
                }
                if(state.get(RIGHT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(0D, 2.75D, 10D, 8D, 15.25D, 10.5D), Block.makeCuboidShape(7D, 2D, 9D, 9D, 15.5D, 11D));
                }
                return defShape;
            case WEST:
                if(state.get(RIGHT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(5D, 2D, 7D, 8D, 15.5D, 9D), Block.makeCuboidShape(5.5D, 2.75D, 0D, 6D, 15.25D, 9D));
                }
                if(state.get(LEFT)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(5D, 2D, 7D, 8D, 15.5D, 9D), Block.makeCuboidShape(5.5D, 2.75D, 8D, 6D, 15.25D, 16D));
                }
                if(state.get(RIGHT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(8D, 2.75D, 5.5D, 16D, 15.25D, 6D), Block.makeCuboidShape(7D, 2D, 5D, 9D, 15.5D, 9D));
                }
                if(state.get(LEFT_BACK)) {
                    defShape = VoxelShapes.or(defShape, Block.makeCuboidShape(8D, 2.75D, 10D, 16D, 15.25D, 10.5D), Block.makeCuboidShape(7D, 2D, 9D, 9D, 15.5D, 11D));

                }
                return defShape;
        }
        return defShape;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LEFT, RIGHT,LEFT_BACK, RIGHT_BACK, POWERED, WATERLOGGED);
    }

    public void poweredBlocks (BlockState s, World w, BlockPos p, Block targetBlock) {
        if (s.getBlock() == targetBlock && !s.get(POWERED)) {
            ArrayList<BlockPos> poss = new ArrayList();
            ArrayList<BlockPos> unposs = new ArrayList();
            poss.add(p);
            for(Direction dir : Direction.Plane.HORIZONTAL) {
                unposs.add(p.add(dir.getDirectionVec()));
            }
            while (unposs.size() > 0) {
                ArrayList<BlockPos> newSet = new ArrayList();
                for (BlockPos upp : unposs) {
                    if (w.getBlockState(upp).getBlock() == targetBlock) {
                        poss.add(upp);
                        for(Direction dir : Direction.Plane.HORIZONTAL) {
                            BlockPos pp = upp.add(dir.getDirectionVec());
                            if (!poss.contains(pp)) {
                                w.setBlockState(p, s.with(POWERED, true), 6);
                                w.setBlockState(upp, w.getBlockState(upp).with(POWERED, true),6);
                                newSet.add(pp);
                            }
                        }
                    }
                    newSet.remove(upp);
                }
                unposs = newSet;
            }
        }
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        BlockState newState = this.getDefaultState();
        BlockState newStateSouth = w.getBlockState(p.south());
        BlockState newStateNorth = w.getBlockState(p.north());
        BlockState newStateEast = w.getBlockState(p.east());
        BlockState newStateWest = w.getBlockState(p.west());
        if (w.isBlockPowered(p)) {
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
        }
        if (!w.isRemote) {
            switch (s.get(FACING)) {
                case NORTH:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.EAST)).with(LEFT, canConnect(w,p,Direction.WEST));
                    if(w.getBlockState(p.south()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.south()).get(FACING) == Direction.WEST && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.south(), newStateSouth.with(RIGHT, true), 16);
                        }
                        if (w.getBlockState(p.south()).get(FACING) == Direction.EAST && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.south(), newStateSouth.with(LEFT, true), 16);
                        }
                    }

                    w.setBlockState(p, newState);
                    return;
                case SOUTH:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.WEST)).with(LEFT, canConnect(w,p,Direction.EAST));
                    if(w.getBlockState(p.north()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.north()).get(FACING) == Direction.EAST && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.north(), newStateNorth.with(RIGHT, true), 16);
                        }
                        if (w.getBlockState(p.north()).get(FACING) == Direction.WEST && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);

                            w.setBlockState(p.north(), newStateNorth.with(LEFT, true), 16);
                        }
                    }

                    w.setBlockState(p, newState.with(FACING, Direction.SOUTH));
                    return;
                case WEST:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.NORTH)).with(LEFT, canConnect(w,p,Direction.SOUTH));
                    if(w.getBlockState(p.east()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.east()).get(FACING) == Direction.SOUTH && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.east(), newStateEast.with(RIGHT, true), 16);
                        }
                        if (w.getBlockState(p.east()).get(FACING) == Direction.NORTH && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.east(), newStateEast.with(LEFT, true), 16);
                        }
                    }
                    w.setBlockState(p, newState.with(FACING, Direction.WEST));
                    return;
                case EAST:
                    newState = newState.with(RIGHT, canConnect(w, p, Direction.SOUTH)).with(LEFT, canConnect(w,p,Direction.NORTH));
                    if(w.getBlockState(p.west()).getBlock() instanceof ElectroFence) {
                        if (w.getBlockState(p.west()).get(FACING) == Direction.NORTH && !newState.get(LEFT)) {
                            newState = newState.with(LEFT_BACK, true);
                            w.setBlockState(p.west(), newStateWest.with(RIGHT, true), 16);
                        }
                        if (w.getBlockState(p.west()).get(FACING) == Direction.SOUTH && !newState.get(RIGHT)) {
                            newState = newState.with(RIGHT_BACK, true);
                            w.setBlockState(p.west(), newStateWest.with(LEFT, true), 16);
                        }
                    }
                        w.setBlockState(p, newState.with(FACING, Direction.EAST));
            }
        }
    }
    public boolean canConnect(World w, BlockPos p, Direction facing) {
        BlockState state = w.getBlockState(p.offset(facing));
        Block block = w.getBlockState(p.offset(facing)).getBlock();

        if(block instanceof ElectroFence) {
            if(w.getBlockState(p.offset(facing)).get(FACING) == w.getBlockState(p).get(FACING)) return true;
        }
        if(block instanceof ElectroFenceTumbler) {
            if(w.getBlockState(p).get(FACING) == Direction.WEST || w.getBlockState(p).get(FACING) == Direction.EAST ) {
                if(facing != Direction.NORTH) {
                    return state.get(FACING) == Direction.SOUTH;
                }
            }
            if(w.getBlockState(p).get(FACING) == Direction.WEST || w.getBlockState(p).get(FACING) == Direction.EAST ) {
                if(facing != Direction.SOUTH) {
                    return state.get(FACING) == Direction.NORTH;
                }
            }
            if(w.getBlockState(p).get(FACING) == Direction.SOUTH || w.getBlockState(p).get(FACING) == Direction.NORTH ) {
                if(facing != Direction.EAST) {
                    return state.get(FACING) == Direction.WEST;
                }
            }
            if(w.getBlockState(p).get(FACING) == Direction.SOUTH || w.getBlockState(p).get(FACING) == Direction.NORTH ) {
                if(facing != Direction.WEST) {
                    return state.get(FACING) == Direction.EAST;
                }
            }
        }
        return false;
    }
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if (w.isBlockPowered(p)) {
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
            this.poweredBlocks(s, w, p, RegBlocks.ELECTRO_FENCE.get());
        }
    }
}
