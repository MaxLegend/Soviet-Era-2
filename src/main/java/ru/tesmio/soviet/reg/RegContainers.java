package ru.tesmio.soviet.reg;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.soviet.blocks.affinage_factory.AffinageContainer;
import ru.tesmio.soviet.blocks.crusher.CrusherContainer;
import ru.tesmio.soviet.blocks.decorative.props.stillage.StillageContainer;
import ru.tesmio.soviet.blocks.diesel_generator.DieselGeneratorContainer;
import ru.tesmio.soviet.blocks.storage.desc_drawers.LinearTableDrawersContainer;
import ru.tesmio.soviet.blocks.storage.dsp_tump.DspTumbContainer;
import ru.tesmio.soviet.blocks.storage.kitchen_table.KitchenTableContainer;
import ru.tesmio.soviet.blocks.storage.safe.ContainerSafe;
import ru.tesmio.soviet.core.Core;

public class RegContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Core.MODID);

    public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONT =
            register("crusher", CrusherContainer::new);
    public static final RegistryObject<ContainerType<AffinageContainer>> AFFINAGE_CONT =
            register("affinage", AffinageContainer::new);
    public static final RegistryObject<ContainerType<LinearTableDrawersContainer>> STORAGE_CONT =
            register("storage_cont", LinearTableDrawersContainer::new);

    public static final RegistryObject<ContainerType<ContainerSafe>> SAFE_CONT =
            register("safe_cont", ContainerSafe::new);
    public static final RegistryObject<ContainerType<DspTumbContainer>> DSP_TUMB_CONT =
            register("dsp_tumb_cont", DspTumbContainer::new);

    public static final RegistryObject<ContainerType<KitchenTableContainer>> KITCHEN_TABLE_CONT =
            register("kitchen_table_cont", KitchenTableContainer::new);
    public static final RegistryObject<ContainerType<StillageContainer>> STILLAGE_CONT =
            register("stillage_cont", StillageContainer::new);

    public static final RegistryObject<ContainerType<DieselGeneratorContainer>> DIESEL_CONTAINER = CONTAINER_TYPES.register("diesel_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new DieselGeneratorContainer(windowId, world, pos, inv, inv.player);
    }));

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory) {
        return CONTAINER_TYPES.register(name, () -> IForgeContainerType.create(factory));
    }
}