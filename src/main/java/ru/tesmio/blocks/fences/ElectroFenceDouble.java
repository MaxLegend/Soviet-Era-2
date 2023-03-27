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
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockSideConnect;
import ru.tesmio.blocks.tumbler.ElectroFenceTumbler;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ElectroFenceDouble extends BlockSideConnect {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public ElectroFenceDouble(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(IS_EAST, false).with(IS_SOUTH, false).with(IS_NORTH, false).with(IS_WEST, false)
                .with(WATERLOGGED, false).with(POWERED, false));
        this.collisionShapes = this.makeShapes(1.5F, 2.1F, 16, 0.0F, 16);
        this.shapes = this.makeShapes(1.5F, 2.1F, 16, 0.0F, 15);
    }

    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if (w.isBlockPowered(p)) {
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
            this.poweredBlocks(s, w, p, RegBlocks.ELECTRO_FENCE_DOUBLE.get());
        }
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        BlockState newState = s.getBlockState();
        if (w.isBlockPowered(p)) {
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
        } else {
            w.setBlockState(p, s.with(POWERED, false));
        }

    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2,5)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(2,5))
        };
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
    @Override
    public boolean canConnect(BlockState state, boolean isSideSolid, Direction direction) {
        Block block = state.getBlock();
        boolean b1 = block instanceof ElectroFenceDouble;
        boolean b2 = block instanceof ElectroFenceTumbler;
        return !cannotAttach(block) && isSideSolid || b1 || b2 ;
    }
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(state.get(POWERED)) {
            entityIn.attackEntityFrom(DamageSource.CACTUS, 5.0F);

        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(IS_NORTH, IS_EAST, IS_WEST, IS_SOUTH,POWERED, WATERLOGGED);
    }
}
