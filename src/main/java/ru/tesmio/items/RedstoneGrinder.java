package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.core.Core;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class RedstoneGrinder extends Item {
    private static final Random RAND = new Random();
    public RedstoneGrinder() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1));
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack s, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("info.redstone_grinder"));
        tooltip.add(new TranslationTextComponent("info.uses " + this.getDamage(s) + "/" + this.getMaxDamage(s)));
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return 6400;
    }

    public void addDurability(ItemStack s, int v) {
        this.setDamage(s, this.getDamage(s) + v);
    }
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if(state.getMaterial() == Material.ANVIL || state.getMaterial() == Material.IRON) {
            if(this.getDamage(stack) == 0) {
                return 0.2F;
            }
            return 55F;
        }
        return 0.2F;
    }
    public boolean canHarvestBlock(BlockState s) {
        if(s.getBlock().getMaterialColor() == MaterialColor.IRON) {
            return true;
        } else return false;
    }
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
            if(state.getBlock().getMaterialColor() == MaterialColor.IRON) {
                this.setDamage(stack, this.getDamage(stack) - 1);
                return true;
            }
        return false;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, PlayerEntity pl, Hand h) {
        ItemStack is = pl.getHeldItem(Hand.MAIN_HAND);

            if (pl.getHeldItem(Hand.OFF_HAND).getItem() == Items.REDSTONE) {
                if (pl.getHeldItem(Hand.MAIN_HAND).getItem() == this) {
                    if(pl.isCrouching()) {
                        if (this.getDamage(is) < getMaxDamage(is)) {
                            if(pl.getHeldItem(Hand.OFF_HAND).getCount() > 10) {
                                pl.getHeldItem(Hand.OFF_HAND).shrink(10);
                                addDurability(is, 640);
                            }
                        }
                    }
                    if (this.getDamage(is) < getMaxDamage(is)) {
                        pl.getHeldItem(Hand.OFF_HAND).shrink(1);
                        addDurability(is, 64);
                    }
                    return ActionResult.resultSuccess(is);
                }
            }

        return ActionResult.resultFail(is);
    }
}
