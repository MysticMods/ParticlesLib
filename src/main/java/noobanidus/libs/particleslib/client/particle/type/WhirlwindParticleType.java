package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.base.DirectedParticle;
import noobanidus.libs.particleslib.client.particle.base.WhirlwindParticle;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;
import noobanidus.libs.particleslib.client.particle.data.WhirlwindParticleData;

public class WhirlwindParticleType extends ParticleType<WhirlwindParticleData> {
  public WhirlwindParticleType() {
    super(false, WhirlwindParticleData.WHIRLWIND_DESERIALIZER);
  }

  @Override
  public Codec<WhirlwindParticleData> codec() {
    return WhirlwindParticleData.directedCodecFor(this);
  }

  public static class WhirlwindFactory implements ParticleProvider<WhirlwindParticleData> {
    protected final SpriteSet sprite;

    public WhirlwindFactory(SpriteSet sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(WhirlwindParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      WhirlwindParticle ret = new WhirlwindParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
