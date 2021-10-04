package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.LeafParticle;
import noobanidus.libs.particleslib.client.particle.RadialParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;

public class RadialParticleType extends GenericParticleType {
  public RadialParticleType() {
    super();
  }

  public static class Factory extends GenericFactory {
    public Factory(IAnimatedSprite sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      RadialParticle ret = new RadialParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
