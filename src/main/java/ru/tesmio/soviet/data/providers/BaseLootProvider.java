package ru.tesmio.soviet.data.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootProvider extends LootTableProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
    protected final Map<ResourceLocation, LootTable.Builder> lootChest = new HashMap<>();
    private final DataGenerator generator;

    public BaseLootProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected static LootTable.Builder genChestLoot(String name, Item item, int weight, float global_min, float global_max, float item_min, float item_max) {
        LootPool.Builder builder = LootPool.builder().name(name)
                    .rolls(RandomValueRange.of(global_min, global_max))
                    .addEntry(ItemLootEntry.builder(item).weight(weight))
                    .acceptFunction(SetCount.builder(RandomValueRange.of(item_min, item_max)));

        return LootTable.builder().addLootPool(builder);
    }

protected static LootTable.Builder drop1x1Rand(String name, Item item, int min, int max) {
    LootPool.Builder builder = LootPool.builder().name(name).addEntry(ItemLootEntry.builder(item)).rolls(RandomValueRange.of(min, max));
    return LootTable.builder().addLootPool(builder);
}
    protected static LootTable.Builder drop1x1Rand(String name, Item item, int count) {
        LootPool.Builder builder = LootPool.builder().name(name).addEntry(ItemLootEntry.builder(item)).rolls(RandomValueRange.of(0, count));
        return LootTable.builder().addLootPool(builder);
    }
    protected static LootTable.Builder drop2x2Const(String name, Item item1, Item item2) {
        LootPool.Builder builder = LootPool.builder().name(name).addEntry(ItemLootEntry.builder(item1)).rolls(RandomValueRange.of(1,2));
        LootPool.Builder builder2 = LootPool.builder().name(name).addEntry(ItemLootEntry.builder(item2)).rolls(RandomValueRange.of(2,5));
        return LootTable.builder().addLootPool(builder).addLootPool(builder2);
    }
    protected static LootTable.Builder drop2x2Rand(String name, Item item1, Item item2, int min1, int max1, int min2, int max2) {
        LootPool.Builder builder = LootPool.builder().name(name).addEntry(ItemLootEntry.builder(item1)).rolls(RandomValueRange.of(min1, max1));
        LootPool.Builder builder2 = LootPool.builder().name(name) .addEntry(ItemLootEntry.builder(item2)).rolls(RandomValueRange.of(min2, max2));
        return LootTable.builder().addLootPool(builder).addLootPool(builder2);
    }

    @Override
    public void act(DirectoryCache cache) {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        for (Map.Entry<ResourceLocation, LootTable.Builder> entry : lootChest.entrySet()) {
            tables.put(entry.getKey(), entry.getValue().setParameterSet(LootParameterSets.CHEST).build());
        }

        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    @Override
    public String getName() {
        return "SovietLootTables";
    }
}