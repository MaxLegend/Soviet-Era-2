package ru.tesmio.data.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
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
    private final DataGenerator generator;

    public BaseLootProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected static LootTable.Builder standartBuilder(String name, Block block) {
        LootPool.Builder builder = LootPool.builder().name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                .addOperation("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                .addOperation("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                        .acceptFunction(SetContents.builderIn()
                                .addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("soviet", "contents"))))
                );
        return LootTable.builder().addLootPool(builder);
    }


    protected static LootTable.Builder standartItemBlockBuilder(String name, Item item, int count) {
        LootPool.Builder builder = LootPool.builder().name(name)
                .rolls(ConstantRange.of(count))
                .addEntry(ItemLootEntry.builder(item));
        return LootTable.builder().addLootPool(builder);
    }
    protected static LootTable.Builder standart2ItemBlockBuilder(String name, Item item1, Item item2) {
        LootPool.Builder builder = LootPool.builder().name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(item1))
                .rolls(ConstantRange.of(3)).addEntry(ItemLootEntry.builder(item2));

        return LootTable.builder().addLootPool(builder);
    }


    @Override
    public void act(DirectoryCache cache) {
        addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
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