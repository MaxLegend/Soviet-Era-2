package ru.tesmio.soviet.data.providers.advancements;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import ru.tesmio.soviet.core.Core;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.reg.RegItems;

import java.util.function.Consumer;

public class SovietAdvancement implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> aConsumer) {
        Advancement START_EXPLORE = Advancement.Builder.builder()
                .withDisplay(RegBlocks.YELLOW_BRICKS_1.get(),
                        new TranslationTextComponent("adv.start_exlore"),
                        new TranslationTextComponent("adv.start_exlore.description"),
                        new ResourceLocation(Core.MODID, "textures/advancements/backgrounds/adv_background.png"),
                        FrameType.TASK, true, true, false)
                .withCriterion("explore", InventoryChangeTrigger.Instance.forItems(RegItems.CRACKED_YELLOW_BRICK.get()))
                .register(aConsumer, "soviet:root");

        Advancement WRENCH_CRAFT = Advancement.Builder.builder()
                .withParent(START_EXPLORE).withDisplay(RegItems.WRENCH.get(),
                        new TranslationTextComponent("adv.wrench"),
                        new TranslationTextComponent("adv.wrench.description"),
                        null,
                        FrameType.TASK, true, true, false)
                .withCriterion("wrench", InventoryChangeTrigger.Instance.forItems(RegItems.WRENCH.get()))
                .register(aConsumer, "soviet:wrench");
        Advancement WIRECUTTERS_CRAFT = Advancement.Builder.builder()
                .withParent(START_EXPLORE).withDisplay(RegItems.WIRE_CUTTERS.get(),
                        new TranslationTextComponent("adv.wc"),
                        new TranslationTextComponent("adv.wc.description"),
                        null,
                        FrameType.TASK, true, true, false)
                .withCriterion("wirecutters", InventoryChangeTrigger.Instance.forItems(RegItems.WIRE_CUTTERS.get()))

                .register(aConsumer, "soviet:wirecutters");

        Advancement WIP = Advancement.Builder.builder()
                .withParent(WRENCH_CRAFT).withDisplay(RegItems.VARIANT_ITEM.get(),
                        new TranslationTextComponent("adv.wip"),
                        new TranslationTextComponent("adv.wip.description"),
                        null,
                        FrameType.TASK, true, true, false)
                .withCriterion("wirecutters", InventoryChangeTrigger.Instance.forItems(RegItems.VARIANT_ITEM.get()))

                .register(aConsumer, "soviet:wip");
        Advancement WIP2 = Advancement.Builder.builder()
                .withParent(WIRECUTTERS_CRAFT).withDisplay(RegItems.VARIANT_ITEM.get(),
                        new TranslationTextComponent("adv.wip"),
                        new TranslationTextComponent("adv.wip.description"),
                        null,
                        FrameType.TASK, true, true, false)
                .register(aConsumer, "soviet:wip2");
    }


}
