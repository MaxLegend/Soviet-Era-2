package ru.tesmio.reg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.BlockInfo;

import java.util.function.Supplier;

public class RegBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Core.MODID);


    public static RegistryObject<Block> CONCRETE_ORANGE,CONCRETE_ORANGE_BR,CONCRETE_BLUE,CONCRETE_RED,CONCRETE_GREEN,CONCRETE_GRAY,CONCRETE_BEIGE,CONCRETE_BEIGE2,CONCRETE_YELLOW,CONCRETE_WHITE,CONCRETE_BLUE_BR,CONCRETE_RED_BR,CONCRETE_GREEN_BR,CONCRETE_GRAY_BR,CONCRETE_BEIGE_BR,CONCRETE_BEIGE2_BR,CONCRETE_YELLOW_BR,CONCRETE_WHITE_BR;
    public static RegistryObject<Block> TILE_QUAD_WHITE,TILE_QUAD_GRAY, TILE_QUAD_BLUE, TILE_QUAD_CONCRETE, TILE_QUAD_WHITE_BR, TILE_QUAD_BLUE_BR;
    public static RegistryObject<Block> TILE_QUAD_1, TILE_QUAD_1_BR, TILE_QUAD_2, TILE_QUAD_2_BR, TILE_QUAD_3, TILE_QUAD_3_BR, TILE_QUAD_4, TILE_QUAD_5,TILE_QUAD_5_BR,TILE_QUAD_5_BRf;

    public static RegistryObject<Block> TILE_REST_DARK_BLUE, TILE_REST_DARK_BLUE_BR,TILE_REST_BLUE, TILE_REST_BLUE_BR, TILE_REST_BROWN, TILE_REST_BROWN_BR, TILE_REST_WHITE, TILE_REST_WHITE_BR, TILE_REST_BLACK, TILE_REST_BLACK_BR;


    public static void init() {
        //concrete
        CONCRETE_ORANGE = registerBlock("concrete/concrete_orange", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_orange"));
        CONCRETE_BLUE = registerBlock("concrete/concrete_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_blue"));
        CONCRETE_RED = registerBlock("concrete/concrete_red", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_red"));
        CONCRETE_YELLOW = registerBlock("concrete/concrete_yellow", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_yellow"));
        CONCRETE_WHITE = registerBlock("concrete/concrete_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_white"));
        CONCRETE_BEIGE = registerBlock("concrete/concrete_beige", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige"));
        CONCRETE_GREEN = registerBlock("concrete/concrete_green", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_green"));
        CONCRETE_GRAY = registerBlock("concrete/concrete_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_gray"));
        CONCRETE_BEIGE2 = registerBlock("concrete/concrete_beige2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.concrete_beige2"));
        CONCRETE_BLUE_BR = registerBlock("concrete/concrete_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_blue_br"));
        CONCRETE_ORANGE_BR = registerBlock("concrete/concrete_orange_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_orange_br"));
        CONCRETE_RED_BR = registerBlock("concrete/concrete_red_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_red_br"));
        CONCRETE_YELLOW_BR = registerBlock("concrete/concrete_yellow_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_yellow_br"));
        CONCRETE_WHITE_BR = registerBlock("concrete/concrete_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_white_br"));
        CONCRETE_BEIGE_BR = registerBlock("concrete/concrete_beige_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige_br"));
        CONCRETE_GREEN_BR = registerBlock("concrete/concrete_green_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_green_br"));
        CONCRETE_GRAY_BR = registerBlock("concrete/concrete_gray_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_gray_br"));
        CONCRETE_BEIGE2_BR = registerBlock("concrete/concrete_beige2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(2.5f,8f), "info.concrete_beige2_br"));

        //quad_tiles
        TILE_QUAD_GRAY = registerBlock("quadtile/tile_quad_gray", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_gray"));
        TILE_QUAD_WHITE = registerBlock("quadtile/tile_quad_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white"));
        TILE_QUAD_BLUE = registerBlock("quadtile/tile_quad_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue"));
        TILE_QUAD_1 = registerBlock("quadtile/tile_quad_1", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1"));
        TILE_QUAD_2 = registerBlock("quadtile/tile_quad_2", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2"));
        TILE_QUAD_4 = registerBlock("quadtile/tile_quad_4", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_4"));
        TILE_QUAD_5 = registerBlock("quadtile/tile_quad_5", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5"));
        TILE_QUAD_3 = registerBlock("quadtile/tile_quad_3", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3"));
        TILE_QUAD_1_BR = registerBlock("quadtile/tile_quad_1_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_1_br"));
        TILE_QUAD_2_BR = registerBlock("quadtile/tile_quad_2_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_2_br"));
        TILE_QUAD_3_BR = registerBlock("quadtile/tile_quad_3_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_3_br"));
        TILE_QUAD_5_BR = registerBlock("quadtile/tile_quad_5_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_br"));
        TILE_QUAD_5_BRf = registerBlock("quadtile/tile_quad_5_brf", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_5_brf"));
        TILE_QUAD_CONCRETE = registerBlock("quadtile/tile_quad_concrete", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_concrete"));
        TILE_QUAD_BLUE_BR = registerBlock("quadtile/tile_quad_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_blue_br"));
        TILE_QUAD_WHITE_BR = registerBlock("quadtile/tile_quad_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.quadtile_white_br"));

        //rest_tiles
        TILE_REST_DARK_BLUE = registerBlock("resttile/tile_rest_dark_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue"));
        TILE_REST_DARK_BLUE_BR = registerBlock("resttile/tile_rest_dark_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_dark_blue_br"));

        TILE_REST_BLUE = registerBlock("resttile/tile_rest_blue", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue"));
        TILE_REST_BLUE_BR = registerBlock("resttile/tile_rest_blue_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_blue_br"));
        TILE_REST_BLACK = registerBlock("resttile/tile_rest_black", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black"));
        TILE_REST_BLACK_BR = registerBlock("resttile/tile_rest_black_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_black_br"));
       TILE_REST_BROWN = registerBlock("resttile/tile_rest_brown", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown"));
        TILE_REST_BROWN_BR = registerBlock("resttile/tile_rest_brown_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_brown_br"));
        TILE_REST_WHITE = registerBlock("resttile/tile_rest_white", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white"));
        TILE_REST_WHITE_BR = registerBlock("resttile/tile_rest_white_br", () -> new BlockInfo(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f), "info.tile_rest_white_br"));

    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        init();
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RegItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Core.ItemGroups.TAB_MAIN)));
    }
}
