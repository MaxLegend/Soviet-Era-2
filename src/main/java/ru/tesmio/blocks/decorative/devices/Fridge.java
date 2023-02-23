package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

import javax.annotation.Nullable;

public class Fridge extends BlockSideCustomModel {
    public static final EnumProperty<EnumPart> HALF = EnumProperty.create("half", EnumPart.class);
    public Fridge(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(HALF, EnumPart.LOWER).with(WATERLOGGED, false));
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return state.get(HALF) == EnumPart.LOWER ? blockstate.isSolidSide(worldIn, blockpos, Direction.UP) : blockstate.matchesBlock(this);
    }
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && player.isCreative()) {
            removeBottomHalf(worldIn, pos, state, player);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    protected void removeBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        EnumPart doubleblockhalf = state.get(HALF);
        if (doubleblockhalf == EnumPart.UPPER) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == EnumPart.LOWER) {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }
        if (doubleblockhalf == EnumPart.LOWER) {
            BlockPos blockpos = pos.up();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == EnumPart.UPPER) {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }
    }
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), state.with(HALF, EnumPart.UPPER), 3);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, EnumPart.LOWER);
        } else {
            return null;
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, WATERLOGGED);
    }
    public enum EnumPart implements IStringSerializable {
        UPPER("upper"),
        LOWER("lower");
        private final String name;
        EnumPart(String name) {this.name = name;}
        @Override
        public String getString() {
            return this.name;
        }
    }
}
