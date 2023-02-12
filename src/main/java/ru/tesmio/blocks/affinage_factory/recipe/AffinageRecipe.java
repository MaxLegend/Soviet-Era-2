package ru.tesmio.blocks.affinage_factory.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import ru.tesmio.core.Core;
import ru.tesmio.recipes.OneByOneRecipe;
import ru.tesmio.reg.RegRecipeSerializers;

public class AffinageRecipe extends OneByOneRecipe {

    public AffinageRecipe(ResourceLocation id, Ingredient input, ItemStack output, int countInput, int countOutput, boolean oneByOne) {
        super(id, input, output, countInput, countOutput, new ResourceLocation(Core.MODID,"affinage"), oneByOne);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegRecipeSerializers.AFFINAGE_SERIALIZER.get();
    }
}