package ru.tesmio.reg;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.blocks.storage.ContainerStorage;
import ru.tesmio.core.Core;
import ru.tesmio.blocks.affinage_factory.AffinageContainer;
import ru.tesmio.blocks.crusher.CrusherContainer;
import ru.tesmio.blocks.diesel_generator.DieselGeneratorContainer;

public class RegContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Core.MODID);
    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONT = CONTAINER_TYPES
            .register("crusher", () -> IForgeContainerType.create(CrusherContainer::new));
    public static final RegistryObject<ContainerType<AffinageContainer>> AFFINAGE_CONT = CONTAINER_TYPES
            .register("affinage", () -> IForgeContainerType.create(AffinageContainer::new));
    public static final RegistryObject<ContainerType<ContainerStorage>> STORAGE_CONT = CONTAINER_TYPES
            .register("storage_cont", () -> IForgeContainerType.create(ContainerStorage::new));

    public static final RegistryObject<ContainerType<DieselGeneratorContainer>> DIESEL_CONTAINER = CONTAINER_TYPES.register("diesel_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new DieselGeneratorContainer(windowId, world, pos, inv, inv.player);
    }));

}
