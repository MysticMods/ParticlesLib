package noobanidus.libs.particleslib.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.Registry;
import noobanidus.libs.particleslib.client.particle.type.*;

import static noobanidus.libs.particleslib.ParticlesLib.REGISTRATE;

public class ModParticles {
  // Insert particles here
  public static RegistryEntry<CloudParticleType> CLOUD_PARTICLE = REGISTRATE.simple("cloud_particle", Registry.PARTICLE_TYPE_REGISTRY, CloudParticleType::new);
  public static RegistryEntry<FireParticleType> FIRE_PARTICLE = REGISTRATE.simple("fire_particle", Registry.PARTICLE_TYPE_REGISTRY, FireParticleType::new);
  public static RegistryEntry<GlowParticleType> GLOW_PARTICLE = REGISTRATE.simple("glow_particle", Registry.PARTICLE_TYPE_REGISTRY, GlowParticleType::new);
  public static RegistryEntry<LeafArcParticleType> LEAF_ARC_PARTICLE = REGISTRATE.simple("leaf_arc_particle", Registry.PARTICLE_TYPE_REGISTRY, LeafArcParticleType::new);
  public static RegistryEntry<LeafDirectedParticleType> LEAF_DIRECTED_PARTICLE = REGISTRATE.simple("leaf_directed_particle", Registry.PARTICLE_TYPE_REGISTRY, LeafDirectedParticleType::new);
  public static RegistryEntry<LeafParticleType> LEAF_PARTICLE = REGISTRATE.simple("leaf_particle", Registry.PARTICLE_TYPE_REGISTRY, LeafParticleType::new);
  public static RegistryEntry<LeafWhirlwindParticleType> LEAF_WHIRLWIND_PARTICLE = REGISTRATE.simple("leaf_whirlwind_particle", Registry.PARTICLE_TYPE_REGISTRY, LeafWhirlwindParticleType::new);
  public static RegistryEntry<LineGlowParticleType> LINE_GLOW_PARTICLE = REGISTRATE.simple("line_glow_particle", Registry.PARTICLE_TYPE_REGISTRY, LineGlowParticleType::new);
  public static RegistryEntry<PetalParticleType> PETAL_PARTICLE = REGISTRATE.simple("petal_particle", Registry.PARTICLE_TYPE_REGISTRY, PetalParticleType::new);
  public static RegistryEntry<RingParticleType> RING_PARTICLE = REGISTRATE.simple("ring_particle", Registry.PARTICLE_TYPE_REGISTRY, RingParticleType::new);
  public static RegistryEntry<SmokeParticleType> SMOKE_PARTICLE = REGISTRATE.simple("smoke_particle", Registry.PARTICLE_TYPE_REGISTRY, SmokeParticleType::new);
  public static RegistryEntry<SparkleParticleType> SPARKLE_PARTICLE = REGISTRATE.simple("sparkle_particle", Registry.PARTICLE_TYPE_REGISTRY, SparkleParticleType::new);
  public static RegistryEntry<StarParticleType> STAR_PARTICLE = REGISTRATE.simple("star_particle", Registry.PARTICLE_TYPE_REGISTRY, StarParticleType::new);
  public static RegistryEntry<ThornParticleType> THORN_PARTICLE = REGISTRATE.simple("thorn_particle", Registry.PARTICLE_TYPE_REGISTRY, ThornParticleType::new);
  public static RegistryEntry<DenseSmokeParticleType> DENSE_SMOKE_PARTICLE = REGISTRATE.simple("dense_smoke_particle", Registry.PARTICLE_TYPE_REGISTRY, DenseSmokeParticleType::new);
  public static RegistryEntry<FieryParticleType> FIERY_PARTICLE = REGISTRATE.simple("fiery_particle", Registry.PARTICLE_TYPE_REGISTRY, FieryParticleType::new);
  public static RegistryEntry<MoteParticleType> MOTE_PARTICLE = REGISTRATE.simple("mote_particle", Registry.PARTICLE_TYPE_REGISTRY, MoteParticleType::new);
  public static RegistryEntry<RadialParticleType> RADIAL_PARTICLE = REGISTRATE.simple("radial_particle", Registry.PARTICLE_TYPE_REGISTRY, RadialParticleType::new);
  public static RegistryEntry<SoftRadialParticleType> SOFT_RADIAL_PARTICLE = REGISTRATE.simple("soft_radial_particle", Registry.PARTICLE_TYPE_REGISTRY, SoftRadialParticleType::new);
  public static RegistryEntry<FeyLightParticleType> FEY_LIGHT_PARTICLE = REGISTRATE.simple("fey_light_particle", Registry.PARTICLE_TYPE_REGISTRY, FeyLightParticleType::new);

  public static void load () {

  }
}
