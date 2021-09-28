package noobanidus.libs.particleslib.init;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.particles.ParticleType;
import noobanidus.libs.particleslib.client.particle.type.CloudParticleType;
import noobanidus.libs.particleslib.client.particle.type.FireParticleType;

import static noobanidus.libs.particleslib.ParticlesLib.REGISTRATE;

public class ModParticles {
  // Insert particles here
  public static RegistryEntry<CloudParticleType> CLOUD_PARTICLE = REGISTRATE.simple("cloud_particle", ParticleType.class, CloudParticleType::new);
  public static RegistryEntry<FireParticleType> FIRE_PARTICLE = REGISTRATE.simple("fire_particle", ParticleType.class, FireParticleType::new);

  public static void load () {

  }
}
