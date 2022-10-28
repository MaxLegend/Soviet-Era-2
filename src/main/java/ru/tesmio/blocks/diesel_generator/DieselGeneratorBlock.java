package ru.tesmio.blocks.diesel_generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.NetworkHooks;
import ru.tesmio.blocks.BlockCustomModel;
import ru.tesmio.reg.RegTileEntitys;
import ru.tesmio.utils.Tooltip;

import javax.annotation.Nullable;
import java.util.List;

public class DieselGeneratorBlock extends BlockCustomModel {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16.0D, 16D);
    public DieselGeneratorBlock(Properties properties, VoxelShape s) {
        super(properties, s);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return BOX;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote)
        {
            ItemStack itemStack = new ItemStack(this);

            TileDieselGenerator localTileEntity = (TileDieselGenerator) worldIn.getTileEntity(pos);

            int internalEnergy = localTileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);

            if(internalEnergy > 0) {
                CompoundNBT energyValue = new CompoundNBT();
                energyValue.putInt("value", internalEnergy);

                CompoundNBT energy = new CompoundNBT();
                energy.put("diesel", energyValue);

                CompoundNBT root = new CompoundNBT();
                root.put("BlockEntityTag", energy);
                itemStack.setTag(root);

                NetworkHooks.openGui((ServerPlayerEntity) player, localTileEntity, localTileEntity.getPos());
            }
        }
        return ActionResultType.SUCCESS;
    }
    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.DIESEL_TE.get().create();
    }
    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
        int energy = 0;
        if(compoundnbt != null)
            if(compoundnbt.contains("diesel"))
                energy = compoundnbt.getCompound("diesel").getInt("value");

        Tooltip.showInfoCtrl(energy, tooltip);

    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }
}
