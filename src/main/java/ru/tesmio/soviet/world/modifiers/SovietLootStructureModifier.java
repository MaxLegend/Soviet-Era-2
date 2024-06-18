package ru.tesmio.soviet.world.modifiers;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class SovietLootStructureModifier extends LootModifier {
    private final Item addition;

    protected SovietLootStructureModifier(ILootCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
   //     if(context.getRandom().nextFloat() > 0.15) {
            generatedLoot.add(new ItemStack(addition, 1));
   //     }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<SovietLootStructureModifier> {

        @Override
        public SovietLootStructureModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(JSONUtils.getString(object, "addition")));
            return new SovietLootStructureModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(SovietLootStructureModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}