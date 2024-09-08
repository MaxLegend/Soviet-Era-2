package ru.tesmio.items;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BaseEnumOrientation;
import ru.tesmio.blocks.baseblock.BlockRotatedAxis;
import ru.tesmio.reg.RegBlocks;

public class ArmatureFrame extends Item {
    public ArmatureFrame(Properties properties) {
        super(properties);
    }
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockState stateOffset = context.getWorld().getBlockState(context.getPos().offset(context.getFace()));
        World w = context.getWorld();
        BlockPos pos = context.getPos();
        if(stateOffset.getBlock() instanceof AirBlock) {
            w.setBlockState(pos.offset(context.getFace()), RegBlocks.RAILING_BLOCK.get().getDefaultState().with(BlockRotatedAxis.FACING, BaseEnumOrientation.DOWN_X));
            if(!context.getPlayer().isCreative()) {
                context.getPlayer().getHeldItemMainhand().shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
