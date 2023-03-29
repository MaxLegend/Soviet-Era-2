package ru.tesmio.reg;

import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.core.Core;
import ru.tesmio.items.*;

import java.util.function.Supplier;

public class RegItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);
    public static final DeferredRegister<Item> ITEMS_2 = DeferredRegister.create(ForgeRegistries.ITEMS, Core.MODID);


    public static RegistryObject<Item> LINO, ARMATURE, ARMATURES,QUAD_TILE,BIG_TILE,REST_TILE,SMALL_TILE,CERAMIC_DUST,SILICON_INGOT,MORTAR,PESTLE,SIEVE,LEAD_DUST,SMALL_LEAD_DUST,
    LEADCERAMIC_DUST,LEAD_INGOT ;





    public static RegistryObject<Item> LEADCERAMIC_TILE;

    public static RegistryObject<Item> FUEL_CANISTER,KEY_DOOR, WRENCH, REDSTONE_GRINDER;
    public static RegistryObject<Item> VARIANT_ITEM;


    public static RegistryObject<Item> ALUMINUM_SCRAP, LEAD_SCRAP, CERAMIC_SHARD, RUSTY_SCRAP, WOOD_SCRAP, COPPER_SCRAP;

    public static RegistryObject<Item> RED_CONDENSER, ORANGE_CONDENSER, YELLOW_CONDENSER, GREEN_CONDENSER, BLUE_CONDENSER, DIODE_BLACK, DIODE_GRAY, BLACK_MICRO, DARK_YELLOW_MICRO,
            DARK_RED_MICRO, DARK_BLUE_MICRO, DARK_YELLOW_TRANSISTOR, DARK_GRAY_TRANSISTOR, YELLOW_JACK, GREEN_JACK, BLUE_JACK;
    public static RegistryObject<Item> RED_CONDENSER_DUST, ORANGE_CONDENSER_DUST, YELLOW_CONDENSER_DUST, GREEN_CONDENSER_DUST, BLUE_CONDENSER_DUST, DIODE_BLACK_DUST, DIODE_GRAY_DUST, BLACK_MICRO_DUST, DARK_YELLOW_MICRO_DUST,
            DARK_RED_MICRO_DUST, DARK_BLUE_MICRO_DUST, DARK_YELLOW_TRANSISTOR_DUST, DARK_GRAY_TRANSISTOR_DUST, YELLOW_JACK_DUST, REOCHORD, GREEN_JACK_DUST, BLUE_JACK_DUST;

    public static RegistryObject<Item> WIRE_CUTTERS, PULLER, FLUOLAMP;

    public static RegistryObject<Item> PLATINUM_PICKAXE, PLATINUM_AXE, PLATINUM_SHOVEL, PLATINUM_SWORD, PLATINUM_HOE;
    public static RegistryObject<Item> PLATOL_PICKAXE, PLATOL_AXE, PLATOL_SHOVEL, PLATOL_SWORD, PLATOL_HOE;
    public static RegistryObject<Item>  PALLADIUM_DUST, PALLADIUM_INGOT, ALUMINUM_INGOT, COPPER_INGOT, COPPER_DUST, SILVER_INGOT, SILVER_DUST, GOLD_DUST, DIAMOND_DUST, NETHERITE_DUST, PLATINUM_INGOT, PLATINUM_DUST, PLATOL_DUST, PLATOL_INGOT;
    public static void init() {
        //other items
        QUAD_TILE = registerItem("quad_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        BIG_TILE = registerItem("big_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        REST_TILE = registerItem("rest_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        SMALL_TILE = registerItem("small_tile", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        LINO = registerItem("lino", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        FLUOLAMP = registerItem("fluolamp", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        FUEL_CANISTER = registerItem("fuel_canister", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS).maxStackSize(1), "info.fuel_canister"));

        LEAD_SCRAP = registerItem("lead_scrap", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.restore"));
        WOOD_SCRAP = registerItem("wood_scrap", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.wood_scrap"));
        RUSTY_SCRAP = registerItem("rusty_scrap", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.restore"));
        CERAMIC_SHARD = registerItem("ceramic_shard", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.restore"));
        ARMATURES = registerItem("armatures", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        ARMATURE = registerItem("armature", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        COPPER_SCRAP = registerItem("copper_scrap", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.restore"));
        ALUMINUM_SCRAP = registerItem("aluminum_scrap", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.restore"));

        //tools
        PLATINUM_SWORD = registerItem("platinum_sword", () -> new SwordItem(SovietItemTier.PLATINUM, 5, -1F, (new Item.Properties()).group(ItemGroup.COMBAT)));
        PLATINUM_HOE = registerItem("platinum_hoe", () -> new HoeItem(SovietItemTier.PLATINUM, 1, -2F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATINUM_SHOVEL = registerItem("platinum_shovel", () -> new ShovelItem(SovietItemTier.PLATINUM, 2, -3F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATINUM_AXE = registerItem("platinum_axe", () -> new AxeItem(SovietItemTier.PLATINUM, 8, -3F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATINUM_PICKAXE = registerItem("platinum_pickaxe", () -> new PickaxeItem(SovietItemTier.PLATINUM, 2, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        WIRE_CUTTERS = registerItem("wire_cutters", () -> new WireCutter());
        REDSTONE_GRINDER = registerItem("redstone_grinder", () -> new RedstoneGrinder());
        PULLER = registerItem("puller", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS).maxStackSize(1).defaultMaxDamage(180).setNoRepair(), "info.puller"));
        PLATOL_SWORD = registerItem("platol_sword", () -> new SwordItem(SovietItemTier.PLATOL, 5, -1F, (new Item.Properties()).group(ItemGroup.COMBAT)));
        PLATOL_HOE = registerItem("platol_hoe", () -> new HoeItem(SovietItemTier.PLATOL, 1, -2F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATOL_SHOVEL = registerItem("platol_shovel", () -> new ShovelItem(SovietItemTier.PLATOL, 2, -3F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATOL_AXE = registerItem("platol_axe", () -> new AxeItem(SovietItemTier.PLATOL, 8, -3F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        PLATOL_PICKAXE = registerItem("platol_pickaxe", () -> new PickaxeItem(SovietItemTier.PLATOL, 2, -2.8F, (new Item.Properties()).group(ItemGroup.TOOLS)));
        MORTAR = registerItem("mortar", Mortar::new);
        PESTLE = registerItem("pestle", Pestle::new);
        SIEVE = registerItem("sieve", DamagebleItem::new);
        KEY_DOOR = registerItem2("key_door", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "key.key_door"));
        WRENCH = registerItem("wrench", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "wrench.info"));
        VARIANT_ITEM = registerItem("variant_item", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "info.variant_item"));

        //ingots
        ALUMINUM_INGOT = registerItem("aluminum_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        COPPER_INGOT = registerItem("copper_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        SILVER_INGOT = registerItem("silver_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        PALLADIUM_INGOT = registerItem("palladium_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        PLATOL_INGOT = registerItem("platol_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        LEAD_INGOT = registerItem("lead_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        SILICON_INGOT = registerItem("silicon_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));

        //dust
        LEAD_DUST = registerItem("lead_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        SMALL_LEAD_DUST = registerItem("small_lead_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        LEADCERAMIC_DUST = registerItem("leadceramic_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        CERAMIC_DUST = registerItem("ceramic_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        PLATOL_DUST = registerItem("platol_dust", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "platol.platol_dust"));
        PALLADIUM_DUST = registerItem("palladium_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        COPPER_DUST = registerItem("copper_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
         SILVER_DUST = registerItem("silver_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        PLATINUM_INGOT = registerItem("platinum_ingot", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        DIAMOND_DUST = registerItem("diamond_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        GOLD_DUST = registerItem("gold_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        NETHERITE_DUST = registerItem("netherite_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
        PLATINUM_DUST = registerItem("platinum_dust", () -> new Item(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS)));
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

        //details
        REOCHORD = registerItem("circuits/reochord", () -> new ItemInfo(new Item.Properties().group(Core.ItemGroups.TAB_ITEMS), "circ.reochord"));
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
    enum SovietItemTier implements IItemTier {

        PLATINUM(2, 370, 17.0F, 1.0F, 30,() -> {
            return Ingredient.fromItems(RegItems.PLATINUM_INGOT.get());
        }),
        PLATOL(4, 3400, 24.0F, 6.0F, 45,() -> {
            return Ingredient.fromItems(RegItems.PLATOL_INGOT.get());
        });
        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        SovietItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }


        @Override public int getMaxUses() {return maxUses;}
        @Override public float getEfficiency() {return efficiency;}
        @Override public float getAttackDamage() {return attackDamage;}
        @Override public int getHarvestLevel() {return harvestLevel;}
        @Override public int getEnchantability() {return enchantability;}
        @Override public Ingredient getRepairMaterial() {return repairMaterial.getValue();}
    }
}
