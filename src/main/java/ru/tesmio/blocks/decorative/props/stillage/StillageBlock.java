package ru.tesmio.blocks.decorative.props.stillage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import ru.tesmio.blocks.baseblock.BlockSideUpDownCM;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegTileEntitys;

public class StillageBlock extends BlockSideUpDownCM {
    public StillageBlock(Properties p) {
        super(p, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(PART, BlockSideUpDownCM.EnumPart.MIDDLE));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(s.get(PART) == EnumPart.UP) {
            return Block.makeCuboidShape(0,0,0,16,1,16);
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.WOOD_SCRAP.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(1,3))
        };
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
   
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        World w = context.getWorld();
        BlockPos p = context.getPos();
        BlockState thisBS = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
        if (w.getBlockState(p.up()).getBlock() != thisBS.getBlock()) {
            thisBS = thisBS.with(PART, BlockSideUpDownCM.EnumPart.UP);
        } else {
            thisBS = thisBS.with(PART, BlockSideUpDownCM.EnumPart.MIDDLE);
        }
        return thisBS;
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.STILLAGE_TE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof StillageTileEntity) {

                    NetworkHooks.openGui((ServerPlayerEntity) player, (StillageTileEntity) te, pos);

            }
        }
        return ActionResultType.SUCCESS;
    }
}
