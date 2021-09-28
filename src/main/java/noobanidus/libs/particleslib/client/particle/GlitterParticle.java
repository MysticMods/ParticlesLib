package noobanidus.libs.particleslib.client.particle;

import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.render.GlowParticleRenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

public class GlitterParticle extends GenericParticle {
  public GlitterParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    super(world, data, x, y, z, vx, vy, vz, GlowParticleRenderType.INSTANCE, RenderUtil.GLOWING_PARTICLE);
  }

  @Override
  public void tick() {
    this.oRoll = this.roll;
    this.roll += data.spin;
    super.tick();
  }
}
