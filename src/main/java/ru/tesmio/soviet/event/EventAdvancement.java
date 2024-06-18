package ru.tesmio.soviet.event;

import net.minecraft.advancements.Advancement;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.soviet.core.Core;

//WiP - in update 2.1 -
@Mod.EventBusSubscriber(modid = Core.MODID)
public class EventAdvancement {
    @SubscribeEvent
    public static void rewardItemsEvent(AdvancementEvent e) {
        Advancement a = e.getAdvancement();

//       if(e.getAdvancement().getId().toString().equals("soviet:wirecutters")) {
//           e.getPlayer().addItemStackToInventory(new ItemStack(Items.STICK, 10));
//       }
//        if(e.getAdvancement().getId().toString().equals("soviet:root")) {
//            e.getPlayer().addItemStackToInventory(new ItemStack(Items.BRICK, 10));
//
//        }
//        if(e.getAdvancement().getId().toString().equals("soviet:wrench")) {
//            e.getPlayer().addItemStackToInventory(new ItemStack(Items.BONE, 10));
//
//        }
    }
}
