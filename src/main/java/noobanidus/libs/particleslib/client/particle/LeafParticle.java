package noobanidus.libs.particleslib.client.particle;

import net.minecraft.client.world.ClientWorld;
import noobanidus.libs.particleslib.client.particle.render.GlowParticleRenderType;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

public class LeafParticle extends GenericParticle {
  public LeafParticle(ClientWorld world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    super(world, data, x, y, z, vx, vy, vz, data.additive ? GlowParticleRenderType.INSTANCE : SpriteParticleRenderType.INSTANCE, data.additive ? RenderUtil.GLOWING_PARTICLE : RenderUtil.DELAYED_PARTICLE);
  }

  @Override
  public void tick() {
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;
    if (this.age++ >= this.lifetime) {
      this.remove();
    } else {
      if (!this.data.gravity) {
        this.yd -= 0.04D * (double) this.gravity;
        this.move(this.xd, this.yd, this.zd);
        this.xd *= 0.98F;
        this.yd *= 0.98F;
        this.zd *= 0.98F;
        if (this.onGround) {
          this.xd *= 0.7F;
          this.zd *= 0.7F;
        }
      }
    }
  }
}
