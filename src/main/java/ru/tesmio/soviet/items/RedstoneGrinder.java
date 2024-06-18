package ru.tesmio.soviet.items;

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
import org.jetbrains.annotations.NotNull;
import ru.tesmio.soviet.core.Core;

import javax.annotation.Nullable;
import java.util.List;

public class RedstoneGrinder extends Item {
    public RedstoneGrinder() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1));
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(@NotNull ItemStack s, @Nullable World worldIn, List<ITextComponent> tooltip, @NotNull ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("info.redstone_grinder"));
        tooltip.add(new TranslationTextComponent(new TranslationTextComponent("info.uses ").getString() + this.getDamage(s) + "/" + this.getMaxDamage(s)));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 6400;
    }

    public void addDurability(ItemStack s, int v) {
        this.setDamage(s, this.getDamage(s) + v);
    }

    public float getDestroySpeed(@NotNull ItemStack stack, BlockState state) {
        if (state.getMaterial() == Material.ANVIL || state.getMaterial() == Material.IRON) {
            if (this.getDamage(stack) == 0) {
                return 0.2F;
            }
            return 8F;
        }
        return 0.2F;
    }

    public boolean canHarvestBlock(BlockState s) {
        return s.getBlock().getMaterialColor() == MaterialColor.IRON;
    }

    //сделать включаемым по конфигу
//    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
//        if(entityIn instanceof PlayerEntity) {
//            if(this.getDamage(stack) > 0) {
//            if(isSelected) {
//                if (entityIn.ticksExisted == 2) {
//                    worldIn.playSound((PlayerEntity) entityIn, entityIn.getPosition(), RegSounds.SOUND_GRINDER_IDLE.get(), SoundCategory.PLAYERS, 0.1F, 1f);
//                }
//                if (entityIn.ticksExisted % 39 == 0) {
//                    worldIn.playSound((PlayerEntity) entityIn, entityIn.getPosition(), RegSounds.SOUND_GRINDER_IDLE.get(), SoundCategory.PLAYERS, 0.04F, 1f);
//                }
//            }
//            }
//        }
//
//    }
    @Override
    public boolean onBlockDestroyed(@NotNull ItemStack stack, @NotNull World worldIn, BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entityLiving) {
        if (state.getBlock().getMaterialColor() == MaterialColor.IRON) {
            this.setDamage(stack, this.getDamage(stack) - 1);
            return true;
        }
        return false;
    }

    @Override
    public @NotNull ActionResult<ItemStack> onItemRightClick(@NotNull World world, PlayerEntity player, @NotNull Hand hand) {
        ItemStack mainHand = player.getHeldItem(Hand.MAIN_HAND);
        ItemStack offHand = player.getHeldItem(Hand.OFF_HAND);

        if (offHand.getItem() == Items.REDSTONE && mainHand.getItem() == this) {
            if (player.isCrouching()) {
                if (this.getDamage(mainHand) < getMaxDamage(mainHand)) {
                    if (offHand.getCount() > 10) {
                        offHand.shrink(10);
                        addDurability(mainHand, 640);
                    }
                }
            }
            if (this.getDamage(mainHand) < getMaxDamage(mainHand)) {
                offHand.shrink(1);
                addDurability(mainHand, 64);
            }
            return ActionResult.resultSuccess(mainHand);
        }
        return ActionResult.resultFail(mainHand);
    }
}
