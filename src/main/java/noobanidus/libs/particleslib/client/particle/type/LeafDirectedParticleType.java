package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.DirectedParticleData;
import noobanidus.libs.particleslib.client.particle.LeafDirectedParticle;

public class LeafDirectedParticleType extends DirectedParticleType {
  public LeafDirectedParticleType() {
    super();
  }

  public static class Factory extends DirectedFactory {
    public Factory(IAnimatedSprite sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(DirectedParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      LeafDirectedParticle ret = new LeafDirectedParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
