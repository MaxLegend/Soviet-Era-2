package ru.tesmio.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.FourWayBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
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
import ru.tesmio.blocks.baseblock.BlockRotatedAxis;
import ru.tesmio.blocks.doors.LockedDoor;
import ru.tesmio.reg.RegBlocks;
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

        if (pl.getHeldItem(Hand.OFF_HAND).getItem() == Items.SAND) {
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
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.IRON_BEAM_THIN.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_IRON_BEAM_THIN.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.IRON_BEAM_CONCRETE.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_IRON_BEAM_CONCRETE.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RAILING_BLOCK.get() && w.rand.nextInt(4) == 2) {
                    w.setBlockState(pos, RegBlocks.CLEAN_RAILING_BLOCK.get().getDefaultState().with(BlockRotatedAxis.FACING, state.get(BlockRotatedAxis.FACING)).with(BlockRotatedAxis.WATERLOGGED, state.get(BlockRotatedAxis.WATERLOGGED)));
                    return ActionResultType.FAIL;
                }
                if (state.getBlock() == RegBlocks.RAILING_DOOR.get() && w.rand.nextInt(4) == 2) {
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
                    w.setBlockState(pos, RegBlocks.CLEAN_RUSTY_BARS.get().getDefaultState()
                            .with(FourWayBlock.NORTH, state.get(FourWayBlock.NORTH))
                            .with(FourWayBlock.SOUTH, state.get(FourWayBlock.SOUTH))
                            .with(FourWayBlock.WEST, state.get(FourWayBlock.WEST))
                            .with(FourWayBlock.EAST, state.get(FourWayBlock.EAST))
                            .with(FourWayBlock.WATERLOGGED, state.get(FourWayBlock.WATERLOGGED)));
                    return ActionResultType.CONSUME;
                }
            } else {
                return ActionResultType.CONSUME;
            }
            return ActionResultType.CONSUME;
        }
        return ActionResultType.CONSUME;
    }
}
