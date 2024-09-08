package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.subtype.FerroconcreteBlock;
import ru.tesmio.blocks.placeable.BucketDye;
import ru.tesmio.core.Core;
import ru.tesmio.enums.EnumBucketState;
import ru.tesmio.reg.RegBlocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class DyeBrush extends Item {
    public final String COLOR_NBT = "color";
    public final String MAXUSE_NBT = "uses";


    public DyeBrush() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1)
                .defaultMaxDamage(1200)
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
    public void saveColor(ItemStack itemStack, String key, String value) {
        CompoundNBT nbt = itemStack.getOrCreateTag();
        nbt.putString(key, value);
        itemStack.setTag(nbt);
    }
    public String getColor(ItemStack itemStack, String key) {
        CompoundNBT nbt = itemStack.getTag();
        if (nbt != null && nbt.contains(key)) {
            return nbt.getString(key);
        }
        return "";
    }
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        World w = context.getWorld();
        BlockPos pos = context.getPos();
        ItemStack thisItem = context.getItem();
        PlayerEntity playerEntity = context.getPlayer();

            if(state.getBlock() instanceof BucketDye) {
                if(Objects.equals(state.get(BucketDye.COLOR).getString(), "water") || Objects.equals(state.get(BucketDye.COLOR).getString(), "empty")) {
                    return ActionResultType.FAIL;
                } else {

                    if(playerEntity.getHeldItemMainhand().getItem() instanceof DyeBrush) {
                        if (Objects.equals(getColor(playerEntity.getHeldItemMainhand(), COLOR_NBT), "")) {
                            saveColor(thisItem, COLOR_NBT, state.get(BucketDye.COLOR).getString());
                            saveMaxUse(thisItem, MAXUSE_NBT, 128);
                            w.setBlockState(pos, state.with(BucketDye.COLOR, EnumBucketState.EMPTY));
                            return ActionResultType.SUCCESS;
                        } else {
                            return ActionResultType.FAIL;
                        }
                    }
                }
            }

            if(state.getBlock() instanceof FerroconcreteBlock && getColor(thisItem, COLOR_NBT) != null ) {
                int counter = getMaxUse(thisItem, MAXUSE_NBT);
                if(counter > 0) {

                switch (getColor(thisItem, COLOR_NBT)) {

                    case "beige":
                            if(state.getBlock() == RegBlocks.CONCRETE_BEIGE.get()) return ActionResultType.PASS;
                            w.setBlockState(pos, RegBlocks.CONCRETE_BEIGE.get().getDefaultState());
                            if(!playerEntity.isCreative()) {
                                counter = counter - 1;
                                saveMaxUse(thisItem, MAXUSE_NBT, counter);
                                context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                                playerEntity.giveExperiencePoints(1);
                                playerEntity.giveExperiencePoints(1);

                            }
                        return ActionResultType.SUCCESS;
                    case "beige2":
                        if(state.getBlock() == RegBlocks.CONCRETE_BEIGE2.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_BEIGE2.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "orange":
                        if(state.getBlock() == RegBlocks.CONCRETE_ORANGE.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_ORANGE.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "blue":
                        if(state.getBlock() == RegBlocks.CONCRETE_BLUE.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_BLUE.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "red":
                        if(state.getBlock() == RegBlocks.CONCRETE_RED.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_RED.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "yellow":
                        if(state.getBlock() == RegBlocks.CONCRETE_YELLOW.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_YELLOW.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "white":
                        if(state.getBlock() == RegBlocks.CONCRETE_WHITE.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_WHITE.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;
                    case "green":
                        if(state.getBlock() == RegBlocks.CONCRETE_GREEN.get()) return ActionResultType.PASS;
                        w.setBlockState(pos, RegBlocks.CONCRETE_GREEN.get().getDefaultState());
                        if(!playerEntity.isCreative()) {
                            counter = counter - 1;
                            saveMaxUse(thisItem, MAXUSE_NBT, counter);
                            context.getItem().damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(context.getHand()));
                            playerEntity.giveExperiencePoints(1);
                        }
                        return ActionResultType.SUCCESS;


                }
                            return ActionResultType.SUCCESS;
                        }

                } else {
                    saveColor(thisItem, COLOR_NBT, "empty");
                    return ActionResultType.FAIL;
                }
    return ActionResultType.FAIL;
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {

        tooltip.add(new TranslationTextComponent("info.brush"));
        tooltip.add(new TranslationTextComponent("info.color").appendString(": ").appendSibling(new TranslationTextComponent("color." + getColor(stack, COLOR_NBT))));
        tooltip.add(new TranslationTextComponent("info.count").appendString(": " + getMaxUse(stack, MAXUSE_NBT)));
    }
    //добавить сохранение взятого цвета в нбт. Либо просто ебануть 8 вариантов кисточек и похуй
}
