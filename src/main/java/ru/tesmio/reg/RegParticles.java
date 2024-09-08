package ru.tesmio.reg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.tesmio.core.Core;
import ru.tesmio.particles.SandsprayParticles;

@Mod.EventBusSubscriber(modid = Core.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegParticles {

    public static final BasicParticleType SANDSPRAY_PARTICLES = new BasicParticleType(true);

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> e) {
        e.getRegistry().register(SANDSPRAY_PARTICLES.setRegistryName("sandspray_particles"));
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerParticle(ParticleFactoryRegisterEvent event) {
        (Minecraft.getInstance()).particles.registerFactory(SANDSPRAY_PARTICLES, CustomParticleFactory::new);
    }
    @OnlyIn(Dist.CLIENT)
    private static class CustomParticleFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public CustomParticleFactory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SandsprayParticles(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
