package ru.tesmio.reg;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;

public class RegItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);

    public static final RegistryObject<Item> ARMATURE = ITEMS.register("armature", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> QUAD_TILE = ITEMS.register("quad_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> CERAMIC_DUST = ITEMS.register("ceramic_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> SILICON_INGOT = ITEMS.register("silicon_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
