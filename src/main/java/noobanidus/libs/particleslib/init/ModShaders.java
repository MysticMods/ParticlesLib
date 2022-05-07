package noobanidus.libs.particleslib.init;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.particleslib.ParticlesLib;

import java.io.IOException;

@Mod.EventBusSubscriber(value= Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD, modid= ParticlesLib.MODID)
public class ModShaders {
  private static ShaderInstance GLOWING_PARTICLE_SHADER, GLOWING_SPRITE_SHADER;

  public static ShaderInstance getGlowingParticleShader() {
    return GLOWING_PARTICLE_SHADER;
  }

  public static ShaderInstance getGlowingSpriteShader () {
    return GLOWING_SPRITE_SHADER;
  }

  @SubscribeEvent
  public static void onRegisterShaders (RegisterShadersEvent event) throws IOException {
    event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(ParticlesLib.MODID, "glowing_particle"), DefaultVertexFormat.PARTICLE), (shader) -> GLOWING_PARTICLE_SHADER = shader);
    event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(ParticlesLib.MODID, "glowing_sprite"), DefaultVertexFormat.POSITION_TEX_COLOR), (shader) -> GLOWING_SPRITE_SHADER = shader);
  }
}
