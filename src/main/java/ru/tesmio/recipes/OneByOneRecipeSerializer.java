package ru.tesmio.recipes;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class OneByOneRecipeSerializer<T extends OneByOneRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    private final int countInput;
    private final int countOutput;
    private final boolean oneByOne;
    public  final IBuilder<T> factory;



    public OneByOneRecipeSerializer(int countInput, int countOutput, IBuilder<T> factory, boolean oneByOne) {
        this.countInput = countInput;
        this.countOutput = countOutput;
    this.factory = factory;
    this.oneByOne = oneByOne;
    }


    @Override
    public T read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
        int countInput = JSONUtils.getInt(json, "countInput", this.countInput);
        boolean oneByOne = JSONUtils.getBoolean(json, "oneByOne", this.oneByOne);
        int countOutput = JSONUtils.getInt(json, "countOutput", this.countOutput);
        return factory.create(recipeId, input, output, countInput, countOutput, oneByOne);
    }

    @Override
    public T read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        int countInput = buffer.readInt();
        int countOutput = buffer.readInt();
        boolean oneByOne = buffer.readBoolean();
        ItemStack output = buffer.readItemStack();
        return factory.create(recipeId, input, output, countInput, countOutput, oneByOne);
    }

    @Override
    public void write(PacketBuffer buffer, T recipe) {
        Ingredient input = recipe.getIngredients().get(0);
        input.write(buffer);
        buffer.writeInt(recipe.getCountInput());
        buffer.writeInt(recipe.getCountOutput());
        buffer.writeBoolean(recipe.getOneByOne());
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }
    public interface IBuilder<T extends OneByOneRecipe> {
        T create(ResourceLocation recipeId, Ingredient input, ItemStack output, int countInput, int countOutput, boolean oneByOne);
    }
}

