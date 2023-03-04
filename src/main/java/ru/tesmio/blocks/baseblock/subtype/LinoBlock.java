package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BaseBlock;

public class LinoBlock extends BaseBlock {
    public LinoBlock() {
        super(AbstractBlock.Properties.create(Material.WOOL)
                .setRequiresTool()
                .hardnessAndResistance(1f,2f)
                .harvestTool(ToolType.AXE)
                .sound(SoundType.CLOTH));
    }

}
