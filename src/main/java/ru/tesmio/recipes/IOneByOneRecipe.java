package ru.tesmio.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IOneByOneRecipe extends IRecipe<RecipeWrapper>  {

        @Override
        IRecipeType<?> getType();

        @Override
        default boolean canFit(int width, int height) {
             return false;
        }

        int getCountOutput();
        Ingredient getInput();
        int getCountInput();
        boolean getOneByOne();
        ResourceLocation getPath();
        }
