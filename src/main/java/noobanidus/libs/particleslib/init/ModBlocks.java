package noobanidus.libs.particleslib.init;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.block.Blocks;
import noobanidus.libs.particleslib.blocks.ParticleBlock;

import static noobanidus.libs.particleslib.ParticlesLib.REGISTRATE;

public class ModBlocks {
  public static BlockEntry<ParticleBlock> PARTICLE_BLOCK = REGISTRATE.block("particle_block", ParticleBlock::new)
      .defaultBlockstate()
      .defaultLang()
      .defaultLoot()
      .item()
      .model((ctx, p) -> p.blockItem(ctx::getEntry))
      .build()
      .register();

  public static void load () {

  }
}
