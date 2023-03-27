package ru.tesmio.reg;

import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.core.Core;
import ru.tesmio.items.RedstoneGrinder;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class RegEvents {
    @SubscribeEvent
    public static void leftClickGrinder(PlayerInteractEvent.LeftClickBlock e) {
        if(e.getPlayer().ticksExisted == 2) {
            e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), RegSounds.SOUND_GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.1F, 1f);
        }
        if(e.getPlayer().ticksExisted % 35 == 0) {
            Item item = e.getPlayer().getHeldItem(e.getHand()).getItem();
            if(item instanceof RedstoneGrinder) {
                if(item.getDamage(e.getPlayer().getHeldItem(e.getHand())) > 0)
                e.getWorld().playSound(e.getPlayer(), e.getPlayer().getPosition(), RegSounds.SOUND_GRINDER_WORK.get(), SoundCategory.PLAYERS, 0.04F, 1f);
            }
        }
    }

}
