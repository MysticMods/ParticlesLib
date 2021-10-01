package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleType;
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

  public static class WhirlwindFactory implements IParticleFactory<WhirlwindParticleData> {
    protected final IAnimatedSprite sprite;

    public WhirlwindFactory(IAnimatedSprite sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(WhirlwindParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      WhirlwindParticle ret = new WhirlwindParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
