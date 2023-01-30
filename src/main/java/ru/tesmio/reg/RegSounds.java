package ru.tesmio.reg;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ru.tesmio.Core;

public class RegSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Core.MODID);
    public static final RegistryObject<SoundEvent> SOUND_MOTION_SENSOR =
            SOUNDS.register("motion_sensor", () -> new SoundEvent(new ResourceLocation(Core.MODID, "motion_sensor")));

}
