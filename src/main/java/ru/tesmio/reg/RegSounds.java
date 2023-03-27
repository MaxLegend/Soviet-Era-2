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
    public static final RegistryObject<SoundEvent> SOUND_CRUSHER =
            SOUNDS.register("crusher", () -> new SoundEvent(new ResourceLocation(Core.MODID, "crusher")));
    public static final RegistryObject<SoundEvent> SOUND_DEVICE =
            SOUNDS.register("device_enable", () -> new SoundEvent(new ResourceLocation(Core.MODID, "device_enable")));
    public static final RegistryObject<SoundEvent> SOUND_SNAP =
            SOUNDS.register("snap", () -> new SoundEvent(new ResourceLocation(Core.MODID, "snap")));
    public static final RegistryObject<SoundEvent> SOUND_RELAY =
            SOUNDS.register("relay", () -> new SoundEvent(new ResourceLocation(Core.MODID, "relay")));
    public static final RegistryObject<SoundEvent> SOUND_FLUO_LAMP =
            SOUNDS.register("fluo_lamp", () -> new SoundEvent(new ResourceLocation(Core.MODID, "fluo_lamp")));
    public static final RegistryObject<SoundEvent> SOUND_AFFINAGE =
            SOUNDS.register("affinage", () -> new SoundEvent(new ResourceLocation(Core.MODID, "affinage")));
    public static final RegistryObject<SoundEvent> SOUND_GRINDER_IDLE =
            SOUNDS.register("grinder_idle", () -> new SoundEvent(new ResourceLocation(Core.MODID, "grinder_idle")));
    public static final RegistryObject<SoundEvent> SOUND_GRINDER_WORK =
            SOUNDS.register("grinder_work", () -> new SoundEvent(new ResourceLocation(Core.MODID, "grinder_work")));
    public static final RegistryObject<SoundEvent> SOUND_DEMONTAGE =
            SOUNDS.register("demont", () -> new SoundEvent(new ResourceLocation(Core.MODID, "demont")));
}
