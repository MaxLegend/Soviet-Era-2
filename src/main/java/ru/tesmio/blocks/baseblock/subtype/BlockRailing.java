package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModelInfo;

public class BlockRailing extends BlockRotatedAxisCustomModelInfo {
    public final VoxelShape SHP2 = Block.makeCuboidShape(0,0,0,16,16,8);
    public BlockRailing(Properties builder, String info, float sI) {
        super(builder, info, sI);
    }

    @Override
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(e instanceof LivingEntity) {
            e.attackEntityFrom(DamageSource.CACTUS, 1.0F);
        }
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return SHP2;
    }
}
