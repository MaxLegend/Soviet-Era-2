package ru.tesmio.soviet.data.providers.advancements;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;

import java.util.List;
import java.util.function.Consumer;

public class SovietAdvancementsProvider extends AdvancementProvider {
    private final List<Consumer<Consumer<Advancement>>> advancements = ImmutableList.of(new SovietAdvancement());
    protected net.minecraftforge.common.data.ExistingFileHelper fileHelper;

    public SovietAdvancementsProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void registerAdvancements(Consumer<Advancement> consumer, net.minecraftforge.common.data.ExistingFileHelper fileHelper) {
        for(Consumer<Consumer<Advancement>> consumer1 : this.advancements) {
            consumer1.accept(consumer);
        }
    }
}
