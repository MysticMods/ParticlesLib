package noobanidus.libs.particleslib.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.LeafDirectedParticle;
import noobanidus.libs.particleslib.client.particle.LineGlowParticle;
import noobanidus.libs.particleslib.client.particle.PetalParticle;
import noobanidus.libs.particleslib.client.particle.type.*;

import static noobanidus.libs.particleslib.ParticlesLib.REGISTRATE;

public class ModParticles {
  // Insert particles here
  public static RegistryEntry<CloudParticleType> CLOUD_PARTICLE = REGISTRATE.simple("cloud_particle", ParticleType.class, CloudParticleType::new);
  public static RegistryEntry<FireParticleType> FIRE_PARTICLE = REGISTRATE.simple("fire_particle", ParticleType.class, FireParticleType::new);
  public static RegistryEntry<GlowParticleType> GLOW_PARTICLE = REGISTRATE.simple("glow_particle", ParticleType.class, GlowParticleType::new);
  public static RegistryEntry<LeafArcParticleType> LEAF_ARC_PARTICLE = REGISTRATE.simple("leaf_arc_particle", ParticleType.class, LeafArcParticleType::new);
  public static RegistryEntry<LeafDirectedParticleType> LEAF_DIRECTED_PARTICLE = REGISTRATE.simple("leaf_directed_particle", ParticleType.class, LeafDirectedParticleType::new);
  public static RegistryEntry<LeafParticleType> LEAF_PARTICLE = REGISTRATE.simple("leaf_particle", ParticleType.class, LeafParticleType::new);
  public static RegistryEntry<LeafWhirlwindParticleType> LEAF_WHIRLWIND_PARTICLE = REGISTRATE.simple("leaf_whirlwind_particle", ParticleType.class, LeafWhirlwindParticleType::new);
  public static RegistryEntry<LineGlowParticleType> LINE_GLOW_PARTICLE = REGISTRATE.simple("line_glow_particle", ParticleType.class, LineGlowParticleType::new);
  public static RegistryEntry<PetalParticleType> PETAL_PARTICLE = REGISTRATE.simple("petal_particle", ParticleType.class, PetalParticleType::new);
  public static RegistryEntry<RingParticleType> RING_PARTICLE = REGISTRATE.simple("ring_particle", ParticleType.class, RingParticleType::new);
  public static RegistryEntry<SmokeParticleType> SMOKE_PARTICLE = REGISTRATE.simple("smoke_particle", ParticleType.class, SmokeParticleType::new);
  public static RegistryEntry<SparkleParticleType> SPARKLE_PARTICLE = REGISTRATE.simple("sparkle_particle", ParticleType.class, SparkleParticleType::new);
  public static RegistryEntry<StarParticleType> STAR_PARTICLE = REGISTRATE.simple("star_particle", ParticleType.class, StarParticleType::new);
  public static RegistryEntry<ThornParticleType> THORN_PARTICLE = REGISTRATE.simple("thorn_particle", ParticleType.class, ThornParticleType::new);
  public static RegistryEntry<DenseSmokeParticleType> DENSE_SMOKE_PARTICLE = REGISTRATE.simple("dense_smoke_particle", ParticleType.class, DenseSmokeParticleType::new);
  public static RegistryEntry<FieryParticleType> FIERY_PARTICLE = REGISTRATE.simple("fiery_particle", ParticleType.class, FieryParticleType::new);
  public static RegistryEntry<MoteParticleType> MOTE_PARTICLE = REGISTRATE.simple("mote_particle", ParticleType.class, MoteParticleType::new);
  public static RegistryEntry<RadialParticleType> RADIAL_PARTICLE = REGISTRATE.simple("radial_particle", ParticleType.class, RadialParticleType::new);
  public static RegistryEntry<SoftRadialParticleType> SOFT_RADIAL_PARTICLE = REGISTRATE.simple("soft_radial_particle", ParticleType.class, SoftRadialParticleType::new);

  public static void load () {

  }
}
