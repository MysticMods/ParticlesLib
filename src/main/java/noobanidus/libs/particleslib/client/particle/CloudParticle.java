package noobanidus.libs.particleslib.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import noobanidus.libs.particleslib.client.particle.base.GenericParticle;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

public class CloudParticle extends GenericParticle {
  public CloudParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    super(world, data, x, y, z, vx, vy, vz, SpriteParticleRenderType.INSTANCE, RenderUtil.DELAYED_PARTICLE);
  }

  @Override
  public void tick() {
    super.tick();
    this.yd = 0;
  }
}
