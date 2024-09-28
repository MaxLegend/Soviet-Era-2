package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.subtype.FerroconcreteBlockDmg;
import ru.tesmio.blocks.placeable.BucketDye;
import ru.tesmio.core.Core;
import ru.tesmio.reg.RegBlocks;

import java.util.Objects;

public class DyeScrapper extends ItemInfo {
    public DyeScrapper( String info) {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1)
                .defaultMaxDamage(1200)
                .setNoRepair(), info);
    }
    //шпателем по аналогичной с кисточкой механике можно будет ремонтировать блоки потрескавшегося бетона
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        World w = context.getWorld();
        BlockPos pos = context.getPos();
        if(w.isRemote) {
            if(state.getBlock() instanceof FerroconcreteBlockDmg) {
                w.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D + context.getWorld().rand.nextDouble(),
                        pos.getY() + 0.5D, pos.getZ() + context.getWorld().rand.nextDouble() + 0.5D,
                        0d, 0.05d, 0.0d);
            }
        }
        if (!context.getWorld().isRemote) {
            if(state.getBlock() instanceof BucketDye) {
                if (Objects.equals(state.get(BucketDye.COLOR).getString(), "filler")) {
                    return ActionResultType.FAIL;
                }
            }
            if(state.getBlock() instanceof FerroconcreteBlockDmg) {
                PlayerEntity playerEntity = context.getPlayer();
                if (playerEntity != null) {
                    if(state.getBlock() == RegBlocks.CONCRETE_GRAY.get() || state.getBlock() == RegBlocks.CONCRETE_GRAY_BR.get()) {
                        return ActionResultType.SUCCESS;
                    }
                    w.setBlockState(pos, RegBlocks.CONCRETE_GRAY_BR.get().getDefaultState());

                    if(!playerEntity.isCreative()) {
                        context.getPlayer().giveExperiencePoints(1);
                        context.getItem().damageItem(1, playerEntity, (player) -> {
                            player.sendBreakAnimation(context.getHand());
                        });
                    }
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

}
