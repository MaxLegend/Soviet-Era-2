package ru.tesmio.blocks.placeable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.enums.EnumBucketState;
import ru.tesmio.reg.RegItems;

public class BucketDye extends BlockCustomModel {

    public static final EnumProperty<EnumBucketState> COLOR = EnumProperty.create("color", EnumBucketState.class);

    public BucketDye(Properties properties, float shadingInside) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(COLOR, EnumBucketState.WATER).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    VoxelShape[] SHP = new VoxelShape[] {
            Block.makeCuboidShape(4,3,4,12,10,12),
            Block.makeCuboidShape(5,3,3,11,10,13),
            Block.makeCuboidShape(3,3,5,13,10,11),
            Block.makeCuboidShape(5,3,3,11,10,13),
            Block.makeCuboidShape(5,0,4,11,3,12),
            Block.makeCuboidShape(4,0,5,12,3,11)
    };
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
                return VoxelShapes.or(
                        SHP[0], SHP[1], SHP[2], SHP[3], SHP[4], SHP[5]);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return getShape(s,w,p,c);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(COLOR, WATERLOGGED);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            //какие то проблемы с проверками. Странно работает установка ведрами воды. Условие НЕ не выполняется, и при этом - выполняется...) - просто похуй, сделаю крафт через ведро-ведро в сетке крафта. Игра же)
        /*    if(player.getHeldItemMainhand().getStack().getItem() == RegItems.DYE_BRUSH.get()) {
                if(worldIn.getBlockState(pos).getBlock() instanceof BucketDye) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.EMPTY));
                    return ActionResultType.SUCCESS;
                }
                return ActionResultType.FAIL;
            }
*/
            if(state.get(COLOR) == EnumBucketState.WATER) {
                //beige 1
                if(player.getHeldItemMainhand().getStack().getItem() == RegItems.BEIGE2_DYE.get()) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.BEIGE2));
                    if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
                    return ActionResultType.SUCCESS;
                }
                //beige 2
                if(player.getHeldItemMainhand().getStack().getItem() == RegItems.BEIGE_DYE.get()) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.BEIGE));
                    if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
                    return ActionResultType.SUCCESS;
                }
                //filler
                if((player.getHeldItemMainhand().getStack().getItem() == Items.GRAY_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.FILLER));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //green
                if((player.getHeldItemMainhand().getStack().getItem() == Items.GREEN_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.GREEN));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //red
                if((player.getHeldItemMainhand().getStack().getItem() == Items.RED_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.RED));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //orange
                if((player.getHeldItemMainhand().getStack().getItem() == Items.ORANGE_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.ORANGE));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //blue
                if((player.getHeldItemMainhand().getStack().getItem() == Items.BLUE_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.BLUE));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //white
                if((player.getHeldItemMainhand().getStack().getItem() == Items.WHITE_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.WHITE));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
                //yellow
                if((player.getHeldItemMainhand().getStack().getItem() == Items.YELLOW_DYE)) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumBucketState.YELLOW));
                    if(!player.isCreative()) {
                        player.getHeldItemMainhand().shrink(1);
                        player.getHeldItemOffhand().shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                }
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.FAIL;
    }
}
