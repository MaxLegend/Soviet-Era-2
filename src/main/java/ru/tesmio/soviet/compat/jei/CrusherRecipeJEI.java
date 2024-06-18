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
import ru.tesmio.soviet.blocks.crusher.recipe.CrusherRecipe;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegBlocks;

public class CrusherRecipeJEI implements IRecipeCategory<CrusherRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Core.MODID, "crusher");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Core.MODID, "textures/gui/crusher.png");
    private final IDrawable background;
    private final IDrawable icon;

    //   private final IDrawableStatic progress;
    public CrusherRecipeJEI(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 69);
        this.icon = helper.createDrawableIngredient(new ItemStack(RegBlocks.CRUSHER.get()));

        //     this.progress = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CrusherRecipe> getRecipeClass() {
        return CrusherRecipe.class;
    }

    @Override
    public String getTitle() {
        return RegBlocks.CRUSHER.get().getTranslatedName().getString();
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
    public void setIngredients(CrusherRecipe recipe, IIngredients ing) {
        ing.setInputIngredients(recipe.getIngredients());
        ing.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CrusherRecipe recipe, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 50, 29);
        recipeLayout.getItemStacks().init(1, false, 106, 29);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public void draw(CrusherRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        Minecraft mc = Minecraft.getInstance();
        //  String s = new TranslationTextComponent("").getString();

        mc.fontRenderer.drawString(matrixStack, I18n.format("redstone.energy"), 7.0f, 55.0f, 0x404040);
        //         this.progress.draw(matrixStack, 82, 9);
    }
}
