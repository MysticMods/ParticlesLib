package noobanidus.libs.particleslib.client.render;

import com.mojang.blaze3d.vertex.BufferBuilder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

import java.util.HashMap;
import java.util.Map;

public class DelayedRender {
  public static MultiBufferSource.BufferSource DELAYED_RENDER = null;

  public static MultiBufferSource.BufferSource getDelayedRender() {
    if (DELAYED_RENDER == null) {
      Map<RenderType, BufferBuilder> buffers = new HashMap<>();
      for (RenderType type : new RenderType[]{
          RenderUtil.DELAYED_PARTICLE,
          RenderUtil.GLOWING_PARTICLE}) {
        buffers.put(type, new BufferBuilder(type.bufferSize()));
      }
      DELAYED_RENDER = MultiBufferSource.immediateWithBuffers(buffers, new BufferBuilder(256));
    }
    return DELAYED_RENDER;
  }
}
