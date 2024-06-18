package ru.tesmio.soviet.blocks.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.tesmio.soviet.reg.RegItems;

public class ToxicWaterBlock extends FlowingFluidBlock implements IBucketPickupHandler {
    public ToxicWaterBlock(java.util.function.Supplier<? extends FlowingFluid> supplier, Properties p_i49014_2_) {
        super(supplier, p_i49014_2_);
    }

    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if (e instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) e;
            if (player.inventory.armorInventory.get(0).getItem() == RegItems.SUIT_BOOTS.get()
                    && ((PlayerEntity) e).inventory.armorInventory.get(2).getItem() == RegItems.SUIT_JACKET.get()
                    && ((PlayerEntity) e).inventory.armorInventory.get(1).getItem() == RegItems.SUIT_LEGS.get()) {

            } else
                e.attackEntityFrom(DamageSource.WITHER, 1);
        }


    }
}
