package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.soviet.blocks.baseblock.BlockCustomModel;
import ru.tesmio.soviet.reg.RegItems;

public class Antenn extends BlockCustomModel {

    public static final EnumProperty<BioStillage.Part> PART = EnumProperty.create("part", BioStillage.Part.class);

    public Antenn(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if (w instanceof WorldGenRegion) return s;
        return updateState((World) w, p, s);
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.RUSTY_SCRAP.get(), 1),
        };
    }

    final VoxelShape SHP = VoxelShapes.create(0.42D, 0D, 0.42D, 0.58D, 1D, 0.58D);

    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }

    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape shape = VoxelShapes.create(0.375, 0, 0.375, 0.625, 1, 0.625);
        switch (s.get(PART)) {
            case UP:
                shape = VoxelShapes.or(VoxelShapes.create(0.375, 0, 0.375, 0.625, 0.125, 0.625),
                        VoxelShapes.create(0.375, 0.25, 0.375, 0.625, 0.4375, 0.4375),
                        VoxelShapes.create(0.375, 0.25, 0.5625, 0.625, 0.4375, 0.625),
                        VoxelShapes.create(0.4375, 0.125, 0.4375, 0.5625, 0.625, 0.5625),
                        VoxelShapes.create(0.46875, 0.625, 0.46875, 0.53125, 1.125, 0.53125));
                break;
            case MID:
                shape = VoxelShapes.create(0.375, 0, 0.375, 0.625, 1, 0.625);
                break;
            case DOWN:
                shape = VoxelShapes.or(
                        VoxelShapes.create(0.3125, 0.0625, 0.3125, 0.6875, 0.125, 0.6875),
                        VoxelShapes.create(0.375, 0.0625, 0.375, 0.625, 1, 0.625),
                        VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.75));
                break;

        }
        return shape;
    }

    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            BlockState up = w.getBlockState(p.up());
            BlockState down = w.getBlockState(p.down());
            if (down.getBlock() instanceof Antenn) {
                if (up.getBlock() instanceof Antenn) {
                    return ts.with(PART, BioStillage.Part.MID);
                }
                return ts.with(PART, BioStillage.Part.UP);
            }
            if (up.getBlock() instanceof Antenn) {
                return ts.with(PART, BioStillage.Part.DOWN);
            } else {
                return ts.with(PART, BioStillage.Part.DEF);
            }

        }
        return s;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(PART, WATERLOGGED);
    }
}
