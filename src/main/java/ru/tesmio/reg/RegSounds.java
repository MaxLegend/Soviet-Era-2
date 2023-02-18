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
}
