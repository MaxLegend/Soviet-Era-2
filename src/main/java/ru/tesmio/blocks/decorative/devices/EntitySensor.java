package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegSounds;

import java.util.Random;

public abstract class EntitySensor extends BlockSideDevice {
    public static final BooleanProperty POWERED = BooleanProperty.create("active");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final EnumProperty<EnumEntityType> ENTITY_TYPE = EnumProperty.create("etype", EnumEntityType.class);
    public static final IntegerProperty RANGE = IntegerProperty.create("range", 0, 3);
    public static final AxisAlignedBB CUBE_BOX = VoxelShapes.fullCube().getBoundingBox();
    public final Class<? extends Entity> entityClass = LivingEntity.class;

    public enum EnumEntityType implements IStringSerializable {
        ALL_ENTITY("all"),
        PLAYER("player"),
        MOB("mob");
        private final String name;
        EnumEntityType(String name) {this.name = name;}
        @Override
        public String getString() {
            return this.name;
        }
    }
    private static VoxelShape[] AABB = new VoxelShape[] {
            Block.makeCuboidShape(3.68D, 4.8D, 12.96D, 12.16D, 12D, 16D),
            Block.makeCuboidShape(3.68D, 4.8D, 0D, 12.16D, 12D, 3.04D),
            Block.makeCuboidShape(0D, 4.8D, 3.68D, 3.04D, 12D, 12.16D),
            Block.makeCuboidShape(12.96D, 4.8D, 3.68D, 16D, 12D, 12.16D)
    };
    public EntitySensor(Properties properties) {
        super(properties, 1F);

        this.setDefaultState(this.getDefaultState().with(POWERED, false).with(RANGE, 0).with(DOWN, false).with(UP, false));
    }
//    public <T extends Entity> Predicate<T> getEntityFilter(World world, BlockPos p) {
//        BlockState s = world.getBlockState(p);
//        if(entityClass == PlayerEntity.class) {
//            world.setBlockState(p, s.with(ENTITY_TYPE, EnumEntityType.PLAYER));
//            return Predicates.alwaysTrue();
//        }
//        if(entityClass == MobEntity.class) {
//            world.setBlockState(p, s.with(ENTITY_TYPE, EnumEntityType.MOB));
//            return Predicates.alwaysTrue();
//        }
//        if(entityClass == LivingEntity.class) {
//            world.setBlockState(p, s.with(ENTITY_TYPE, EnumEntityType.ALL_ENTITY));
//            return Predicates.alwaysTrue();
//        }
//        return Predicates.alwaysFalse();
//    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, RANGE, UP, DOWN, WATERLOGGED);
    }
    @Override
    public boolean canProvidePower(BlockState state)
    {
        return true;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return blockState.getWeakPower(blockAccess, pos, side);
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side)
    {
        return blockState.get(POWERED) ? 15 : 0;
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (state.hasProperty(RANGE)) {
            int oldLevel = state.get(RANGE);
            int newLevel = (oldLevel + 1) % 4;
            if(world.isBlockPowered(pos)) {
//                Item activeItemRight = player.getHeldItemMainhand().getItem();
//                Item activeItemLeft = player.getHeldItemOffhand().getItem();
//                if(activeItemRight == RegItems.VARIANT_ITEM.get() || activeItemLeft == RegItems.VARIANT_ITEM.get()) {
//                    state = state.cycleValue(ENTITY_TYPE);
//                    world.setBlockState(pos, state);
//                    return ActionResultType.SUCCESS;
//                }
                world.setBlockState(pos, state.with(RANGE, newLevel));
                this.onUpdate(state, world, pos);
                float volume = 0.25F + world.rand.nextFloat() * 0.1F;
                float pitch = 0.5F + world.rand.nextFloat() * 0.1F;
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, volume, pitch);

                return ActionResultType.SUCCESS;
            }
        } else {
            return super.onBlockActivated(state, world, pos, player, hand, hit);
        }
        return ActionResultType.FAIL;
    }
        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

            switch (state.get(FACING)) {
                case WEST:
                    return AABB[3];
                case EAST:
                    return AABB[2];
                case NORTH:
                    return AABB[0];
                case SOUTH:
                    return AABB[1];
            }
        return AABB[0];
    }

    @Override
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand)
    {
        if(w.isBlockPowered(p)) {
            this.onUpdate(s, w, p);
            if(s.get(POWERED)) {

                // w.playSound(p.getX(),p.getY(),p.getZ(), RegSounds.SOUND_MOTION_SENSOR.get(), SoundCategory.BLOCKS, 0.1f, 1f, true);
                w.playSound(null, p, RegSounds.SOUND_MOTION_SENSOR.get(), SoundCategory.BLOCKS, 0.04f, 1f);
            }
        }
    }

    @Override
    public void onBlockAdded(BlockState state, World w, BlockPos p, BlockState oldState, boolean isMoving)
    {
        if (state.getBlock() != oldState.getBlock())
        {
            if(w.isBlockPowered(p)) {
                w.getPendingBlockTicks().scheduleTick(p, this, 4);
                this.onUpdate(state, w, p);

            }
            this.updateState(state,w,p);
        }
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        this.updateState(s,w,p);
    }
    public void onEntityCollision(BlockState s, World w, BlockPos p, Entity e) {
   //     if(!s.get(POWERED)) w.setBlockState(p, s.with(POWERED, true));

    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    public void updateState(BlockState s, World w, BlockPos p) {
            BlockState s2 = s.getBlockState();
            if (w.getBlockState(p.up()).isSolidSide(w, p.up(), Direction.DOWN)) {
                s2 = s2.with(UP, true);
            } else {
                s2 = s2.with(UP, false);
            }
            if (w.getBlockState(p.down()).isSolidSide(w, p.down(), Direction.UP)) {
                    s2 = s2.with(DOWN, true);
            }else {
                s2 = s2.with(DOWN, false);
            }
            w.setBlockState(p, s2, 3);
    }

    public void onUpdate(BlockState s, World w, BlockPos p)
    {

        BlockState as = w.getBlockState(p);

        double range = 2D * Math.pow(2D, as.get(RANGE));
        AxisAlignedBB east = CUBE_BOX.offset(p).grow(range,0,0).offset(range, 0,0);
        AxisAlignedBB west = CUBE_BOX.offset(p).grow(range,0,0).offset(-range, 0,0);
        AxisAlignedBB north = CUBE_BOX.offset(p).grow(0,0,range).offset(0, 0,range);
        AxisAlignedBB south = CUBE_BOX.offset(p).grow(0,0,range).offset(0, 0,-range);
        double range2 = range*range;
        Vector3d center = new Vector3d(p.getX() + 0.5D, p.getY() + 0.5D, p.getZ() + 0.5D);
        if (!w.isRemote() && as.hasProperty(RANGE) && as.hasProperty(POWERED)) {
            boolean areEntitiesNear = false;
                switch (as.get(FACING)) {
                    case NORTH:
                        areEntitiesNear = w.getEntitiesWithinAABB(this.entityClass, south).stream().anyMatch(entity -> entity.getDistanceSq(center) < range2);
                        break;
                    case WEST:
                        areEntitiesNear = w.getEntitiesWithinAABB(this.entityClass, west).stream().anyMatch(entity -> entity.getDistanceSq(center) < range2);
                        break;
                    case EAST:
                        areEntitiesNear = w.getEntitiesWithinAABB(this.entityClass, east).stream().anyMatch(entity -> entity.getDistanceSq(center) < range2);
                        break;
                    case SOUTH:
                        areEntitiesNear = w.getEntitiesWithinAABB(this.entityClass, north).stream().anyMatch(entity -> entity.getDistanceSq(center) < range2);
                        break;
                }
            if (areEntitiesNear != as.get(POWERED))
            {

                w.setBlockState(p, as.with(POWERED, areEntitiesNear), 3);
            }
            }

                    w.getPendingBlockTicks().scheduleTick(p, this, 4);
            }

    }

