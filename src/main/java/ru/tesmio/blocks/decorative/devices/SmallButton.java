package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.decorative.devices.base.BlockForFacingDevice;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;

public class SmallButton extends BlockForFacingDevice {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public SmallButton(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(ENABLE, false).with(WATERLOGGED, false));
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, ENABLE, WATERLOGGED);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1))
        };
    }
    public boolean canProvidePower(BlockState state) {
        return true;
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
            worldIn.playSound(null, pos, RegSounds.SOUND_SNAP.get(), SoundCategory.BLOCKS, 0.5F, 1F);
            state = state.cycleValue(ENABLE);

            worldIn.setBlockState(pos, state);
        this.updateNeighbors(state, worldIn, pos);
            return ActionResultType.SUCCESS;

    }
    protected static Direction getFacing(BlockState state) {
        switch(state.get(FACING)) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case NORTH:
            default:
                return Direction.NORTH;
            case SOUTH:
                return Direction.SOUTH;
            case EAST:
                return Direction.EAST;
            case WEST:
                return Direction.WEST;
        }
    }
    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.notifyNeighborsOfStateChange(pos, this);
        world.notifyNeighborsOfStateChange(pos.offset(getFacing(state).getOpposite()), this);
    }
    public int getWeakPower(BlockState s, IBlockReader br, BlockPos p, Direction side) {
        if(s.get(ENABLE)){
            return 15;
        }
        return 0;
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] SHAPES = new VoxelShape[] {
                VoxelShapes.create(0.3725D, 0.3725D, 0.9D, 0.6275D, 0.6275D, 1D),
                VoxelShapes.create(0.3725D, 0.3725D, 0D, 0.6275D, 0.6275D, 0.1D),
                VoxelShapes.create(0.1D, 0.3725D, 0.3725D, 0D, 0.6275D, 0.6275D),
                VoxelShapes.create(0.9D, 0.3725D, 0.3725D, 1D, 0.6275D, 0.6275D),
                VoxelShapes.create(0.3725D, 0D, 0.3725D, 0.6275D, 0.1D, 0.6275D),
                VoxelShapes.create(0.3725D, 0.9D, 0.3725D, 0.6275D, 1D, 0.6275D)
        };
        switch (state.get(FACING)) {
            case SOUTH:
                return SHAPES[0];
            case NORTH:
                return SHAPES[1];
            case WEST:
                return SHAPES[2];
            case EAST:
                return SHAPES[3];
            case DOWN:
                return SHAPES[4];
            case UP:
                return SHAPES[5];

        }
        return VoxelShapes.fullCube();
    }
}
