package noobanidus.libs.particleslib.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.LeafDirectedParticle;
import noobanidus.libs.particleslib.client.particle.type.*;

import static noobanidus.libs.particleslib.ParticlesLib.REGISTRATE;

public class ModParticles {
  // Insert particles here
  public static RegistryEntry<CloudParticleType> CLOUD_PARTICLE = REGISTRATE.simple("cloud_particle", ParticleType.class, CloudParticleType::new);
  public static RegistryEntry<FireParticleType> FIRE_PARTICLE = REGISTRATE.simple("fire_particle", ParticleType.class, FireParticleType::new);
  public static RegistryEntry<GlowParticleType> GLOW_PARTICLE = REGISTRATE.simple("glow_particle", ParticleType.class, GlowParticleType::new);
  public static RegistryEntry<LeafParticleType> LEAF_PARTICLE = REGISTRATE.simple("leaf_particle", ParticleType.class, LeafParticleType::new);
  public static RegistryEntry<LeafArcParticleType> LEAF_ARC_PARTICLE = REGISTRATE.simple("leaf_arc_particle", ParticleType.class, LeafArcParticleType::new);
  public static RegistryEntry<SmokeParticleType> SMOKE_PARTICLE = REGISTRATE.simple("smoke_particle", ParticleType.class, SmokeParticleType::new);
  public static RegistryEntry<SparkleParticleType> SPARKLE_PARTICLE = REGISTRATE.simple("sparkle_particle", ParticleType.class, SparkleParticleType::new);
  public static RegistryEntry<LeafDirectedParticleType> LEAF_DIRECTED_PARTICLE = REGISTRATE.simple("leaf_directed_particle", ParticleType.class, LeafDirectedParticleType::new);

  public static void load () {

  }
}
