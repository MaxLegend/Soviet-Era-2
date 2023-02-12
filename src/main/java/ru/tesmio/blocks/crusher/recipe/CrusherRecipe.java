package ru.tesmio.blocks.crusher.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import ru.tesmio.core.Core;
import ru.tesmio.recipes.OneByOneRecipe;
import ru.tesmio.reg.RegRecipeSerializers;

public class CrusherRecipe extends OneByOneRecipe {

    public CrusherRecipe(ResourceLocation id, Ingredient input, ItemStack output, int countInput, int countOutput, boolean oneByOne) {
        super(id, input, output, countInput, countOutput, new ResourceLocation(Core.MODID,"crusher"), oneByOne);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RegRecipeSerializers.CRUSHER_SERIALIZER.get();
    }
}
