package ru.tesmio.blocks.decorative.lamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
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
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class StreetLamp extends BlockSideCustomModel {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public StreetLamp(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, Boolean.valueOf(false)).with(WATERLOGGED, false));
    }
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, reader, pos, context);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(2)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(3)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(1)),
        };
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if(w instanceof WorldGenRegion) return s;
        return updateState((World) w,p,s);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            if(w.isBlockPowered(p)) {
                w.playSound(null, p, RegSounds.SOUND_FLUO_LAMP.get(), SoundCategory.BLOCKS, 0.40f, 1f);
                ts = ts.with(LIT, true);
            } else if(!w.isBlockPowered(p)) {

                ts = ts.with(LIT, false);
            }
            return ts;
        }

        return s;
    }
    public VoxelShape getShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        VoxelShape SHP = Block.makeCuboidShape(0,0,4.5,16,15.75,11.5);

        switch (s.get(FACING)) {
            case EAST:
            case WEST:
                return SHP;
            case NORTH:
            case SOUTH:
                return VoxelShapeUtil.shapeRotCW90(SHP);
        }
        return VoxelShapes.fullCube();
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld();
        BlockPos p = context.getPos();
        BlockState thisBS = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        return thisBS;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT, WATERLOGGED);
    }
    @Override
    public int getLightValue(BlockState s, IBlockReader br, BlockPos p) {
        if(s.get(LIT)) {
            return 15;
        }
        return 0;
    }
}
