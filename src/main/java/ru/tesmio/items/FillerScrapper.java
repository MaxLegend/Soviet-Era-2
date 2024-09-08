package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.subtype.BlockRailing;
import ru.tesmio.blocks.baseblock.subtype.FerroconcreteBlockDmg;
import ru.tesmio.blocks.decorative.props.RailingBlock;
import ru.tesmio.blocks.placeable.BucketDye;
import ru.tesmio.core.Core;
import ru.tesmio.enums.EnumBucketState;
import ru.tesmio.reg.RegBlocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class FillerScrapper extends Item {
    public final String FILLER_NBT = "filler";
    public final String MAXUSE_NBT = "uses";
    public FillerScrapper() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1).defaultMaxDamage(1200)
                .setNoRepair());
    }
    public void saveMaxUse(ItemStack itemStack, String key, int value) {
        CompoundNBT nbt = itemStack.getOrCreateTag();
        nbt.putInt(key, value);
        itemStack.setTag(nbt);
    }
    public int getMaxUse(ItemStack itemStack, String key) {
        CompoundNBT nbt = itemStack.getTag();
        if (nbt != null && nbt.contains(key)) {
            return nbt.getInt(key);
        }
        return 0;
    }
    public void saveFiller(ItemStack itemStack, String key, String value) {
        CompoundNBT nbt = itemStack.getOrCreateTag();
        nbt.putString(key, value);
        itemStack.setTag(nbt);
    }
    public String getFiller(ItemStack itemStack, String key) {
        CompoundNBT nbt = itemStack.getTag();
        if (nbt != null && nbt.contains(key)) {
            return nbt.getString(key);
        }
        return "";
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {

        tooltip.add(new TranslationTextComponent("info.scrapper"));
        tooltip.add(new TranslationTextComponent("info.filler").appendString(": ").appendSibling(new TranslationTextComponent("fil." + getFiller(stack, FILLER_NBT))));
        tooltip.add(new TranslationTextComponent("info.count").appendString(": " + getMaxUse(stack, MAXUSE_NBT)));
    }
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        World w = context.getWorld();
        BlockPos pos = context.getPos();
        ItemStack thisItem = context.getItem();
        PlayerEntity playerEntity = context.getPlayer();
        if (w.isRemote) {
            if (state.getBlock() instanceof FerroconcreteBlockDmg) {
                w.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D + context.getWorld().rand.nextDouble(),
                        pos.getY() + 0.5D, pos.getZ() + context.getWorld().rand.nextDouble() + 0.5D,
                        0d, 0.05d, 0.0d);
            }
        }

            if (state.getBlock() instanceof BucketDye) {

                if (Objects.equals(state.get(BucketDye.COLOR).getString(), "filler")) {
                    saveFiller(thisItem, FILLER_NBT, state.get(BucketDye.COLOR).getString());
                    saveMaxUse(thisItem, MAXUSE_NBT, 128);
                    w.setBlockState(pos, state.with(BucketDye.COLOR, EnumBucketState.EMPTY));
                    return ActionResultType.SUCCESS;
                }

            }
                if (state.getBlock() == RegBlocks.CONCRETE_GRAY_BR.get()) {
                    int counter = getMaxUse(thisItem, MAXUSE_NBT);
                    if (counter > 0) {

                        w.setBlockState(pos, RegBlocks.CONCRETE_GRAY.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;

                    }
                }
        if (state.getBlock() instanceof BlockRailing) {
            int counter = getMaxUse(thisItem, MAXUSE_NBT);
            if (counter >= 8) {

                w.setBlockState(pos, RegBlocks.CONCRETE_GRAY.get().getDefaultState());
                if(!playerEntity.isCreative()) {
                    counter = counter - 8;
                    saveMaxUse(thisItem, MAXUSE_NBT, counter);
                    context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                    playerEntity.giveExperiencePoints(3);
                }
                return ActionResultType.SUCCESS;

            }
        }
        if (state.getBlock() instanceof RailingBlock) {
            int counter = getMaxUse(thisItem, MAXUSE_NBT);
            if (counter >= 12) {
                w.setBlockState(pos, RegBlocks.CONCRETE_GRAY.get().getDefaultState());
                if(!playerEntity.isCreative()) {
                    counter = counter - 12;
                    saveMaxUse(thisItem, MAXUSE_NBT, counter);
                    context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                    playerEntity.giveExperiencePoints(5);
                }
            }
        }
        if(getMaxUse(thisItem, MAXUSE_NBT) <= 0) {
            saveFiller(thisItem, FILLER_NBT, "");
        }
                return ActionResultType.PASS;


    }
}
