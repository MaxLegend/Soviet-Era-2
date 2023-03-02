package ru.tesmio.blocks.decorative.stairs;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class BaseStairs extends StairsBlock {
    String info;
    public BaseStairs(BlockState state, Properties properties, String info) {

        super(state, properties);
        this.info = info;
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(info));
    }
}
