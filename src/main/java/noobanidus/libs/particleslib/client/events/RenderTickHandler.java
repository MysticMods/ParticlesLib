package noobanidus.libs.particleslib.client.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.particleslib.ParticlesLib;
import noobanidus.libs.particleslib.client.effects.BoltEffect;
import noobanidus.libs.particleslib.client.render.BoltRenderer;
import noobanidus.libs.particleslib.client.render.PLibRenderTypes;
import noobanidus.libs.particleslib.client.util.RenderUtil;
import noobanidus.libs.particleslib.config.ConfigManager;

import static noobanidus.libs.particleslib.client.render.DelayedRender.getDelayedRender;

/*
This class taken from and adapted from RenderTickHandler from the Mekanism project.
As the license of this project is also MIT, this is a license-compatible usage.
Original source: https://github.com/mekanism/Mekanism/blob/1.16.x/src/main/java/mekanism/client/render/RenderTickHandler.java

Additional elements adapted from Ars Nouveau & Eidolon by BaileyH and Elucent, originally derived from Roots 2 by Elucent.
 */

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ParticlesLib.MODID)
public class RenderTickHandler {
  private static float clientTicks = 0;
  private static final BoltRenderer boltRenderer = new BoltRenderer();

  public static void renderBolt(Object renderer, BoltEffect bolt) {
    boltRenderer.update(renderer, bolt, Minecraft.getInstance().getFrameTime());
  }

  public static Matrix4f particleMVMatrix = null;

  @SuppressWarnings("deprecation")
  @SubscribeEvent
  public static void renderLevel(RenderLevelLastEvent event) {
    if (boltRenderer.hasBoltsToRender()) {
      //Only do matrix transforms and mess with buffers if we actually have any bolts to render
      PoseStack matrix = event.getPoseStack();
      matrix.pushPose();
      // here we translate based on the inverse position of the client viewing camera to get back to 0, 0, 0
      Vec3 camVec = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
      matrix.translate(-camVec.x, -camVec.y, -camVec.z);
      //TODO: FIXME, this doesn't work on fabulous, I think it needs something like
      // https://github.com/MinecraftForge/MinecraftForge/pull/7225
      MultiBufferSource.BufferSource renderer = Minecraft.getInstance().renderBuffers().bufferSource();
      boltRenderer.render(event.getPartialTick(), matrix, renderer);
      renderer.endBatch(PLibRenderTypes.MEK_LIGHTNING);
      matrix.popPose();
    }
    if (ConfigManager.BETTER_LAYERING.get()) {
      RenderSystem.getModelViewStack().pushPose(); // this feels...cheaty
      RenderSystem.getModelViewStack().setIdentity();
      if (particleMVMatrix != null) RenderSystem.getModelViewStack().mulPoseMatrix(particleMVMatrix);
      RenderSystem.applyModelViewMatrix();
      getDelayedRender().endBatch(RenderUtil.DELAYED_PARTICLE);
      getDelayedRender().endBatch(RenderUtil.GLOWING_PARTICLE);
      RenderSystem.getModelViewStack().popPose();
      RenderSystem.applyModelViewMatrix();

      getDelayedRender().endBatch(RenderUtil.GLOWING_SPRITE);
      //getDelayedRender().endBatch(RenderUtil.GLOWING);
    }
    clientTicks += event.getPartialTick();
  }

  public static float getClientTicks() {
    return clientTicks;
  }
}
