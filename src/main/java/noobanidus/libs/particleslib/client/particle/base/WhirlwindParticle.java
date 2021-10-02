package noobanidus.libs.particleslib.client.particle.base;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.math.MathHelper;
import noobanidus.libs.particleslib.client.particle.data.WhirlwindParticleData;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.render.DelayedRender;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import noobanidus.libs.particleslib.config.ConfigManager;

import java.awt.*;

public class WhirlwindParticle extends SpriteTexturedParticle {
  public WhirlwindParticleData data;
  public float[] hsv1 = new float[3];
  public float[] hsv2 = new float[3];
  private final IParticleRenderType renderer;
  private final RenderType type;
  private float initRoll = -1;

  public WhirlwindParticle(ClientWorld world, WhirlwindParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    this(world, data, x, y, z, vx, vy, vz, SpriteParticleRenderType.INSTANCE, RenderUtil.DELAYED_PARTICLE);
  }

  protected WhirlwindParticle(ClientWorld world, WhirlwindParticleData data, double x, double y, double z, double vx, double vy, double vz, IParticleRenderType renderer, RenderType type) {
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
    // TODO: Init roll
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
    if (initRoll == -1) {
      initRoll = random.nextFloat() * (2.0f * (float) Math.PI * 2);
      roll = initRoll;
    }
    double nx, ny, nz;
    float thisAngle = (float) age / lifetime * (2 * (float) Math.PI * 2) + initRoll;
    ny = data.center.y + MathHelper.sin(0.007312312f * thisAngle);
    double s1 = data.radius * MathHelper.sin(thisAngle);
    double c1 = data.radius * MathHelper.cos(thisAngle);
    if (!data.inverse) {
      nx = data.center.x + s1;
      nz = data.center.z + c1;
    } else {
      nx = data.center.x + c1;
      nz = data.center.z + s1;
    }
    this.setPos(nx, ny, nz);
    oRoll = roll;
    roll += data.spin;
  }

  @Override
  public void tick() {
    updateTraits();
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;
    if (this.age++ >= this.lifetime) {
      this.remove();
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
