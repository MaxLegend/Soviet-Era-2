package ru.tesmio.blocks.decorative.lamp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class BrokenFluoLamp extends BlockRotatedAxisCustomModel {
    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(16D, 0D, 11D, 0D, 2D, 5D),
            Block.makeCuboidShape(11D, 0D, 16D, 5D, 2D, 0D),
            Block.makeCuboidShape(11D, 16D, 16D, 5D, 14D, 0D),
            Block.makeCuboidShape(16D, 16D, 11D, 0D, 14D, 5D),
            Block.makeCuboidShape(0D, 5D, 0D, 16D, 11D, 2D),
            Block.makeCuboidShape(0D, 5D, 14D, 16D, 11D, 16D),
            Block.makeCuboidShape(14D, 5D, 0D, 16D, 11D, 16D),
            Block.makeCuboidShape(0D, 5D, 0D, 2D, 11D, 16D)};

    public BrokenFluoLamp(Properties builder) {
        super(builder, 1F);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item activeItemRight = player.getHeldItemMainhand().getItem();
        Item activeItemLeft = player.getHeldItemOffhand().getItem();
        if(activeItemRight == RegItems.FLUOLAMP.get()) {
            if(player.getHeldItemMainhand().getCount() >= 2) {
                worldIn.setBlockState(pos, RegBlocks.FLUORESCENT_LAMP.get().getDefaultState().with(FluoLamp.CLOSED, false).with(FluoLamp.FACING, state.get(FACING)));
                if(!player.isCreative()) player.getHeldItemMainhand().shrink(2);
                return ActionResultType.SUCCESS;
            }
        } if(activeItemLeft == RegItems.FLUOLAMP.get()) {
            if(player.getHeldItemOffhand().getCount() >= 2) {
                worldIn.setBlockState(pos, RegBlocks.FLUORESCENT_LAMP.get().getDefaultState().with(FluoLamp.CLOSED, false).with(FluoLamp.FACING, state.get(FACING)));
                if(!player.isCreative())player.getHeldItemOffhand().shrink(2);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
        }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case UP_X:
                return BOXS[3];
            case UP_Z:
                return BOXS[2];
            case DOWN_X:
                return BOXS[0];
            case DOWN_Z:
                return BOXS[1];
            case NORTH:
                return BOXS[4];
            case SOUTH:
                return BOXS[5];
            case WEST:
                return BOXS[7];
            case EAST:
                return BOXS[6];


        }
        return VoxelShapes.fullCube();
    }
}
