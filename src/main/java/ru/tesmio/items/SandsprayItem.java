package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.BlockRotatedAllSideCM;
import ru.tesmio.blocks.baseblock.BlockRotatedAxis;
import ru.tesmio.blocks.decorative.props.*;
import ru.tesmio.blocks.doors.LockedDoor;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegParticles;

import javax.annotation.Nullable;
import java.util.List;

public class SandsprayItem extends Item {
    public final String MAXUSE_NBT = "uses";
    public SandsprayItem(Properties properties) {
        super(properties);
    }
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack s, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new TranslationTextComponent("info.sandspray"));
        tooltip.add(new TranslationTextComponent("info.count").appendString(": " + getMaxUse(s, MAXUSE_NBT)));
    }

    public void setMaxUse(ItemStack itemStack, String key, int value) {
        CompoundNBT nbt = itemStack.getOrCreateTag();
        nbt.putInt(key, value);
        itemStack.setTag(nbt);
    }
    public void addUses(ItemStack itemStack, String key, int value) {
        CompoundNBT nbt = itemStack.getTag();
        nbt.putInt(key, nbt.getInt(MAXUSE_NBT)+value);
        itemStack.setTag(nbt);
    }
    public int getMaxUse(ItemStack itemStack, String key) {
        CompoundNBT nbt = itemStack.getTag();
        if (nbt != null && nbt.contains(key)) {
            return nbt.getInt(key);
        }
        return 0;
    }
    public int getMaxUses() {
        return 640;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, PlayerEntity pl, Hand h) {
        ItemStack is = pl.getHeldItem(Hand.MAIN_HAND);

        if (pl.getHeldItem(Hand.OFF_HAND).getItem() == RegItems.SANDSPRAY_DUST.get()) {
            if (pl.getHeldItem(Hand.MAIN_HAND).getItem() == this) {
                if(pl.getHeldItem(Hand.MAIN_HAND).getTag() == null) setMaxUse(is, MAXUSE_NBT, 0);
                if(pl.isCrouching()) {
                    if (this.getMaxUse(is, MAXUSE_NBT) < getMaxUses()) {
                        if(pl.getHeldItem(Hand.OFF_HAND).getCount() > 10) {
                            pl.getHeldItem(Hand.OFF_HAND).shrink(10);
                            addUses(is, MAXUSE_NBT, 80);
                        }
                    }
                }
                if (this.getMaxUse(is, MAXUSE_NBT) < getMaxUses()) {
                    pl.getHeldItem(Hand.OFF_HAND).shrink(1);
                    addUses(is, MAXUSE_NBT, 8);
                }
                return ActionResult.resultFail(is);
            }
        }

        return ActionResult.resultFail(is);
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        return false;
    }
    public ActionResultType onItemUse(ItemUseContext context) {
        World w = context.getWorld();
        double hitX = context.getHitVec().x;
        double hitY = context.getHitVec().y;
        double hitZ = context.getHitVec().z;
        BlockState state = context.getWorld().getBlockState(context.getPos());
ItemStack stack = context.getPlayer().getHeldItemMainhand();

        BlockPos pos = context.getPos();
        if(w.isRemote) {
            if(this.getMaxUse(context.getPlayer().getHeldItemMainhand(), MAXUSE_NBT) > 0) {
                for (int i = 0; i < 18; i++) {
                    w.addParticle(RegParticles.SANDSPRAY_PARTICLES, hitX + w.rand.nextDouble() * 0.06 * i,
                            hitY + w.rand.nextDouble() * 0.06 * i, hitZ + w.rand.nextDouble() * 0.06 * i,
                            w.rand.nextDouble() * 0.03, w.rand.nextDouble() * 0.03, w.rand.nextDouble() * 0.03);
                }
                for (int i = 0; i < 18; i++) {
                    w.addParticle(RegParticles.SANDSPRAY_PARTICLES, hitX - w.rand.nextDouble() * -0.06 * i,
                            hitY - w.rand.nextDouble() * -0.06 * i, hitZ - w.rand.nextDouble() * -0.06 * i,
                            w.rand.nextDouble() * -0.03, w.rand.nextDouble() * -0.03, w.rand.nextDouble() * -0.03);
                }
            }
        }

        if (!context.getWorld().isRemote) {
            if (this.getMaxUse(context.getPlayer().getHeldItemMainhand(), MAXUSE_NBT) > 0) {
                    this.setMaxUse(context.getPlayer().getHeldItemMainhand(), MAXUSE_NBT, this.getMaxUse(context.getPlayer().getHeldItemMainhand(), MAXUSE_NBT) - 1);
                if (state.getBlock() == RegBlocks.IRON_BEAM.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_IRON_BEAM.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    context.getPlayer().giveExperiencePoints(1);
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.IRON_BEAM_THIN.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_IRON_BEAM_THIN.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    context.getPlayer().giveExperiencePoints(1);
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.IRON_BEAM_CONCRETE.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_IRON_BEAM_CONCRETE.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    context.getPlayer().giveExperiencePoints(1);
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RAILING_BLOCK.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_RAILING_BLOCK.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    context.getPlayer().giveExperiencePoints(1);
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RAILING_DOOR.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEAN_RAILING_DOOR.get().getDefaultState()
                            .with(LockedDoor.HALF, state.get(LockedDoor.HALF))
                            .with(LockedDoor.HINGE, state.get(LockedDoor.HINGE))
                            .with(LockedDoor.FACING, state.get(LockedDoor.FACING))
                            .with(LockedDoor.OPEN, state.get(LockedDoor.OPEN))
                            .with(LockedDoor.POWERED, state.get(LockedDoor.POWERED))
                            .with(LockedDoor.LOCKED, state.get(LockedDoor.LOCKED))
                            .with(LockedDoor.WATERLOGGED, state.get(LockedDoor.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RUSTY_BARS.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEAN_RUSTY_BARS.get().getDefaultState()
                            .with(FourWayBlock.NORTH, state.get(FourWayBlock.NORTH))
                            .with(FourWayBlock.SOUTH, state.get(FourWayBlock.SOUTH))
                            .with(FourWayBlock.WEST, state.get(FourWayBlock.WEST))
                            .with(FourWayBlock.EAST, state.get(FourWayBlock.EAST))
                            .with(FourWayBlock.WATERLOGGED, state.get(FourWayBlock.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.CONTAINMENT_BLOCK.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEAN_CONTAINMENT_BLOCK.get().getDefaultState());
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RUSTYMETAL_BLOCK.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEAN_RUSTYMETAL_BLOCK.get().getDefaultState());
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.ANTENN.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_ANTENN.get().getDefaultState()
                            .with(Antenn.PART, state.get(Antenn.PART))
                            .with(Antenn.WATERLOGGED, state.get(Antenn.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.STEEL_PYLON.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_STEEL_PYLON.get().getDefaultState()
                            .with(PylonBlock.AXIS, state.get(PylonBlock.AXIS))
                            .with(PylonBlock.WATERLOGGED, state.get(PylonBlock.WATERLOGGED))
                            .with(PylonBlock.NORTH, state.get(PylonBlock.NORTH))
                            .with(PylonBlock.NORTH, state.get(PylonBlock.NORTH))
                            .with(PylonBlock.EAST, state.get(PylonBlock.EAST))
                            .with(PylonBlock.WEST, state.get(PylonBlock.WEST))
                            .with(PylonBlock.SOUTH, state.get(PylonBlock.SOUTH))
                            .with(PylonBlock.UP, state.get(PylonBlock.UP))
                            .with(PylonBlock.DOWN, state.get(PylonBlock.DOWN)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.HORIZONTAL_STEEL_PYLON.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_HORIZONTAL_STEEL_PYLON.get().getDefaultState()
                            .with(PylonBlockH.AXIS, state.get(PylonBlockH.AXIS))
                            .with(PylonBlockH.WATERLOGGED, state.get(PylonBlockH.WATERLOGGED))
                            .with(PylonBlockH.NORTH, state.get(PylonBlockH.NORTH))
                            .with(PylonBlockH.EAST, state.get(PylonBlockH.EAST))
                            .with(PylonBlockH.WEST, state.get(PylonBlockH.WEST))
                            .with(PylonBlockH.SOUTH, state.get(PylonBlockH.SOUTH))
                            .with(PylonBlockH.UP, state.get(PylonBlockH.UP))
                            .with(PylonBlockH.DOWN, state.get(PylonBlockH.DOWN)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.CONTAINMENT_TRAPDOOR.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_CONTAINMENT_TRAPDOOR.get().getDefaultState()
                            .with(TrapDoorBlock.OPEN, state.get(TrapDoorBlock.OPEN))
                            .with(TrapDoorBlock.HALF, state.get(TrapDoorBlock.HALF))
                            .with(TrapDoorBlock.POWERED, state.get(TrapDoorBlock.POWERED))
                            .with(TrapDoorBlock.WATERLOGGED, state.get(TrapDoorBlock.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.CONTAINMENT_DOOR.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_CONTAINMENT_DOOR.get().getDefaultState()
                            .with(LockedDoor.HALF, state.get(LockedDoor.HALF))
                            .with(LockedDoor.HINGE, state.get(LockedDoor.HINGE))
                            .with(LockedDoor.FACING, state.get(LockedDoor.FACING))
                            .with(LockedDoor.OPEN, state.get(LockedDoor.OPEN))
                            .with(LockedDoor.POWERED, state.get(LockedDoor.POWERED))
                            .with(LockedDoor.LOCKED, state.get(LockedDoor.LOCKED))
                            .with(LockedDoor.WATERLOGGED, state.get(LockedDoor.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.RUSTY_IRON_DOOR.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_RUSTY_IRON_DOOR.get().getDefaultState()
                            .with(LockedDoor.HALF, state.get(LockedDoor.HALF))
                            .with(LockedDoor.HINGE, state.get(LockedDoor.HINGE))
                            .with(LockedDoor.FACING, state.get(LockedDoor.FACING))
                            .with(LockedDoor.OPEN, state.get(LockedDoor.OPEN))
                            .with(LockedDoor.POWERED, state.get(LockedDoor.POWERED))
                            .with(LockedDoor.LOCKED, state.get(LockedDoor.LOCKED))
                            .with(LockedDoor.WATERLOGGED, state.get(LockedDoor.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.HALF_CIRCLE_GRID.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_HALF_CIRCLE_GRID.get().getDefaultState()
                            .with(BlockRotatedAllSideCM.FACING, state.get(BlockRotatedAllSideCM.FACING))
                            .with(BlockRotatedAllSideCM.WATERLOGGED, state.get(BlockRotatedAllSideCM.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.DIAGONAL_GRID.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_DIAGONAL_GRID.get().getDefaultState()
                            .with(BlockRotatedAllSideCM.FACING, state.get(BlockRotatedAllSideCM.FACING))
                            .with(BlockRotatedAllSideCM.WATERLOGGED, state.get(BlockRotatedAllSideCM.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.FULL_DIAGONAL_GRID.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_FULL_DIAGONAL_GRID.get().getDefaultState()
                            .with(BlockRotatedAllSideCM.FACING, state.get(BlockRotatedAllSideCM.FACING))
                            .with(BlockRotatedAllSideCM.WATERLOGGED, state.get(BlockRotatedAllSideCM.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.FULL_DIAGONAL_GRID_INVERT.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_FULL_DIAGONAL_GRID_INVERT.get().getDefaultState()
                            .with(BlockRotatedAllSideCM.FACING, state.get(BlockRotatedAllSideCM.FACING))
                            .with(BlockRotatedAllSideCM.WATERLOGGED, state.get(BlockRotatedAllSideCM.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.FLOOR_GRID.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_FLOOR_GRID.get().getDefaultState()
                            .with(FloorGrid.FACING, state.get(FloorGrid.FACING))
                            .with(FloorGrid.WATERLOGGED, state.get(FloorGrid.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.BARB_WIRE.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_BARB_WIRE.get().getDefaultState()
                            .with(BarbWire.NORTH, state.get(BarbWire.NORTH))
                            .with(BarbWire.EAST, state.get(BarbWire.EAST))
                            .with(BarbWire.WEST, state.get(BarbWire.WEST))
                            .with(BarbWire.SOUTH, state.get(BarbWire.SOUTH))
                            .with(BarbWire.WATERLOGGED, state.get(BarbWire.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.SPIRAL_BARB_WIRE.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_SPIRAL_BARB_WIRE.get().getDefaultState()
                            .with(SpiralBarbWire.FACING, state.get(SpiralBarbWire.FACING))
                            .with(SpiralBarbWire.WATERLOGGED, state.get(SpiralBarbWire.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.CHAIN.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_CHAIN.get().getDefaultState()
                            .with(Chain.PART, state.get(Chain.PART))
                            .with(Chain.WATERLOGGED, state.get(Chain.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.CHAIN.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_CHAIN.get().getDefaultState()
                            .with(Chain.PART, state.get(Chain.PART))
                            .with(Chain.WATERLOGGED, state.get(Chain.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.LADDER_1.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_LADDER_1.get().getDefaultState()
                            .with(BlockLadder.FACING, state.get(BlockLadder.FACING))
                            .with(BlockLadder.WATERLOGGED, state.get(BlockLadder.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.LADDER_2.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_LADDER_2.get().getDefaultState()
                            .with(BlockLadder.FACING, state.get(BlockLadder.FACING))
                            .with(BlockLadder.WATERLOGGED, state.get(BlockLadder.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.VENT_PIPE.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_VENT_PIPE.get().getDefaultState()
                            .with(VentPipe.DEFAULT, state.get(VentPipe.DEFAULT))
                            .with(VentPipe.NORTH, state.get(VentPipe.NORTH))
                            .with(VentPipe.UP, state.get(VentPipe.UP))
                            .with(VentPipe.DOWN, state.get(VentPipe.DOWN))
                            .with(VentPipe.SOUTH, state.get(VentPipe.SOUTH))
                            .with(VentPipe.WEST, state.get(VentPipe.WEST))
                            .with(VentPipe.EAST, state.get(VentPipe.EAST))
                            .with(VentPipe.WATERLOGGED, state.get(VentPipe.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.VENT_PIPE_BASE.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_VENT_PIPE_BASE.get().getDefaultState()
                            .with(VentPipeBase.WATERLOGGED, state.get(VentPipeBase.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.VENT_PIPE_FILTER.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_VENT_PIPE_FILTER.get().getDefaultState()
                            .with(VentPipeFilter.FACING, state.get(VentPipeFilter.FACING))
                            .with(VentPipeFilter.WATERLOGGED, state.get(VentPipeFilter.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.HOME_PIPES.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_HOME_PIPES.get().getDefaultState()
                            .with(HomePipes.FACING, state.get(HomePipes.FACING))
                            .with(HomePipes.ENUM_CONNECT, state.get(HomePipes.ENUM_CONNECT))
                            .with(HomePipes.WATERLOGGED, state.get(HomePipes.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.HOME_PIPES_BATTERY.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_HOME_PIPES_BATTERY.get().getDefaultState()
                            .with(HomePipesBattery.FACING, state.get(HomePipesBattery.FACING))
                            .with(HomePipesBattery.WATERLOGGED, state.get(HomePipesBattery.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.VENT_GRID.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_VENT_GRID.get().getDefaultState()
                            .with(VentGrid.FACING, state.get(VentGrid.FACING))
                            .with(VentGrid.WATERLOGGED, state.get(VentGrid.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.TUBING_VERTICAL.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_TUBING_VERTICAL.get().getDefaultState()
                            .with(TubingBlock.FACING, state.get(TubingBlock.FACING))
                            .with(TubingBlock.WATERLOGGED, state.get(TubingBlock.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.TUBING_HORIZONTAL.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_TUBING_HORIZONTAL.get().getDefaultState()
                            .with(TubingBlock.FACING, state.get(TubingBlock.FACING))
                            .with(TubingBlock.WATERLOGGED, state.get(TubingBlock.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.METRO_RAIL.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_METRO_RAIL.get().getDefaultState()
                            .with(MetroRail.FACING, state.get(MetroRail.FACING))
                            .with(MetroRail.WATERLOGGED, state.get(MetroRail.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.RUSTY_HANDHOLD.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_RUSTY_HANDHOLD.get().getDefaultState()
                            .with(RustyHandhold.FACING, state.get(RustyHandhold.FACING))
                            .with(RustyHandhold.WATERLOGGED, state.get(RustyHandhold.WATERLOGGED)));
                }
                if (state.getBlock() == RegBlocks.STREET_FENCE.get() && w.rand.nextInt(4) == 2) {
                    context.getPlayer().giveExperiencePoints(1);
                    w.setBlockState(pos, RegBlocks.CLEANED_STREET_FENCE.get().getDefaultState()
                            .with(ThinHandhold.FACING, state.get(ThinHandhold.FACING))
                            .with(ThinHandhold.WATERLOGGED, state.get(ThinHandhold.WATERLOGGED)));
                }
            } else {
                return ActionResultType.FAIL;
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.FAIL;
    }
}
