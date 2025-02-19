package xun.unoredinary.client.particle.options;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ScalableParticleOptionsBase;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class DustParticles extends ScalableParticleOptionsBase {

    public static final Vector3f CRYOSTONE_PARTICLE_COLOR = Vec3.fromRGB24(2308090).toVector3f();
    public static final DustParticleOptions CRYOSTONE;
    public static final MapCodec<DustParticleOptions> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, DustParticleOptions> STREAM_CODEC;

    public DustParticles(float scale) {
        super(scale);
    }

    @Override
    public ParticleType<?> getType() {
        return null;
    }

    static {
        CRYOSTONE = new DustParticleOptions(CRYOSTONE_PARTICLE_COLOR, 1.0F);
        CODEC = RecordCodecBuilder.mapCodec((p_341566_) -> p_341566_.group(ExtraCodecs.VECTOR3F.fieldOf("color").forGetter(DustParticleOptions::getColor), SCALE.fieldOf("scale").forGetter(ScalableParticleOptionsBase::getScale)).apply(p_341566_, DustParticleOptions::new));
        STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.VECTOR3F, DustParticleOptions::getColor, ByteBufCodecs.FLOAT, ScalableParticleOptionsBase::getScale, DustParticleOptions::new);
    }
}
