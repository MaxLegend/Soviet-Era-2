package ru.tesmio.reg;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.blocks.affinage_factory.AffinageContainer;
import ru.tesmio.blocks.crusher.CrusherContainer;
import ru.tesmio.blocks.decorative.props.stillage.StillageContainer;
import ru.tesmio.blocks.diesel_generator.DieselGeneratorContainer;
import ru.tesmio.blocks.storage.desc_drawers.LinearTableDrawersContainer;
import ru.tesmio.blocks.storage.dsp_tump.DspTumbContainer;
import ru.tesmio.blocks.storage.kitchen_table.KitchenTableContainer;
import ru.tesmio.blocks.storage.safe.ContainerSafe;
import ru.tesmio.core.Core;

public class RegContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Core.MODID);
    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONT = CONTAINER_TYPES
            .register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));
    public static final RegistryObject<ContainerType<AffinageContainer>> AFFINAGE_CONT = CONTAINER_TYPES
            .register("affinage", () -> IForgeContainerType.create(AffinageContainer::new));
    public static final RegistryObject<ContainerType<LinearTableDrawersContainer>> STORAGE_CONT = CONTAINER_TYPES
            .register("storage_cont", () -> IForgeContainerType.create(LinearTableDrawersContainer::new));

    public static final RegistryObject<ContainerType<ContainerSafe>> SAFE_CONT = CONTAINER_TYPES
            .register("safe_cont", () -> IForgeContainerType.create(ContainerSafe::new));
    public static final RegistryObject<ContainerType<DspTumbContainer>> DSP_TUMB_CONT = CONTAINER_TYPES
            .register("dsp_tumb_cont", () -> IForgeContainerType.create(DspTumbContainer::new));

    public static final RegistryObject<ContainerType<KitchenTableContainer>> KITCHEN_TABLE_CONT = CONTAINER_TYPES
            .register("kitchen_table_cont", () -> IForgeContainerType.create(KitchenTableContainer::new));

    public static final RegistryObject<ContainerType<StillageContainer>> STILLAGE_CONT = CONTAINER_TYPES
            .register("stillage_cont", () -> IForgeContainerType.create(StillageContainer::new));

    public static final RegistryObject<ContainerType<DieselGeneratorContainer>> DIESEL_CONTAINER = CONTAINER_TYPES.register("diesel_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new DieselGeneratorContainer(windowId, world, pos, inv, inv.player);
    }));

}
