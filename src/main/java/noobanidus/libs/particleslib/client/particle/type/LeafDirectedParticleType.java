package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;
import noobanidus.libs.particleslib.client.particle.LeafDirectedParticle;

public class LeafDirectedParticleType extends DirectedParticleType {
  public LeafDirectedParticleType() {
    super();
  }

  public static class Factory extends DirectedFactory {
    public Factory(SpriteSet sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(DirectedParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      LeafDirectedParticle ret = new LeafDirectedParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
