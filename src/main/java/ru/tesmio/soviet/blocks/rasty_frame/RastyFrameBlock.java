package ru.tesmio.soviet.blocks.rasty_frame;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.soviet.blocks.baseblock.BaseBlock;
import ru.tesmio.soviet.reg.RegBlocks;
import ru.tesmio.soviet.utils.BlockSavingHelper;

import javax.annotation.Nullable;
import java.util.List;

public class RastyFrameBlock extends BaseBlock {
    public RastyFrameBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NOT_CONTAINS_BLOCK, Boolean.TRUE)
                .with(CONTAINS_BLOCK_NORTH, Boolean.FALSE)
                .with(CONTAINS_BLOCK_SOUTH, Boolean.FALSE)
                .with(CONTAINS_BLOCK_WEST, Boolean.FALSE)
                .with(CONTAINS_BLOCK_EAST, Boolean.FALSE)
                .with(CONTAINS_BLOCK_TOP, Boolean.FALSE)
                .with(CONTAINS_BLOCK_BOTTOM, Boolean.FALSE));
    }

    public static final BooleanProperty NOT_CONTAINS_BLOCK = BooleanProperty.create("not_contains");
    public static final BooleanProperty CONTAINS_BLOCK_NORTH = BooleanProperty.create("contains_block_n");
    public static final BooleanProperty CONTAINS_BLOCK_SOUTH = BooleanProperty.create("contains_block_s");
    public static final BooleanProperty CONTAINS_BLOCK_EAST = BooleanProperty.create("contains_block_e");
    public static final BooleanProperty CONTAINS_BLOCK_WEST = BooleanProperty.create("contains_block_w");
    public static final BooleanProperty CONTAINS_BLOCK_TOP = BooleanProperty.create("contains_block_t");
    public static final BooleanProperty CONTAINS_BLOCK_BOTTOM = BooleanProperty.create("contains_block_b");

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(NOT_CONTAINS_BLOCK).add(CONTAINS_BLOCK_NORTH).add(CONTAINS_BLOCK_SOUTH).add(CONTAINS_BLOCK_EAST).add(CONTAINS_BLOCK_WEST).add(CONTAINS_BLOCK_TOP).add(CONTAINS_BLOCK_BOTTOM);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            //  dropContainedBlock(worldIn, pos);

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("info.rasty_frame"));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RastyFrameTile();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        ItemStack item = player.getHeldItem(hand);
        Direction d = trace.getFace();
        TileEntity tileEntity = world.getTileEntity(pos);
        RastyFrameTile fTEG = (RastyFrameTile) tileEntity;
        if (!world.isRemote) {
            if (item.getItem() instanceof BlockItem) {
                if (((BlockItem) item.getItem()).getBlock() == RegBlocks.RASTY_FRAME.get()) {
                    return ActionResultType.FAIL;
                }
                int count = player.getHeldItem(hand).getCount();
                Block heldBlock = ((BlockItem) item.getItem()).getBlock();
                BlockState handBlockState = ((BlockItem) item.getItem()).getBlock().getDefaultState();

                if (tileEntity instanceof RastyFrameTile && !item.isEmpty() && BlockSavingHelper.isValidBlock(heldBlock)) {
                    switch (d) {
                        case NORTH:
                            if (!state.get(CONTAINS_BLOCK_NORTH)) {
                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_NORTH, Boolean.TRUE), 2);
                                fTEG.setNorthContains(handBlockState);
                            }
                            break;
                        case SOUTH:
                            if (!state.get(CONTAINS_BLOCK_SOUTH)) {
                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_SOUTH, Boolean.TRUE), 2);
                                fTEG.setSouthContains(handBlockState);
                            }
                            break;
                        case EAST:
                            if (!state.get(CONTAINS_BLOCK_EAST)) {

                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_EAST, Boolean.TRUE), 2);
                                fTEG.setEastContains(handBlockState);
                            }
                            break;
                        case WEST:
                            if (!state.get(CONTAINS_BLOCK_WEST)) {
                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_WEST, Boolean.TRUE), 2);
                                fTEG.setWestContains(handBlockState);
                            }
                            break;
                        case UP:
                            if (!state.get(CONTAINS_BLOCK_TOP)) {
                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_TOP, Boolean.TRUE), 2);
                                fTEG.setUpContains(handBlockState);
                            }
                            break;
                        case DOWN:
                            if (!state.get(CONTAINS_BLOCK_BOTTOM)) {
                                world.setBlockState(pos, state.with(CONTAINS_BLOCK_BOTTOM, Boolean.TRUE), 2);
                                fTEG.setDownContains(handBlockState);
                            }
                            break;
                    }
                }
            }

        }
        if (player.getHeldItem(hand).getItem() == Items.IRON_INGOT) {
            fTEG.clear();
            state = state
                    .with(CONTAINS_BLOCK_NORTH, Boolean.FALSE)
                    .with(CONTAINS_BLOCK_BOTTOM, Boolean.FALSE)
                    .with(CONTAINS_BLOCK_TOP, Boolean.FALSE)
                    .with(CONTAINS_BLOCK_SOUTH, Boolean.FALSE)
                    .with(CONTAINS_BLOCK_WEST, Boolean.FALSE)
                    .with(CONTAINS_BLOCK_EAST, Boolean.FALSE);
            world.setBlockState(pos, state, 2);

        }
        return ActionResultType.SUCCESS;
    }

    protected void dropContainedBlock(World worldIn, BlockPos pos, BlockRayTraceResult result) {
        if (!worldIn.isRemote) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof RastyFrameTile) {
                RastyFrameTile frameTileEntity = (RastyFrameTile) tileentity;
                BlockState blockState = frameTileEntity.getDownContains();
                switch (result.getFace()) {
                    case DOWN:
                        blockState = frameTileEntity.getDownContains();
                        break;
                    case UP:
                        blockState = frameTileEntity.getUpContains();
                        break;
                    case WEST:
                        blockState = frameTileEntity.getWestContains();
                        break;
                    case EAST:
                        blockState = frameTileEntity.getEastContains();
                        break;
                    case SOUTH:
                        blockState = frameTileEntity.getSouthContains();
                        break;
                    case NORTH:
                        blockState = frameTileEntity.getNorthContains();
                        break;
                }

                if (!(blockState == null)) {
                    worldIn.playEvent(1010, pos, 0);
                    frameTileEntity.clear();
                    double d0 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
                    double d1 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.060000002F + 0.6D;
                    double d2 = (double) (worldIn.rand.nextFloat() * 0.7F) + (double) 0.15F;
                    ItemStack itemstack1 = blockState.getBlock().asItem().getDefaultInstance();
                    ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, itemstack1);
                    itementity.setDefaultPickupDelay();
                    worldIn.addEntity(itementity);
                    frameTileEntity.clear();
                }
            }
        }
    }

    public boolean isTransparent(BlockState state) {
        //return this.isTransparent;
        return true;
    }
}
