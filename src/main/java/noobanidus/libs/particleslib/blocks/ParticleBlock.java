package noobanidus.libs.particleslib.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import noobanidus.libs.particleslib.client.particle.Particles;
import noobanidus.libs.particleslib.init.ModParticles;

import java.util.Random;

public class ParticleBlock extends Block {
  public ParticleBlock(Properties p_i48440_1_) {
    super(p_i48440_1_);
  }

  private static AABB RANGE = new AABB(-9, -9, -9, 10, 10, 10);
  private static final double[] stages = new double[]{0.1, 0.15, 0.2, 0.25, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1};

  @OnlyIn(Dist.CLIENT)
  @Override
  public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
    Vec3 center = new Vec3(pos.getX() + 0.5, pos.getY() + 3.5, pos.getZ() + 0.5);
    float posX = pos.getX() + 0.5f;
    float posY = pos.getY();
    float posZ = pos.getZ() + 0.5f;

    float ty = posY;

    for (float i = 0; i < 360; i += random.nextFloat() * 90.0f) {
      float tx = posX + 1.5f * (float) Math.sin(Math.toRadians(i));
      float tz = posZ + 1.5f * (float) Math.cos(Math.toRadians(i));
      Particles.create(ModParticles.SMOKE_PARTICLE)
          .setAlpha(0.7f, 0.15f)
          .setColor(70 / 255.0f, 70 / 255.0f, 70 / 255.0f)
          .setScale(1.2f, 0.7f)
          .disableAdditive()
          .disableGravity()
          .setLifetime(60)
          .setSpin(0.01f * (random.nextFloat() - 0.5f))
          .spawn(level, new Vec3(tx, ty, tz));
    }
    for (float i = 0; i < 360; i += random.nextFloat() * 90.0f) {
      float tx = (float) posX + 2.25f * (float) Math.sin(Math.toRadians(i));
      float tz = (float) posZ + 2.25f * (float) Math.cos(Math.toRadians(i));
      Particles.create(ModParticles.SMOKE_PARTICLE)
          .setAlpha(0.7f, 0.15f)
          .setColor(70 / 255.0f, 70 / 255.0f, 70 / 255.0f)
          .setScale(0.9f, 0.6f)
          .disableAdditive()
          .disableGravity()
          .setLifetime(60)
          .setSpin(0.01f * (random.nextFloat() - 0.5f))
          .spawn(level, new Vec3(tx, ty, tz));
    }
    for (float i = 0; i < 360; i += random.nextFloat() * 90.0f) {
      double deg = Math.toRadians(i);
      float vx = 0.09f * (float) Math.sin(deg + random.nextFloat());
      float vz = 0.09f * (float) Math.cos(deg + random.nextFloat());
      float tx = (float) posX + 2f * (float) Math.sin(deg);
      float tz = (float) posZ + 2f * (float) Math.cos(deg);
      Particles.create(ModParticles.SMOKE_PARTICLE)
          .setAlpha(0.6f, 0.15f)
          .setVelocity(-vx, 0, -vz)
          .setColor(70 / 255.0f, 70 / 255.0f, 70 / 255.0f)
          .setScale(1.2f, 0.6f)
          .disableAdditive()
          .disableGravity()
          .setLifetime(60)
          .setSpin(0.01f * (random.nextFloat() - 0.5f))
          .spawn(level, new Vec3(tx, ty, tz));
    }

/*    for (int s = 0; s < 12; s++) {
      double r = stages[s];
      double y = 0.6 + (s * 0.13);
      for (int i = 0; i < (r * 1.4 * 8); i++) {
        Particles.create(ModParticles.LEAF_WHIRLWIND_PARTICLE)
            .setAlpha(0.9f)
            .setLifetime(200)
            .setColor(60 / 255.0f + random.nextFloat() * 0.05f, 120 / 255.0f + random.nextFloat() * 0.05f, 60 / 255.0f + random.nextFloat() * 0.05f)
            .setCenter(center.add(0, y, 0))
            .setScale(0.07f)
            .disableAdditive()
            .setSpin(random.nextFloat() * 0.5f)
            .setRadius(r)
            .spawn(level, center.add(0, y, 0));
        Particles.create(ModParticles.LEAF_WHIRLWIND_PARTICLE)
            .setAlpha(0.9f)
            .setLifetime(200)
            .setColor(30 / 255.0f + random.nextFloat() * 0.05f, 60 / 255.0f + random.nextFloat() * 0.05f, 30 / 255.0f + random.nextFloat() * 0.05f)
            .setCenter(center.add(0, y, 0))
            .setScale(0.07f)
            .disableAdditive()
            .setSpin(random.nextFloat() * 0.5f)
            .setRadius(r * 0.85f)
            .spawn(level, center.add(0, y, 0));
      }
    }
    Vec3 me = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

    BlockPos.betweenClosedStream(RANGE.move(pos)).filter(o -> level.getBlockState(o).is(Blocks.MELON)).forEach(o -> {
      if (random.nextInt(6) == 0) {
        Vec3 dest = new Vec3(0.5 + o.getX() + (random.nextDouble() - 0.5), 0.5 + o.getY() + (random.nextDouble() - 0.5), 0.5 + o.getZ() + (random.nextDouble() - 0.5));
        Particles.create(ModParticles.LEAF_DIRECTED_PARTICLE)
            .setAlpha(0.8f)
            .setColor(60 / 255.0f + random.nextFloat() * 0.05f, 120 / 255.0f + random.nextFloat() * 0.05f, 60 / 255.0f + random.nextFloat() * 0.05f)
            .setScale(0.2f)
            .setDestination(me)
            .setDistance(0.6)
            .disableGravity()
            .enableDestinationVelocity()
            .spawn(level, dest);
      }
    });*/

    Particles.create(ModParticles.FEY_LIGHT_PARTICLE)
        .addVelocity(0, 0, 0)
        .setAlpha(0.8f)
        .setScale(0.2f)
        .setColor(0.875f, 0.3f, 0.56f, 0.375f, 0.5f, 0.95f)
        .setLifetime(90)
        .disableAdditive()
        .disableGravity()
        .setSpin(0.01f * (random.nextFloat() - 0.5f))
        .spawn(level, pos.getX() + 0.5, pos.getY() + 3.5, pos.getZ() + 0.5);
  }
}
