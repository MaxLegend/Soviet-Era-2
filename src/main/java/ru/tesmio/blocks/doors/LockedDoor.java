package ru.tesmio.blocks.doors;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;

import javax.annotation.Nullable;

public class LockedDoor extends DoorBlock implements IWaterLoggable {
    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public SoundEvent soundOpen;
    public LockedDoor(Properties builder) {
        super(builder);
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {

    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = stateIn.get(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.matchesBlock(this) && facingState.get(HALF) != doubleblockhalf ? stateIn.with(FACING, facingState.get(FACING)).with(OPEN, facingState.get(OPEN)).with(HINGE, facingState.get(HINGE)).with(POWERED, facingState.get(POWERED)).with(LOCKED, facingState.get(LOCKED)) : Blocks.AIR.getDefaultState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
       return true;
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return true;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, OPEN, HINGE, POWERED, LOCKED, WATERLOGGED);
    }
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if (!w.isRemote) {
            if (!pl.isCreative()) {
                getDropsWithBlock(w, p,pl);
                getAdditionDrops(w,p,getStackAddDrop(pl));
            }
        }
    }
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }

    public SoundEvent getSoundOpen() {
        return soundOpen;
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        {
            boolean isLocked = state.get(LOCKED);
            Item activeItemRight = player.getHeldItemMainhand().getItem();
            Item activeItemLeft = player.getHeldItemOffhand().getItem();
            if(isLocked) {
                if(activeItemRight == RegItems.KEY_DOOR.get() || activeItemLeft == RegItems.KEY_DOOR.get()) {
                    worldIn.playSound(null, pos, RegSounds.SOUND_LOCKED.get(), SoundCategory.BLOCKS, 0.30f, 1f);
                    state = state.cycleValue(LOCKED);
                    worldIn.setBlockState(pos, state, 10);
                    return ActionResultType.SUCCESS;
                } else return ActionResultType.FAIL;
            } else {
                if(activeItemRight == RegItems.KEY_DOOR.get() || activeItemLeft == RegItems.KEY_DOOR.get()) {
                    worldIn.playSound(null, pos, RegSounds.SOUND_LOCKED.get(), SoundCategory.BLOCKS, 0.30f, 1f);
                    state = state.cycleValue(LOCKED);

                } else {
                    worldIn.playSound(null, pos, getSoundOpen(), SoundCategory.BLOCKS, 0.40f, 1f);
                    state = state.cycleValue(OPEN);
                }
                worldIn.setBlockState(pos, state, 10);
                return ActionResultType.SUCCESS;
            }
        }

    }
}
