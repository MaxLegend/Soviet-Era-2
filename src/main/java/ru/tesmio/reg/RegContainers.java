package ru.tesmio.reg;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.crusher.CrusherContainer;

public class RegContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Core.MODID);
    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONT = CONTAINER_TYPES
            .register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));
}
