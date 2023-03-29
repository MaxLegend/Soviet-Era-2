package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class Televisor extends BlockSideDevice {
    final VoxelShape BOX = Block.makeCuboidShape(3,0.5,0,13,13.5,16);
    public Televisor(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        if(worldIn instanceof WorldGenRegion) return stateIn;
        return updateState((World) worldIn, currentPos,stateIn);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.WOOD_SCRAP.get(), tr.nextInt(1,3)),
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(1,2))
        };
    }

    @Override
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        if(tr.nextInt(34) == 4) {
            return new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), 1);
        }
        return ItemStack.EMPTY;
    }

    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (w.isBlockPowered(p)) {
            w.playSound(null, p, RegSounds.SOUND_SPARKING.get(), SoundCategory.BLOCKS, 0.05f, 1f);
        }
        return s;
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:
                return BOX;
            case WEST:
                return VoxelShapeUtil.shapeRot180(BOX);
            case NORTH:
                return VoxelShapeUtil.shapeRotCW90(BOX);
            case SOUTH:
                return VoxelShapeUtil.shapeRotCCW90(BOX);
        }
    return VoxelShapes.fullCube();
    }
}
