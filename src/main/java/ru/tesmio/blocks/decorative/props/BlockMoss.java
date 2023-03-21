package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockRotatedAllSideCM;

public class BlockMoss extends BlockRotatedAllSideCM {
    public BlockMoss() {
        super(AbstractBlock.Properties.create(Material.AIR)
                .hardnessAndResistance(0,0)
                .notSolid().sound(SoundType.WET_GRASS), 1f);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }
}
