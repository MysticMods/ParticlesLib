package noobanidus.libs.particleslib.client.particle.type;

import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import noobanidus.libs.particleslib.client.particle.FireParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;

public class FireParticleType extends GenericParticleType {
  public FireParticleType() {
  }

  public static class Factory extends GenericParticleType.GenericFactory {
    public Factory(SpriteSet sprite) {
      super(sprite);
    }

    @Override
    public Particle createParticle(GenericParticleData data, ClientLevel world, double x, double y, double z, double mx, double my, double mz) {
      FireParticle ret = new FireParticle(world, data, x, y, z, mx, my, mz);
      ret.pickSprite(sprite);
      return ret;
    }
  }
}
