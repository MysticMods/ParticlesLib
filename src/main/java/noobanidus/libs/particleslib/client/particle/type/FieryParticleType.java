package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.FieryParticle;
import noobanidus.libs.particleslib.client.particle.LeafParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;

public class FieryParticleType extends GenericParticleType {
  public FieryParticleType() {
    super();
  }

  public static class Factory extends GenericFactory {
    public Factory(IAnimatedSprite sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      FieryParticle ret = new FieryParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}