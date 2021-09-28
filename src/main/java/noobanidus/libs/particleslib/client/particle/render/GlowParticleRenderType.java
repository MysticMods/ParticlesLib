package noobanidus.libs.particleslib.client.particle.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import noobanidus.libs.particleslib.client.render.DelayedRender;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import org.lwjgl.opengl.GL11;

public class GlowParticleRenderType implements IParticleRenderType {
    public static final GlowParticleRenderType INSTANCE = new GlowParticleRenderType();

    private static void beginRenderCommon(BufferBuilder bufferBuilder, TextureManager textureManager) {
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        RenderSystem.disableAlphaTest();
        RenderSystem.disableLighting();

        textureManager.bind(AtlasTexture.LOCATION_PARTICLES);
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.PARTICLE);
    }

    private static void endRenderCommon() {
        Minecraft.getInstance().textureManager.getTexture(AtlasTexture.LOCATION_PARTICLES).restoreLastBlurMipmap();
        RenderSystem.enableAlphaTest();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
    }

    @Override
    public void begin(BufferBuilder b, TextureManager tex) {
        beginRenderCommon(b, tex);
    }

    @Override
    public void end(Tessellator t) {
        t.end();
        DelayedRender.getDelayedRender().getBuffer(RenderUtil.GLOWING_PARTICLE);
        RenderSystem.enableDepthTest();
        endRenderCommon();
    }
}
