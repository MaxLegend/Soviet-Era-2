package ru.tesmio.soviet.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.soviet.core.Core;

public class SovietSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Core.MODID);

    public static final RegistryObject<SoundEvent> MOTION_SENSOR = register("motion_sensor");
    public static final RegistryObject<SoundEvent> RADIATION_ALARM = register("rad_alarm");
    public static final RegistryObject<SoundEvent> BIOLOGICAL_ALARM = register("bio_alarm");
    public static final RegistryObject<SoundEvent> CHEMICAL_ALARM = register("chem_alarm");
    public static final RegistryObject<SoundEvent> AIRLOCK_DOOR = register("airlock_door");
    public static final RegistryObject<SoundEvent> METAL_DOOR = register("metal_door");
    public static final RegistryObject<SoundEvent> CONTAINMENT_DOOR = register("containment_door");
    public static final RegistryObject<SoundEvent> ALUMINUM_DOOR = register("alm_door");
    public static final RegistryObject<SoundEvent> WOOD_DOOR = register("wood_door");
    public static final RegistryObject<SoundEvent> LOCKED = register("locked");
    public static final RegistryObject<SoundEvent> RUSTY_LEVER = register("rusty_lever");
    public static final RegistryObject<SoundEvent> SPARKING = register("sparking");
    public static final RegistryObject<SoundEvent> CRUSHER = register("crusher");
    public static final RegistryObject<SoundEvent> DEVICE = register("device_enable");
    public static final RegistryObject<SoundEvent> SNAP = register("snap");
    public static final RegistryObject<SoundEvent> RELAY = register("relay");
    public static final RegistryObject<SoundEvent> FLUO_LAMP = register("fluo_lamp");
    public static final RegistryObject<SoundEvent> AFFINAGE = register("affinage");
    public static final RegistryObject<SoundEvent> GRINDER_IDLE = register("grinder_idle");
    public static final RegistryObject<SoundEvent> GRINDER_WORK = register("grinder_work");
    public static final RegistryObject<SoundEvent> DEMONTAGE = register("demont");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Core.MODID, name)));
    }
}
