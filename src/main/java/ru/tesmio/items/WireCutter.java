package ru.tesmio.items;

import net.minecraft.item.Item;
import ru.tesmio.Core;

public class WireCutter extends Item {
    public WireCutter() {
        super(new Item.Properties()
                .group(Core.ItemGroups.TAB_ITEMS)
                .maxStackSize(1)
                .defaultMaxDamage(3750)
                .setNoRepair());

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
