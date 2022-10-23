package ru.tesmio.recipes;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.crusher.CrusherRecipe;
import ru.tesmio.blocks.crusher.CrusherRecipeSerializer;

public class RecipeSerializerInit {
        public static final IRecipeSerializer<CrusherRecipe> CRUSHER_RECIPE_SERIALIZER = new CrusherRecipeSerializer();
        public static final IRecipeType<ICrusherRecipe> CRUSHER_TYPE = registerType(ICrusherRecipe.RECIPE_TYPE_ID);

        public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Core.MODID);

        public static final RegistryObject<IRecipeSerializer<?>> CRUSHER_SERIALIZER = RECIPE_SERIALIZERS.register("crusher", () -> CRUSHER_RECIPE_SERIALIZER);

private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
    @Override
    public String toString() {
        return Registry.RECIPE_TYPE.getKey(this).toString();
    }
}

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }
}