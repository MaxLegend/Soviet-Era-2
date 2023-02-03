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

    public BlockSideConnectUpDown(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(ENUM_VARIANT, EnumVariant.DEFAULT).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState(w,p,s);
    }
    public BlockState updateState(IWorld w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState s2 = w.getBlockState(p);
            BlockState posUp = w.getBlockState(p.up());
            BlockState posDown = w.getBlockState(p.down());

            if (posUp.getBlock() instanceof BlockSideConnectUpDown && posUp.get(FACING) == s2.get(FACING)) {
                s2 = s2.with(ENUM_VARIANT, EnumVariant.DOWN_CONNECT);
                return s2;
            }
            if (posDown.getBlock() instanceof BlockSideConnectUpDown && posDown.get(FACING) == s2.get(FACING)) {
                s2 = s2.with(ENUM_VARIANT, EnumVariant.UP_CONNECT);
                return s2;
            }
            if ((posDown.getBlock() instanceof BlockSideConnectUpDown && posUp.getBlock() instanceof BlockSideConnectUpDown) && (posDown.get(FACING) == s2.get(FACING) && posUp.get(FACING) == s2.get(FACING))) {
                s2 = s2.with(ENUM_VARIANT, EnumVariant.CENTER);
                return s2;
            }


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
