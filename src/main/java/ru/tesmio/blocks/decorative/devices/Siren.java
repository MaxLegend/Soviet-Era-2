package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.decorative.devices.base.BlockForFacingDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Siren extends BlockForFacingDevice {

    public static final EnumProperty<Type> TYPE = EnumProperty.create("type", Type.class);
    public Siren(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, EnumOrientation.DOWN).with(TYPE, Type.BIO));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return getShape(state, worldIn, pos, context);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();

        return new ItemStack[] {
                new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), tr.nextInt(1,2))
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case SOUTH:
                return Block.makeCuboidShape(4D, 4D, 12.5D, 12D, 12D, 15.5D);
            case WEST:
                return VoxelShapeUtil.shapeRotCCW90(Block.makeCuboidShape(4D, 4D, 12.5D, 12D, 12D, 15.5D));
            case EAST:
                return VoxelShapeUtil.shapeRotCW90(Block.makeCuboidShape(4D, 4D, 12.5D, 12D, 12D, 15.5D));
            case NORTH:
                return VoxelShapeUtil.shapeRot180(Block.makeCuboidShape(4D, 4D, 12.5D, 12D, 12D, 15.5D));
            case DOWN:
                return Block.makeCuboidShape(4D, 0.5D, 4D, 12D, 3.5D, 12D);
            case UP:
                return Block.makeCuboidShape(4D, 12.5D, 4D, 12D, 15.5D, 12D);
        }
        return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if(!w.isRemote()) {
            if (w.isBlockPowered(p)) {
                switch (s.get(TYPE)) {
                    case BIO:
                        w.getPendingBlockTicks().scheduleTick(p, this, 45);
                        w.playSound(null, p, RegSounds.SOUND_BIOLOGICAL_ALARM.get(), SoundCategory.BLOCKS, 4.00f, 1f);
                        break;
                    case RAD:
                        w.getPendingBlockTicks().scheduleTick(p, this, 155);
                        w.playSound(null, p, RegSounds.SOUND_RADIATION_ALARM.get(), SoundCategory.BLOCKS, 4.00f, 1f);
                        break;
                    case CHEM:
                        w.getPendingBlockTicks().scheduleTick(p, this, 20);
                        w.playSound(null, p, RegSounds.SOUND_CHEMICAL_ALARM.get(), SoundCategory.BLOCKS, 4.00f, 1f);
                        break;
                }
            }
        }
    }

    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        w.getPendingBlockTicks().scheduleTick(p, this, 6);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand h, BlockRayTraceResult hit) {
        BlockState s2 = w.getBlockState(p);
            s2 = s2.cycleValue(TYPE);
            w.setBlockState(p, s2);
            return ActionResultType.SUCCESS;
    }
    public enum Type implements IStringSerializable {
        BIO("bio"),
        CHEM("chem"),
        RAD("rad");

        @Override
        public String getString() {
            return this.name;
        }
        private final String name;
        Type(String name) {
            this.name = name;
        }
    }
}
