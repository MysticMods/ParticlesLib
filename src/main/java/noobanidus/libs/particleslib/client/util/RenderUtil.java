package noobanidus.libs.particleslib.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import noobanidus.libs.particleslib.ParticlesLib;
import noobanidus.libs.particleslib.init.ModShaders;

@SuppressWarnings("deprecation")
public class RenderUtil {
  public static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("lightning_transparency", () -> {
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
  }, () -> {
    RenderSystem.disableBlend();
    RenderSystem.defaultBlendFunc();
  });

  public static final RenderStateShard.TransparencyStateShard NORMAL_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("lightning_transparency", () -> {
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
  }, () -> {
    RenderSystem.disableBlend();
    RenderSystem.defaultBlendFunc();
  });

  public static RenderType
      DELAYED_PARTICLE = RenderType.create(
      ParticlesLib.MODID + ":delayed_particle",
      DefaultVertexFormat.PARTICLE,
      VertexFormat.Mode.QUADS, 256, true, false,
      RenderType.CompositeState.builder()
          .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
          .setTransparencyState(NORMAL_TRANSPARENCY)
          .setLightmapState(new RenderStateShard.LightmapStateShard(true))
          .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_PARTICLES, false, false))
          .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getParticleShader))
          .createCompositeState(false)),
      GLOWING_PARTICLE = RenderType.create(
          ParticlesLib.MODID + ":glowing_particle",
          DefaultVertexFormat.PARTICLE,
          VertexFormat.Mode.QUADS, 256, true, false,
          RenderType.CompositeState.builder()
              .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
              .setLightmapState(new RenderStateShard.LightmapStateShard(false))
              .setTransparencyState(ADDITIVE_TRANSPARENCY)
              .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_PARTICLES, false, false))
              .setShaderState(new RenderStateShard.ShaderStateShard(ModShaders::getGlowingParticleShader))
              .createCompositeState(false)),
      GLOWING_SPRITE = RenderType.create(
          ParticlesLib.MODID + ":glowing_sprite",
          DefaultVertexFormat.POSITION_TEX_COLOR,
          VertexFormat.Mode.QUADS, 256, true, false,
          RenderType.CompositeState.builder()
              .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
              .setLightmapState(new RenderStateShard.LightmapStateShard(false))
              .setTransparencyState(ADDITIVE_TRANSPARENCY)
              .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_BLOCKS, false, false))
              .setShaderState(new RenderStateShard.ShaderStateShard(ModShaders::getGlowingSpriteShader))
              .createCompositeState(false));
}
