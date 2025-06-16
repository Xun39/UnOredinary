package net.xun.unoredinary.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class SubzeroFrostParticle extends TextureSheetParticle {

    private final SpriteSet sprites;
    private final float baseSize;
    private final float angularVelocity;
    private final float startR, startG, startB;
    private final float endR = 0.65f;
    private final float endG = 0.82f;
    private final float endB = 0.92f;

    protected SubzeroFrostParticle(
            ClientLevel level, double x, double y, double z,
            double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites
    ) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        this.gravity = 0.02F;
        this.friction = 0.96F;

        this.xd = xSpeed * 0.05 + (Math.random() * 2.0 - 1.0) * 0.03F;
        this.yd = ySpeed - 0.1F + (Math.random() * 0.05F);
        this.zd = zSpeed * 0.05 + (Math.random() * 2.0 - 1.0) * 0.03F;

        this.baseSize = 0.2F * (this.random.nextFloat() * 0.4F + 0.6F);
        this.quadSize = 0;

        this.lifetime = (int)(50.0 / ((double)this.random.nextFloat() * 0.8 + 0.2)) + 30;
        this.setSpriteFromAge(sprites);
        this.alpha = 0.95F;

        this.roll = (float) (Math.random() * Math.PI * 2);
        this.oRoll = this.roll;
        this.angularVelocity = (this.random.nextFloat() - 0.5F) * 0.1F;

        this.startR = this.rCol;
        this.startG = this.gCol;
        this.startB = this.bCol;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return 0xF000F0;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);

        this.oRoll = this.roll;
        this.roll += this.angularVelocity;

        float lifeProgress = (float) this.age / this.lifetime;
        if (lifeProgress < 0.2F) {
            this.quadSize = baseSize * (lifeProgress * 5F);
        } else if (lifeProgress > 0.7F) {
            this.quadSize = baseSize * ((1 - lifeProgress) * 3.3F);
        } else {
            this.quadSize = baseSize;
        }

        float colorProgress = Math.min(1.0F, lifeProgress * 1.5F);
        setColor(
                startR + (endR - startR) * colorProgress,
                startG + (endG - startG) * colorProgress,
                startB + (endB - startB) * colorProgress
        );

        if (this.age % 5 == 0) {
            this.xd += (Math.random() - 0.5) * 0.015;
            this.zd += (Math.random() - 0.5) * 0.015;
        }

        if (this.age > this.lifetime - 15) {
            this.alpha = Math.max(0, this.alpha - 0.066F);
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