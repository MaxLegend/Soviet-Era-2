package ru.tesmio.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.blocks.decorative.devices.Turnstile;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegSounds;

import java.util.concurrent.ThreadLocalRandom;

public class AirlockDoorController extends BlockSideCustomModel {
    public static EnumProperty<Turnstile.EnumStatus> STATUS = EnumProperty.create("status", Turnstile.EnumStatus.class);
    public AirlockDoorController(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(STATUS, Turnstile.EnumStatus.OFF));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        if(w.isBlockPowered(p)) {
            if(s.get(STATUS) == Turnstile.EnumStatus.OFF) {
                w.setBlockState(p, s.with(STATUS, Turnstile.EnumStatus.CLOSE));
            }
        } else {
            w.setBlockState(p, s.with(STATUS, Turnstile.EnumStatus.OFF));
        }
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(4)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(3)),
                new ItemStack(RegBlocks.NETHERITE_CIRCUIT.get(), tr.nextInt(2)),
                new ItemStack(RegBlocks.PLATINUM_CIRCUIT.get(), tr.nextInt(1))
        };
    }
    @Override
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand hand, BlockRayTraceResult hit) {
        if(hit.getFace() == s.get(FACING)) {
            if (s.get(STATUS) == Turnstile.EnumStatus.CLOSE) {
                w.playSound(null, p, RegSounds.SOUND_RUSTY_LEVER.get(), SoundCategory.BLOCKS, 0.10f, 1f);
                w.setBlockState(p, s.with(STATUS, Turnstile.EnumStatus.OPEN));
                return ActionResultType.SUCCESS;
            } else if (s.get(STATUS) == Turnstile.EnumStatus.OPEN) {
                w.playSound(null, p, RegSounds.SOUND_RUSTY_LEVER.get(), SoundCategory.BLOCKS, 0.10f, 1f);
                w.setBlockState(p, s.with(STATUS, Turnstile.EnumStatus.CLOSE));
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return Block.makeCuboidShape(0,0,0,16,16,14);
    }

    public int getWeakPower(BlockState state, IBlockReader br, BlockPos p, Direction s) {
        if(state.get(STATUS) == Turnstile.EnumStatus.CLOSE) {
            return 15;
        } else
        return 0;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, STATUS);
    }

}
