package noobanidus.libs.particleslib.client.particle;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import noobanidus.libs.particleslib.client.particle.data.DirectedParticleData;
import noobanidus.libs.particleslib.client.particle.data.GenericParticleData;
import noobanidus.libs.particleslib.client.particle.data.WhirlwindParticleData;

import java.util.Random;

public class Particles {
  public static class ParticleBuilder {
    private static final Random random = new Random();

    private boolean directed = false;
    private boolean leaf = false;
    private boolean whirlwind = false;

    public ParticleType<?> type;
    public GenericParticleData data;
    public Vector3d destination = Vector3d.ZERO;
    public double distance = 0;
    public double vx = 0, vy = 0, vz = 0;
    public double dx = 0, dy = 0, dz = 0;
    public double maxXSpeed = 0, maxYSpeed = 0, maxZSpeed = 0;
    public double maxXDist = 0, maxYDist = 0, maxZDist = 0;
    public boolean inverse = false;

    protected ParticleBuilder(ParticleType<?> type) {
      this.type = type;
      this.data = new GenericParticleData(type);
    }

    public ParticleBuilder setColor(float r, float g, float b) {
      setColor(r, g, b, data.a1, r, g, b, data.a2);
      return this;
    }

    public ParticleBuilder setColor(float r, float g, float b, float a) {
      setColor(r, g, b, a, r, g, b, a);
      return this;
    }

    public ParticleBuilder setColor(float r, float g, float b, float a1, float a2) {
      setColor(r, g, b, a1, r, g, b, a2);
      return this;
    }

    public ParticleBuilder setColor(float r1, float g1, float b1, float r2, float g2, float b2) {
      setColor(r1, g1, b1, data.a1, r2, g2, b2, data.a2);
      return this;
    }

    public ParticleBuilder setColor(float r1, float g1, float b1, float r2, float g2, float b2, float a) {
      setColor(r1, g1, b1, a, r2, g2, b2, a);
      return this;
    }

    public ParticleBuilder setColor(float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2) {
      data.r1 = r1;
      data.g1 = g1;
      data.b1 = b1;
      data.a1 = a1;
      data.r2 = r2;
      data.g2 = g2;
      data.b2 = b2;
      data.a2 = a2;
      return this;
    }

    public ParticleBuilder setAlpha(float a) {
      setAlpha(a, a);
      return this;
    }

    public ParticleBuilder setAlpha(float a1, float a2) {
      data.a1 = a1;
      data.a2 = a2;
      return this;
    }

    public ParticleBuilder setScale(float scale) {
      setScale(scale, scale);
      return this;
    }

    public ParticleBuilder setScale(float scale1, float scale2) {
      data.scale1 = scale1;
      data.scale2 = scale2;
      return this;
    }

    public ParticleBuilder enableGravity() {
      data.gravity = true;
      return this;
    }

    public ParticleBuilder disableGravity() {
      data.gravity = false;
      return this;
    }

    public ParticleBuilder enableAdditive () {
      data.additive = true;
      return this;
    }

    public ParticleBuilder disableAdditive () {
      data.additive = false;
      return this;
    }

    public ParticleBuilder setSpin(float angularVelocity) {
      data.spin = angularVelocity;
      return this;
    }

    public ParticleBuilder setLifetime(int lifetime) {
      data.lifetime = lifetime;
      return this;
    }

    public ParticleBuilder setDestination(Vector3d destination) {
      directed = true;
      cache = null;
      this.destination = destination;
      return this;
    }

    public ParticleBuilder setCenter (Vector3d center) {
      whirlwind = true;
      cache2 = null;
      this.destination = center;
      return this;
    }

    public ParticleBuilder setDistance(double distance) {
      directed = true;
      this.distance = distance;
      return this;
    }

    public ParticleBuilder setRadius (double radius) {
      whirlwind = true;
      this.distance = radius;
      return this;
    }

    public ParticleBuilder invert () {
      whirlwind = true;
      inverse = true;
      return this;
    }

    public ParticleBuilder uninvert () {
      whirlwind = true;
      inverse = false;
      return this;
    }

    public ParticleBuilder randomVelocity(double maxSpeed) {
      randomVelocity(maxSpeed, maxSpeed, maxSpeed);
      return this;
    }

    public ParticleBuilder randomVelocity(double maxHSpeed, double maxVSpeed) {
      randomVelocity(maxHSpeed, maxVSpeed, maxHSpeed);
      return this;
    }

    public ParticleBuilder randomVelocity(double maxXSpeed, double maxYSpeed, double maxZSpeed) {
      this.maxXSpeed = maxXSpeed;
      this.maxYSpeed = maxYSpeed;
      this.maxZSpeed = maxZSpeed;
      return this;
    }

    public ParticleBuilder addVelocity(double vx, double vy, double vz) {
      this.vx += vx;
      this.vy += vy;
      this.vz += vz;
      return this;
    }

    public ParticleBuilder setVelocity(double vx, double vy, double vz) {
      this.vx = vx;
      this.vy = vy;
      this.vz = vz;
      return this;
    }

    public ParticleBuilder randomOffset(double maxDistance) {
      randomOffset(maxDistance, maxDistance, maxDistance);
      return this;
    }

    public ParticleBuilder randomOffset(double maxHDist, double maxVDist) {
      randomOffset(maxHDist, maxVDist, maxHDist);
      return this;
    }

    public ParticleBuilder randomOffset(double maxXDist, double maxYDist, double maxZDist) {
      this.maxXDist = maxXDist;
      this.maxYDist = maxYDist;
      this.maxZDist = maxZDist;
      return this;
    }

    public ParticleBuilder enableDestinationVelocity () {
      this.leaf = true;
      return this;
    }

    public ParticleBuilder disableDestinationVelocity () {
      this.leaf = false;
      return this;
    }

    private DirectedParticleData cache = null;
    private WhirlwindParticleData cache2 = null;


    public ParticleBuilder spawn(World world, double x, double y, double z) {
      // TODO: Calculate motion, etc, base off of potential destination
      return spawn(world, new Vector3d(x, y, z));
    }

    public ParticleBuilder spawn(World world, Vector3d pos) {
      double yaw = random.nextFloat() * Math.PI * 2, pitch = random.nextFloat() * Math.PI - Math.PI / 2;
      double xSpeed = random.nextFloat() * maxXSpeed, ySpeed = random.nextFloat() * maxYSpeed, zSpeed = random.nextFloat() * maxZSpeed;
      this.vx += Math.sin(yaw) * Math.cos(pitch) * xSpeed;
      this.vy += Math.sin(pitch) * ySpeed;
      this.vz += Math.cos(yaw) * Math.cos(pitch) * zSpeed;
      double yaw2 = random.nextFloat() * Math.PI * 2, pitch2 = random.nextFloat() * Math.PI - Math.PI / 2;
      double xDist = random.nextFloat() * maxXDist, yDist = random.nextFloat() * maxYDist, zDist = random.nextFloat() * maxZDist;
      this.dx = Math.sin(yaw2) * Math.cos(pitch2) * xDist;
      this.dy = Math.sin(pitch2) * yDist;
      this.dz = Math.cos(yaw2) * Math.cos(pitch2) * zDist;

      if (this.directed && destination != Vector3d.ZERO) {
        if (cache == null) {
          cache = new DirectedParticleData(data.getType(), data, pos, this.destination, this.distance);
        }

        if (leaf) {
          // TODO: Scale based on distance/lifetime with speed instead of forced speed?
          Vector3d origAngle = pos.subtract(destination);
          cache.lifetime = (int) ((origAngle.lengthSqr() * 10) - 5);

          Vector3d angle = destination.subtract(pos).normalize().scale(0.05);

          world.addParticle(cache, pos.x + dx, pos.y + dy, pos.z + dz, angle.x, angle.y, angle.z);
        } else {
          world.addParticle(cache, pos.x + dx, pos.y + dy, pos.z + dz, vx, vy, vz);
        }
      } else if (this.whirlwind && destination != Vector3d.ZERO) {
        if (cache2 == null) {
          cache2 = new WhirlwindParticleData(data.getType(), data, this.destination, this.inverse, this.distance);
        }

        world.addParticle(cache2, pos.x + dx, pos.y + dy, pos.z + dz, vx, vy, vz);
      } else {
        world.addParticle(data, pos.x + dx, pos.y + dy, pos.z + dz, vx, vy, vz);
      }
      return this;
    }

    public ParticleBuilder repeat(World world, double x, double y, double z, int n) {
      return repeat(world, new Vector3d(x, y, z), n);
    }

    public ParticleBuilder repeat (World world, Vector3d pos, int n) {
      for (int i = 0; i < n; i++) {
        spawn(world, pos);
      }
      return this;
    }
  }

  public static <T extends ParticleType<?>> ParticleBuilder create(T type) {
    return new ParticleBuilder(type);
  }

  public static <T extends ParticleType<?>> ParticleBuilder create(RegistryEntry<T> type) {
    return new ParticleBuilder(type.get());
  }
}
