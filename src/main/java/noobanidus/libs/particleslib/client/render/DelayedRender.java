package noobanidus.libs.particleslib.client.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import noobanidus.libs.particleslib.client.util.RenderUtil;

import java.util.HashMap;
import java.util.Map;

public class DelayedRender {
  public static IRenderTypeBuffer.Impl DELAYED_RENDER = null;

  public static IRenderTypeBuffer.Impl getDelayedRender() {
    if (DELAYED_RENDER == null) {
      Map<RenderType, BufferBuilder> buffers = new HashMap<>();
      for (RenderType type : new RenderType[]{
          RenderUtil.DELAYED_PARTICLE,
          RenderUtil.GLOWING_PARTICLE}) {
        buffers.put(type, new BufferBuilder(type.bufferSize()));
      }
      DELAYED_RENDER = IRenderTypeBuffer.immediateWithBuffers(buffers, new BufferBuilder(256));
    }
    return DELAYED_RENDER;
  }
}
