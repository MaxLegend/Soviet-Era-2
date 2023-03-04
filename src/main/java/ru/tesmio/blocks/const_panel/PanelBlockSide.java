package ru.tesmio.blocks.const_panel;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PanelBlockSide extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.UP, Direction.DOWN );
    public PanelBlockSide() {
        super(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3f,8f));
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        spawnDrops(state, worldIn, pos, te, player, stack);
    }
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
                switch (direction) {
                    case DOWN:
                        return this.getDefaultState().with(FACING, Direction.DOWN);
                    case UP:
                        return this.getDefaultState().with(FACING, Direction.UP);
                    case NORTH:
                        return this.getDefaultState().with(FACING, Direction.NORTH);
                    case SOUTH:
                       return this.getDefaultState().with(FACING, Direction.SOUTH);
                    case EAST:
                       return this.getDefaultState().with(FACING, Direction.EAST);
                    case WEST:
                        return this.getDefaultState().with(FACING, Direction.WEST);
                }

        }
        return null;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}