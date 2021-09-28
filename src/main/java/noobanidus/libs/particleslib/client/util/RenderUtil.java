package noobanidus.libs.particleslib.client.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import noobanidus.libs.particleslib.ParticlesLib;
import org.lwjgl.opengl.GL11;

@SuppressWarnings("deprecation")
public class RenderUtil {
  public static final RenderState.TransparencyState ADDITIVE_TRANSPARENCY = new RenderState.TransparencyState("lightning_transparency", () -> {
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
  }, () -> {
    RenderSystem.disableBlend();
    RenderSystem.defaultBlendFunc();
  });

  public static final RenderState.TransparencyState NORMAL_TRANSPARENCY = new RenderState.TransparencyState("lightning_transparency", () -> {
    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
  }, () -> {
    RenderSystem.disableBlend();
    RenderSystem.defaultBlendFunc();
  });

  public static RenderType DELAYED_PARTICLE = RenderType.create(
      ParticlesLib.MODID + ":delayed_particle",
      DefaultVertexFormats.PARTICLE,
      GL11.GL_QUADS, 256,
      RenderType.State.builder()
          .setShadeModelState(new RenderState.ShadeModelState(true))
          .setWriteMaskState(new RenderState.WriteMaskState(true, false))
          .setLightmapState(new RenderState.LightmapState(false))
          .setDiffuseLightingState(new RenderState.DiffuseLightingState(false))
          .setTransparencyState(NORMAL_TRANSPARENCY)
          .setTextureState(new RenderState.TextureState(AtlasTexture.LOCATION_PARTICLES, false, false))
          .createCompositeState(false)
  );
  public static RenderType GLOWING_PARTICLE = RenderType.create(
      ParticlesLib.MODID + ":glowing_particle",
      DefaultVertexFormats.PARTICLE,
      GL11.GL_QUADS, 256,
      RenderType.State.builder()
          .setShadeModelState(new RenderState.ShadeModelState(true))
          .setWriteMaskState(new RenderState.WriteMaskState(true, false))
          .setLightmapState(new RenderState.LightmapState(false))
          .setDiffuseLightingState(new RenderState.DiffuseLightingState(false))
          .setTransparencyState(ADDITIVE_TRANSPARENCY)
          .setTextureState(new RenderState.TextureState(AtlasTexture.LOCATION_PARTICLES, false, false))
          .createCompositeState(false)
  );
}
