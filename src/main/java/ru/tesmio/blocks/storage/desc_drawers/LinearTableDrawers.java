package ru.tesmio.blocks.storage.desc_drawers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import ru.tesmio.blocks.decorative.props.LinearTable;
import ru.tesmio.blocks.storage.base.TileEntityStorage;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegTileEntitys;

public class LinearTableDrawers extends LinearTable {

    public LinearTableDrawers(Properties properties) {
        super(properties);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.WOOD_SCRAP.get(), tr.nextInt(2,4)),
        };
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.DRAWERS_STORAGE_TE.get().create();
    }
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.matchesBlock(newState.getBlock())) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
                worldIn.updateComparatorOutputLevel(pos, this);
            }
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (player.getActiveItemStack().isEmpty()) {
                if (te instanceof TileEntityStorage) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (TileEntityStorage) te, pos);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }
}
