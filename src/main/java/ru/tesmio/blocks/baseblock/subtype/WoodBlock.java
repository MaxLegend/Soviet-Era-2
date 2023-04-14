package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import ru.tesmio.blocks.baseblock.BaseBlock;

public class WoodBlock extends BaseBlock {
    public WoodBlock() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .setRequiresTool()
                .hardnessAndResistance(3f,8f));
    }
}
