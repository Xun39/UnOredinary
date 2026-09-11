package net.xun.unoredinary.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class TransenchantParticle extends TextureSheetParticle {
    private final double targetX, targetY, targetZ;
    private final double startX, startY, startZ;

    protected TransenchantParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
        super(level, x, y, z);
        this.startX = x;
        this.startY = y;
        this.startZ = z;
        this.targetX = x + dx;
        this.targetY = y + dy;
        this.targetZ = z + dz;
        this.lifetime = 20;
        this.quadSize = 0.1F;
        this.hasPhysics = false;
        this.gravity = 0.0F;
        this.xd = 0; this.yd = 0; this.zd = 0;
        this.setColor(0.86F, 0.59F, 1.0F);
        this.alpha = 0.9F;
    }

    @Override
    public void tick() {
        this.xo = this.x; this.yo = this.y; this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }
        float t = (float) this.age / this.lifetime;
        this.x = Mth.lerp(t, startX, targetX);
        this.y = Mth.lerp(t, startY, targetY);
        this.z = Mth.lerp(t, startZ, targetZ);
        this.alpha = Mth.clamp(1.0F - t, 0.0F, 0.9F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) { this.sprites = sprites; }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z, double dx, double dy, double dz) {
            TransenchantParticle p = new TransenchantParticle(level, x, y, z, dx, dy, dz);
            p.pickSprite(this.sprites);
            p.setColor(1.0F, 1.0F, 1.0F);
            return p;
        }
    }
}