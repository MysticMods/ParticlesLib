package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.LeafArcParticle;
import noobanidus.libs.particleslib.client.particle.LeafWhirlwindParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;
import noobanidus.libs.particleslib.client.particle.data.WhirlwindParticleData;

public class LeafWhirlwindParticleType extends WhirlwindParticleType {
  public LeafWhirlwindParticleType() {
    super();
  }

  public static class Factory extends WhirlwindFactory {
    public Factory(IAnimatedSprite sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(WhirlwindParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      LeafWhirlwindParticle ret = new LeafWhirlwindParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
