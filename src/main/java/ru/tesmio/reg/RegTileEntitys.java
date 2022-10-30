package ru.tesmio.reg;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.affinage_factory.AffinageTileEntity;
import ru.tesmio.blocks.crusher.CrusherTileEntity;
import ru.tesmio.blocks.diesel_generator.TileDieselGenerator;

public class RegTileEntitys {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Core.MODID);
    public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TE = TILE_ENTITY_TYPES
            .register("crusher", () -> TileEntityType.Builder
                    .create(CrusherTileEntity::new, RegBlocks.CRUSHER.get()).build(null));
    public static final RegistryObject<TileEntityType<AffinageTileEntity>> AFFINAGE_TE = TILE_ENTITY_TYPES
            .register("affinage", () -> TileEntityType.Builder
                    .create(AffinageTileEntity::new, RegBlocks.AFFINAGE_FACTORY.get()).build(null));

    public static final RegistryObject<TileEntityType<TileDieselGenerator>> DIESEL_TILE =
            TILE_ENTITY_TYPES.register("diesel_tile", () -> TileEntityType.Builder.create(TileDieselGenerator::new, RegBlocks.ENERGY_GENERATOR.get()).build(null));

}
