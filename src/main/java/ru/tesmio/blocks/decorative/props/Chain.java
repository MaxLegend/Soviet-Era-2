package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.BlockCustomModel;

public class Chain extends BlockCustomModel {

    public static final EnumProperty<BioStillage.Part> PART = EnumProperty.create("part", BioStillage.Part.class);

    public Chain(Properties properties) {
        super(properties);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.create(0.42D, 0D, 0.42D, 0.58D, 1D, 0.58D);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            BlockState up = w.getBlockState(p.up());
            BlockState down = w.getBlockState(p.down());
            if(down.getBlock() instanceof Chain) {
                if(up.getBlock() instanceof Chain) {
                    return ts.with(PART, BioStillage.Part.MID);
                }
                return ts.with(PART, BioStillage.Part.UP);
            }
            if(up.getBlock() instanceof Chain) {
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
