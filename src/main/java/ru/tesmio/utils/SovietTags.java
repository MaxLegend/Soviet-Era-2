package ru.tesmio.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import ru.tesmio.core.Core;

public class SovietTags {

    public static class Blocks {

//        public static final Tags.IOptionalNamedTag<Block> FIRESTONE_CLICKABLE_BLOCKS = createTag("firestone_clickable_blocks");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Core.MODID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> SILICON_INGOT = createForgeTag("ingots/silicon_ingot");
        public static final Tags.IOptionalNamedTag<Item> LEAD_INGOT = createForgeTag("ingots/lead_ingot");
        public static final Tags.IOptionalNamedTag<Item> LEAD_NUGGET = createForgeTag("nuggets/lead_nugget");
        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Core.MODID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }

        public static Item getItemFromTag(Tags.IOptionalNamedTag<Item> tag) {
            return tag.getAllElements().get(0);
        }
    }
}
