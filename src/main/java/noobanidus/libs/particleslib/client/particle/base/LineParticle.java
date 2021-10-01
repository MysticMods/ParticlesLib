package noobanidus.libs.particleslib.client.particle.base;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.math.MathHelper;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.render.DelayedRender;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import noobanidus.libs.particleslib.config.ConfigManager;

import java.awt.*;

public class LineParticle extends SpriteTexturedParticle {
  public DirectedParticleData data;
  public float[] hsv1 = new float[3];
  public float[] hsv2 = new float[3];
  private final IParticleRenderType renderer;
  private final RenderType type;

  public LineParticle(ClientWorld world, DirectedParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    this(world, data, x, y, z, vx, vy, vz, SpriteParticleRenderType.INSTANCE, RenderUtil.DELAYED_PARTICLE);
  }

  protected LineParticle(ClientWorld world, DirectedParticleData data, double x, double y, double z, double vx, double vy, double vz, IParticleRenderType renderer, RenderType type) {
    super(world, x, y, z, vx, vy, vz);
    this.type = type;
    this.renderer = renderer;
    this.setPos(x, y, z);
    this.data = data;
    this.xd = vx;
    this.yd = vy;
    this.zd = vz;
    this.setLifetime(data.lifetime);
    this.gravity = data.gravity ? 1 : 0;
    Color.RGBtoHSB((int) (255 * Math.min(1.0f, data.r1)), (int) (255 * Math.min(1.0f, data.g1)), (int) (255 * Math.min(1.0f, data.b1)), hsv1);
    Color.RGBtoHSB((int) (255 * Math.min(1.0f, data.r2)), (int) (255 * Math.min(1.0f, data.g2)), (int) (255 * Math.min(1.0f, data.b2)), hsv2);
    updateTraits();
  }

  protected float getCoeff() {
    return (float) this.age / this.lifetime;
  }

  protected void updateTraits() {
    float coeff = getCoeff();
    quadSize = MathHelper.lerp(coeff, data.scale1, data.scale2);
    float h = MathHelper.rotLerp(coeff, 360 * hsv1[0], 360 * hsv2[0]) / 360;
    float s = MathHelper.lerp(coeff, hsv1[1], hsv2[1]);
    float v = MathHelper.lerp(coeff, hsv1[2], hsv2[2]);
    int packed = Color.HSBtoRGB(h, s, v);
    float r = ColorHelper.PackedColor.red(packed) / 255.0f;
    float g = ColorHelper.PackedColor.green(packed) / 255.0f;
    float b = ColorHelper.PackedColor.blue(packed) / 255.0f;
    setColor(r, g, b);
    setAlpha(MathHelper.lerp(coeff, data.a1, data.a2));
    oRoll = roll;
    roll += data.spin;
  }

  public double distance() {
    double a = this.x - data.destination.x;
    double b = this.y - data.destination.y;
    double c = this.z - data.destination.z;
    return (a * a + b * b + c * c);
  }

  @Override
  public void tick() {
    updateTraits();
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;
    if (this.age++ >= this.lifetime || distance() <= data.distance) {
      this.remove();
    } else {
      if (this.data.gravity) {
        this.yd -= 0.04D * (double) this.gravity;
      }
      this.move(this.xd, this.yd, this.zd);
      if (this.onGround) {
        this.xd *= 0.7F;
        this.zd *= 0.7F;
      }
    }
  }

  @Override
  public IParticleRenderType getRenderType() {
    return renderer;
  }

  @Override
  public void render(IVertexBuilder b, ActiveRenderInfo info, float pticks) {
    super.render(ConfigManager.BETTER_LAYERING.get() ? DelayedRender.getDelayedRender().getBuffer(this.type) : b, info, pticks);
  }

  @Override
  protected int getLightColor(float partialTicks) {
    return 0xF000F0;
  }
}
