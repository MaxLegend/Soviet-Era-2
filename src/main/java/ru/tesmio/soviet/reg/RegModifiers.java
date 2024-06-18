package ru.tesmio.soviet.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.world.modifiers.SovietLootStructureModifier;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Core.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegModifiers {
    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new SovietLootStructureModifier.Serializer().setRegistryName(new ResourceLocation(Core.MODID, "gas_mask_in_mineshaft")),
                new SovietLootStructureModifier.Serializer().setRegistryName(new ResourceLocation(Core.MODID, "suit_jacket_in_igloo")),
                new SovietLootStructureModifier.Serializer().setRegistryName(new ResourceLocation(Core.MODID, "suit_boots_in_ruined_portal")),
                new SovietLootStructureModifier.Serializer().setRegistryName(new ResourceLocation(Core.MODID, "suit_legs_in_woodland_mansion"))
        );
    }
}
