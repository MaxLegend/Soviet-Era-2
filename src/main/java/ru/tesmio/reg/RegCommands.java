package ru.tesmio.reg;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import ru.tesmio.core.Core;
import ru.tesmio.utils.structure_saver.CommandStrucSave;

@Mod.EventBusSubscriber(modid = Core.MODID)
public class RegCommands {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent e) {
        new CommandStrucSave(e.getDispatcher());
        ConfigCommand.register(e.getDispatcher());
    }

    public static BlockPos pos1;
    public static BlockPos pos2;
    @SubscribeEvent
    public static void getPosRight(PlayerInteractEvent.RightClickBlock e) {
        if(!e.getWorld().isRemote) {

            ItemStack hand = e.getItemStack();
            if (!hand.isEmpty()) {
                if (hand.getItem() == Items.STICK) {
                    pos1 = e.getPos();
                    ITextComponent itx = new StringTextComponent("Set position start: " + pos1) {
                    };
                    e.getPlayer().sendMessage(itx, e.getPlayer().getUniqueID());
                } else if (hand.getItem() == Items.BONE) {
                    pos2 = e.getPos();
                    ITextComponent itx = new StringTextComponent("Set position end: " + pos2);
                    e.getPlayer().sendMessage(itx, e.getPlayer().getUniqueID());
                }
            }
        }
    }
    public static int getMin(int a, int b) {
        return Math.min(a, b);
    }

    public static int getMax(int a, int b) {
        return Math.max(a, b);
    }
}
