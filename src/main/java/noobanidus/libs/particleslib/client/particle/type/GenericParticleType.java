package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.base.GenericParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;

public abstract class GenericParticleType extends ParticleType<GenericParticleData> {
  public GenericParticleType() {
    super(false, GenericParticleData.DESERIALIZER);
  }

  @Override
  public Codec<GenericParticleData> codec() {
    return GenericParticleData.codecFor(this);
  }

  public static class GenericFactory implements ParticleProvider<GenericParticleData> {
    protected final SpriteSet sprite;

    public GenericFactory(SpriteSet sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      GenericParticle ret = new GenericParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
