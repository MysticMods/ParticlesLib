package noobanidus.libs.particleslib.client.particle.base;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;
import noobanidus.libs.particleslib.client.particle.render.SpriteParticleRenderType;
import noobanidus.libs.particleslib.client.render.DelayedRender;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import noobanidus.libs.particleslib.config.ConfigManager;

import java.awt.*;

public class GenericParticle extends TextureSheetParticle {
  public GenericParticleData data;
  public float[] hsv1 = new float[3];
  public float[] hsv2 = new float[3];
  private final ParticleRenderType renderer;
  private final RenderType type;

  public GenericParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
    this(world, data, x, y, z, vx, vy, vz, SpriteParticleRenderType.INSTANCE, RenderUtil.DELAYED_PARTICLE);
  }

  protected GenericParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz, ParticleRenderType renderer, RenderType type) {
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
    this.hasPhysics = data.physics;
    Color.RGBtoHSB((int) (255 * Math.min(1.0f, data.r1)), (int) (255 * Math.min(1.0f, data.g1)), (int) (255 * Math.min(1.0f, data.b1)), hsv1);
    Color.RGBtoHSB((int) (255 * Math.min(1.0f, data.r2)), (int) (255 * Math.min(1.0f, data.g2)), (int) (255 * Math.min(1.0f, data.b2)), hsv2);
    if (data.spin > 0f) {
      this.roll = (float) Math.random() * ((float) Math.PI * 2F);
    } else {
      this.roll = 0f;
    }

    updateTraits();
  }

  protected float getCoeff() {
    return (float) this.age / this.lifetime;
  }

  protected void updateTraits() {
    float coeff = getCoeff();
    float pScale = Mth.lerp(coeff, data.scale1, data.scale2);
    quadSize = pScale;
    this.setSize(0.2F * pScale, 0.2F * pScale);
    float h = Mth.rotLerp(coeff, 360 * hsv1[0], 360 * hsv2[0]) / 360;
    float s = Mth.lerp(coeff, hsv1[1], hsv2[1]);
    float v = Mth.lerp(coeff, hsv1[2], hsv2[2]);
    int packed = Color.HSBtoRGB(h, s, v);
    float r = FastColor.ARGB32.red(packed) / 255.0f;
    float g = FastColor.ARGB32.green(packed) / 255.0f;
    float b = FastColor.ARGB32.blue(packed) / 255.0f;
    setColor(r, g, b);
    setAlpha(Mth.lerp(coeff, data.a1, data.a2));
    // TODO: Init roll
    if (data.spin != 0f) {
      this.oRoll = this.roll;
      this.roll += (float) Math.PI * data.spin * 2.0F;
    } else {
      this.roll = 0f;
    }
  }

  @Override
  public void tick() {
    updateTraits();
    this.xo = this.x;
    this.yo = this.y;
    this.zo = this.z;
    if (this.age++ >= this.lifetime) {
      this.remove();
    } else {
      if (this.data.gravity) {
        this.yd -= 0.04D * (double) this.gravity;
      }
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

  @Override
  public ParticleRenderType getRenderType() {
    return renderer;
  }

  @Override
  public void render(VertexConsumer b, Camera info, float pticks) {
    super.render(ConfigManager.BETTER_LAYERING.get() ? DelayedRender.getDelayedRender().getBuffer(this.type) : b, info, pticks);
  }

  @Override
  protected int getLightColor(float partialTicks) {
    return 0xF000F0;
  }
}
