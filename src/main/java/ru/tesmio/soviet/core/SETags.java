package ru.tesmio.soviet.core;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class SETags {



    public static class Blocks {

    }
    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> TAG_COPPER_INGOT = createForgeTag("ingots/copper");
        public static final Tags.IOptionalNamedTag<Item> TAG_SILVER_INGOT = createForgeTag( "ingots/silver");
        public static final Tags.IOptionalNamedTag<Item> TAG_LEAD_INGOT = createForgeTag( "ingots/lead");
        public static final Tags.IOptionalNamedTag<Item> TAG_ALUMINUM_INGOT = createForgeTag( "ingots/aluminum");
        public static final Tags.IOptionalNamedTag<Item> TAG_PALLADIUM_INGOT = createForgeTag( "ingots/palladium");
        public static final Tags.IOptionalNamedTag<Item> TAG_PLATINUM_INGOT = createForgeTag( "ingots/platinum");
        public static final Tags.IOptionalNamedTag<Item> TAG_PLATOL_INGOT = createForgeTag( "ingots/platol");
        public static final Tags.IOptionalNamedTag<Item> TAG_SILICON_INGOT = createForgeTag( "ingots/silicon");
        public static final Tags.IOptionalNamedTag<Item> TAG_BRICK = createForgeTag( "ingots/bricks");

         public static final Tags.IOptionalNamedTag<Item>  COPPER_TAG_DUST = createForgeTag("dusts/copper");
         public static final Tags.IOptionalNamedTag<Item>  SILVER_TAG_DUST = createForgeTag( "dusts/silver");
         public static final Tags.IOptionalNamedTag<Item>  LEAD_TAG_DUST = createForgeTag( "dusts/lead");
         public static final Tags.IOptionalNamedTag<Item>  DIAMOND_TAG_DUST = createForgeTag( "dusts/diamond");
         public static final Tags.IOptionalNamedTag<Item>  GOLD_TAG_DUST = createForgeTag( "dusts/gold");
         public static final Tags.IOptionalNamedTag<Item>  PALLADIUM_TAG_DUST = createForgeTag( "dusts/palladium");
         public static final Tags.IOptionalNamedTag<Item>  PLATINUM_TAG_DUST = createForgeTag( "dusts/platinum");
         public static final Tags.IOptionalNamedTag<Item>  PLATOL_TAG_DUST = createForgeTag( "dusts/platol");
         public static final Tags.IOptionalNamedTag<Item>  NETHERITE_TAG_DUST = createForgeTag( "dusts/netherite");

        public static final Tags.IOptionalNamedTag<Item> TAG_BROKEN_BRICK = createTag( "broken_brick");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Core.MODID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
