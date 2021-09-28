package noobanidus.libs.particleslib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.particleslib.client.particle.Particles;
import noobanidus.libs.particleslib.init.ModParticles;

import java.util.Random;

public class ParticleBlock extends Block {
  public ParticleBlock(Properties p_i48440_1_) {
    super(p_i48440_1_);
  }

  @OnlyIn(Dist.CLIENT)
  @Override
  public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
    double x = p_180655_3_.getX() + 0.5;
    double y = p_180655_3_.getY() + + 3;
    double z = p_180655_3_.getZ() + 0.5;

    Particles.create(ModParticles.FIRE_PARTICLE)
        .addVelocity(0, 0, 0)
        .setAlpha(0.8f, 0.3f)
        .setScale(0.8f, 0.5f)
        .setColor(0.875f, 0.3f, 0.56f, 0.375f, 0.5f, 0.95f)
        .setLifetime(10)
        .spawn(p_180655_2_, x, y, z);
  }
}
