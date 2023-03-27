package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.decorative.props.base.BlockAxisProps;
import ru.tesmio.utils.VoxelShapeUtil;


public class BioStillage extends BlockAxisProps {
    public static final EnumProperty<Part> PART = EnumProperty.create("part", Part.class);
    public BioStillage(Properties properties) {
        super(properties);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHP[] = new VoxelShape[] {
                Block.makeCuboidShape(0,0,0.23,16,1,15.5),
                Block.makeCuboidShape(7,1,0.5,9,16,1.5),
                Block.makeCuboidShape(7,1,14.25,9,16,15.25),

                Block.makeCuboidShape(2,5.75,1.5,14,6.75,14.25),//нижняя полка
                Block.makeCuboidShape(2,10.5,1.5,14,11.5,14.25),//верхняя полка для down и def

                Block.makeCuboidShape(2,1,1.5,14,2,14.25), //нижняя полка
                Block.makeCuboidShape(2,6,1.5,14,7,14.25), //средняя полка
                Block.makeCuboidShape(2,11,1.5,14,12,14.25), //верхняя полка для up и mid


                Block.makeCuboidShape(6.5,15.25,1.5,9.5,15.75,14.25),//верхняя перекладина
        };
        if(s.get(AXIS) == Direction.Axis.X) {
            if(s.get(PART) == Part.DEF || s.get(PART) == Part.DOWN) return VoxelShapes.or(SHP[0],SHP[1],SHP[2],SHP[3],SHP[4],SHP[8]);
            else return VoxelShapes.or(SHP[1],SHP[2],SHP[5],SHP[6],SHP[7],SHP[8]);
        }
        if(s.get(AXIS) == Direction.Axis.Z) {
            if(s.get(PART) == Part.DEF || s.get(PART) == Part.DOWN) return VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCW90(SHP[0]),
                    VoxelShapeUtil.shapeRotCW90(SHP[1]),
                    VoxelShapeUtil.shapeRotCW90(SHP[2]),
                    VoxelShapeUtil.shapeRotCW90(SHP[3]),
                    VoxelShapeUtil.shapeRotCW90(SHP[4]),
                    VoxelShapeUtil.shapeRotCW90(SHP[8]));
            else return VoxelShapes.or(
                    VoxelShapeUtil.shapeRotCW90(SHP[1]),
                    VoxelShapeUtil.shapeRotCW90(SHP[2]),
                    VoxelShapeUtil.shapeRotCW90(SHP[5]),
                    VoxelShapeUtil.shapeRotCW90(SHP[6]),
                    VoxelShapeUtil.shapeRotCW90(SHP[7]),
                    VoxelShapeUtil.shapeRotCW90( SHP[8]));
        }
        return VoxelShapes.fullCube();
    }

    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            BlockState ts = w.getBlockState(p);
            BlockState up = w.getBlockState(p.up());
            BlockState down = w.getBlockState(p.down());
            if(down.getBlock() instanceof BioStillage) {
                if(up.getBlock() instanceof BioStillage) {
                    return ts.with(PART, Part.MID);
                }
                return ts.with(PART, Part.UP);
            }
            if(up.getBlock() instanceof BioStillage) {
                return ts.with(PART, Part.DOWN);
            } else {
                return ts.with(PART, Part.DEF);
            }

        }
        return s;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS, PART, WATERLOGGED);
    }
    public enum Part implements IStringSerializable
    {
        DEF("def"),
        DOWN("down"),
        MID("mid"),
        UP("up");
        private final String name;

        Part(String name) {
            this.name = name;
        }

        @Override
        public String getString() {
            return this.name;
        }
    }
}
