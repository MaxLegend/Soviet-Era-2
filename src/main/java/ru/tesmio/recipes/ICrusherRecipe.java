package ru.tesmio.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import ru.tesmio.Core;

import javax.annotation.Nonnull;

public interface ICrusherRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(Core.MODID, "mech");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOrDefault(RECIPE_TYPE_ID);
    }

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    Ingredient getInput();

}
