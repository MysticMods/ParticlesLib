package noobanidus.libs.particleslib.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import noobanidus.libs.particleslib.ParticlesLib;
import org.lwjgl.opengl.GL11;

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

  public static RenderType DELAYED_PARTICLE = RenderType.create(
      ParticlesLib.MODID + ":delayed_particle",
      DefaultVertexFormat.PARTICLE,
      GL11.GL_QUADS, 256,
      RenderType.CompositeState.builder()
          .setShadeModelState(new RenderStateShard.ShadeModelStateShard(true))
          .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
          .setLightmapState(new RenderStateShard.LightmapStateShard(false))
          .setDiffuseLightingState(new RenderStateShard.DiffuseLightingStateShard(false))
          .setTransparencyState(NORMAL_TRANSPARENCY)
          .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_PARTICLES, false, false))
          .createCompositeState(false)
  );
  public static RenderType GLOWING_PARTICLE = RenderType.create(
      ParticlesLib.MODID + ":glowing_particle",
      DefaultVertexFormat.PARTICLE,
      GL11.GL_QUADS, 256,
      RenderType.CompositeState.builder()
          .setShadeModelState(new RenderStateShard.ShadeModelStateShard(true))
          .setWriteMaskState(new RenderStateShard.WriteMaskStateShard(true, false))
          .setLightmapState(new RenderStateShard.LightmapStateShard(false))
          .setDiffuseLightingState(new RenderStateShard.DiffuseLightingStateShard(false))
          .setTransparencyState(ADDITIVE_TRANSPARENCY)
          .setTextureState(new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_PARTICLES, false, false))
          .createCompositeState(false)
  );
}
