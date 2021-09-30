package noobanidus.libs.particleslib.client.particle;

import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.render.GlowParticleRenderType;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

public class LeafArcParticle extends GenericParticle {
  public LeafArcParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    super(world, data, x, y, z, vx, vy, vz, data.additive ? GlowParticleRenderType.INSTANCE : SpriteParticleRenderType.INSTANCE, data.additive ? RenderUtil.GLOWING_PARTICLE : RenderUtil.DELAYED_PARTICLE);
  }

  @Override
  public void tick() {
    super.tick();
    this.gravity += 0.001f;
    if (gravity > 0.45f) {
      gravity = 0.45f;
    }
  }
}
