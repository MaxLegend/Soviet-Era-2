package ru.tesmio.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;

public class SandsprayParticles extends SpriteTexturedParticle {
    private final IAnimatedSprite spriteSet;
    public SandsprayParticles(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, IAnimatedSprite spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        setSize(0.3F, 0.3F);
        this.particleScale *= 1.5F;
        this.maxAge = Math.max(1, 50 + this.rand.nextInt(30) - 15);
        this.particleGravity = 0F;
        this.canCollide = true;
        this.motionX = vx;
        this.motionY = vy;
        this.motionZ = vz;
        selectSpriteRandomly(spriteSet);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
    }
}
