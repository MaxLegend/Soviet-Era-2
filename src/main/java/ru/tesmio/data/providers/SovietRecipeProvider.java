package ru.tesmio.data.providers;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import ru.tesmio.core.Core;
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
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_DUST.get())
                .key('#', RegItems.GOLD_DUST.get())
                .key('$', RegItems.PLATINUM_DUST.get())
                .patternLine("$$$")
                .patternLine("$#$")
                .patternLine("$$$")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_dust");
        ShapelessRecipeBuilder.shapelessRecipe(RegItems.SMALL_LEAD_DUST.get(), 1)
                .addIngredient(RegItems.LEADCERAMIC_DUST.get())
                .addIngredient(RegItems.SIEVE.get())
                .addCriterion("has_item", hasItem(RegItems.SMALL_LEAD_DUST.get()))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_SWORD.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("  $")
                .patternLine(" $ ")
                .patternLine("#  ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platinum_sword");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_PICKAXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("$$$")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platinum_pickaxe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_AXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine(" $$")
                .patternLine(" #$")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platinum_axe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_HOE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("$$ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platinum_hoe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_SHOVEL.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine(" $ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platinum_shovel");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_SWORD.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("  $")
                .patternLine(" $ ")
                .patternLine("#  ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_sword");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_PICKAXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("$$$")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_pickaxe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_AXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine(" $$")
                .patternLine(" #$")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_axe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_HOE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("$$ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_hoe");
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_SHOVEL.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine(" $ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, "platol_shovel");
        ShapelessRecipeBuilder.shapelessRecipe(RegItems.LEAD_DUST.get(), 1)
                .addIngredient(RegItems.SMALL_LEAD_DUST.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.LEAD_DUST.get()))
                .build(consumer);
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.PLATOL_DUST.get()), RegItems.PLATOL_INGOT.get(), 0.7f, 100)
                .addCriterion("has_item", hasItem(RegItems.PLATOL_DUST.get()))
                .build(consumer, modId("platol_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.LEAD_DUST.get()), RegItems.LEAD_INGOT.get(), 0.7f, 100)
                .addCriterion("has_item", hasItem(RegItems.LEAD_DUST.get()))
                .build(consumer, modId("lead_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.CERAMIC_DUST.get()), RegItems.SILICON_INGOT.get(), 0.7f, 100)
                .addCriterion("has_item", hasItem(RegItems.CERAMIC_DUST.get()))
                .build(consumer, modId("silicon_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.ARMATURES.get()), Items.IRON_INGOT, 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.ARMATURES.get()))
                .build(consumer, modId("iron_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.GOLD_DUST.get()), Items.GOLD_INGOT, 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.GOLD_DUST.get()))
                .build(consumer, modId("gold_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.DIAMOND_DUST.get()), Items.DIAMOND, 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.DIAMOND_DUST.get()))
                .build(consumer, modId("diamond"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.PLATINUM_DUST.get()), RegItems.PLATINUM_INGOT.get(), 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.PLATINUM_DUST.get()))
                .build(consumer, modId("platinum_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.SILVER_DUST.get()), RegItems.SILVER_INGOT.get(), 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.SILVER_DUST.get()))
                .build(consumer, modId("silver_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.COPPER_DUST.get()), RegItems.COPPER_INGOT.get(), 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.COPPER_DUST.get()))
                .build(consumer, modId("copper_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.NETHERITE_DUST.get()), Items.NETHERITE_INGOT, 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.NETHERITE_DUST.get()))
                .build(consumer, modId("netherite_ingot"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(Core.MODID, path);
    }
}