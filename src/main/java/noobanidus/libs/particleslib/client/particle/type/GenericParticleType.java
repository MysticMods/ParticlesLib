package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.GenericParticle;
import noobanidus.libs.particleslib.client.particle.GenericParticleData;

public abstract class GenericParticleType extends ParticleType<GenericParticleData> {
  public GenericParticleType() {
    super(false, GenericParticleData.DESERIALIZER);
  }

  @Override
  public Codec<GenericParticleData> codec() {
    return GenericParticleData.codecFor(this);
  }

  public static class GenericFactory implements IParticleFactory<GenericParticleData> {
    protected final IAnimatedSprite sprite;

    public GenericFactory(IAnimatedSprite sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      GenericParticle ret = new GenericParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
