package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.BaseBlock;
import ru.tesmio.reg.RegItems;
//решить вопрос с рендером стороны
public class ToxicAir extends BaseBlock {
    public ToxicAir(Properties p_i48451_1_) {
        super(p_i48451_1_);
    }
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if(e instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) e;
        if(player.inventory.armorInventory.get(3).getItem() == RegItems.SUIT_GAS_MASK.get()) {

        }else e.attackEntityFrom(DamageSource.WITHER, 1);
        }

    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
        return adjacentBlockState.matchesBlock(this) ? true : super.isSideInvisible(state, adjacentBlockState, side);
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
}
