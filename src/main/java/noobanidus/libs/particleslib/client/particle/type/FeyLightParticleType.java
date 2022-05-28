package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteSet;
import noobanidus.libs.particleslib.client.particle.FeyLightParticle;
import noobanidus.libs.particleslib.client.particle.GlowParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;

public class FeyLightParticleType extends GenericParticleType {
  public FeyLightParticleType() {
  }

  public static class Factory extends GenericFactory {
    public Factory(SpriteSet sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      FeyLightParticle ret = new FeyLightParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
