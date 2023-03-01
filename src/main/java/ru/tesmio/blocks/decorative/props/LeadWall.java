package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegBlocks;

public class LeadWall extends BlockSideCustomModel {
    public LeadWall(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        if(s.getBlock() == RegBlocks.LEAD_WALL.get()) return Block.makeCuboidShape(0,0,2,16,16,15);
        if(s.getBlock() == RegBlocks.THIN_LEAD_WALL.get())return Block.makeCuboidShape(0,0,11,16,16,16);
        return VoxelShapes.fullCube();
    }
}
