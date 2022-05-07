package noobanidus.libs.particleslib.client.render;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import org.lwjgl.opengl.GL11;

/*
This class taken from and adapted from MekanismRenderType from the Mekanism project.
As the license of this project is also MIT, this is a license-compatible usage.
Original source: https://github.com/mekanism/Mekanism/blob/1.16.x/src/main/java/mekanism/client/render/MekanismRenderType.java
 */

public class PLibRenderTypes extends RenderType {
  public static final RenderType MEK_LIGHTNING = create("mek_lightning", DefaultVertexFormat.POSITION_COLOR, VertexFormat.Mode.QUADS, 256,
      false, true, RenderType.CompositeState.builder()
          .setShaderState(RenderStateShard.POSITION_COLOR_SHADER)
          .setTransparencyState(LIGHTNING_TRANSPARENCY)
          .createCompositeState(false)
  );

  //Ignored
  private PLibRenderTypes(String name, VertexFormat format, VertexFormat.Mode drawMode, int bufferSize, boolean useDelegate, boolean needsSorting, Runnable runnablePre, Runnable runnablePost) {
    super(name, format, drawMode, bufferSize, useDelegate, needsSorting, runnablePre, runnablePost);
  }
}
