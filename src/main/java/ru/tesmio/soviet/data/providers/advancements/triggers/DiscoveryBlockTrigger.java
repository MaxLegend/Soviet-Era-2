package ru.tesmio.soviet.data.providers.advancements.triggers;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.loot.ConditionArraySerializer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import ru.tesmio.soviet.core.Core;

import javax.annotation.Nullable;

public class DiscoveryBlockTrigger extends AbstractCriterionTrigger<DiscoveryBlockTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Core.MODID, "discovery_block");
    public static final DiscoveryBlockTrigger INSTANCE = new DiscoveryBlockTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    public DiscoveryBlockTrigger.Instance deserializeTrigger(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        Block block = deserializeBlock(json);
        StatePropertiesPredicate statepropertiespredicate = StatePropertiesPredicate.deserializeProperties(json.get("state"));
        if (block != null) {
            statepropertiespredicate.forEachNotPresent(block.getStateContainer(), (property) -> {
                throw new JsonSyntaxException("Block " + block + " has no property " + property + ":");
            });
        }

        ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
        return new DiscoveryBlockTrigger.Instance(entityPredicate, block, statepropertiespredicate, itempredicate);
    }

    @Nullable
    private static Block deserializeBlock(JsonObject object) {
        if (object.has("block")) {
            ResourceLocation resourcelocation = new ResourceLocation(JSONUtils.getString(object, "block"));
            return Registry.BLOCK.getOptional(resourcelocation).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown block type '" + resourcelocation + "'");
            });
        } else {
            return null;
        }
    }

    public void trigger(BlockState state, ServerPlayerEntity player, ItemStack item) {
        this.triggerListeners(player, (instance) -> {
            return instance.test(state, item);
        });
    }

    public static class Instance extends CriterionInstance {
        private final Block block;
        private final StatePropertiesPredicate properties;

        private final ItemPredicate item;

        public Instance(EntityPredicate.AndPredicate player, @Nullable Block block, StatePropertiesPredicate properties, ItemPredicate item) {
            super(DiscoveryBlockTrigger.ID, player);
            this.block = block;
            this.properties = properties;

            this.item = item;
        }

        public static DiscoveryBlockTrigger.Instance discoveryBlock(Block block) {

            return new DiscoveryBlockTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND, block, StatePropertiesPredicate.EMPTY, ItemPredicate.ANY);
        }

        public boolean test(BlockState state, ItemStack item) {
            if (this.block != null && !state.matchesBlock(this.block)) {
                return false;
            } else if (!this.properties.matches(state)) {
                return false;
            } else {
                return this.item.test(item);
            }
        }

        public JsonObject serialize(ConditionArraySerializer conditions) {
            JsonObject jsonobject = super.serialize(conditions);
            if (this.block != null) {
                jsonobject.addProperty("block", Registry.BLOCK.getKey(this.block).toString());
            }

            jsonobject.add("state", this.properties.toJsonElement());
            jsonobject.add("item", this.item.serialize());
            return jsonobject;
        }
    }
}
