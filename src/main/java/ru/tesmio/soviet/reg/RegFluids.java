package ru.tesmio.soviet.reg;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.soviet.blocks.fluid.ToxicWaterBlock;
import ru.tesmio.soviet.core.Core;

public class RegFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Core.MODID);
    public static final ResourceLocation TOXIC_WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation TOXIC_WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation TOXIC_WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    //реализовать возможность болотной воды стать обычной водой
    public static final RegistryObject<FlowingFluid> TOXIC_WATER
            = FLUIDS.register("toxic_water_fluid", () -> new ForgeFlowingFluid.Source(RegFluids.TW_PROPERTIES));


    public static final RegistryObject<FlowingFluid> TOXIC_WATER_FLOWING
            = FLUIDS.register("toxic_water_flowing", () -> new ForgeFlowingFluid.Flowing(RegFluids.TW_PROPERTIES));


    public static final ForgeFlowingFluid.Properties TW_PROPERTIES = new ForgeFlowingFluid.Properties(
            TOXIC_WATER, TOXIC_WATER_FLOWING, FluidAttributes.builder(TOXIC_WATER_STILL_RL, TOXIC_WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.BLOCK_WATER_AMBIENT).overlay(TOXIC_WATER_OVERLAY_RL)).slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(RegFluids.TOXIC_WATER_BLOCK).bucket(() -> RegItems.TOXIC_WATER_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> TOXIC_WATER_BLOCK = RegBlocks.BLOCKS.register("toxic_water",
            () -> new ToxicWaterBlock(RegFluids.TOXIC_WATER, AbstractBlock.Properties.create(Material.WATER)
                    .doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
