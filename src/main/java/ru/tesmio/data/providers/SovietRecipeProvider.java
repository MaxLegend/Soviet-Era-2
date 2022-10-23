package ru.tesmio.data.providers;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import ru.tesmio.Core;
import ru.tesmio.reg.RegItems;

import java.util.function.Consumer;

public class SovietRecipeProvider extends ForgeRecipeProvider {
    public SovietRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.QUAD_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.QUAD_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust1");
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.REST_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.REST_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust2");
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.SMALL_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.SMALL_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust3");
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.BIG_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.SMALL_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust4");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PESTLE.get())
                .key('$', Items.STICK)
                .key('%', Items.IRON_INGOT)
                .patternLine("   ")
                .patternLine(" $ ")
                .patternLine("%  ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "pestle");
        ShapedRecipeBuilder.shapedRecipe(RegItems.MORTAR.get())
                .key('$', Blocks.STONE)
                .patternLine("   ")
                .patternLine("$ $")
                .patternLine(" $ ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "mortar");
        ShapedRecipeBuilder.shapedRecipe(RegItems.SIEVE.get())
                .key('$', Tags.Items.NUGGETS_IRON)
                .patternLine("   ")
                .patternLine("$ $")
                .patternLine(" $ ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "sieve");

        ShapelessRecipeBuilder.shapelessRecipe(RegItems.SMALL_LEAD_DUST.get(), 1)
                .addIngredient(RegItems.LEADCERAMIC_DUST.get())
                .addIngredient(RegItems.SIEVE.get())
                .addCriterion("has_item", hasItem(RegItems.SMALL_LEAD_DUST.get()))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(RegItems.LEAD_DUST.get(), 1)
                .addIngredient(RegItems.SMALL_LEAD_DUST.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.LEAD_DUST.get()))
                .build(consumer);
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.LEAD_DUST.get()), RegItems.LEAD_INGOT.get(), 0.7f, 100)
                .addCriterion("has_item", hasItem(RegItems.LEAD_DUST.get()))
                .build(consumer, modId("lead_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.CERAMIC_DUST.get()), RegItems.SILICON_INGOT.get(), 0.7f, 100)
                .addCriterion("has_item", hasItem(RegItems.CERAMIC_DUST.get()))
                .build(consumer, modId("silicon_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.ARMATURES.get()), Items.IRON_INGOT, 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.ARMATURES.get()))
                .build(consumer, modId("iron_ingot"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(Core.MODID, path);
    }
}