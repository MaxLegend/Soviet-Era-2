package ru.tesmio.blocks.baseblock;

import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BaseFourWayBlock extends FourWayBlock {
    boolean isCustomDrop = false;
    public BaseFourWayBlock(float p_i48420_1_, float p_i48420_2_, float p_i48420_3_, float p_i48420_4_, float p_i48420_5_, Properties p_i48420_6_) {
        super(p_i48420_1_, p_i48420_2_, p_i48420_3_, p_i48420_4_, p_i48420_5_, p_i48420_6_);
    }
    public boolean isCustomDrop() {
        return isCustomDrop;
    }
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if (!w.isRemote) {
            if (!pl.isCreative()) {
                if(isCustomDrop()) {
                    getDropsWithBlock(w, p, pl);
                }
                getAdditionDrops(w,p,getStackAddDrop(pl));
            }
        }
    }
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }
}
