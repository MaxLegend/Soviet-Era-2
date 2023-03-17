package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class BlockSideConnectUpDown extends BlockSideCustomModel {

    public static final EnumProperty<EnumVariant> ENUM_VARIANT = EnumProperty.create("var", EnumVariant.class);

    public BlockSideConnectUpDown(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ENUM_VARIANT, EnumVariant.DEFAULT).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState(w,p,s);
    }
    public EnumVariant getConnect(BlockState s, BlockPos p, IWorld w) {
        EnumVariant var = EnumVariant.DEFAULT;

        if(w.getBlockState(p.up()).getBlock() instanceof BlockSideConnectUpDown) {
            if(w.getBlockState(p.up()).get(FACING) == s.get(FACING)) {
                var = EnumVariant.DOWN_CONNECT;
            }
        }
        if(w.getBlockState(p.down()).getBlock() instanceof BlockSideConnectUpDown) {
            if (w.getBlockState(p.down()).get(FACING) == s.get(FACING)) {
                var = EnumVariant.UP_CONNECT;
            }
        }
        if(w.getBlockState(p.down()).getBlock() instanceof BlockSideConnectUpDown && w.getBlockState(p.up()).getBlock() instanceof BlockSideConnectUpDown) {
            if (w.getBlockState(p.down()).get(FACING) == s.get(FACING) && w.getBlockState(p.up()).get(FACING) == s.get(FACING)) {
                var = EnumVariant.CENTER;
            }
        }
        return var;
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState s2 = w.getBlockState(p);
            BlockState posUp = w.getBlockState(p.up());
            BlockState posDown = w.getBlockState(p.down());
            boolean isUp = posUp.getBlock() instanceof BlockSideConnectUpDown;
            boolean isDown = posDown.getBlock() instanceof BlockSideConnectUpDown;
                s2 = s2.with(ENUM_VARIANT, getConnect(s, p, w));

            return s2;
        }
        return s;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENUM_VARIANT,WATERLOGGED);
    }
    public enum EnumVariant implements IStringSerializable {
        DEFAULT("not"),
        UP_CONNECT("up"),
        DOWN_CONNECT("down"),
        CENTER("center");

        @Override
        public String getString() {
            return this.name;
        }
        private final String name;
        EnumVariant(String name) {
            this.name = name;
        }


    }
}
