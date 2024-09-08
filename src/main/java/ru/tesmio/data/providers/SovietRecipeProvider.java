package ru.tesmio.data.providers;

import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import ru.tesmio.core.Core;
import ru.tesmio.core.SETags;
import ru.tesmio.data.recipes.ShapelessRecipeBuilder2;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.function.Consumer;

public class SovietRecipeProvider extends ForgeRecipeProvider {
    public SovietRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }
    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        ShapedRecipeBuilder.shapedRecipe(RegItems.REDSTONE_GRINDER.get())
                .key('#', Items.IRON_INGOT)
                .key('$', SETags.Items.TAG_COPPER_INGOT)
                .key('R', Items.REDSTONE)
                .key('T', Items.DIAMOND)
                .patternLine("T##")
                .patternLine("TR#")
                .patternLine("#$$")
                .addCriterion("has_item", hasItem(Items.DIAMOND))
                .build(consumer, modId("redstone_grinder"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.WIRE_CUTTERS.get())
                .key('#', Items.IRON_INGOT)
                .key('$', Items.STICK)
                .patternLine("# #")
                .patternLine("$#$")
                .patternLine("$ $")
                .addCriterion("has_item", hasItem(Items.IRON_INGOT))
                .build(consumer, modId("wire_cutters"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PULLER.get())
                .key('#', Items.IRON_INGOT)
                .key('$', Items.STICK)
                .patternLine("# #")
                .patternLine(" $ ")
                .patternLine(" $ ")
                .addCriterion("has_item", hasItem(Items.IRON_INGOT))
                .build(consumer, modId("puller"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.WRENCH.get())
                .key('#', Items.IRON_INGOT)
                .patternLine("# #")
                .patternLine("###")
                .patternLine("  #")
                .addCriterion("has_item", hasItem(Items.IRON_INGOT))
                .build(consumer, modId("wrench"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.QUAD_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("has_item", hasItem(RegItems.MORTAR.get()))
                .build(consumer, modId("ceramic_dust1"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.REST_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("has_item", hasItem(RegItems.MORTAR.get()))
                .build(consumer, modId("ceramic_dust2"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.SMALL_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("has_item", hasItem(RegItems.MORTAR.get()))
                .build(consumer, modId("ceramic_dust3"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.BIG_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("has_item", hasItem(RegItems.MORTAR.get()))
                .build(consumer, modId("ceramic_dust4"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PESTLE.get())
                .key('$', Items.STICK)
                .key('%', Items.IRON_INGOT)
                .patternLine("   ")
                .patternLine(" $ ")
                .patternLine("%  ")
                .addCriterion("has_item",  hasItem(Items.IRON_INGOT))
                .build(consumer, modId("pestle"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.MORTAR.get())
                .key('$', Blocks.STONE)
                .patternLine("   ")
                .patternLine("$ $")
                .patternLine(" $ ")
                .addCriterion("has_item",  hasItem(Blocks.STONE))
                .build(consumer, modId("mortar"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.SIEVE.get())
                .key('$', Tags.Items.NUGGETS_IRON)
                .patternLine("   ")
                .patternLine("$ $")
                .patternLine(" $ ")
                .addCriterion("has_item",  hasItem(Tags.Items.NUGGETS_IRON))
                .build(consumer, modId("sieve"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_DUST.get())
                .key('#', RegItems.GOLD_DUST.get())
                .key('$', RegItems.PLATINUM_DUST.get())
                .patternLine("$$$")
                .patternLine("$#$")
                .patternLine("$$$")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_DUST.get())).addCriterion("has_item2",  hasItem(RegItems.PLATINUM_DUST.get()))
                .build(consumer, modId("platol_dust"));
        ShapelessRecipeBuilder.shapelessRecipe(RegItems.SMALL_LEAD_DUST.get(), 1)
                .addIngredient(RegItems.LEADCERAMIC_DUST.get())
                .addIngredient(RegItems.SIEVE.get())
                .addCriterion("has_item", hasItem(RegItems.LEADCERAMIC_DUST.get()))
                .build(consumer, modId("small_lead_dust"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_SWORD.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("  $")
                .patternLine(" $ ")
                .patternLine("#  ")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_INGOT.get()))
                .build(consumer, modId("platinum_sword"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_PICKAXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("$$$")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_INGOT.get()))
                .build(consumer, modId("platinum_pickaxe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_AXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine(" $$")
                .patternLine(" #$")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_INGOT.get()))
                .build(consumer, modId("platinum_axe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_HOE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine("$$ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_INGOT.get()))
                .build(consumer, modId("platinum_hoe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATINUM_SHOVEL.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATINUM_INGOT.get())
                .patternLine(" $ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATINUM_INGOT.get()))
                .build(consumer, modId("platinum_shovel"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_SWORD.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("  $")
                .patternLine(" $ ")
                .patternLine("#  ")
                .addCriterion("has_item",  hasItem(RegItems.PLATOL_INGOT.get()))
                .build(consumer, modId("platol_sword"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_PICKAXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("$$$")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATOL_INGOT.get()))
                .build(consumer, modId("platol_pickaxe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_AXE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine(" $$")
                .patternLine(" #$")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATOL_INGOT.get()))
                .build(consumer, modId("platol_axe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_HOE.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine("$$ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATOL_INGOT.get()))
                .build(consumer, modId("platol_hoe"));
        ShapedRecipeBuilder.shapedRecipe(RegItems.PLATOL_SHOVEL.get())
                .key('#', Items.STICK)
                .key('$', RegItems.PLATOL_INGOT.get())
                .patternLine(" $ ")
                .patternLine(" # ")
                .patternLine(" # ")
                .addCriterion("has_item",  hasItem(RegItems.PLATOL_INGOT.get()))
                .build(consumer, modId("platol_shovel"));
        ShapelessRecipeBuilder.shapelessRecipe(RegItems.LEAD_DUST.get(), 1)
                .addIngredient(RegItems.SMALL_LEAD_DUST.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.SMALL_LEAD_DUST.get()))
                .build(consumer, modId("lead_dust"));
        ShapelessRecipeBuilder.shapelessRecipe(Items.BRICK)
                .addIngredient(Items.CLAY_BALL, 1).addIngredient(RegItems.CRACKED_RED_BRICK.get())
                .addCriterion("has_item", hasItem(RegItems.CRACKED_RED_BRICK.get()))
                .build(consumer, modId("vanilla_brick"));
        ShapelessRecipeBuilder.shapelessRecipe(RegBlocks.BRICKS_BR.get())
                .addIngredient(RegItems.CRACKED_RED_BRICK.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.CRACKED_RED_BRICK.get()))
                .build(consumer, modId("red_bricks"));
        ShapelessRecipeBuilder.shapelessRecipe(RegBlocks.YELLOW_BRICKS_3_BR.get())
                .addIngredient(RegItems.CRACKED_YELLOW_BRICK.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.CRACKED_YELLOW_BRICK.get()))
                .build(consumer, modId("yel_bricks"));
        ShapelessRecipeBuilder.shapelessRecipe(RegBlocks.WHITE_BRICKS_BR.get())
                .addIngredient(RegItems.WHITE_BRICK.get(), 4)
                .addCriterion("has_item", hasItem(RegItems.WHITE_BRICK.get()))
                .build(consumer, modId("white_bricks"));

        ShapelessRecipeBuilder2.shapelessRecipe(Blocks.GRAVEL)
                .addIngredient(SETags.Items.TAG_BROKEN_BRICK, 9)
                .addCriterion("has_item", hasItem(SETags.Items.TAG_BROKEN_BRICK))
                .build(consumer, modId("vanilla_gravel"));



        ShapelessRecipeBuilder2.shapelessRecipe(RegItems.BEIGE_DYE.get())
                .addIngredient(Items.WHITE_DYE, 1)
                .addIngredient(Items.BROWN_DYE, 1)
                .addIngredient(Items.YELLOW_DYE, 1)
                .addCriterion("has_item", hasItem(Items.YELLOW_DYE))
                .addCriterion("has_item2", hasItem(Items.BROWN_DYE))
                .addCriterion("has_item3", hasItem(Items.WHITE_DYE))
                .build(consumer, modId("beige_dye"));
        ShapelessRecipeBuilder2.shapelessRecipe(RegItems.BEIGE2_DYE.get())
                .addIngredient(Items.WHITE_DYE, 1)
                .addIngredient(Items.BROWN_DYE, 1)
                .addCriterion("has_item", hasItem(Items.BROWN_DYE))
                .addCriterion("has_item2", hasItem(Items.WHITE_DYE))
                .build(consumer, modId("beige2_dye"));

        ShapelessRecipeBuilder2.shapelessRecipe(RegBlocks.DYE_BUCKET.get())
                .addIngredient(Items.CRIMSON_ROOTS, 1)
                .addIngredient(Items.WATER_BUCKET, 1)
                .addCriterion("has_item", hasItem(Items.CRIMSON_ROOTS))
                .build(consumer, modId("dye_bucket"));

        //blasting recipe
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.PLATOL_DUST.get()), RegItems.PLATOL_INGOT.get(), 5f, 100)
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
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.PLATINUM_DUST.get()), RegItems.PLATINUM_INGOT.get(), 3f, 600)
                .addCriterion("has_item", hasItem(RegItems.PLATINUM_DUST.get()))
                .build(consumer, modId("platinum_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.SILVER_DUST.get()), RegItems.SILVER_INGOT.get(), 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.SILVER_DUST.get()))
                .build(consumer, modId("silver_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.COPPER_DUST.get()), RegItems.COPPER_INGOT.get(), 0.4f, 600)
                .addCriterion("has_item", hasItem(RegItems.COPPER_DUST.get()))
                .build(consumer, modId("copper_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.ALUMINUM_DUST.get()), RegItems.ALUMINUM_INGOT.get(), 0.7f, 600)
                .addCriterion("has_item", hasItem(RegItems.ALUMINUM_DUST.get()))
                .build(consumer, modId("aluminum_dust"));

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.ALUMINUM_SCRAP.get()), RegItems.ALUMINUM_INGOT.get(), 0.7f, 600)
                .addCriterion("has_item", hasItem(RegItems.ALUMINUM_SCRAP.get()))

                .build(consumer, modId("aluminum_scrap"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.LEAD_DUST.get()), RegItems.LEAD_INGOT.get(), 0.7f, 600)
                .addCriterion("has_item", hasItem(RegItems.LEAD_DUST.get()))
                .build(consumer, modId("lead_dust2"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.RUSTY_SCRAP.get()), Items.IRON_INGOT, 0.2f, 1000)
                .addCriterion("has_item", hasItem(RegItems.RUSTY_SCRAP.get()))
                .build(consumer, modId("rusty_scrap"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.PALLADIUM_DUST.get()), RegItems.PALLADIUM_INGOT.get(), 3.5f, 1600)
                .addCriterion("has_item", hasItem(RegItems.PALLADIUM_DUST.get()))
                .build(consumer, modId("palladium_ingot"));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(RegItems.NETHERITE_DUST.get()), Items.NETHERITE_INGOT, 0.9f, 1500)
                .addCriterion("has_item", hasItem(RegItems.NETHERITE_DUST.get()))
                .build(consumer, modId("netherite_ingot"));

    }

    //крафты сделать 4 мусор в измельчитель -> 1 порошок -> 1 слиток. Как раньше.
    private static ResourceLocation modId(String path) {
        return new ResourceLocation(Core.MODID, path);
    }
}