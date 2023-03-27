package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.core.Core;
import ru.tesmio.reg.RegBlocks;

import javax.annotation.Nullable;
import java.util.List;

public class WireCutter extends Item {
    public WireCutter() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1)
                .defaultMaxDamage(3750)
                .setNoRepair());

    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("info.wire_cutter"));
    }
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState state = context.getWorld().getBlockState(context.getPos());
        if (!context.getWorld().isRemote) {
            if(state.getBlock() == RegBlocks.REDSTONE_WIRE.get()) {
                PlayerEntity playerEntity = context.getPlayer();
                if (playerEntity != null) {
                    context.getWorld().getBlockState(context.getPos()).spawnAdditionalDrops((ServerWorld) context.getWorld(), context.getPos(), new ItemStack(RegBlocks.REDSTONE_WIRE.get()));
                    context.getWorld().setBlockState(context.getPos(), Blocks.AIR.getDefaultState());
                    context.getItem().damageItem(1, playerEntity, (player) -> {
                        player.sendBreakAnimation(context.getHand());
                    });
                    return ActionResultType.SUCCESS;
                }
            }

        }
        return ActionResultType.PASS;
    }
}
