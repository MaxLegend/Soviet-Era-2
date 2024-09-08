package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BlockInfo;

public class FerroconcreteBlockDmg extends BlockInfo {
    public FerroconcreteBlockDmg(String info) {
        super(AbstractBlock.Properties.create(Material.ROCK)
                .setRequiresTool()
                .hardnessAndResistance(6f,16f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .sound(SoundType.STONE), info);
    }
}