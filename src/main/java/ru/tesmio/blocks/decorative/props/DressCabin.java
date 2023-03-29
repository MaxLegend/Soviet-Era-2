package ru.tesmio.blocks.decorative.props;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.blocks.decorative.devices.Fridge;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;

public class DressCabin extends BlockSideCustomModel {
    public static final EnumProperty<Fridge.EnumPart> HALF = EnumProperty.create("half", Fridge.EnumPart.class);
    public DressCabin(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(HALF, Fridge.EnumPart.LOWER).with(WATERLOGGED, false));
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return state.get(HALF) == Fridge.EnumPart.LOWER ? blockstate.isSolidSide(worldIn, blockpos, Direction.UP) : blockstate.matchesBlock(this);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1,2))
        };
    }
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && player.isCreative()) {
            removeBottomHalf(worldIn, pos, state, player);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    protected void removeBottomHalf(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        Fridge.EnumPart doubleblockhalf = state.get(HALF);
        if (doubleblockhalf == Fridge.EnumPart.UPPER) {
            BlockPos blockpos = pos.down();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == Fridge.EnumPart.LOWER) {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }
        if (doubleblockhalf == Fridge.EnumPart.LOWER) {
            BlockPos blockpos = pos.up();
            BlockState blockstate = world.getBlockState(blockpos);
            if (blockstate.getBlock() == state.getBlock() && blockstate.get(HALF) == Fridge.EnumPart.UPPER) {
                world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                world.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
            }
        }
    }
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos.up(), state.with(HALF, Fridge.EnumPart.UPPER), 3);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockpos = context.getPos();
        if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
            return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, Fridge.EnumPart.LOWER);
        } else {
            return null;
        }
    }
    @Override
    public VoxelShape getCollisionShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(s,worldIn,pos,context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] BOXS = new VoxelShape[]{
                Block.makeCuboidShape(0,0,1,16,4,15),
                Block.makeCuboidShape(0,0,1.25,1,16,14.75),
                Block.makeCuboidShape(15,0,1.25,16,16,14.75),
        };
        if (state.get(HALF) == Fridge.EnumPart.LOWER) {
        switch (state.get(FACING)) {

            case SOUTH:
            case NORTH:
                return VoxelShapes.or(BOXS[0],BOXS[1],BOXS[2]);
            case WEST:
            case EAST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(BOXS[0]),VoxelShapeUtil.shapeRotCW90(BOXS[1]),VoxelShapeUtil.shapeRotCW90(BOXS[2]));
        }
        }
        if (state.get(HALF) == Fridge.EnumPart.UPPER) {
            switch (state.get(FACING)) {
                case SOUTH:
                case NORTH:
                    return VoxelShapes.or(BOXS[1],BOXS[2]);
                case WEST:
                case EAST:
                    return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(BOXS[1]),VoxelShapeUtil.shapeRotCW90(BOXS[2]));
            }
        }
        return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, WATERLOGGED);
    }
}
