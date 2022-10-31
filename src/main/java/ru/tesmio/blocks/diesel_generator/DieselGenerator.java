package ru.tesmio.blocks.diesel_generator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import ru.tesmio.blocks.BlockCustomModel;
import ru.tesmio.reg.RegBlocks;

import javax.annotation.Nullable;
import java.util.List;

public class DieselGenerator extends BlockCustomModel {
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(Block.makeCuboidShape(0D, 0D, 2D, 14D, 1.0D, 14D),
            Block.makeCuboidShape(1D, 1D, 4.25D, 13D, 3.0D, 11.5D),
            Block.makeCuboidShape(1D, 4D, 3D, 15D, 9.0D, 13D),
            Block.makeCuboidShape(0D, 9D, 4D, 16D, 11.25D, 12D),
            Block.makeCuboidShape(0D, 11.75D, 5.75D, 16D, 13.5D, 10.25D),
            Block.makeCuboidShape(0D, 11.75D, 6.75D, 16D, 14.5D, 9.25D));
    private static final VoxelShape SOUTH_AABB = VoxelShapes.or(Block.makeCuboidShape(16D, 0D, 2D, 2D, 1.0D, 14D),
            Block.makeCuboidShape(3D, 1D, 4.25D, 15D, 3.0D, 11.5D),
            Block.makeCuboidShape(1D, 4D, 3D, 15D, 9.0D, 13D),
            Block.makeCuboidShape(0D, 9D, 4D, 16D, 11.25D, 12D),
            Block.makeCuboidShape(0D, 11.75D, 5.75D, 16D, 13.5D, 10.25D),
            Block.makeCuboidShape(0D, 11.75D, 6.75D, 16D, 14.5D, 9.25D));
    private static final VoxelShape WEST_AABB = VoxelShapes.or(Block.makeCuboidShape(14D, 0D, 2D, 2D, 1.0D, 16D),
            Block.makeCuboidShape(12D, 1D, 3D, 4.25D, 3.0D, 15D),
            Block.makeCuboidShape(13D, 4D, 1D, 3D, 9.0D, 15D),
            Block.makeCuboidShape(12D, 9D, 0D, 4D, 11.25D, 16D),
            Block.makeCuboidShape(10.25D, 11.75D, 0D, 5.75D, 13.5D, 16D),
            Block.makeCuboidShape(9.25D, 11.75D, 0D, 6.75D, 14.5D, 16D));
    private static final VoxelShape EAST_AABB = VoxelShapes.or(Block.makeCuboidShape(14D, 0D, 0D, 2D, 1.0D, 14D),
            Block.makeCuboidShape(12D, 1D, 1D, 4.25D, 3.0D, 12.5D),
            Block.makeCuboidShape(13D, 4D, 1D, 3D, 9.0D, 15D),
            Block.makeCuboidShape(12D, 9D, 0D, 4D, 11.25D, 16D),
            Block.makeCuboidShape(10.25D, 11.75D, 0D, 5.75D, 13.5D, 16D),
            Block.makeCuboidShape(9.25D, 11.75D, 0D, 6.75D, 14.5D, 16D));
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape BOX = Block.makeCuboidShape(0D, 0D, 0D, 16D, 16.0D, 16D);
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

    }
    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("message.diesel_gen", Integer.toString(1000)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileDieselGenerator) {
                ((TileDieselGenerator) tile).setCustomName(stack.getDisplayName());
            }
        }
    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    public DieselGenerator(Properties properties, VoxelShape s) {
        super(properties, s);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(BlockStateProperties.POWERED, Boolean.valueOf(false)));
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                    return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
                return EAST_AABB;
        }
        return NORTH_AABB;

    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileDieselGenerator();
    }
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        if(worldIn.getTileEntity(pos) instanceof TileDieselGenerator) {
            return true;
        }
        else return  false;

    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileDieselGenerator) {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen.mytutorial.firstblock");
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new DieselGeneratorContainer(i, world, pos, playerInventory, playerEntity);
                    }
                };
                if(state.get(FACING) == Direction.NORTH) {
                    BlockState stateEGen = world.getBlockState(pos.offset(Direction.EAST));
                    BlockState stateTank = world.getBlockState(pos.offset(Direction.WEST));
                    if(stateEGen.getBlock() == RegBlocks.DIESEL_E_GENERATOR.get() && stateTank.getBlock() == RegBlocks.DIESEL_TANK.get()) {
                       if(stateEGen.get(FACING) == state.get(FACING) && stateTank.get(FACING) == state.get(FACING)) {
                            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                        }
                    }
                }
                if(state.get(FACING) == Direction.SOUTH) {
                    BlockState stateEGen = world.getBlockState(pos.offset(Direction.WEST));
                    BlockState stateTank = world.getBlockState(pos.offset(Direction.EAST));
                    if(stateEGen.getBlock() == RegBlocks.DIESEL_E_GENERATOR.get() && stateTank.getBlock() == RegBlocks.DIESEL_TANK.get()) {
                        if(stateEGen.get(FACING) == state.get(FACING) && stateTank.get(FACING) == state.get(FACING)) {
                            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                        }
                    }
                }
                if(state.get(FACING) == Direction.EAST) {
                    BlockState stateEGen = world.getBlockState(pos.offset(Direction.SOUTH));
                    BlockState stateTank = world.getBlockState(pos.offset(Direction.NORTH));
                    if(stateEGen.getBlock() == RegBlocks.DIESEL_E_GENERATOR.get() && stateTank.getBlock() == RegBlocks.DIESEL_TANK.get()) {
                        if(stateEGen.get(FACING) == state.get(FACING) && stateTank.get(FACING) == state.get(FACING)) {
                            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                        }
                    }
                }
                if(state.get(FACING) == Direction.WEST) {
                    BlockState stateEGen = world.getBlockState(pos.offset(Direction.NORTH));
                    BlockState stateTank = world.getBlockState(pos.offset(Direction.SOUTH));
                    if(stateEGen.getBlock() == RegBlocks.DIESEL_E_GENERATOR.get() && stateTank.getBlock() == RegBlocks.DIESEL_TANK.get()) {
                        if(stateEGen.get(FACING) == state.get(FACING) && stateTank.get(FACING) == state.get(FACING)) {
                            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                        }
                    }
                }


            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED, BlockStateProperties.POWERED);
    }
}
