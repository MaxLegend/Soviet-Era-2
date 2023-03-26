package ru.tesmio.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.core.Core;

public class RegSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Core.MODID);
    public static final RegistryObject<SoundEvent> SOUND_MOTION_SENSOR =
            SOUNDS.register("motion_sensor", () -> new SoundEvent(new ResourceLocation(Core.MODID, "motion_sensor")));
    public static final RegistryObject<SoundEvent> SOUND_RADIATION_ALARM =
            SOUNDS.register("rad_alarm", () -> new SoundEvent(new ResourceLocation(Core.MODID, "rad_alarm")));
    public static final RegistryObject<SoundEvent> SOUND_BIOLOGICAL_ALARM =
            SOUNDS.register("bio_alarm", () -> new SoundEvent(new ResourceLocation(Core.MODID, "bio_alarm")));
    public static final RegistryObject<SoundEvent> SOUND_CHEMICAL_ALARM =
            SOUNDS.register("chem_alarm", () -> new SoundEvent(new ResourceLocation(Core.MODID, "chem_alarm")));
    public static final RegistryObject<SoundEvent> SOUND_AIRLOCK_DOOR =
            SOUNDS.register("airlock_door", () -> new SoundEvent(new ResourceLocation(Core.MODID, "airlock_door")));
    public static final RegistryObject<SoundEvent> SOUND_METAL_DOOR =
            SOUNDS.register("metal_door", () -> new SoundEvent(new ResourceLocation(Core.MODID, "metal_door")));
    public static final RegistryObject<SoundEvent> SOUND_CONTAINMENT_DOOR =
            SOUNDS.register("containment_door", () -> new SoundEvent(new ResourceLocation(Core.MODID, "containment_door")));
    public static final RegistryObject<SoundEvent> SOUND_ALUMINUM_DOOR =
            SOUNDS.register("alm_door", () -> new SoundEvent(new ResourceLocation(Core.MODID, "alm_door")));
    public static final RegistryObject<SoundEvent> SOUND_WOOD_DOOR =
            SOUNDS.register("wood_door", () -> new SoundEvent(new ResourceLocation(Core.MODID, "wood_door")));
    public static final RegistryObject<SoundEvent> SOUND_LOCKED =
            SOUNDS.register("locked", () -> new SoundEvent(new ResourceLocation(Core.MODID, "locked")));
    public static final RegistryObject<SoundEvent> SOUND_RUSTY_LEVER =
            SOUNDS.register("rusty_lever", () -> new SoundEvent(new ResourceLocation(Core.MODID, "rusty_lever")));
    public static final RegistryObject<SoundEvent> SOUND_SPARKING =
            SOUNDS.register("sparking", () -> new SoundEvent(new ResourceLocation(Core.MODID, "sparking")));
}
