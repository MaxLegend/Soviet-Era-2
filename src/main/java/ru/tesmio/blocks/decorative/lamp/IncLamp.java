package ru.tesmio.blocks.decorative.lamp;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockRotatedLamp;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class IncLamp extends BlockRotatedLamp {

    public IncLamp(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(LIT_VALUE, 0));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(2)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(1)),
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape[] SHAPES = new VoxelShape[] {
                VoxelShapes.create(0.31D, 0.31D, 0.55D, 0.69D, 0.69D, 1D),
                VoxelShapes.create(0.31D, 0.31D, 0D, 0.69D, 0.69D, 0.45D),
                VoxelShapes.create(0.45D, 0.31D, 0.31D, 0D, 0.69D, 0.69D),
                VoxelShapes.create(0.55D, 0.31D, 0.31D, 1D, 0.69D, 0.69D),
                VoxelShapes.create(0.31D, 0D, 0.31D, 0.69D, 0.45D, 0.69D),
                VoxelShapes.create(0.31D, 0.55D, 0.31D, 0.69D, 1D, 0.69D)
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
