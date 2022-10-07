package ru.tesmio.data.providers;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.ResourceLocation;
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
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.QUAD_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust2");
        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.SMALL_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("pestle", InventoryChangeTrigger.Instance.forItems(RegItems.PESTLE.get(),RegItems.QUAD_TILE.get(), RegItems.MORTAR.get()))
                .build(consumer, "ceramic_dust3");
    }

    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
//        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SILVER_INGOT.get(), 9)
//                .requires(ModBlocks.SILVER_BLOCK.get())
//                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
//                .save(consumer);

        ShapedRecipeBuilder.shapedRecipe(RegItems.CERAMIC_DUST.get())
                .key('#', RegItems.PESTLE.get())
                .key('$', RegItems.QUAD_TILE.get())
                .key('%', RegItems.MORTAR.get())
                .patternLine(" # ")
                .patternLine(" $ ")
                .patternLine(" % ")
                .addCriterion("has_item", hasItem(RegItems.CERAMIC_DUST.get()))
                .build(consumer);


//        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 200)
//                .unlockedBy("has_item", has(ModBlocks.SILVER_ORE.get()))
//                .save(consumer, modId("silver_ingot_smelting"));
//        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 100)
//                .unlockedBy("has_item", has(ModBlocks.SILVER_ORE.get()))
//                .save(consumer, modId("silver_ingot_blasting"));
    }

    private static ResourceLocation modId(String path) {
        return new ResourceLocation(Core.MODID, path);
    }
}