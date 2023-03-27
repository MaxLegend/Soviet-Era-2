package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;

public class PhysDevices extends BlockSideDevice {
    public PhysDevices(float shadingInside) {
        super(AbstractBlock.Properties.create(Material.IRON)
                .setRequiresTool()
                .hardnessAndResistance(1f,4f)
                .notSolid()
                .harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL), shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        VoxelShape SHP[] = new VoxelShape[] {
                Block.makeCuboidShape(1,0,1.25,15,4,14),
                Block.makeCuboidShape(0.75,0.5,2.25,15.25,7.5,14),
                Block.makeCuboidShape(1.5,0.5,0.5,14.5,13.5,15),
                Block.makeCuboidShape(2.75,0,0.5,12.25,7.25,15),
                Block.makeCuboidShape(0,0,0,16,6,14),
                Block.makeCuboidShape(3,0,0,13,6,12),
                Block.makeCuboidShape(2,1,2.5,14,16,14),
                Block.makeCuboidShape(0,0,0,16,16,5),
                Block.makeCuboidShape(1, 0, 3.5, 15, 8, 11.5),
                Block.makeCuboidShape(0, 0, 0, 16, 7, 14.5),
                Block.makeCuboidShape(0, 0, 0, 16, 16, 14.5),
                Block.makeCuboidShape(0, 0.5, 1, 16, 13.5, 14),
                Block.makeCuboidShape(0.75, 0.5, 3, 15.25, 8.75, 12.5),
                Block.makeCuboidShape(1.5, 0, 0, 14.5, 12.5, 14),
                Block.makeCuboidShape(0, 0, 0, 16, 5, 16),
                Block.makeCuboidShape(2.25, 1, 4.25, 13.75, 7.5, 11.5),
                Block.makeCuboidShape(0.5, 0, 2, 15.5, 16, 12),
                Block.makeCuboidShape(2, 0, 3, 14, 14, 13),
                Block.makeCuboidShape(0.75, 0, 0, 15.25, 14.5, 13),
                Block.makeCuboidShape(0, 0.5, 1.75, 16, 16, 10.5),
                Block.makeCuboidShape(0.5, 0, 1.25, 13, 7, 9),
                Block.makeCuboidShape(3, 0, 1.25, 15.5, 7, 9),
                Block.makeCuboidShape(0, 0, 0.5, 16, 16, 15),
                Block.makeCuboidShape(0, 0, 1, 16, 6, 12),
                Block.makeCuboidShape(0, 0, 2, 16, 8, 11),
                Block.makeCuboidShape(0, 0, 1, 16, 5, 13),
        };
        boolean slimDevices = s.getBlock() == RegBlocks.EM_METER.get() || s.getBlock() == RegBlocks.OM_METER.get() || s.getBlock() == RegBlocks.FREQUE_METER.get()|| s.getBlock() == RegBlocks.M_METER.get();
        boolean normalDevices = s.getBlock() == RegBlocks.A_METER.get() || s.getBlock() == RegBlocks.P_METER.get() || s.getBlock() == RegBlocks.W_METER.get()|| s.getBlock() == RegBlocks.V_METER.get()|| s.getBlock() == RegBlocks.K_METER.get();

        if(slimDevices) {
            return SHP[0];
        }
        if(normalDevices) {
            return SHP[1];
        }
        if(s.getBlock() == RegBlocks.AUTOWRITER.get()) {
            return SHP[2];
        }
        if(s.getBlock() == RegBlocks.OSCILLOSCOPE.get()) {
            return SHP[3];
        }
        if(s.getBlock() == RegBlocks.CONTROLLER.get()) {
            return SHP[4];
        }
        if(s.getBlock() == RegBlocks.FREQUE_ANALISATOR.get()) {
            return SHP[5];
        }
        if(s.getBlock() == RegBlocks.ASSIGNER_BLOCK.get()) {
            return SHP[6];
        }
        if(s.getBlock() == RegBlocks.GEIGER_STATIC.get()) {
            return SHP[7];
        }
        if(s.getBlock() == RegBlocks.STANDART_SIGNAL_GEN.get()) {
            return SHP[8];
        }
//        if(s.getBlock() == RegBlocks.PULT3.get()) {
//            return SHP[9];
//        }
//        if(s.getBlock() == RegBlocks.PULT2.get() || s.getBlock() == RegBlocks.PULT1.get()|| s.getBlock() == RegBlocks.PULT.get()) {
//            return SHP[10];
//        }
        if(s.getBlock() == RegBlocks.WELDING_MACHINE.get()) {
            return SHP[11];
        }
        if(s.getBlock() == RegBlocks.E_CONVERTER.get()) {
            return SHP[12];
        }
        if(s.getBlock() == RegBlocks.SOUND_POWER_AMPLIFIER.get()) {
            return SHP[13];
        }
        if(s.getBlock() == RegBlocks.COULOMETRIC_INTEGRATOR.get()) {
            return SHP[14];
        }
        if(s.getBlock() == RegBlocks.E_TESTER.get()) {
            return SHP[15];
        }
        if(s.getBlock() == RegBlocks.COULOMETRIC_INTEGRATOR2.get()) {
            return SHP[16];
        }
        if(s.getBlock() == RegBlocks.WAVEMETER.get()) {
            return SHP[17];
        }
        if(s.getBlock() == RegBlocks.RADIOSTATION.get()) {
            return SHP[18];
        }
        if(s.getBlock() == RegBlocks.AUDIORECORDER.get()) {
            return SHP[19];
        }
        if(s.getBlock() == RegBlocks.SOLDERING_STATION.get()) {
            if(s.get(FACING) == Direction.WEST) return SHP[21];
            return SHP[20];
        }
        if(s.getBlock() == RegBlocks.TRANSFORMATOR_BASE.get()) {
            return SHP[22];
        }
        if(s.getBlock() == RegBlocks.TRANSFORMATOR_ISOLATORS.get()) {
            return SHP[23];
        }
        if(s.getBlock() == RegBlocks.TELEGRAPH.get()) {
            return SHP[24];
        }
        if(s.getBlock() == RegBlocks.AUDIORECORDER2.get()) {
            return SHP[25];
        }
        return VoxelShapes.fullCube();
    }
}
