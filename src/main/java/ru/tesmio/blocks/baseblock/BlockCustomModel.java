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

public class BlockCustomModel extends  BaseBlock {
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
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if (!w.isRemote) {
            if (!pl.isCreative()) {
                getDropsWithBlock(w, p,pl);
                getAdditionDrops(w,p,getStackAddDrop(pl));
            }
        }
    }
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
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

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}