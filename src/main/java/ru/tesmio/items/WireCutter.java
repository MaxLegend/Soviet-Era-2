package ru.tesmio.items;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import ru.tesmio.blocks.BlockCopperCircuit;

public class WireCutter extends  DamagebleItem {

    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getWorld().getBlockState(context.getPos()).getBlock() instanceof BlockCopperCircuit) {

            if (!context.getWorld().isRemote) {

                if (context.getPlayer() != null) {

                    context.getItem().attemptDamageItem(1, context.getWorld().getRandom(), (ServerPlayerEntity)context.getPlayer());
                    return ActionResultType.SUCCESS;

                }
                return ActionResultType.SUCCESS;
            }

        }

        return ActionResultType.SUCCESS;
    }
}
