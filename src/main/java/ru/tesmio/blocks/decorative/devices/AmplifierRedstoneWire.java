package ru.tesmio.blocks.decorative.devices;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class AmplifierRedstoneWire extends BlockSideDevice {
    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    private boolean canProvidePower = true;
    public AmplifierRedstoneWire(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, Boolean.valueOf(false)).with(FACING, Direction.NORTH).with(POWER, 0));
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return Block.makeCuboidShape(0D, 0D, 13D, 16D, 16D, 16D);
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("info.amplifier_redstone_wire", Integer.toString(1000)));
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(2,4))
        };
    }

    private int getStrongestSignal(World world, BlockState s, BlockPos pos) {
            this.canProvidePower = false;
            int i = world.getRedstonePowerFromNeighbors(pos);
            this.canProvidePower = true;
            int j = 0;
            if (i < 15 && i > 0 && s.get(POWERED)) {
                i = 15;
            }
            return i;

    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!w.isRemote) {
            this.updatePower(w, p, s);
            w.getPendingBlockTicks().scheduleTick(p, this, 4);
        }
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(player.isCrouching()) {
            state = state.cycleValue(POWERED);
            worldIn.setBlockState(pos, state);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
    private int getPower(BlockState state) {
        return state.matchesBlock(this) ? state.get(POWER) : 0;
    }
    private void updatePower(World world, BlockPos pos, BlockState state) {
        int i = this.getStrongestSignal(world, state, pos);
        System.out.println("fff " + i);
        if (state.get(POWER) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, Integer.valueOf(i)), 2);
            }
            Set<BlockPos> set = Sets.newHashSet();
            set.add(pos);

            for(Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }

            for(BlockPos blockpos : set) {
                world.notifyNeighborsOfStateChange(blockpos, this);
            }
        }
    }
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return !this.canProvidePower ? 0 : blockState.getWeakPower(blockAccess, pos, side);
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        if (this.canProvidePower) {
                int i = blockState.get(POWER);

                if (i > 0  && blockState.get(POWERED)) {
                    return 15;
                } else if(i > 0  && !blockState.get(POWERED)) {
                    return i - 1;
                }
                else {
                    return 0;
                }

        } else {
            return 0;
        }

    }
    public boolean canProvidePower(BlockState state) {
        return this.canProvidePower;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(POWER, POWERED, FACING,WATERLOGGED);
    }
}
