package ru.tesmio.soviet.blocks.decorative.props;

import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.tesmio.soviet.reg.RegItems;

public class ToxicAir extends AirBlock {
    public ToxicAir(Properties p_i48451_1_) {
        super(p_i48451_1_);
    }

    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
        if (e instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) e;
            if (player.inventory.armorInventory.get(3).getItem() == RegItems.SUIT_GAS_MASK.get()) {

            } else e.attackEntityFrom(DamageSource.WITHER, 1);
        }

    }
}
