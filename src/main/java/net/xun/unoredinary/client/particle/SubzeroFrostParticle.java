package net.xun.unoredinary.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SubzeroFrostParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected SubzeroFrostParticle(
            ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites
    ) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.gravity = 0.04F;
        this.friction = 0.98F;
        this.sprites = sprites;
        this.xd = xSpeed * 0.1 + (Math.random() * 2.0 - 1.0) * 0.02F;
        this.yd = ySpeed - 0.15F;
        this.zd = zSpeed * 0.1 + (Math.random() * 2.0 - 1.0) * 0.02F;
        this.quadSize = 0.15F * (this.random.nextFloat() * 0.5F + 0.5F);
        this.lifetime = (int)(40.0 / ((double)this.random.nextFloat() * 0.8 + 0.2)) + 20;
        this.setSpriteFromAge(sprites);
        this.alpha = 0.9F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        this.xd *= 0.92F;
        this.zd *= 0.92F;

        if (this.age > this.lifetime - 20) {
            this.alpha = Math.max(0, this.alpha - 0.05F);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(
                SimpleParticleType type,
                ClientLevel level,
                double x,
                double y,
                double z,
                double xSpeed,
                double ySpeed,
                double zSpeed
        ) {
            SubzeroFrostParticle particle = new SubzeroFrostParticle(
                    level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites
            );
            particle.setColor(0.85F, 0.92F, 0.97F);
            return particle;
        }
    }
}