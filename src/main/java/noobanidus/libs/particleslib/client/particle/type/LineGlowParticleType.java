package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.LeafDirectedParticle;
import noobanidus.libs.particleslib.client.particle.LineGlowParticle;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;

public class LineGlowParticleType extends DirectedParticleType {
  public LineGlowParticleType() {
    super();
  }

  public static class Factory extends DirectedFactory {
    public Factory(IAnimatedSprite sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(DirectedParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
      LineGlowParticle ret = new LineGlowParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
