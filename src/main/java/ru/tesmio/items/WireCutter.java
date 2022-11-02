package ru.tesmio.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.Core;

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
//    public ActionResultType onItemUse(ItemUseContext context) {
//        BlockState state = context.getWorld().getBlockState(context.getPos());
//        if (!context.getWorld().isRemote) {
//            if(state.getBlock() == RegBlocks.COPPER_CIRCUIT.get()) {
//                PlayerEntity playerEntity = context.getPlayer();
//                if (playerEntity != null) {
//                    context.getItem().damageItem(1, playerEntity, (player) -> {
//                        player.sendBreakAnimation(context.getHand());
//                    });
//                    return ActionResultType.SUCCESS;
//                }
//            }
//
//        }
//        return ActionResultType.PASS;
//    }
}
