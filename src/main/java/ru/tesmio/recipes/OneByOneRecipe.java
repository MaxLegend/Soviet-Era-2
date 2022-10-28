package ru.tesmio.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class OneByOneRecipe implements IOneByOneRecipe {

    private final ResourceLocation id;
    public Ingredient input;
    private int countInput;
    private int countOutput;
    private boolean oneByOne;
    private final ResourceLocation path;

    private final ItemStack output;

    public  OneByOneRecipe(ResourceLocation id, Ingredient input, ItemStack output, int countInput, int countOutput, ResourceLocation path, boolean oneByOne) {
        this.id = id;
        this.output = output;
        this.input = input;
        this.countInput = countInput;
        this.countOutput = countOutput;
        this.path = path;
        this.oneByOne = oneByOne;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        ItemStack output = inv.getStackInSlot(1);
        if(output == null) {
            return false;
        }
        if(!output.isEmpty() && output.getItem() != this.getRecipeOutput().getItem()) {
            return false;
        } else
                if (!(inv.getStackInSlot(1).getCount() >= 64)) {

                        if (this.getOneByOne()) {
                            if (this.input.test(inv.getStackInSlot(0))) return true;
                        } else if (!this.getOneByOne()) {
                            if (this.input.test(inv.getStackInSlot(0)) && inv.getStackInSlot(0).getCount() >= this.getCountInput())
                                return true;
                        }

                }


        return false;
    }
    @Override
    public boolean getOneByOne() {
        return this.oneByOne;
    }
    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOrDefault(getPath());
    }
    @Override
    public Ingredient getInput() {
        return this.input;
    }
    @Override
    public int getCountOutput() {
        return this.countOutput;
    }
    @Override
    public int getCountInput() {
        return this.countInput;
    }
    @Override
    public ResourceLocation getPath() {
        return this.path;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.input);
    }
}
