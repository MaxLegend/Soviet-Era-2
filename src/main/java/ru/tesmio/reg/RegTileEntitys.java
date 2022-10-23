package ru.tesmio.reg;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;
import ru.tesmio.blocks.crusher.CrusherTileEntity;

public class RegTileEntitys {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Core.MODID);
    public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TE = TILE_ENTITY_TYPES
            .register("crusher", () -> TileEntityType.Builder
                    .create(CrusherTileEntity::new, RegBlocks.CRUSHER.get()).build(null));
}
