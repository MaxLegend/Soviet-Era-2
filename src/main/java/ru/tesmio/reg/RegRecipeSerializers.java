package ru.tesmio.reg;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.affinage_factory.recipe.AffinageRecipe;
import ru.tesmio.blocks.crusher.recipe.CrusherRecipe;
import ru.tesmio.recipes.IOneByOneRecipe;
import ru.tesmio.recipes.OneByOneRecipeSerializer;

public class RegRecipeSerializers {
    static ResourceLocation CRUSHER_TYPE_ID = new ResourceLocation(Core.MODID, "crusher");
    static ResourceLocation AFFINAGE_TYPE_ID = new ResourceLocation(Core.MODID, "affinage");
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Core.MODID);
    public static final IRecipeType<IOneByOneRecipe> CRUSHER_TYPE = registerType(CRUSHER_TYPE_ID);
    public static final IRecipeType<IOneByOneRecipe> AFFINAGE_TYPE = registerType(AFFINAGE_TYPE_ID);
    public static final RegistryObject<IRecipeSerializer<?>> CRUSHER_SERIALIZER = RECIPE_SERIALIZERS.register("crusher", () -> new OneByOneRecipeSerializer<>(1,1, CrusherRecipe::new, false));
    public static final RegistryObject<IRecipeSerializer<?>> AFFINAGE_SERIALIZER = RECIPE_SERIALIZERS.register("affinage", () -> new OneByOneRecipeSerializer<>(1, 1, AffinageRecipe::new, false));

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RegRecipeSerializers.RecipeType<>());
    }

}
