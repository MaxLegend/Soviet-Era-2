package ru.tesmio.blocks.baseblock;

import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.BedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class BlockBed extends BedBlock  {


    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public BlockBed(Properties properties) {
        super(DyeColor.BLACK, properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(PART, BedPart.FOOT).with(OCCUPIED, Boolean.valueOf(false)).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        spawnDrops(state, worldIn, pos, te, player, stack);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, PART, OCCUPIED, WATERLOGGED);
    }

    private static Direction getDirectionToOther(BedPart part, Direction direction) {
        return part == BedPart.FOOT ? direction : direction.getOpposite();
    }
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.CONSUME;
        } else {
            if (state.get(PART) != BedPart.HEAD) {
                pos = pos.offset(state.get(HORIZONTAL_FACING));
                state = worldIn.getBlockState(pos);
                if (!state.matchesBlock(this)) {
                    return ActionResultType.CONSUME;
                }
            }

            if (!doesBedWork(worldIn)) {
                worldIn.removeBlock(pos, false);
                BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING).getOpposite());
                if (worldIn.getBlockState(blockpos).matchesBlock(this)) {
                    worldIn.removeBlock(blockpos, false);
                }

                worldIn.createExplosion((Entity)null, DamageSource.causeBedExplosionDamage(), (ExplosionContext)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, Explosion.Mode.DESTROY);
                return ActionResultType.SUCCESS;
            } else if (state.get(OCCUPIED)) {
                if (!this.tryWakeUpVillager(worldIn, pos)) {
                    player.sendStatusMessage(new TranslationTextComponent("block.minecraft.bed.occupied"), true);
                }

                return ActionResultType.SUCCESS;
            } else {
                player.trySleep(pos).ifLeft((result) -> {
                    if (result != null) {
                        player.sendStatusMessage(result.getMessage(), true);
                    }

                });
                return ActionResultType.SUCCESS;
            }
        }
    }

    public static boolean doesBedWork(World world) {
        return world.getDimensionType().doesBedWork();
    }

    private boolean tryWakeUpVillager(World world, BlockPos pos) {
        List<VillagerEntity> list = world.getEntitiesWithinAABB(VillagerEntity.class, new AxisAlignedBB(pos), LivingEntity::isSleeping);
        if (list.isEmpty()) {
            return false;
        } else {
            list.get(0).wakeUp();
            return true;
        }
    }
    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        if (entityIn.isSuppressingBounce()) {
            super.onLanded(worldIn, entityIn);
        } else {
            this.bounceEntity(entityIn);
        }

    }
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
    }
    private void bounceEntity(Entity entity) {
        Vector3d vector3d = entity.getMotion();
        if (vector3d.y < 0.0D) {
            double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;
            entity.setMotion(vector3d.x, -vector3d.y * (double)0.66F * d0, vector3d.z);
        }

    }
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing == getDirectionToOther(stateIn.get(PART), stateIn.get(HORIZONTAL_FACING))) {
            return facingState.matchesBlock(this) && facingState.get(PART) != stateIn.get(PART) ?
                    stateIn.with(OCCUPIED, facingState.get(OCCUPIED)) : Blocks.AIR.getDefaultState();
        } else {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote && player.isCreative()) {
            BedPart bedpart = state.get(PART);
            if (bedpart == BedPart.FOOT) {
                BlockPos blockpos = pos.offset(getDirectionToOther(bedpart, state.get(HORIZONTAL_FACING)));
                BlockState blockstate = worldIn.getBlockState(blockpos);
                if (blockstate.getBlock() == this && blockstate.get(PART) == BedPart.HEAD) {
                    worldIn.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 35);
                    worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
                }
            }
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getPlacementHorizontalFacing();
        BlockPos blockpos = context.getPos();
        BlockPos blockpos1 = blockpos.offset(direction);
        return context.getWorld().getBlockState(blockpos1).isReplaceable(context) ? this.getDefaultState().with(HORIZONTAL_FACING, direction) : null;
    }
    private static boolean isBedBelow(IBlockReader blockReader, BlockPos pos) {
        return blockReader.getBlockState(pos.down()).getBlock() instanceof BedBlock;
    }

    private static Optional<Vector3d> func_242653_a(EntityType<?> type, ICollisionReader collisionReader, BlockPos pos, Direction direction1, Direction direction2) {
        int[][] aint = func_242658_b(direction1, direction2);
        Optional<Vector3d> optional = func_242654_a(type, collisionReader, pos, aint, true);
        if (optional.isPresent()) {
            return optional;
        } else {
            BlockPos blockpos = pos.down();
            Optional<Vector3d> optional1 = func_242654_a(type, collisionReader, blockpos, aint, true);
            if (optional1.isPresent()) {
                return optional1;
            } else {
                int[][] aint1 = func_242655_a(direction1);
                Optional<Vector3d> optional2 = func_242654_a(type, collisionReader, pos, aint1, true);
                if (optional2.isPresent()) {
                    return optional2;
                } else {
                    Optional<Vector3d> optional3 = func_242654_a(type, collisionReader, pos, aint, false);
                    if (optional3.isPresent()) {
                        return optional3;
                    } else {
                        Optional<Vector3d> optional4 = func_242654_a(type, collisionReader, blockpos, aint, false);
                        return optional4.isPresent() ? optional4 : func_242654_a(type, collisionReader, pos, aint1, false);
                    }
                }
            }
        }
    }

    private static Optional<Vector3d> func_242654_a(EntityType<?> type, ICollisionReader collisionReader, BlockPos pos, int[][] p_242654_3_, boolean p_242654_4_) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int[] aint : p_242654_3_) {
            blockpos$mutable.setPos(pos.getX() + aint[0], pos.getY(), pos.getZ() + aint[1]);
            Vector3d vector3d = TransportationHelper.func_242379_a(type, collisionReader, blockpos$mutable, p_242654_4_);
            if (vector3d != null) {
                return Optional.of(vector3d);
            }
        }

        return Optional.empty();
    }
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }


    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (!worldIn.isRemote) {
            BlockPos blockpos = pos.offset(state.get(HORIZONTAL_FACING));
            worldIn.setBlockState(blockpos, state.with(PART, BedPart.HEAD), 3);
            worldIn.updateBlock(pos, Blocks.AIR);
            state.updateNeighbours(worldIn, pos, 3);
        }

    }
    private static int[][] func_242658_b(Direction direction1, Direction direction2) {
        return new int[][]{{direction2.getXOffset(), direction2.getZOffset()}, {direction2.getXOffset() - direction1.getXOffset(), direction2.getZOffset() - direction1.getZOffset()}, {direction2.getXOffset() - direction1.getXOffset() * 2, direction2.getZOffset() - direction1.getZOffset() * 2}, {-direction1.getXOffset() * 2, -direction1.getZOffset() * 2}, {-direction2.getXOffset() - direction1.getXOffset() * 2, -direction2.getZOffset() - direction1.getZOffset() * 2}, {-direction2.getXOffset() - direction1.getXOffset(), -direction2.getZOffset() - direction1.getZOffset()}, {-direction2.getXOffset(), -direction2.getZOffset()}, {-direction2.getXOffset() + direction1.getXOffset(), -direction2.getZOffset() + direction1.getZOffset()}, {direction1.getXOffset(), direction1.getZOffset()}, {direction2.getXOffset() + direction1.getXOffset(), direction2.getZOffset() + direction1.getZOffset()}};
    }

    private static int[][] func_242655_a(Direction direction) {
        return new int[][]{{0, 0}, {-direction.getXOffset(), -direction.getZOffset()}};
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new BedTileEntity(DyeColor.BROWN);
    }


}
