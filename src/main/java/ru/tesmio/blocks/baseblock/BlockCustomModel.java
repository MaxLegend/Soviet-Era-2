package ru.tesmio.blocks.baseblock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockCustomModel extends  Block {
    protected static VoxelShape SHAPE;
    private float shadingInside;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public BlockCustomModel(Properties properties, VoxelShape s, float shadingInside) {
        super(properties);
        this.SHAPE = s;
        this.shadingInside = shadingInside;
        this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, Boolean.valueOf(false)));
    }
    public BlockCustomModel(Properties properties) {
        super(properties);
        this.shadingInside = 1F;
    }
    public BlockCustomModel(VoxelShape s, float shadingInside) {
        super(AbstractBlock.Properties.create(Material.IRON)
                .setRequiresTool()
                .hardnessAndResistance(0.1f,0.1f)
                .notSolid()
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL));

    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return this.shadingInside;
    }

    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
    }
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        player.addStat(Stats.BLOCK_MINED.get(this));
        player.addExhaustion(0.005F);
        spawnDrops(state, worldIn, pos, te, player, stack);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}