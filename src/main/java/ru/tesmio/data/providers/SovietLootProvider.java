package ru.tesmio.data.providers;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SovietLootProvider extends LootTableProvider {
    public SovietLootProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(SovietBlockLootProvider::new, LootParameterSets.BLOCK)
        );
    }
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_));
    }
    public static class SovietBlockLootProvider extends BlockLootTables {
        @Override
        protected void addTables() {
            droppingItemFromBlock(RegBlocks.CONCRETE_ORANGE.get(), RegItems.ARMATURE.get(), 3);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return RegBlocks.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
        protected static LootTable.Builder droppingBlock(Block block) {
            return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block)));
        }
        protected static LootTable.Builder droppingItemFromBlock(Block block, IItemProvider item, int range) {
            return LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(range)).addEntry(ItemLootEntry.builder(item)));
        }
    }
}