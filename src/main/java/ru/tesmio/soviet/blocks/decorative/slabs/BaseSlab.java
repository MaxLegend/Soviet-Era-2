package ru.tesmio.soviet.blocks.decorative.slabs;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.soviet.blocks.baseblock.BlockForFacingCustomModel;

import javax.annotation.Nullable;
import java.util.List;

public class BaseSlab extends BlockForFacingCustomModel {
    public final VoxelShape SHP2 = Block.makeCuboidShape(0, 0, 0, 16, 16, 8);
    String info;

    public BaseSlab(String info) {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(1f, 4f)
                .notSolid(), 1f);
        this.info = info;
    }

    public boolean disableJSONDrop() {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(info));
    }

    public VoxelShape getFacingShape(BlockState s) {
        return this.SHP2;
    }

    @Override
    public VoxelShape getRenderShape(BlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return VoxelShapes.empty();
    }
}
