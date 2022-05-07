package noobanidus.libs.particleslib.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleType;
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

  public static class DirectedFactory implements ParticleProvider<DirectedParticleData> {
    protected final SpriteSet sprite;

    public DirectedFactory(SpriteSet sprite) {
      this.sprite = sprite;
    }

    @Override
    public Particle createParticle(DirectedParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      DirectedParticle ret = new DirectedParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
