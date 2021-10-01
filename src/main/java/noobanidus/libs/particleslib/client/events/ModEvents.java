package noobanidus.libs.particleslib.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.particleslib.ParticlesLib;
import noobanidus.libs.particleslib.client.particle.type.*;
import noobanidus.libs.particleslib.init.ModParticles;

@Mod.EventBusSubscriber(modid = ParticlesLib.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
  @SubscribeEvent
  public static void registerFactories(ParticleFactoryRegisterEvent evt) {
    ParticleManager manager = Minecraft.getInstance().particleEngine;
    manager.register(ModParticles.CLOUD_PARTICLE.get(), CloudParticleType.Factory::new);
    manager.register(ModParticles.FIRE_PARTICLE.get(), FireParticleType.Factory::new);
    manager.register(ModParticles.GLOW_PARTICLE.get(), GlowParticleType.Factory::new);
    manager.register(ModParticles.LEAF_PARTICLE.get(), LeafParticleType.Factory::new);
    manager.register(ModParticles.LEAF_ARC_PARTICLE.get(), LeafArcParticleType.Factory::new);
    manager.register(ModParticles.LEAF_DIRECTED_PARTICLE.get(), LeafDirectedParticleType.Factory::new);
    manager.register(ModParticles.SMOKE_PARTICLE.get(), SmokeParticleType.Factory::new);
    manager.register(ModParticles.SPARKLE_PARTICLE.get(), SparkleParticleType.Factory::new);
/*    Minecraft.getInstance().particleEngine.register(FLAME_PARTICLE.get(), FlameParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(SMOKE_PARTICLE.get(), SmokeParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(SPARKLE_PARTICLE.get(), SparkleParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(WISP_PARTICLE.get(), WispParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(BUBBLE_PARTICLE.get(), BubbleParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(STEAM_PARTICLE.get(), SteamParticleType.Factory::new);
    Minecraft.getInstance().particleEngine.register(LINE_WISP_PARTICLE.get(), LineWispParticleType.Factory::new);*/
  }
}

