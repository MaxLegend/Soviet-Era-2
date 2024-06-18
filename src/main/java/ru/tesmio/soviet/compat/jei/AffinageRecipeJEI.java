package ru.tesmio.soviet.compat.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ru.tesmio.soviet.blocks.affinage_factory.recipe.AffinageRecipe;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegBlocks;

public class AffinageRecipeJEI implements IRecipeCategory<AffinageRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Core.MODID, "affinage");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Core.MODID, "textures/gui/affinage_factory.png");
    private final IDrawable background;
    private final IDrawable icon;

    public AffinageRecipeJEI(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 69);
        this.icon = helper.createDrawableIngredient(new ItemStack(RegBlocks.AFFINAGE_FACTORY.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AffinageRecipe> getRecipeClass() {
        return AffinageRecipe.class;
    }

    @Override
    public String getTitle() {
        return RegBlocks.AFFINAGE_FACTORY.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(AffinageRecipe recipe, IIngredients ing) {
        ing.setInputIngredients(recipe.getIngredients());
        ing.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AffinageRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 50, 29);

        recipeLayout.getItemStacks().init(1, false, 106, 29);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(AffinageRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        Minecraft mc = Minecraft.getInstance();
        //  String s = new TranslationTextComponent("").getString();

        mc.fontRenderer.drawString(matrixStack, I18n.format("redstone.energy"), 7.0f, 55.0f, 0x404040);
        //         this.progress.draw(matrixStack, 82, 9);
    }
}
