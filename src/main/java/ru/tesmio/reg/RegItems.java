package ru.tesmio.reg;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.items.*;

import java.util.function.Supplier;

public class RegItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);
    public static final DeferredRegister<Item> ITEMS_2 = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);

    public static final RegistryObject<Item> LINO = ITEMS.register("lino", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> ARMATURE = ITEMS.register("armature", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> ARMATURES = ITEMS.register("armatures", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> QUAD_TILE = ITEMS.register("quad_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> BIG_TILE = ITEMS.register("big_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> REST_TILE = ITEMS.register("rest_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> SMALL_TILE = ITEMS.register("small_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> CERAMIC_DUST = ITEMS.register("ceramic_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> SILICON_INGOT = ITEMS.register("silicon_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> MORTAR = ITEMS.register("mortar", () -> new Mortar());
    public static final RegistryObject<Item> PESTLE = ITEMS.register("pestle", () -> new Pestle());
    public static final RegistryObject<Item> SIEVE = ITEMS.register("sieve", () -> new DamagebleItem());
    public static final RegistryObject<Item> LEAD_DUST = ITEMS.register("lead_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> SMALL_LEAD_DUST = ITEMS.register("small_lead_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static final RegistryObject<Item> LEADCERAMIC_DUST = ITEMS.register("leadceramic_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
    public static RegistryObject<Item> LEADCERAMIC_TILE;
    public static RegistryObject<Item> RED_CONDENSER, ORANGE_CONDENSER, YELLOW_CONDENSER, GREEN_CONDENSER, BLUE_CONDENSER, DIODE_BLACK, DIODE_GRAY, BLACK_MICRO, DARK_YELLOW_MICRO,
            DARK_RED_MICRO, DARK_BLUE_MICRO, DARK_YELLOW_TRANSISTOR, DARK_GRAY_TRANSISTOR, YELLOW_JACK, GREEN_JACK, BLUE_JACK;
    public static RegistryObject<Item> RED_CONDENSER_DUST, ORANGE_CONDENSER_DUST, YELLOW_CONDENSER_DUST, GREEN_CONDENSER_DUST, BLUE_CONDENSER_DUST, DIODE_BLACK_DUST, DIODE_GRAY_DUST, BLACK_MICRO_DUST, DARK_YELLOW_MICRO_DUST,
            DARK_RED_MICRO_DUST, DARK_BLUE_MICRO_DUST, DARK_YELLOW_TRANSISTOR_DUST, DARK_GRAY_TRANSISTOR_DUST, YELLOW_JACK_DUST, GREEN_JACK_DUST, BLUE_JACK_DUST;
    public static RegistryObject<Item> WIRE_CUTTERS;
    public static void init() {
        DARK_YELLOW_MICRO_DUST = registerItem("detail_scrap/dark_yellow_micro_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DARK_RED_MICRO_DUST  = registerItem("detail_scrap/dark_red_micro_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DARK_BLUE_MICRO_DUST  = registerItem("detail_scrap/dark_blue_micro_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DARK_YELLOW_TRANSISTOR_DUST  = registerItem("detail_scrap/dark_yellow_transistor_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DARK_GRAY_TRANSISTOR_DUST  = registerItem("detail_scrap/dark_gray_transistor_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DIODE_BLACK_DUST  = registerItem("detail_scrap/diode_black_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DIODE_GRAY_DUST  = registerItem("detail_scrap/diode_gray_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        BLACK_MICRO_DUST  = registerItem("detail_scrap/black_micro_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        RED_CONDENSER_DUST  = registerItem("detail_scrap/red_condenser_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        YELLOW_CONDENSER_DUST  = registerItem("detail_scrap/yellow_condenser_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        GREEN_CONDENSER_DUST  = registerItem("detail_scrap/green_condenser_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        BLUE_CONDENSER_DUST  = registerItem("detail_scrap/blue_condenser_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        YELLOW_JACK_DUST  = registerItem("detail_scrap/yellow_jack_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        BLUE_JACK_DUST  = registerItem("detail_scrap/blue_jack_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        GREEN_JACK_DUST  = registerItem("detail_scrap/green_jack_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        ORANGE_CONDENSER_DUST  = registerItem("detail_scrap/orange_condenser_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));


        DARK_YELLOW_MICRO = registerItem("circuits/dark_yellow_micro", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.dark_yellow_micro"));
        DARK_RED_MICRO = registerItem("circuits/dark_red_micro", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.dark_red_micro"));
        DARK_BLUE_MICRO = registerItem("circuits/dark_blue_micro", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.dark_blue_micro"));
        DARK_YELLOW_TRANSISTOR = registerItem("circuits/dark_yellow_transistor", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.dark_yellow_transistor"));
        DARK_GRAY_TRANSISTOR = registerItem("circuits/dark_gray_transistor", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.dark_gray_transistor"));
        DIODE_BLACK = registerItem("circuits/diode_black", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.diode_black"));
        DIODE_GRAY = registerItem("circuits/diode_gray", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.diode_gray"));
        BLACK_MICRO = registerItem("circuits/black_micro", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.black_micro"));
        RED_CONDENSER = registerItem("circuits/red_condenser", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.red_condenser"));
        YELLOW_CONDENSER = registerItem("circuits/yellow_condenser", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.yellow_condenser"));
        LEADCERAMIC_TILE = registerItem("leadceramic_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        GREEN_CONDENSER = registerItem("circuits/green_condenser", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.green_condenser"));
        BLUE_CONDENSER = registerItem("circuits/blue_condenser", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.blue_condenser"));
        YELLOW_JACK = registerItem("circuits/yellow_jack", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.yellow_jack"));
        BLUE_JACK = registerItem("circuits/blue_jack", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.blue_jack"));
        GREEN_JACK = registerItem("circuits/green_jack", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.green_jack"));
        ORANGE_CONDENSER = registerItem("circuits/orange_condenser", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.orange_condenser"));
        WIRE_CUTTERS = registerItem("wire_cutters", () -> new WireCutter());
    }
    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        RegistryObject<T> toReturn = ITEMS.register(name, item);
        return toReturn;
    }
    private static <T extends Item> RegistryObject<T> registerItem2(String name, Supplier<T> item) {
        RegistryObject<T> toReturn = ITEMS_2.register(name, item);
        return toReturn;
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        ITEMS_2.register(eventBus);
        init();
    }
}
