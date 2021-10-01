package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.base.DirectedParticle;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;

public class DirectedParticleType extends ParticleType<DirectedParticleData> {
  public DirectedParticleType() {
    super(false, DirectedParticleData.DIRECTED_DESERIALIZER);
  }

  @Override
  public Codec<DirectedParticleData> codec() {
    return DirectedParticleData.directedCodecFor(this);
  }

  public static class DirectedFactory implements IParticleFactory<DirectedParticleData> {
    protected final IAnimatedSprite sprite;

    public DirectedFactory(IAnimatedSprite sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(DirectedParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      DirectedParticle ret = new DirectedParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
