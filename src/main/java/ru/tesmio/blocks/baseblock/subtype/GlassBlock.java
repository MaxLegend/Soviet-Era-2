package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BaseBlock;

public class GlassBlock extends BaseBlock {
    public GlassBlock() {
        super(AbstractBlock.Properties.create(Material.GLASS)
                .setRequiresTool()
                .hardnessAndResistance(1f,2f)
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.GLASS)
                .notSolid());
    }
}
