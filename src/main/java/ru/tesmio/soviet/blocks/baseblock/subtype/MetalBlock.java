package ru.tesmio.soviet.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import ru.tesmio.soviet.blocks.baseblock.BaseBlock;

public class MetalBlock extends BaseBlock {
    public MetalBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3f, 8f));
    }
}
