package ru.tesmio.soviet.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import ru.tesmio.soviet.blocks.affinage_factory.recipe.AffinageRecipe;
import ru.tesmio.soviet.blocks.crusher.recipe.CrusherRecipe;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegRecipeSerializers;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class CoreJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Core.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new CrusherRecipeJEI(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(
                new AffinageRecipeJEI(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(RegRecipeSerializers.CRUSHER_TYPE).stream()
                        .filter(r -> r instanceof CrusherRecipe).collect(Collectors.toList()),
                CrusherRecipeJEI.UID);
        registration.addRecipes(rm.getRecipesForType(RegRecipeSerializers.AFFINAGE_TYPE).stream()
                        .filter(r -> r instanceof AffinageRecipe).collect(Collectors.toList()),
                AffinageRecipeJEI.UID);
    }
}
