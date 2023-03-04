package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import ru.tesmio.blocks.baseblock.BlockInfo;

public class FerroconcreteBlock extends BlockInfo {
    public FerroconcreteBlock(String info) {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(3f,8f), info);
    }
}
