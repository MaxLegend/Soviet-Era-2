package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class ContactWireAngle extends BlockSideCustomModel {

    public static final BooleanProperty SIGNAL = BooleanProperty.create("signal");
    public ContactWireAngle(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(SIGNAL, false));
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0,11,0,16,12,16);
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(!w.isRemote()) {
            if (e instanceof LivingEntity) {
                w.setBlockState(p, s.with(SIGNAL, true));
                w.getPendingBlockTicks().scheduleTick(p, this, 4);
                this.poweredBlocks(s, w, p, () -> new Block[]{RegBlocks.CONTACT_WIRE_INNER.get(), RegBlocks.CONTACT_WIRE.get(),RegBlocks.CONTACT_WIRE_OUTER.get()});
            }
        }
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(1,2)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1,2))
        };
    }
    public void poweredBlocks (BlockState s, World w, BlockPos p, Supplier<Block[]> targetBlock) {
        if (s.getBlock() == targetBlock && !s.get(SIGNAL)) {
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
                                w.setBlockState(p, s.with(SIGNAL, true), 6);
                                w.setBlockState(upp, w.getBlockState(upp).with(SIGNAL, true),6);
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
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, SIGNAL, WATERLOGGED);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if (s.get(SIGNAL)) {
            w.updateBlock(p.offset(f), w.getBlockState(p.offset(f)).getBlock());
        }
        return s;
    }
}
