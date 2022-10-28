package ru.tesmio.reg;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.affinage_factory.AffinageContainer;
import ru.tesmio.blocks.crusher.CrusherContainer;
import ru.tesmio.blocks.diesel_generator.ContainerDieselGenerator;

public class RegContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Core.MODID);
    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONT = CONTAINER_TYPES
            .register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));
    public static final RegistryObject<ContainerType<AffinageContainer>> AFFINAGE_CONT = CONTAINER_TYPES
            .register("affinage", () -> IForgeContainerType.create(AffinageContainer::new));

    public static final RegistryObject<ContainerType<ContainerDieselGenerator>> DIESEL_CONT = CONTAINER_TYPES
            .register("diesel", () -> IForgeContainerType.create(ContainerDieselGenerator::new));
}
