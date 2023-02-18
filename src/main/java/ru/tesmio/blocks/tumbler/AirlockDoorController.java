package ru.tesmio.blocks.tumbler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.blocks.doors.AirlockDoorBlock;
import ru.tesmio.enums.EnumStatus;

public class AirlockDoorController extends BlockSideCustomModel {
    public static EnumProperty<EnumStatus> STATUS = EnumProperty.create("status", EnumStatus.class);
    public static BooleanProperty OPENED = BooleanProperty.create("opened");
    public AirlockDoorController(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(STATUS, EnumStatus.DISABLED).with(OPENED, false));
    }
    public ActionResultType onBlockActivated(BlockState s, World w, BlockPos p, PlayerEntity pl, Hand h, BlockRayTraceResult hit) {
        if(s.get(STATUS) == EnumStatus.POWERED) {

                s = s.cycleValue(OPENED);
                w.setBlockState(p, s, 10);
            for(Direction f : Direction.values()) {
                BlockState offset = w.getBlockState(p.offset(f));
                if(offset.getBlock() instanceof AirlockDoorBlock) {
                    offset = offset.cycleValue(AirlockDoorBlock.LOCKED);
                    w.setBlockState(p.offset(f), offset);
                }
            }
                return ActionResultType.SUCCESS;
        }

        return ActionResultType.FAIL;
    }
    public int getStrongPower(BlockState state, IBlockReader br, BlockPos p, Direction s) {
        if(state.get(STATUS) == EnumStatus.POWERED) {
            return 15;
        } else
        return 0;
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fp, boolean isMoving) {
        if(w.isBlockPowered(p)) {
            w.setBlockState(p, s.with(STATUS, EnumStatus.POWERED));
        } else {
            w.setBlockState(p, s.with(STATUS, EnumStatus.DISABLED));
            for(Direction f : Direction.values()) {
                BlockState offset = w.getBlockState(p.offset(f));
                if (offset.getBlock() instanceof AirlockDoorBlock) {
                    w.setBlockState(p.offset(f), offset.with(AirlockDoorBlock.LOCKED, true));
                }
            }
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, STATUS, OPENED);
    }

}
