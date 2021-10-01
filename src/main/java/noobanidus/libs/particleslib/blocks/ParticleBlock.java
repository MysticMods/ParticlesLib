package noobanidus.libs.particleslib.blocks;

import javafx.geometry.BoundingBox;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
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

  private static AxisAlignedBB RANGE = new AxisAlignedBB(-9, -9, -9, 10, 10, 10);

  @OnlyIn(Dist.CLIENT)
  @Override
  public void animateTick(BlockState state, World level, BlockPos pos, Random random) {
    double x = pos.getX() + 0.5;
    double y = pos.getY() + 0.5;
    double z = pos.getZ() + 0.5;
    Vector3d me = new Vector3d(x, y, z);

    BlockPos.betweenClosedStream(RANGE.move(pos)).filter(o -> level.getBlockState(o).is(Blocks.MELON)).forEach(o -> {
      if (random.nextInt(6) == 0) {
        Vector3d dest = new Vector3d(0.5 + o.getX() + (random.nextDouble() - 0.5), 0.5 + o.getY() + (random.nextDouble() - 0.5), 0.5 + o.getZ() + (random.nextDouble() - 0.5));
        Particles.create(ModParticles.LEAF_DIRECTED_PARTICLE)
            .setAlpha(0.8f)
            .setColor(60 / 255.0f + random.nextFloat() * 0.05f, 120 / 255.0f + random.nextFloat() * 0.05f, 60 / 255.0f + random.nextFloat() * 0.05f)
            .setScale(0.2f)
            .setDestination(me)
            .setDistance(0.6)
            .disableGravity()
            .spawn(level, dest);
      }
    });

/*    Particles.create(ModParticles.FIRE_PARTICLE)
        .addVelocity(0, 0, 0)
        .setAlpha(0.8f, 0.3f)
        .setScale(0.8f, 0.5f)
        .setColor(0.875f, 0.3f, 0.56f, 0.375f, 0.5f, 0.95f)
        .setLifetime(10)
        .spawn(p_180655_2_, x, y, z);*/

  }
}
