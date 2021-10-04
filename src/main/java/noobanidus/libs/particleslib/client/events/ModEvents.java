package noobanidus.libs.particleslib.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import noobanidus.libs.particleslib.ParticlesLib;
import noobanidus.libs.particleslib.client.particle.LineGlowParticle;
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
    manager.register(ModParticles.LEAF_ARC_PARTICLE.get(), LeafArcParticleType.Factory::new);
    manager.register(ModParticles.LEAF_DIRECTED_PARTICLE.get(), LeafDirectedParticleType.Factory::new);
    manager.register(ModParticles.LEAF_PARTICLE.get(), LeafParticleType.Factory::new);
    manager.register(ModParticles.PETAL_PARTICLE.get(), PetalParticleType.Factory::new);
    manager.register(ModParticles.RING_PARTICLE.get(), RingParticleType.Factory::new);
    manager.register(ModParticles.RING_PARTICLE.get(), RingParticleType.Factory::new);
    manager.register(ModParticles.SMOKE_PARTICLE.get(), SmokeParticleType.Factory::new);
    manager.register(ModParticles.SPARKLE_PARTICLE.get(), SparkleParticleType.Factory::new);
    manager.register(ModParticles.STAR_PARTICLE.get(), StarParticleType.Factory::new);
    manager.register(ModParticles.THORN_PARTICLE.get(), ThornParticleType.Factory::new);
    manager.register(ModParticles.LINE_GLOW_PARTICLE.get(), LineGlowParticleType.Factory::new);
    manager.register(ModParticles.LEAF_WHIRLWIND_PARTICLE.get(), LeafWhirlwindParticleType.Factory::new);
    manager.register(ModParticles.DENSE_SMOKE_PARTICLE.get(), DenseSmokeParticleType.Factory::new);
    manager.register(ModParticles.FIERY_PARTICLE.get(), FieryParticleType.Factory::new);
    manager.register(ModParticles.MOTE_PARTICLE.get(), MoteParticleType.Factory::new);
    manager.register(ModParticles.RADIAL_PARTICLE.get(), RadialParticleType.Factory::new);
    manager.register(ModParticles.SOFT_RADIAL_PARTICLE.get(), SoftRadialParticleType.Factory::new);
  }
}

