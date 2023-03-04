package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import ru.tesmio.blocks.baseblock.BlockInfo;

public class TilledBlock extends BlockInfo {
    public TilledBlock(String info) {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3f,8f), info);
    }
    public TilledBlock() {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3f,8f));

    }
}
